package net.unorthodox.powerplus.config;

import net.unorthodox.powerplus.lib.IVariant;

public interface IConfigHolder<V extends Enum<V> & IVariant<V>, C> {
    C getConfig();
}
