package net.unorthodox.powerplus.config;

import net.unorthodox.powerplus.lib.IVariant;

public interface IEnergyConfig<V extends Enum<V> & IVariant<V>> {
    long getCapacity(V var1);

    long getTransfer(V var1);
}