package net.unorthodox.powerplus.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.unorthodox.powerplus.PowerPlus;
import net.unorthodox.powerplus.block.ModBlocks;
import net.unorthodox.powerplus.block.entity.machines.CrystalInfuserBlockEntity;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, PowerPlus.MOD_ID);

    public static final Supplier<BlockEntityType<CrystalInfuserBlockEntity>> CRYSTAL_INFUSER_BE =
            BLOCK_ENTITIES.register("crystal_infuser_be", () -> BlockEntityType.Builder.of(
                    CrystalInfuserBlockEntity::new, ModBlocks.CRYSTALINFUSER.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
