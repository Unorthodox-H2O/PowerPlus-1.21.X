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
import net.minecraft.world.level.block.state.BlockState;
import net.unorthodox.powerplus.block.base.benk.BenkBase;
import net.unorthodox.powerplus.item.ModItems;

import java.util.List;
import java.util.Map;

public class SamariumBenk extends BenkBase {
    public SamariumBenk(Properties properties) {
        super(properties);

        this.useTier1 = true;
        this.useTier2 = false;
        this.useTier3 = false;
        this.useTier4 = false;
        this.useTier5 = false;

}

    @Override
    public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.samariumbenk.tooltip.1"));
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.samariumbenk.tooltip.2"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.samariumbenk.tooltip.shift"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}