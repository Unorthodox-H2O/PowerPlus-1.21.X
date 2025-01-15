package net.unorthodox.powerplus.block.entity.thermo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.unorthodox.powerplus.lib.EnergyStorage;

public class BasicThermoGeneratorBE extends BlockEntity implements IThermoGeneratorBE {
    private final EnergyStorage energyStorage = new EnergyStorage();

    // Constants
    private static final int MAX_ENERGY_CAPACITY = 40_000_000;
    private static final float LAVA_MULTIPLIER = 20.0f;
    private static final float COAL_MULTIPLIER = 1.5f;
    private static final Block LAVA_BLOCK = Blocks.LAVA;
    private static final Block COAL_BLOCK = Blocks.COAL_BLOCK;

    // Fields
    private final int baseFePerTick;
    private int currentEnergy = 0;
    private int transferRate = 0;

    public BasicThermoGeneratorBE(BlockEntityType<?> type, BlockPos pos, BlockState state, int baseFePerTick) {
        super(type, pos, state);
        this.baseFePerTick = baseFePerTick;
    }

    public int calculateAndGenerateEnergy() {
        int energyMultiplier = calculateEnergyMultiplier();
        int energyGenerated = baseFePerTick * energyMultiplier;
        addEnergySafely(energyGenerated);
        transferRate = energyGenerated; // Update transfer rate to match generated energy
        return energyGenerated;
    }

    private int calculateEnergyMultiplier() {
        if (isSpecificBlockUnderneath(LAVA_BLOCK)) {
            return (int) LAVA_MULTIPLIER;
        } else if (isSpecificBlockUnderneath(COAL_BLOCK)) {
            return (int) COAL_MULTIPLIER;
        } else {
            return 1;
        }
    }

    public int getCurrentEnergy() {
        return currentEnergy;
    }

    public void addEnergySafely(int energy) {
        currentEnergy = Math.min(currentEnergy + energy, MAX_ENERGY_CAPACITY);
    }

    private boolean isSpecificBlockUnderneath(Block block) {
        if (level == null) return false; // Ensure the level (world) is loaded
        Block blockBelow = level.getBlockState(worldPosition.below()).getBlock();
        return blockBelow == block;
    }

    public int getTransferRate() {
        return transferRate;
    }

    public boolean isFull() {
        return currentEnergy >= MAX_ENERGY_CAPACITY;
    }

    public boolean isEmpty() {
        return currentEnergy <= 0;
    }

    public void setEnergy(int energy) {
        currentEnergy = energy;
    }

    public void setTransferRate(int transferRate) {
        this.transferRate = transferRate;
    }

    public boolean keepEnergy() {
        return true; // Fixed: Ensuring this returns boolean as expected
    }

    public boolean keepFluid() {
        return true;
    }

    public int getSlotLimit(int slot) {
        return 1;
    }

    public boolean canExtract(int slot, ItemStack stack) {
        return true;
    }
    @Override
    public EnergyStorage getEnergyStorage() {
        return this.energyStorage;
    }
}
