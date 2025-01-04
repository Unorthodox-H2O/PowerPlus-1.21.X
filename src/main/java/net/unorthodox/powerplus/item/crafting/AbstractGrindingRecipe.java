package net.unorthodox.powerplus.item.crafting;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class AbstractGrindingRecipe implements Recipe<SingleRecipeInput> {
    protected final RecipeType<?> type;
    protected final GrindingBookCategory category;
    protected final String group;
    protected final Ingredient ingredient;
    protected final ItemStack result;
    protected final float experience;
    protected final int grindingTime;

    public AbstractGrindingRecipe(RecipeType<?> type, String group, GrindingBookCategory category, Ingredient ingredient, ItemStack result, float experience, int grindingTime) {
        this.type = type;
        this.category = category;
        this.group = group;
        this.ingredient = ingredient;
        this.result = result;
        this.experience = experience;
        this.grindingTime = grindingTime;
    }

    public boolean matches(SingleRecipeInput input, Level level) {
        return this.ingredient.test(input.item());
    }

    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return this.result.copy();
    }

    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient);
        return nonnulllist;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.result;
    }

    public String getGroup() {
        return this.group;
    }

    public int getCookingTime() {
        return this.grindingTime;
    }

    public RecipeType<?> getType() {
        return this.type;
    }

    public GrindingBookCategory category() {
        return this.category;
    }

    public interface Factory<T extends AbstractGrindingRecipe> {
        T create(String var1, GrindingBookCategory var2, Ingredient var3, ItemStack var4, float var5, int var6);
    }
}




