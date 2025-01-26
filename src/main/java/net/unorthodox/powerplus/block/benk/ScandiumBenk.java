package net.unorthodox.powerplus.block.benk;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.unorthodox.powerplus.block.base.benk.BenkBase;

import java.util.List;

public class ScandiumBenk extends BenkBase {
    public ScandiumBenk(Properties properties) {
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
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.scandiumbenk.tooltip.1"));
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.scandiumbenk.tooltip.2"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.scandiumbenk.tooltip.shift"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
