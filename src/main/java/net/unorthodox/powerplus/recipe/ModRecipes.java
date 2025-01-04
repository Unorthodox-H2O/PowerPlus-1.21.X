package net.unorthodox.powerplus.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import org.apache.commons.compress.archivers.dump.DumpArchiveEntry;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, PowerPlus.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, PowerPlus.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CrystalInfuserRecipe>> CRYSTALINFUSER_SERIALIZER =
            SERIALIZERS.register("crystal_infusing", CrystalInfuserRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CrystalInfuserRecipe>> CRYSTALINFUSER_TYPE =
            TYPES.register("crystal_infusing", () -> new RecipeType<CrystalInfuserRecipe>() {
                @Override
                public String toString() {
                    return "crystal_infusing";
                    }
                });
    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<CoalGrinderRecipe>> COAL_GRINDER_SERIALIZER =
            SERIALIZERS.register("grinding", CoalGrinderRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<CoalGrinderRecipe>> GRINDING_TYPE =
            TYPES.register("grinding", () -> new RecipeType<CoalGrinderRecipe>() {
                @Override
                public String toString() {
                    return "grinding";
                    }
                });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
        }
    }
