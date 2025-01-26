package net.unorthodox.powerplus.config.values;


import net.unorthodox.powerplus.config.annotations.LongRange;
import net.unorthodox.powerplus.lib.Tier;

public class TieredChannelValues {
    @LongRange(
            min = 1L,
            max = 12L
    )
    public int basic;
    @LongRange(
            min = 1L,
            max = 12L
    )
    public int advanced;
    @LongRange(
            min = 1L,
            max = 12L
    )
    public int elite;
    @LongRange(
            min = 1L,
            max = 12L
    )
    public int superior;
    @LongRange(
            min = 1L,
            max = 12L
    )
    public int legendary;
    @LongRange(
            min = 1L,
            max = 12L
    )
    public int ascendant;
    @LongRange(
            min = 1L,
            max = 12L
    )
    public int radiant;

    public static TieredChannelValues getDefault() {
        return new TieredChannelValues(1, 2, 3, 5, 7, 9, 12);
    }

    private TieredChannelValues() {
    }

    public TieredChannelValues(int basic, int advanced, int elite, int superior, int legendary, int ascendant, int radiant) {
        this.basic = basic;
        this.advanced = advanced;
        this.elite = elite;
        this.superior = superior;
        this.legendary = legendary;
        this.ascendant = ascendant;
        this.radiant = radiant;
    }

    public int get(Tier tier) {
        int var10000;
        switch (tier) {
            case BASIC -> var10000 = this.basic;
            case ADVANCED -> var10000 = this.advanced;
            case ELITE -> var10000 = this.elite;
            case SUPERIOR -> var10000 = this.superior;
            case LEGENDARY -> var10000 = this.legendary;
            case ASCENDANT -> var10000 = this.ascendant;
            case RADIANT -> var10000 = this.radiant;
            case CREATIVE -> var10000 = 0;
            default -> throw new MatchException((String)null, (Throwable)null);
        }

        return var10000;
    }
}

