package net.unorthodox.powerplus.block.benk;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.unorthodox.powerplus.item.ModItems;

import java.util.List;
import static net.unorthodox.powerplus.item.ModItems.*;


public class CeriumBenk extends Block {
    public CeriumBenk(Properties properties) {
        super(properties);
    }


    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity pEntity) {
       if(pEntity instanceof ItemEntity itemEntity) {
           if(isValidItem1(itemEntity.getItem())) {
               itemEntity.setItem(new ItemStack(ModItems.RAW_CERIUM.get(), itemEntity.getItem().getCount()));
           }
           if(isValidItem2(itemEntity.getItem())) {
               itemEntity.setItem(new ItemStack(ModItems.RAW_EUROPIUM.get(), itemEntity.getItem().getCount()));
           }
           if (isValidItem3(itemEntity.getItem())) {
               itemEntity.setItem(new ItemStack(RAW_SAMARIUM.get(), itemEntity.getItem().getCount()));
           }
           if (isValidItem4(itemEntity.getItem())) {
               itemEntity.setItem(new ItemStack(ModItems.RAW_SCANDIUM.get(), itemEntity.getItem().getCount()));
           }
           if (isValidItem5(itemEntity.getItem())) {
               itemEntity.setItem(new ItemStack(ModItems.FORGED_SCRAP_DUST.get(), itemEntity.getItem().getCount()));
           }
       }

        super.stepOn(level, pos, state, pEntity);
    }

    private boolean isValidItem1(ItemStack item) {
        return item.getItem() == Items.DIAMOND_BLOCK.asItem();
    }
    private boolean isValidItem2(ItemStack item) {
        return item.getItem() == Items.GOLD_BLOCK.asItem();
    }
    private boolean isValidItem3(ItemStack item) {
        return item.getItem() == Items.IRON_BLOCK.asItem();
    }
    private boolean isValidItem4(ItemStack item) {
        return item.getItem() == Items.COPPER_BLOCK.asItem();
    }
    private boolean isValidItem5(ItemStack item) { return item.getItem() == Items.REDSTONE_BLOCK.asItem();}

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
                    pTooltipComponents.add(Component.translatable("tooltip.powerplus.ceriumbenk.tooltip.1"));
                    pTooltipComponents.add(Component.translatable("tooltip.powerplus.ceriumbenk.tooltip.2"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.ceriumbenk.tooltip.shift"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
