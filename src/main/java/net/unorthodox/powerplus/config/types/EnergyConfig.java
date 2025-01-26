package net.unorthodox.powerplus.config.types;

import net.unorthodox.powerplus.config.IEnergyConfig;
import net.unorthodox.powerplus.config.values.TieredEnergyValues;
import net.unorthodox.powerplus.lib.Tier;

public class EnergyConfig implements IEnergyConfig<Tier> {
    public TieredEnergyValues capacities;
    public TieredEnergyValues transfer_rates;

    private EnergyConfig() {
    }

    public EnergyConfig(TieredEnergyValues capacities, TieredEnergyValues transfer_rates) {
        this.capacities = capacities;
        this.transfer_rates = transfer_rates;
    }

    public long getCapacity(Tier variant) {
        return this.capacities.get(variant);
    }

    public long getTransfer(Tier variant) {
        return this.transfer_rates.get(variant);
    }
}
