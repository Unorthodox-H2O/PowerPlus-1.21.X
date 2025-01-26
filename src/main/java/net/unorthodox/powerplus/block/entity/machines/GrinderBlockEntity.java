package net.unorthodox.powerplus.block.entity.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.unorthodox.powerplus.block.entity.ModBlockEntities;
import net.unorthodox.powerplus.block.machines.Grinder;
import net.unorthodox.powerplus.recipe.GrinderRecipe;
import net.unorthodox.powerplus.recipe.GrinderRecipeInput;
import net.unorthodox.powerplus.recipe.ModRecipes;
import net.unorthodox.powerplus.screen.machines.grinder.GrinderMenu;
import net.unorthodox.powerplus.util.FuelRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.isFuel;
import static net.neoforged.neoforge.common.Tags.Items.DUSTS;
import static net.neoforged.neoforge.common.Tags.Items.RAW_MATERIALS;

public class GrinderBlockEntity extends BlockEntity implements MenuProvider {
    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int ENERGY_ITEM_SLOT = 2;
    private static final int DEFAULT_MAX_PROGRESS = 200;
    private static final int DATA_SIZE = 2;

    public final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return slot != OUTPUT_SLOT && super.isItemValid(slot, stack);
        }
    };

    private final ContainerData data = new ContainerData() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> progress;
                case 1 -> maxProgress;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if (index == 0) {
                progress = value;
            } else if (index == 1) {
                maxProgress = value;
            }
        }

        @Override
        public int getCount() {
            return DATA_SIZE;
        }
    };

    private int progress = 0;
    private int maxProgress = DEFAULT_MAX_PROGRESS;
    private int burnTime = 0;

    public GrinderBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GRINDER_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.powerplus.grinder");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new GrinderMenu(containerId, playerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("grinder.progress", progress);
        tag.putInt("grinder.max_progress", maxProgress);
        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("grinder.progress");
        maxProgress = tag.getInt("grinder.max_progress");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    public void drops() {
        SimpleContainer container = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            container.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, container);
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        boolean validRecipe = hasRecipe() && isOutputSlotEmptyOrReceivable();
        insertInputFromHopper(level, pos);
        ejectOutputToHopper(level, pos);

        if (validRecipe) {
            handleCrafting(level, pos, state);
        } else {
            resetToDefaultState(level, pos, state);
        }
    }

    private void handleCrafting(Level level, BlockPos pos, BlockState state) {
        if (hasFuel()) {
            if (!state.getValue(Grinder.LIT)) {
                updateBlockState(pos, state.setValue(Grinder.LIT, true));
            }
            increaseProgress();
            if (progress >= maxProgress) {
                craftItem();
                resetProgress();
            }
        } else {
            resetToDefaultState(level, pos, state);
        }
    }

    private void resetToDefaultState(Level level, BlockPos pos, BlockState state) {
        if (state.getValue(Grinder.LIT)) {
            updateBlockState(pos, state.setValue(Grinder.LIT, false));
        }
        resetProgress();
        burnTime = 0;
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;
        ItemStack output = recipe.get().value().output();
        return canInsertIntoOutput(output);
    }

    private Optional<RecipeHolder<GrinderRecipe>> getCurrentRecipe() {
        return level.getRecipeManager().getRecipeFor(ModRecipes.GRINDER_TYPE.get(),
                new GrinderRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)), level);
    }

    private boolean canInsertIntoOutput(ItemStack output) {
        return isSlotEmpty(OUTPUT_SLOT) || isSameItem(output, itemHandler.getStackInSlot(OUTPUT_SLOT));
    }

    private boolean isSlotEmpty(int slot) {
        return itemHandler.getStackInSlot(slot).isEmpty();
    }

    private boolean isSameItem(ItemStack stack1, ItemStack stack2) {
        return stack2.isEmpty() || (stack1.getItem() == stack2.getItem() && stack2.getCount() < stack2.getMaxStackSize());
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);
        return output.isEmpty() || output.getCount() < output.getMaxStackSize();
    }

    private boolean hasFuel() {
        if (burnTime > 0) {
            burnTime--;
            return true;
        }
        return tryConsumeFuel();
    }

    private boolean tryConsumeFuel() {
        ItemStack fuelStack = itemHandler.getStackInSlot(ENERGY_ITEM_SLOT);
        if (!fuelStack.isEmpty() && (isFuel(fuelStack) || AbstractFurnaceBlockEntity.isFuel(fuelStack))) {
            burnTime = getCombinedFuelBurnTime(fuelStack);
            fuelStack.shrink(1);
            return true;
        }
        return false;
    }

    private int getCombinedFuelBurnTime(ItemStack fuelStack) {
        int furnaceBurnTime = AbstractFurnaceBlockEntity.getFuel().getOrDefault(fuelStack.getItem(), 0);
        int modBurnTime = FuelRegistry.getBurnTime(fuelStack.getItem());
        return Math.max(furnaceBurnTime, modBurnTime);
    }

    private void increaseProgress() {
        progress++;
    }

    private void craftItem() {
        ItemStack input = itemHandler.getStackInSlot(INPUT_SLOT);
        itemHandler.extractItem(INPUT_SLOT, 1, false);

        ItemStack output = getDustForRawMaterial(input);
        if (output.isEmpty()) {
            Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe();
            output = recipe.map(r -> r.value().output().copy()).orElse(ItemStack.EMPTY);
        }

        ItemStack currentOutput = itemHandler.getStackInSlot(OUTPUT_SLOT);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                currentOutput.getCount() + output.getCount()));
    }

    private ItemStack getDustForRawMaterial(ItemStack input) {
        if (isTaggedMaterial(input, RAW_MATERIALS)) {
            return getItemsInTag(DUSTS).stream()
                    .filter(item -> isDustForMaterial(item, input.getItem()))
                    .findFirst()
                    .map(ItemStack::new)
                    .orElse(ItemStack.EMPTY);
        }
        return ItemStack.EMPTY;
    }

    private boolean isTaggedMaterial(ItemStack stack, TagKey<Item> tag) {
        return !stack.isEmpty() && stack.getItem().builtInRegistryHolder().is(tag);
    }

    private List<Item> getItemsInTag(TagKey<Item> tagKey) {
        List<Item> items = new ArrayList<>();
        BuiltInRegistries.ITEM.holders().forEach(holder -> {
            if (holder.is(tagKey)) items.add(holder.value());
        });
        return items;
    }

    private boolean isDustForMaterial(Item dustItem, Item rawMaterial) {
        ResourceLocation dustID = dustItem.builtInRegistryHolder().key().location();
        ResourceLocation rawID = rawMaterial.builtInRegistryHolder().key().location();
        return dustID.getNamespace().equals(rawID.getNamespace()) && dustID.getPath().startsWith(rawID.getPath());
    }

    private void insertInputFromHopper(Level level, BlockPos pos) {
        BlockEntity above = level.getBlockEntity(pos.above());
        if (above instanceof HopperBlockEntity hopperAbove) {
            for (int i = 0; i < hopperAbove.getContainerSize(); i++) {
                ItemStack stack = hopperAbove.getItem(i);
                if (!stack.isEmpty() && itemHandler.isItemValid(INPUT_SLOT, stack)) {
                    ItemStack remaining = itemHandler.insertItem(INPUT_SLOT, stack, false);
                    hopperAbove.setItem(i, remaining);
                    break;
                }
            }
        }
        Direction facing = level.getBlockState(pos).getValue(Grinder.FACING);
        Direction backDirection = facing.getOpposite();
        BlockPos backPos = pos.relative(backDirection);
        BlockEntity back = level.getBlockEntity(backPos);
        if (back instanceof HopperBlockEntity hopperBack) {
            for (int i = 0; i < hopperBack.getContainerSize(); i++) {
                ItemStack stack = hopperBack.getItem(i);
                if (!stack.isEmpty() && itemHandler.isItemValid(ENERGY_ITEM_SLOT, stack)) {
                    ItemStack remaining = itemHandler.insertItem(ENERGY_ITEM_SLOT, stack, false);
                    hopperBack.setItem(i, remaining);
                    break;
                }
            }
        }
    }

    private void ejectOutputToHopper(Level level, BlockPos pos) {
        BlockEntity below = level.getBlockEntity(pos.below());
        if (!(below instanceof HopperBlockEntity hopper)) return;

        ItemStack output = itemHandler.getStackInSlot(OUTPUT_SLOT);
        ItemStack remainder = HopperBlockEntity.addItem(null, hopper, output, Direction.UP);
        itemHandler.setStackInSlot(OUTPUT_SLOT, remainder);
    }

    private void updateBlockState(BlockPos pos, BlockState state) {
        level.setBlockAndUpdate(pos, state);
        setChanged(level, pos, state);
    }
}