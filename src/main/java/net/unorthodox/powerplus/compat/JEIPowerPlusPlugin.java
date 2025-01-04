package net.unorthodox.powerplus.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeManager;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.recipe.CrystalInfuserRecipe;
import net.unorthodox.powerplus.recipe.ModRecipes;
import net.unorthodox.powerplus.screen.machines.crystalinfuser.CrystalInfuserScreen;

import java.util.List;
@JeiPlugin
public class JEIPowerPlusPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, "jei_plugin");
    }
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CrystalInfuserRecipeCategory(
                registration.getJeiHelpers().getGuiHelper()));
    }
    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<CrystalInfuserRecipe> crystalInfuserRecipes = recipeManager
                .getAllRecipesFor(ModRecipes.CRYSTALINFUSER_TYPE.get()).stream().map(RecipeHolder::value).toList();
        registration.addRecipes(CrystalInfuserRecipeCategory.CRYSTAL_INFUSER_RECIPE_RECIPE_TYPE, crystalInfuserRecipes);
    }
    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(CrystalInfuserScreen.class, 70, 30, 25, 20,
                CrystalInfuserRecipeCategory.CRYSTAL_INFUSER_RECIPE_RECIPE_TYPE);
    }
}