package net.unorthodox.powerplus.block.base.benk;

import com.google.common.collect.Maps;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.unorthodox.powerplus.block.ModBlocks;
import net.unorthodox.powerplus.item.ModItems;

import java.util.Map;
import java.util.UUID;

public abstract class BenkBase extends Block {
    // Flags to determine which maps are used
    protected boolean useTier1 = false;
    protected boolean useTier2 = false;
    protected boolean useTier3 = false;
    protected boolean useTier4 = false;
    protected boolean useTier5 = false;

    // Map to track scheduled transformations
    private final Map<UUID, Long> scheduledTransformations = Maps.newHashMap();

    public BenkBase(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity pEntity) {
        // Check if the entity is an ItemEntity and this is on the server side
        if (pEntity instanceof ItemEntity itemEntity && !level.isClientSide()) {
            UUID itemUUID = itemEntity.getUUID();
            long currentTime = level.getGameTime();

            // If the item is not scheduled for transformation yet, schedule it
            if (!scheduledTransformations.containsKey(itemUUID) || scheduledTransformations.get(itemUUID) <= currentTime) {
                scheduledTransformations.put(itemUUID, currentTime + 20); // Delay by 1 second (20 ticks)

                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.scheduleTick(pos, this, 20); // Schedule a tick
                }
            }

            // Define the tier maps
            Map<Item, Item> tier1 = Map.of(
                    Items.COPPER_BLOCK.asItem(), ModItems.RAW_SCANDIUM.get(),
                    Items.REDSTONE_BLOCK.asItem(), ModItems.FORGED_SCRAP_DUST.get(),
                    Items.COAL_BLOCK.asItem(), ModItems.CINDERCOAL.get(),
                    ModBlocks.CHARCOALBLOCK.get().asItem(), ModItems.CINDERCOAL.get()
            );
            Map<Item, Item> tier2 = Map.of(
                    Items.IRON_BLOCK.asItem(), ModItems.RAW_SAMARIUM.get()
            );
            Map<Item, Item> tier3 = Map.of(
                    Items.GOLD_BLOCK.asItem(), ModItems.RAW_EUROPIUM.get()
            );
            Map<Item, Item> tier4 = Map.of(
                    Items.DIAMOND_BLOCK.asItem(), ModItems.RAW_CERIUM.get()
            );
            Map<Item, Item> tier5 = Map.of(
                    Items.NETHERITE_BLOCK.asItem(), ModItems.RAW_TERBIUM.get()
            );

            // Get the item from the ItemEntity
            Item item = itemEntity.getItem().getItem();

            // Process Tier1 if enabled
            if (useTier1 && tier1.containsKey(item)) {
                transformItem(level, itemEntity, tier1.get(item));
                return;
            }

            // Process Tier2 if enabled
            if (useTier2 && tier2.containsKey(item)) {
                transformItem(level, itemEntity, tier2.get(item));
                return;
            }

            // Process Tier3 if enabled
            if (useTier3 && tier3.containsKey(item)) {
                transformItem(level, itemEntity, tier3.get(item));
                return;
            }

            // Process Tier4 if enabled
            if (useTier4 && tier4.containsKey(item)) {
                transformItem(level, itemEntity, tier4.get(item));
                return;
            }

            // Process Tier5 if enabled
            if (useTier5 && tier5.containsKey(item)) {
                transformItem(level, itemEntity, tier5.get(item));
                return;
            }
        }

        // Default behavior from the parent class
        super.stepOn(level, pos, state, pEntity);
    }

    private void transformItem(Level level, ItemEntity itemEntity, Item transformedItem) {
        if (level instanceof ServerLevel serverLevel) {
            // Add smoke particles at the item's current position
            serverLevel.sendParticles(
                    ParticleTypes.POOF,                       // Particle type
                    itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), // Position
                    10,                                       // Count
                    0.1, 0.1, 0.1,                           // Offset for randomness
                    0.01                                      // Speed
            );
        }

        // Replace the item with the transformed item
        itemEntity.setItem(new ItemStack(
                transformedItem,
                itemEntity.getItem().getCount()
        ));
    }
}