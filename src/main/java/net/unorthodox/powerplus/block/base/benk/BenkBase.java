package net.unorthodox.powerplus.block.base.benk;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.unorthodox.powerplus.item.ModItems;

public class BenkBase extends Block {
    public BenkBase(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity pEntity) {
        if (pEntity instanceof ItemEntity itemEntity) {
            startDissolve(level, pos, itemEntity);
        }
        super.stepOn(level, pos, state, pEntity);
    }

    private void startDissolve(Level level, BlockPos pos, ItemEntity itemEntity) {
            if (!level.isClientSide()) {
                if (isValidItem1(itemEntity.getItem())) {
                    itemEntity.setItem(new ItemStack(ModItems.RAW_SCANDIUM.get(), itemEntity.getItem().getCount()));
                }
                if (isValidItem2(itemEntity.getItem())) {
                    itemEntity.setItem(new ItemStack(ModItems.FORGED_SCRAP_DUST.get(), itemEntity.getItem().getCount()));
                }
            }
    }
    private boolean isValidItem1(ItemStack item) {
        return item.getItem() == Items.COPPER_BLOCK.asItem();
    }
    
    private boolean isValidItem2(ItemStack item) {
        return item.getItem() == Items.REDSTONE_BLOCK.asItem();
    }
}

