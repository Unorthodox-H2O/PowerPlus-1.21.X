package net.unorthodox.powerplus.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.ModBlocks;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PowerPlus.MOD_ID);

    public static final Supplier<CreativeModeTab> POWERPLUS_ITEMS_TAB = CREATIVE_MODE_TAB.register("powerplus_items_tab", () -> CreativeModeTab.builder()
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
                pOutput.accept(ModItems.UPGRADETIER1);
                pOutput.accept(ModItems.UPGRADETIER2);
                pOutput.accept(ModItems.UPGRADETIER3);
                pOutput.accept(ModItems.UPGRADETIER4);
                pOutput.accept(ModItems.UPGRADETIER5);

                //Wrench
                //pOutput.accept(ModItems.WRENCH);

            }).build());
    public static final Supplier<CreativeModeTab> POWERPLUS_BLOCK_TAB = CREATIVE_MODE_TAB.register("powerplus_block_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.CREATIVETHERMOGENERATOR.get()))
            .withTabsBefore(ResourceLocation.fromNamespaceAndPath(PowerPlus.MOD_ID, "powerplus_items_tab"))
            .title(Component.translatable("itemGroup.powerplus.powerplus_blocks"))
            .displayItems((pParameters, pOutput) -> {

                // add Blocks to Creative mode tab
                //Blocks
                pOutput.accept(ModBlocks.SCANDIUM_BLOCK);
                pOutput.accept(ModBlocks.SAMARIUM_BLOCK);
                pOutput.accept(ModBlocks.EUROPIUM_BLOCK);
                pOutput.accept(ModBlocks.CERIUM_BLOCK);
                pOutput.accept(ModBlocks.TERBIUM_BLOCK);
                pOutput.accept(ModBlocks.BASIC_CASING);
                pOutput.accept(ModBlocks.ADVANCED_CASING);
                pOutput.accept(ModBlocks.ELITE_CASING);
                pOutput.accept(ModBlocks.SUPERIOR_CASING);
                pOutput.accept(ModBlocks.LEGENDARY_CASING);
                pOutput.accept(ModBlocks.ASCENDANT_CASING);
                pOutput.accept(ModBlocks.RADIANT_CASING);

                //Benks
                pOutput.accept(ModBlocks.SCANDIUM_BENK);
                pOutput.accept(ModBlocks.SAMARIUM_BENK);
                pOutput.accept(ModBlocks.EUROPIUM_BENK);
                pOutput.accept(ModBlocks.CERIUM_BENK);
                pOutput.accept(ModBlocks.TERBIUM_BENK);

                //Generators
                pOutput.accept(ModBlocks.BASICTHERMOGENERATOR);
                pOutput.accept(ModBlocks.ADVANCEDTHERMOGENERATOR);
                pOutput.accept(ModBlocks.ELITETHERMOGENERATOR);
                pOutput.accept(ModBlocks.SUPERIORTHERMOGENERATOR);
                pOutput.accept(ModBlocks.LEGENDARYTHERMOGENERATOR);
                pOutput.accept(ModBlocks.ASCENDANTTHERMOGENERATOR);
                pOutput.accept(ModBlocks.RADIANTTHERMOGENERATOR);
                pOutput.accept(ModBlocks.CREATIVETHERMOGENERATOR);


            }).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
