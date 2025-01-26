package net.unorthodox.powerplus.item.upgrades.benk;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.unorthodox.powerplus.block.ModBlocks;

import java.util.List;
import java.util.Map;

public class UpgradeTier3Item extends Item {
    private static final Map<Block, Block> UPGRADETEIR3_MAP =
            Map.of(
                    ModBlocks.SAMARIUM_BENK.get(), ModBlocks.EUROPIUM_BENK.get()
            );

    public UpgradeTier3Item(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(UPGRADETEIR3_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), UPGRADETEIR3_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().shrink(1);

                //context.getItemInHand().shrink(1, ((ServerLevel) level), context.getPlayer(),
                       // item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
    @Override
    public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.upgradetier3.tooltip.1"));
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.upgradetier3.tooltip.2"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.powerplus.upgradetier3.tooltip.shift"));
        }

        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}