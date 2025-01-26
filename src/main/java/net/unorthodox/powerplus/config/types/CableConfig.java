package net.unorthodox.powerplus.config.types;

import net.unorthodox.powerplus.config.IEnergyConfig;
import net.unorthodox.powerplus.config.values.TieredEnergyValues;
import net.unorthodox.powerplus.lib.Tier;

public class CableConfig implements IEnergyConfig<Tier> {
    public TieredEnergyValues transfer_rates;

    private CableConfig() {
    }

    public CableConfig(TieredEnergyValues transfer_rates) {
        this.transfer_rates = transfer_rates;
    }

    public long getCapacity(Tier variant) {
        return 0L;
    }

    public long getTransfer(Tier variant) {
        return this.transfer_rates.get(variant);
    }
}
