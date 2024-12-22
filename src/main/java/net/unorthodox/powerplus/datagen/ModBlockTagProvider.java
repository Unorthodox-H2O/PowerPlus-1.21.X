package net.unorthodox.powerplus.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.unorthodox.powerplus.block.ModBlocks;
import net.unorthodox.powerplus.util.ModTags;
import net.unorthodox.powerplus.PowerPlus;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PowerPlus.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                    .add(ModBlocks.SCANDIUM_BLOCK.get())
                    .add(ModBlocks.SAMARIUM_BLOCK.get())
                    .add(ModBlocks.EUROPIUM_BLOCK.get())
                    .add(ModBlocks.CERIUM_BLOCK.get())
                    .add(ModBlocks.TERBIUM_BLOCK.get());


            this.tag(BlockTags.NEEDS_IRON_TOOL)
                    .add(ModBlocks.SCANDIUM_BLOCK.get())
                    .add(ModBlocks.SAMARIUM_BLOCK.get());

            this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.EUROPIUM_BLOCK.get())
                .add(ModBlocks.CERIUM_BLOCK.get())
                .add(ModBlocks.TERBIUM_BLOCK.get());

    }
}