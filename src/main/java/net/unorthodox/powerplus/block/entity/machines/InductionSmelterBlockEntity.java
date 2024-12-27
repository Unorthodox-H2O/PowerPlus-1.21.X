package net.unorthodox.powerplus.block.entity.machines;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.unorthodox.powerplus.block.entity.ModBlockEntities;
import net.unorthodox.powerplus.block.machines.InductionSmelter;
import net.unorthodox.powerplus.item.ModItems;
import net.unorthodox.powerplus.recipe.InductionSmelterRecipe;
import net.unorthodox.powerplus.recipe.InductionSmelterRecipeInput;
import net.unorthodox.powerplus.recipe.ModRecipes;
import net.unorthodox.powerplus.screen.machines.inductionsmelter.InductionSmelterMenu;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class InductionSmelterBlockEntity extends BlockEntity implements MenuProvider {
    public final ItemStackHandler itemHandler = new ItemStackHandler(6){
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if(!level.isClientSide()){
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }
    };

    private static final int FLUID_ITEM_SLOT = 0;
    private static final int INPUT_SLOT_1 = 1;
    private static final int INPUT_SLOT_2 = 2;
    private static final int INPUT_SLOT_3 = 3;
    private static final int OUTPUT_SLOT = 4;
    private static final int ENERGY_ITEM_SLOT = 5;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 72;

    public InductionSmelterBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.INDUCTION_SMELTER_BE.get(), pos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                  case 0 -> InductionSmelterBlockEntity.this.progress;
                  case 1 -> InductionSmelterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex){
                    case 0: InductionSmelterBlockEntity.this.progress = pValue;
                    case 1: InductionSmelterBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.powerplus.inductionsmelter");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int i, Inventory inventory, Player player) {
        return new InductionSmelterMenu(i, inventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        tag.put("inventory", itemHandler.serializeNBT(registries));
        tag.putInt("induction_smelter.progress", progress);
        tag.putInt("induction_smelter.max_progress", maxProgress);

        super.saveAdditional(tag, registries);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        itemHandler.deserializeNBT(registries, tag.getCompound("inventory"));
        progress = tag.getInt("induction_smelter.progress");
        maxProgress = tag.getInt("induction_smelter.max_progress");

    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++){
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        if(hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            increaseCraftingProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(InductionSmelter.LIT, true));
            setChanged(level, pPos, pState);

            if (hasCraftingFinished()){
                craftItem();
                resetProgress();
            }

        } else {
            resetProgress();
            level.setBlockAndUpdate(pPos, pState.setValue(InductionSmelter.LIT, false));
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }

    private void craftItem() {
        Optional<RecipeHolder<InductionSmelterRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().output();

        itemHandler.extractItem(INPUT_SLOT_1, 1, false);
        itemHandler.extractItem(INPUT_SLOT_2, 1, false);
        itemHandler.extractItem(INPUT_SLOT_3,1, false);
        itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(output.getItem(),
                itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + output.getCount()));
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }


    private boolean hasRecipe() {
        Optional<RecipeHolder<InductionSmelterRecipe>> recipe = getCurrentRecipe();
        if(recipe.isEmpty()){
            return false;
        }
        ItemStack output = recipe.get().value().getResultItem(null);

        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<InductionSmelterRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.INDUCTIONSMELTER_TYPE.get(), new InductionSmelterRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT_1),
                        itemHandler.getStackInSlot(INPUT_SLOT_2),itemHandler.getStackInSlot(INPUT_SLOT_3)), level);

    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                itemHandler.getStackInSlot(OUTPUT_SLOT).getItem() == output.getItem();
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ? 64 : itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
        int currentCount = itemHandler.getStackInSlot(OUTPUT_SLOT).getCount();

        return maxCount >= currentCount + count;
    }


    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }
}
