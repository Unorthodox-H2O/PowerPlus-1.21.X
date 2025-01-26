package net.unorthodox.powerplus.config.types;

import net.unorthodox.powerplus.config.IEnergyConfig;
import net.unorthodox.powerplus.config.values.TieredChannelValues;
import net.unorthodox.powerplus.config.values.TieredEnergyValues;
import net.unorthodox.powerplus.lib.Tier;

public class EmitEnergyConfig implements IEnergyConfig<Tier> {
    public TieredEnergyValues transfer_rates;
    public TieredChannelValues channels;

    private EmitEnergyConfig() {
    }

    public EmitEnergyConfig(TieredEnergyValues transfer_rates, TieredChannelValues channels) {
        this.transfer_rates = transfer_rates;
        this.channels = channels;
    }

    public long getCapacity(Tier variant) {
        return 0L;
    }

    public long getTransfer(Tier variant) {
        return this.transfer_rates.get(variant);
    }
}