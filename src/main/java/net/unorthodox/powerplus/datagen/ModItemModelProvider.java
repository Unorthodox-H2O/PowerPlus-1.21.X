package net.unorthodox.powerplus.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PowerPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.SCANDIUM.get());
        basicItem(ModItems.RAW_SCANDIUM.get());
        basicItem(ModItems.SAMARIUM.get());
        basicItem(ModItems.RAW_SAMARIUM.get());
        basicItem(ModItems.EUROPIUM.get());
        basicItem(ModItems.RAW_EUROPIUM.get());
        basicItem(ModItems.CERIUM.get());
        basicItem(ModItems.RAW_CERIUM.get());
        basicItem(ModItems.TERBIUM.get());
        basicItem(ModItems.RAW_TERBIUM.get());
        basicItem(ModItems.UPGRADETIER1.get());
        basicItem(ModItems.UPGRADETIER2.get());
        basicItem(ModItems.UPGRADETIER3.get());
        basicItem(ModItems.UPGRADETIER4.get());
        basicItem(ModItems.UPGRADETIER5.get());
        basicItem(ModItems.UPGRADEBLANK.get());
        basicItem(ModItems.ADVANCEDUPGRADEBLANK.get());
        basicItem(ModItems.FORGED_SCRAP_INGOT.get());
        basicItem(ModItems.FORGED_SCRAP_DUST.get());
        basicItem(ModItems.FORGED_SCRAP.get());
    }
}
