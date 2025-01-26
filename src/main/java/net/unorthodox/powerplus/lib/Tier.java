package net.unorthodox.powerplus.lib;

public enum Tier implements IVariant<Tier> {
    BASIC,
    ADVANCED,
    ELITE,
    SUPERIOR,
    LEGENDARY,
    ASCENDANT,
    RADIANT,
    CREATIVE;

    public Tier[] getVariants() {
        return values();
    }

    public static Tier[] getNormalVariants() {
        return new Tier[]{BASIC, ADVANCED, ELITE, SUPERIOR, LEGENDARY, ASCENDANT, RADIANT};
    }
}
