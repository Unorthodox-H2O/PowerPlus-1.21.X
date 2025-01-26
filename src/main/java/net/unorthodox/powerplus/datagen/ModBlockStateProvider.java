package net.unorthodox.powerplus.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PowerPlus.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SCANDIUM_BLOCK);
        blockWithItem(ModBlocks.SAMARIUM_BLOCK);
        blockWithItem(ModBlocks.EUROPIUM_BLOCK);
        blockWithItem(ModBlocks.CERIUM_BLOCK);
        blockWithItem(ModBlocks.TERBIUM_BLOCK);

        blockWithItem(ModBlocks.BASIC_CASING);
        blockWithItem(ModBlocks.ADVANCED_CASING);
        blockWithItem(ModBlocks.ELITE_CASING);
        blockWithItem(ModBlocks.SUPERIOR_CASING);
        blockWithItem(ModBlocks.LEGENDARY_CASING);
        blockWithItem(ModBlocks.ASCENDANT_CASING);
        blockWithItem(ModBlocks.RADIANT_CASING);

        blockWithItem(ModBlocks.CHARCOALBLOCK);

        blockWithItem(ModBlocks.SCANDIUM_BENK);
        blockWithItem(ModBlocks.SAMARIUM_BENK);
        blockWithItem(ModBlocks.EUROPIUM_BENK);
        blockWithItem(ModBlocks.CERIUM_BENK);
        blockWithItem(ModBlocks.TERBIUM_BENK);

        blockWithItem(ModBlocks.BASICCELL);
        blockWithItem(ModBlocks.ADVANCEDCELL);
        blockWithItem(ModBlocks.ELITECELL);
        blockWithItem(ModBlocks.SUPERIORCELL);
        blockWithItem(ModBlocks.LEGENDARYCELL);
        blockWithItem(ModBlocks.ASCENDANTCELL);
        blockWithItem(ModBlocks.CREATIVECELL);

    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
