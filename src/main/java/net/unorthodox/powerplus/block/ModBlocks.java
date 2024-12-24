package net.unorthodox.powerplus.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.benk.*;
import net.unorthodox.powerplus.block.thermo.*;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.unorthodox.powerplus.item.ModItems;

import java.util.function.Supplier;


public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(PowerPlus.MOD_ID);

        public static final DeferredBlock<Block> SCANDIUM_BLOCK = BLOCKS.register("scandium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> SAMARIUM_BLOCK = BLOCKS.register("samarium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> EUROPIUM_BLOCK = BLOCKS.register("europium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> CERIUM_BLOCK = BLOCKS.register("cerium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> TERBIUM_BLOCK = BLOCKS.register("terbium_block",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> BASIC_CASING = BLOCKS.register("basic_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> ADVANCED_CASING = BLOCKS.register("advanced_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> ELITE_CASING = BLOCKS.register("elite_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> SUPERIOR_CASING = BLOCKS.register("superior_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> LEGENDARY_CASING = BLOCKS.register("legendary_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> ASCENDANT_CASING = BLOCKS.register("ascendant_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> RADIANT_CASING = BLOCKS.register("radiant_casing",
                () -> new Block(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.METAL)));
        public static final DeferredBlock<Block> SCANDIUM_BENK = BLOCKS.register("scandium_benk",
                () -> new ScandiumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> SAMARIUM_BENK = BLOCKS.register("samarium_benk",
                () -> new SamariumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> EUROPIUM_BENK = BLOCKS.register("europium_benk",
                () -> new EuropiumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> CERIUM_BENK = BLOCKS.register("cerium_benk",
                () -> new CeriumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> TERBIUM_BENK = BLOCKS.register("terbium_benk",
                () -> new TerbiumBenk(BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops().sound(SoundType.LANTERN)));
        public static final DeferredBlock<Block> BASICTHERMOGENERATOR = BLOCKS.register("basicthermogenerator",
                () -> new BasicThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ADVANCEDTHERMOGENERATOR = BLOCKS.register("advancedthermogenerator",
                () -> new AdvancedThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ELITETHERMOGENERATOR = BLOCKS.register("elitethermogenerator",
                () -> new EliteThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> SUPERIORTHERMOGENERATOR = BLOCKS.register("superiorthermogenerator",
                () -> new SuperiorThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> LEGENDARYTHERMOGENERATOR = BLOCKS.register("legendarythermogenerator",
                () -> new LegendaryThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> ASCENDANTTHERMOGENERATOR = BLOCKS.register("ascendantthermogenerator",
                () -> new AscendantThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> RADIANTTHERMOGENERATOR = BLOCKS.register("radiantthermogenerator",
                () -> new RadiantThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));
        public static final DeferredBlock<Block> CREATIVETHERMOGENERATOR = BLOCKS.register("creativethermogenerator",
                () -> new CreativeThermoGenerator(BlockBehaviour.Properties.of().noCollission().requiresCorrectToolForDrops()));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    }


