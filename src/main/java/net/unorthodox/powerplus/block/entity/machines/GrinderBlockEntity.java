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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ComponentItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.unorthodox.powerplus.block.entity.ModBlockEntities;
import net.unorthodox.powerplus.block.machines.CrystalInfuser;
import net.unorthodox.powerplus.block.machines.Grinder;
import net.unorthodox.powerplus.recipe.*;
import net.unorthodox.powerplus.screen.machines.crystalinfuser.CrystalInfuserMenu;
import net.unorthodox.powerplus.screen.machines.grinder.GrinderMenu;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;

import static net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity.isFuel;
import static org.spongepowered.asm.util.Annotations.setValue;

public class GrinderBlockEntity extends BlockEntity implements MenuProvider {
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
            if (slot == OUTPUT_SLOT) {
                return false;
            }
            return super.isItemValid(slot, stack);
        }
    };

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int ENERGY_ITEM_SLOT = 2;

    private final ContainerData data;
    private int progress = 0;
    private int maxProgress = 72;
    private final int DEFAULT_MAX_PROGRESS = 200;

    private int burnTime = 0;
    private int fuelBurning = 0;

    public GrinderBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GRINDER_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> GrinderBlockEntity.this.progress;
                    case 1 -> GrinderBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0:
                        GrinderBlockEntity.this.progress = pValue;
                    case 1:
                        GrinderBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }


    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.powerplus.grinder");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new GrinderMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        pTag.put("inventory", itemHandler.serializeNBT(pRegistries));
        pTag.putInt("grinder.progress", progress);
        pTag.putInt("grinder.max_progress", maxProgress);

        super.saveAdditional(pTag, pRegistries);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        itemHandler.deserializeNBT(pRegistries, pTag.getCompound("inventory"));
        progress = pTag.getInt("grinder.progress");
        maxProgress = pTag.getInt("grinder.max_progress");
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    public void tick(Level level, BlockPos pPos, BlockState pState) {
        boolean hasFuelAvailable = hasFuel(); // Determine fuel state first.

        // Only proceed with crafting if fuel is available and valid recipe/output conditions are met
        if (hasFuelAvailable && hasRecipe() && isOutputSlotEmptyOrReceivable()) {
            // Update block state if not already lit
            if (!pState.getValue(Grinder.LIT)) {
                level.setBlockAndUpdate(pPos, pState.setValue(Grinder.LIT, true));
            }

            //burnTime--; // Ensure burn time is decremented only when crafting happens.
            increaseCraftingProgress(); // Increment crafting progress safely.

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress(); // Ensure progress resets after crafting completion.
            }

            setChanged(level, pPos, pState); // Synchronize state changes.
        } else {
            // Stop crafting and reset progress if fuel or recipe is invalid
            if (pState.getValue(Grinder.LIT)) {
                level.setBlockAndUpdate(pPos, pState.setValue(Grinder.LIT, false));
            }
            resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
        this.maxProgress = DEFAULT_MAX_PROGRESS;
    }
    
    private void resetToDefaultState(Level level, BlockPos blockPos, BlockState blockState) {
        if (blockState.getValue(Grinder.LIT)) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(Grinder.LIT, false));
        }
        resetProgress();
        fuelBurning = 0; // Reset fuel state explicitly        
    }

    private void craftItem() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe();

        if (recipe.isPresent()) {
            ItemStack output = recipe.get().value().output(); // Get output from recipe
            int outputCount = output.getCount(); // Output count from the recipe (JSON)

            // Manipulate inventory
            itemHandler.extractItem(INPUT_SLOT, 1, false);
            itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(
                    output.getItem(),
                    itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + (outputCount * 2)
            ));

            // Consume 1 unit of fuel from ENERGY_ITEM_SLOT if applicable
            ItemStack fuelStack = itemHandler.getStackInSlot(ENERGY_ITEM_SLOT);
            if (!fuelStack.isEmpty() && isFuel(fuelStack)) {
                fuelStack.shrink(1);
            }
        }
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        if (burnTime > 0) {
            progress++; // Safely increment crafting progress
        }
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<GrinderRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().getResultItem(null);
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private Optional<RecipeHolder<GrinderRecipe>> getCurrentRecipe() {
        return this.level.getRecipeManager()
                .getRecipeFor(ModRecipes.GRINDER_TYPE.get(), new GrinderRecipeInput(itemHandler.getStackInSlot(INPUT_SLOT)), level);
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

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        return saveWithoutMetadata(pRegistries);
    }

    private boolean hasFuel() {
        if (burnTime > 0) {
            burnTime--;
            return true;
        }

        // Attempt to replenish fuel if possible
        ItemStack fuelStack = itemHandler.getStackInSlot(ENERGY_ITEM_SLOT);
        if (!fuelStack.isEmpty() && isFuel(fuelStack)) {
            burnTime = net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
                    .getFuel().getOrDefault(fuelStack.getItem(), 0);

            // Record the full burn time cycle
            fuelBurning = burnTime;

            // Consume one fuel item
            fuelStack.shrink(1);
            return true;
        }

        return false; // Return false explicitly when no fuel is available
    }
    
}
