package net.unorthodox.powerplus.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.ModBlocks;
import net.unorthodox.powerplus.recipe.CrystalInfuserRecipe;
import org.jetbrains.annotations.Nullable;


public class CrystalInfuserRecipeCategory implements IRecipeCategory<CrystalInfuserRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, "crystal_infusing");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID,
            "textures/gui/crystallizer/crystallizer_gui.png");
    public static final RecipeType<CrystalInfuserRecipe> CRYSTAL_INFUSER_RECIPE_RECIPE_TYPE =
            new RecipeType<>(UID, CrystalInfuserRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    public CrystalInfuserRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.CRYSTALINFUSER.get()));
    }
    @Override
    public RecipeType<CrystalInfuserRecipe> getRecipeType() {
        return CRYSTAL_INFUSER_RECIPE_RECIPE_TYPE;
    }
    @Override
    public Component getTitle() {
        return Component.literal("Crystallizer");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return icon;
    }
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CrystalInfuserRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 54, 34).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 34).addItemStack(recipe.getResultItem(null));
    }
}