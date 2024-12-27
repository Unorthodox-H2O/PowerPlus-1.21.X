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

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<InductionSmelterRecipe>> INDUCTIONSMELTER_SERIALIZER =
            SERIALIZERS.register("induction_smelting", InductionSmelterRecipe.Serializer::new);
    public static final DeferredHolder<RecipeType<?>, RecipeType<InductionSmelterRecipe>> INDUCTIONSMELTER_TYPE =
            TYPES.register("induction_smelting", () -> new RecipeType<InductionSmelterRecipe>() {
                @Override
                public String toString() {
                    return "induction_smelting";
                }
            });

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        TYPES.register(eventBus);
    }
}
