package net.unorthodox.powerplus.block.entity.thermo;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.unorthodox.powerplus.block.entity.ModBlockEntities;
import net.unorthodox.powerplus.config.IGenerator;
import net.unorthodox.powerplus.lib.EnergyStorage;

import java.util.HashMap;
import java.util.Map;

public class BasicThermoGeneratorBE extends BlockEntity implements IGenerator {

    // Constants
    private static final int MAX_ENERGY_CAPACITY = 10_000;
    private static final int MAX_TRANSFER_RATE = 100;
    private static final int BASE_FE_PER_TICK = 40;

    // Block energy multipliers for thermal energy generation
    private static final Map<Block, Float> FE_MULTIPLIERS = new HashMap<>(Map.of(
            Blocks.LAVA, 20.0f,             // Lava block energy multiplier
            Blocks.COAL_BLOCK, 1.5f         // Coal block energy multiplier
    ));

    // Energy Storage
    private final EnergyStorage energyStorage;

    public BasicThermoGeneratorBE(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.BASICTHERMOGENERATOR_BE.get(), pos, blockState);
        this.energyStorage = new EnergyStorage(MAX_ENERGY_CAPACITY, MAX_TRANSFER_RATE); // Ensure correct parameters
    }

    /**
     * Gets the energy multiplier based on the block below.
     *
     * @return the energy multiplier or BASE_FE_PER_TICK if no valid block is below.
     */
    public int getMultiplier() {
        // Ensure level and position validity
        if (level == null || worldPosition == null || worldPosition.getY() <= 0) {
            return BASE_FE_PER_TICK;
        }

        // Get block state below this generator
        BlockState blockBelow = level.getBlockState(worldPosition.below());

        // Default to BASE_FE_PER_TICK if no specific multiplier is found
        float multiplier = FE_MULTIPLIERS.getOrDefault(blockBelow.getBlock(), (float) BASE_FE_PER_TICK);

        return Math.round(multiplier); // Round off and return as int
    }

    // Getter for the current energy stored
    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    // Getter for the maximum energy capacity
    public int getMaxEnergyCapacity() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public int outputEnergy() {
        return 0;
    }

    @Override
    public int maxEnergyCapacity() {
        return 0;
    }

    @Override
    public int currentEnergy() {
        return 0;
    }

    @Override
    public boolean hasEnergy() {
        return false;
    }

    @Override
    public boolean isOverloaded() {
        return false;
    }

    @Override
    public void consumeFuel(int amount) {

    }
}