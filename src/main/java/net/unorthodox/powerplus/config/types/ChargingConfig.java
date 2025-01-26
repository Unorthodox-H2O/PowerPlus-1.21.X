package net.unorthodox.powerplus.config.types;

import net.unorthodox.powerplus.config.IEnergyConfig;
import net.unorthodox.powerplus.config.values.TieredEnergyValues;
import net.unorthodox.powerplus.lib.Tier;

public class ChargingConfig implements IEnergyConfig<Tier> {
    public TieredEnergyValues capacities;
    public TieredEnergyValues transfer_rates;
    public TieredEnergyValues charging_rates;


    public ChargingConfig(TieredEnergyValues capacities, TieredEnergyValues transfer_rates, TieredEnergyValues charging_rates) {
        this.capacities = capacities;
        this.transfer_rates = transfer_rates;
        this.charging_rates = charging_rates;
    }

    public long getCapacity(Tier variant) {
        return this.capacities.get(variant);
    }

    public long getTransfer(Tier variant) {
        return this.transfer_rates.get(variant);
    }

    public long getChargingSpeed(Tier variant) {
        return this.charging_rates.get(variant);
    }
}