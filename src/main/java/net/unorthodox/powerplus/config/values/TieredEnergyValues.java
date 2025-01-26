package net.unorthodox.powerplus.config.values;

import net.unorthodox.powerplus.config.annotations.LongRange;
import net.unorthodox.powerplus.lib.Tier;

public class TieredEnergyValues {
    @LongRange(
            min = 1L,
            max = 9000000000000000000L
    )
    public long basic;
    @LongRange(
            min = 1L,
            max = 9000000000000000000L
    )
    public long advanced;
    @LongRange(
            min = 1L,
            max = 9000000000000000000L
    )
    public long elite;
    @LongRange(
            min = 1L,
            max = 9000000000000000000L
    )
    public long superior;
    @LongRange(
            min = 1L,
            max = 9000000000000000000L
    )
    public long legendary;
    @LongRange(
            min = 1L,
            max = 9000000000000000000L
    )
    public long ascendant;
    @LongRange(
            min = 1L,
            max = 9000000000000000000L
    )
    public long radiant;

    private TieredEnergyValues() {
    }

    public TieredEnergyValues(long basic, long advanced, long elite, long superior, long legendary, long ascendant, long radiant) {
        this.basic = basic;
        this.advanced = advanced;
        this.elite = elite;
        this.superior = superior;
        this.legendary = legendary;
        this.ascendant = ascendant;
        this.radiant = radiant;
    }

    public long get(Tier tier) {
        long var10000;
        switch (tier) {
            case BASIC -> var10000 = this.basic;
            case ADVANCED -> var10000 = this.advanced;
            case ELITE -> var10000 = this.elite;
            case SUPERIOR -> var10000 = this.superior;
            case LEGENDARY -> var10000 = this.legendary;
            case ASCENDANT -> var10000 = this.ascendant;
            case RADIANT -> var10000 = this.radiant;
            case CREATIVE -> var10000 = 9000000000000000000L;
            default -> throw new MatchException(null, null);
        }

        return var10000;
    }

    public TieredEnergyValues copy(long factor) {
        return new TieredEnergyValues(this.basic * factor, this.advanced * factor, this.elite * factor, this.superior * factor, this.legendary * factor, this.ascendant * factor, this.radiant * factor);
    }
}
