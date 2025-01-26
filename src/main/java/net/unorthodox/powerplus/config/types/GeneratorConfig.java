package net.unorthodox.powerplus.config.types;

import net.unorthodox.powerplus.config.IEnergyConfig;
import net.unorthodox.powerplus.config.values.TieredEnergyValues;
import net.unorthodox.powerplus.lib.Tier;

public class GeneratorConfig implements IEnergyConfig<Tier> {
    public TieredEnergyValues capacities;
    public TieredEnergyValues transfer_rates;
    public TieredEnergyValues generation_rates;

    private GeneratorConfig() {
    }

    public GeneratorConfig(TieredEnergyValues capacities, TieredEnergyValues transfer_rates, TieredEnergyValues generation_rates) {
        this.capacities = capacities;
        this.transfer_rates = transfer_rates;
        this.generation_rates = generation_rates;
    }

    public long getCapacity(Tier variant) {
        return this.capacities.get(variant);
    }

    public long getTransfer(Tier variant) {
        return this.transfer_rates.get(variant);
    }

    public long getGeneration(Tier variant) {
        return this.generation_rates.get(variant);
    }
}
