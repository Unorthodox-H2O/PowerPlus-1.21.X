package net.unorthodox.powerplus.config;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static net.unorthodox.powerplus.block.entity.ModBlockEntities.BASICTHERMOGENERATOR_BE;

public interface IGenerator {

    // Known generator block entities
    Set<BlockEntityType<?>> GENERATORS = Set.of(
            BASICTHERMOGENERATOR_BE.get()
    );

    /**
     * Defines the energy output of the generator.
     * Abstract method to be implemented in specific generator classes.
     */
    int outputEnergy();

    /**
     * Returns the maximum energy capacity of this generator.
     */
    int maxEnergyCapacity();

    /**
     * Returns the current energy stored in the generator.
     */
    int currentEnergy();

    /**
     * Finds all generator instances within the specified radius around the given position.
     *
     * @param radius     The radius in which to search for generators.
     * @param currentPos The base position to search around.
     * @param world      The level (world) in which the search is performed.
     * @return A list of found generator instances that have energy.
     */
    default List<IGenerator> findGeneratorsInRadius(int radius, BlockPos currentPos, Level world) {
        List<IGenerator> foundGenerators = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    // Calculate the position within the radius
                    BlockPos pos = currentPos.offset(x, y, z);

                    // Retrieve the BlockEntity at this position
                    BlockEntity blockEntity = world.getBlockEntity(pos);

                    // Check if the blockEntity:
                    // - is a valid generator (subtype of IGenerator)
                    // - is within the known GENERATORS set
                    // - has energy
                    if (blockEntity instanceof IGenerator generator
                            && GENERATORS.contains(blockEntity.getType())
                            && generator.hasEnergy()) {
                        foundGenerators.add(generator);
                    }
                }
            }
        }

        return foundGenerators;
    }

    /**
     * Checks if the generator has available energy.
     *
     * @return True if energy is present, false otherwise.
     */
    boolean hasEnergy();

    /**
     * Checks if the generator is overloaded.
     *
     * @return True if the generator is overloaded, false otherwise.
     */
    boolean isOverloaded();

    /**
     * Consumes fuel from the generator by the specified amount.
     *
     * @param amount The amount of fuel to consume.
     */
    void consumeFuel(int amount);
}