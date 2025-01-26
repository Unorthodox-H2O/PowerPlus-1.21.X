package net.unorthodox.powerplus.lib;

public class EnergyStorage {
    private int energyStored;
    private int maxEnergyStored;
    public int MAX_ENERGY_CAPACITY = 1000000;

    public EnergyStorage(int maxEnergyCapacity, int maxTransferRate) {
        this.MAX_ENERGY_CAPACITY = maxEnergyCapacity;

    }

    public int getEnergyStored() {
        return energyStored;
    }
    public int getMaxEnergyStored() {
        return maxEnergyStored;
    }
  
}

