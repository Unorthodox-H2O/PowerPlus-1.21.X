package net.unorthodox.powerplus.lib;

import net.minecraft.nbt.CompoundTag;

import java.util.Locale;

public interface IVariant<V extends Enum<V> & IVariant<V>> {
    V[] getVariants();

    default String getName() {
        return ((Enum)this).name().toLowerCase(Locale.ENGLISH);
    }

    default V read(CompoundTag nbt, String key) {
        return this.getVariants()[nbt.getInt(key)];
    }

    default CompoundTag write(CompoundTag nbt, V v, String key) {
        nbt.putInt(key, ((Enum)this).ordinal());
        return nbt;
    }

    default boolean isEmpty() {
        return this instanceof Single || this.getVariants().length == 0;
    }

    static <T extends IVariant> T getEmpty() {
        return (T)IVariant.Single.SINGLE;
    }

    int ordinal();

    enum Single implements IVariant<Single> {
        SINGLE;

        Single() {
        }

        public Single[] getVariants() {
            return new Single[0];
        }
    }
}
