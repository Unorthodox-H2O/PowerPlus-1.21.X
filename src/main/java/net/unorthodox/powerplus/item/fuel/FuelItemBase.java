package net.unorthodox.powerplus.item.fuel;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

import javax.annotation.Nullable;

public class FuelItemBase extends Item {
    private int burnTime = 0;

    public FuelItemBase(Properties properties, int burnTime) {
        super(properties);
        this.burnTime = burnTime;
    }
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return this.burnTime;

    }
}

