package net.unorthodox.powerplus.block.base.cell;

import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.energy.IEnergyStorage;
import net.unorthodox.powerplus.config.IGenerator;

import java.util.ArrayList;
import java.util.List;

public abstract class CellBase extends Block implements IEnergyStorage {

    private int energyStored;
    private final int maxEnergyStored;
    private final int maxReceive;
    private final int maxExtract;
    private final int radius;

    public CellBase(Properties properties, int maxEnergyStored, int maxReceive, int maxExtract, int radius) {
        super(properties);
        this.energyStored = 0;
        this.maxEnergyStored = maxEnergyStored;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.radius = radius;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(maxEnergyStored - energyStored, Math.min(this.maxReceive, maxReceive));
        if (!simulate) {
            energyStored += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energyStored, Math.min(this.maxExtract, maxExtract));
        if (!simulate) {
            energyStored -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return energyStored;
    }

    @Override
    public int getMaxEnergyStored() {
        return maxEnergyStored;
    }

    @Override
    public boolean canExtract() {
        return maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return maxReceive > 0;
    }

    public void tick() {
        List<IGenerator> generators = findGeneratorsInRadius();
        for (IGenerator generator : generators) {
            int energyToTransfer = Math.min(generator.outputEnergy(), this.maxReceive);
            this.receiveEnergy(energyToTransfer, false);
        }
    }

    public List<IGenerator> findGeneratorsInRadius() {
        List<IGenerator> foundGenerators = new ArrayList<>();
        // Scan the blocks within radius and find IGenerators...
        return foundGenerators;
    }
}