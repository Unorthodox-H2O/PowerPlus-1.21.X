package net.unorthodox.powerplus.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PowerPlus.MOD_ID);

    public static final Supplier<CreativeModeTab> POWER_PLUS_ITEMS_TAB = CREATIVE_MODE_TAB.register("scandium_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.SCANDIUM.get()))
            .title(Component.translatable("itemGroup.powerplus.powerplus_items"))
            .displayItems((pParameters, pOutput) -> {
                // add items to Creative mode tab
                pOutput.accept(ModItems.SCANDIUM);
                pOutput.accept(ModItems.SAMARIUM);
                pOutput.accept(ModItems.EUROPIUM);
                pOutput.accept(ModItems.CERIUM);
                pOutput.accept(ModItems.TERBIUM);
                pOutput.accept(ModItems.RAW_SCANDIUM);
                pOutput.accept(ModItems.RAW_SAMARIUM);
                pOutput.accept(ModItems.RAW_EUROPIUM);
                pOutput.accept(ModItems.RAW_CERIUM);
                pOutput.accept(ModItems.RAW_TERBIUM);
                pOutput.accept(ModItems.FORGED_SCRAP_INGOT);
                pOutput.accept(ModItems.FORGED_SCRAP_DUST);
                pOutput.accept(ModItems.FORGED_SCRAP);


                //Upgrades
                pOutput.accept(ModItems.UPGRADEBLANK);
                pOutput.accept(ModItems.ADVANCEDUPGRADEBLANK);


                //Wrench
                //pOutput.accept(ModItems.WRENCH);

            }).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
