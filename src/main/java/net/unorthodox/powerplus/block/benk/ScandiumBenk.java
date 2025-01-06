package net.unorthodox.powerplus.block.benk;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.unorthodox.powerplus.block.ModBlocks;
import net.unorthodox.powerplus.item.ModItems;
import net.unorthodox.powerplus.util.ItemPair;



import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.unorthodox.powerplus.item.ModItems.RAW_SCANDIUM;

public class ScandiumBenk extends Block {

    public ScandiumBenk(Properties properties) {
          super(properties);
      }
    /*
             @Override
             public void stepOn(Level level, BlockPos pos, BlockState state, Entity pEntity) {
                 if (pEntity instanceof ItemEntity itemEntity) {
                     if (isValidItem1(itemEntity.getItem())) {
                         itemEntity.setItem(new ItemStack(ModItems.RAW_SCANDIUM.get(), itemEntity.getItem().getCount()));
                     }
                     if (isValidItem2(itemEntity.getItem())) {
                         itemEntity.setItem(new ItemStack(ModItems.FORGED_SCRAP_DUST.get(), itemEntity.getItem().getCount()));
                     }
                     if (isValidItem3(itemEntity.getItem())) {
                         itemEntity.setItem(new ItemStack(ModItems.IRON_DUST.get(), itemEntity.getItem().getCount()));
                     }
                 }

                 super.stepOn(level, pos, state, pEntity);
             }

             private boolean isValidItem1(ItemStack item) {return item.getItem() == Items.COPPER_BLOCK.asItem();}
             private boolean isValidItem2(ItemStack item) {return item.getItem() == Items.REDSTONE_BLOCK.asItem();}
             private boolean isValidItem3(ItemStack item) {return item.getItem() == Items.COAL.asItem() && Items.IRON_ORE.asItem();}
            */
// Improved transform handling
    private static final Map<Item, Item> singleInputOutput;
    private static final Map<ItemPair, Item> pairedInputOutput;

    static {
        // Single item transformation
        singleInputOutput = Map.of(
                Items.COPPER_BLOCK.asItem(), ModItems.SCANDIUM.get(),
                Items.REDSTONE_BLOCK.asItem(), ModItems.FORGED_SCRAP_DUST.get()
        );

        // Paired item transformation
        pairedInputOutput = Map.of(
                new ItemPair(Items.COAL.asItem(), Items.IRON_ORE.asItem()), ModItems.IRON_DUST.get()
        );
    }

    // Handle single-item transformations
    private static ItemStack getSingleOutput(ItemStack inputStack) {
        Item inputItem = inputStack.getItem();

        if (singleInputOutput.containsKey(inputItem)) {
            return new ItemStack(singleInputOutput.get(inputItem), inputStack.getCount());
        }

        return ItemStack.EMPTY;
    }

    // Handle paired transformations
    private static ItemStack getCombinationOutput(ItemStack input1, ItemStack input2) {
        ItemPair pairKey = new ItemPair(input1.getItem(), input2.getItem());

        if (pairedInputOutput.containsKey(pairKey)) {
            int outputCount = Math.min(input1.getCount(), input2.getCount());
            return new ItemStack(pairedInputOutput.get(pairKey), outputCount);
        }

        return ItemStack.EMPTY;
    }

    // Dynamic combination processing
    private void processCombination(Level level, BlockPos pos) {
        List<ItemEntity> itemsOnBlock = level.getEntitiesOfClass(ItemEntity.class, new AABB(pos).inflate(1.0));

        ItemEntity coal = null;
        ItemEntity ironOre = null;

        // Identify coal and iron ore entities
        for (ItemEntity entity : itemsOnBlock) {
            ItemStack stack = entity.getItem();
            if (stack.getItem() == Items.COAL) {
                coal = entity;
            } else if (stack.getItem() == Items.IRON_ORE) {
                ironOre = entity;
            }
        }

        // Handle pair transformation
        if (coal != null && ironOre != null) {
            int outputCount = Math.min(coal.getItem().getCount(), ironOre.getItem().getCount());

            // Remove input items
            coal.discard();
            ironOre.discard();

            // Spawn output item
            ItemEntity resultItem = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
                    new ItemStack(ModItems.IRON_DUST.get(), outputCount));
            level.addFreshEntity(resultItem);
        }
    }

    // Updated stepOn logic (usable for both single and pair transformations)
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack inputStack = itemEntity.getItem();

            // Attempt single transformation
            ItemStack singleOutput = getSingleOutput(inputStack);

            if (!singleOutput.isEmpty()) {
                itemEntity.setItem(singleOutput);
                return;
            }

            // Handle pair transformations dynamically
            processCombination(level, pos);
        }

        super.stepOn(level, pos, state, entity);
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
