package net.unorthodox.powerplus.lib;

public interface IVariantEntry<V extends IVariant, R extends IVariantEntry<V, R>> {
    V getVariant();
}
