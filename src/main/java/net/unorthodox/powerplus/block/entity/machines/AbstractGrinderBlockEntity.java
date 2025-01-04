package net.unorthodox.powerplus.block.entity.machines;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeCraftingHolder;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.unorthodox.powerplus.item.crafting.AbstractGrindingRecipe;
import org.jetbrains.annotations.ApiStatus;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;

public class AbstractGrinderBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer, RecipeCraftingHolder, StackedContentsCompatible {
    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_FUEL = 1;
    protected static final int SLOT_RESULT = 2;
    public static final int DATA_LIT_TIME = 0;
    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_DOWN = new int[]{2, 1};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1};
    public static final int DATA_LIT_DURATION = 1;
    public static final int DATA_COOKING_PROGRESS = 2;
    public static final int DATA_COOKING_TOTAL_TIME = 3;
    public static final int NUM_DATA_VALUES = 4;
    public static final int BURN_TIME_STANDARD = 200;
    public static final int BURN_COOL_SPEED = 2;

    private final RecipeType<? extends AbstractGrindingRecipe> recipeType;
    protected NonNullList<ItemStack> items;
    int litTime;
    int litDuration;
    int cookingProgress;
    int cookingTotalTime;
    @Nullable
    private static volatile Map<Item, Integer> fuelCache;
    protected final ContainerData dataAccess;
    private final Object2IntOpenHashMap<ResourceLocation> recipesUsed;
    private final RecipeManager.CachedCheck<SingleRecipeInput, ? extends AbstractGrindingRecipe> quickCheck;

    protected AbstractGrinderBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState, RecipeType<? extends AbstractGrindingRecipe> recipeType) {
        super(type, pos, blockState);
        this.items = NonNullList.withSize(3, ItemStack.EMPTY);
        this.dataAccess = new ContainerData() {
            public int get(int p_58431_) {
                switch (p_58431_) {
                    case 0:
                        if (AbstractGrinderBlockEntity.this.litDuration > 32767) {
                            return Mth.floor((double) AbstractGrinderBlockEntity.this.litTime / (double) AbstractGrinderBlockEntity.this.litDuration * (double)32767.0F);
                        }

                        return AbstractGrinderBlockEntity.this.litTime;
                    case 1:
                        return Math.min(AbstractGrinderBlockEntity.this.litDuration, 32767);
                    case 2:
                        return AbstractGrinderBlockEntity.this.cookingProgress;
                    case 3:
                        return AbstractGrinderBlockEntity.this.cookingTotalTime;
                    default:
                        return 0;
                }
            }

            public void set(int p_58433_, int p_58434_) {
                switch (p_58433_) {
                    case 0 -> AbstractGrinderBlockEntity.this.litTime = p_58434_;
                    case 1 -> AbstractGrinderBlockEntity.this.litDuration = p_58434_;
                    case 2 -> AbstractGrinderBlockEntity.this.cookingProgress = p_58434_;
                    case 3 -> AbstractGrinderBlockEntity.this.cookingTotalTime = p_58434_;
                }

            }

            public int getCount() {
                return 4;
            }
        };
        this.recipesUsed = new Object2IntOpenHashMap();
        this.quickCheck = RecipeManager.createCheck(recipeType);
        this.recipeType = recipeType;
    }

    public static void invalidateCache() {
        fuelCache = null;
    }

    /** @deprecated */
    @Deprecated
    public static Map<Item, Integer> getFuel() {
        Map<Item, Integer> map = fuelCache;
        if (map != null) {
            return map;
        } else {
            Map<Item, Integer> map1 = Maps.newLinkedHashMap();
            buildFuels((e, time) -> e.ifRight((tag) -> add(map1, tag, time)).ifLeft((item) -> add(map1, item, time)));
            fuelCache = map1;
            return map1;
        }
    }

    private static void add(ObjIntConsumer<Either<Item, TagKey<Item>>> consumer, ItemLike item, int time) {
        consumer.accept(Either.left(item.asItem()), time);
    }

    private static void add(ObjIntConsumer<Either<Item, TagKey<Item>>> consumer, TagKey<Item> tag, int time) {
        consumer.accept(Either.right(tag), time);
    }

    @ApiStatus.Internal
    public static void buildFuels(ObjIntConsumer<Either<Item, TagKey<Item>>> map1) {
        add(map1, Items.LAVA_BUCKET, 20000);
        add(map1, Blocks.COAL_BLOCK, 16000);
        add(map1, Items.BLAZE_ROD, 2400);
        add(map1, Items.COAL, 1600);
        add(map1, Items.CHARCOAL, 1600);
        add(map1, ItemTags.LOGS, 300);
        add(map1, ItemTags.BAMBOO_BLOCKS, 300);
        add(map1, ItemTags.PLANKS, 300);
        add(map1, Blocks.BAMBOO_MOSAIC, 300);
        add(map1, ItemTags.WOODEN_STAIRS, 300);
        add(map1, Blocks.BAMBOO_MOSAIC_STAIRS, 300);
        add(map1, ItemTags.WOODEN_SLABS, 150);
        add(map1, Blocks.BAMBOO_MOSAIC_SLAB, 150);
        add(map1, ItemTags.WOODEN_TRAPDOORS, 300);
        add(map1, ItemTags.WOODEN_PRESSURE_PLATES, 300);
        add(map1, ItemTags.WOODEN_FENCES, 300);
        add(map1, ItemTags.FENCE_GATES, 300);
        add(map1, Blocks.NOTE_BLOCK, 300);
        add(map1, Blocks.BOOKSHELF, 300);
        add(map1, Blocks.CHISELED_BOOKSHELF, 300);
        add(map1, Blocks.LECTERN, 300);
        add(map1, Blocks.JUKEBOX, 300);
        add(map1, Blocks.CHEST, 300);
        add(map1, Blocks.TRAPPED_CHEST, 300);
        add(map1, Blocks.CRAFTING_TABLE, 300);
        add(map1, Blocks.DAYLIGHT_DETECTOR, 300);
        add(map1, ItemTags.BANNERS, 300);
        add(map1, Items.BOW, 300);
        add(map1, Items.FISHING_ROD, 300);
        add(map1, Blocks.LADDER, 300);
        add(map1, ItemTags.SIGNS, 200);
        add(map1, ItemTags.HANGING_SIGNS, 800);
        add(map1, Items.WOODEN_SHOVEL, 200);
        add(map1, Items.WOODEN_SWORD, 200);
        add(map1, Items.WOODEN_HOE, 200);
        add(map1, Items.WOODEN_AXE, 200);
        add(map1, Items.WOODEN_PICKAXE, 200);
        add(map1, ItemTags.WOODEN_DOORS, 200);
        add(map1, ItemTags.BOATS, 1200);
        add(map1, ItemTags.WOOL, 100);
        add(map1, ItemTags.WOODEN_BUTTONS, 100);
        add(map1, Items.STICK, 100);
        add(map1, ItemTags.SAPLINGS, 100);
        add(map1, Items.BOWL, 100);
        add(map1, ItemTags.WOOL_CARPETS, 67);
        add(map1, Blocks.DRIED_KELP_BLOCK, 4001);
        add(map1, Items.CROSSBOW, 300);
        add(map1, Blocks.BAMBOO, 50);
        add(map1, Blocks.DEAD_BUSH, 100);
        add(map1, Blocks.SCAFFOLDING, 50);
        add(map1, Blocks.LOOM, 300);
        add(map1, Blocks.BARREL, 300);
        add(map1, Blocks.CARTOGRAPHY_TABLE, 300);
        add(map1, Blocks.FLETCHING_TABLE, 300);
        add(map1, Blocks.SMITHING_TABLE, 300);
        add(map1, Blocks.COMPOSTER, 300);
        add(map1, Blocks.AZALEA, 100);
        add(map1, Blocks.FLOWERING_AZALEA, 100);
        add(map1, Blocks.MANGROVE_ROOTS, 300);
    }

    private static boolean isNeverAFurnaceFuel(Item item) {
        return item.builtInRegistryHolder().is(ItemTags.NON_FLAMMABLE_WOOD);
    }

    private static void add(Map<Item, Integer> map, TagKey<Item> itemTag, int burnTime) {
        for(Holder<Item> holder : BuiltInRegistries.ITEM.getTagOrEmpty(itemTag)) {
            if (!isNeverAFurnaceFuel((Item)holder.value())) {
                map.put((Item)holder.value(), burnTime);
            }
        }

    }

    private static void add(Map<Item, Integer> map, ItemLike p_item, int burnTime) {
        Item item = p_item.asItem();
        if (isNeverAFurnaceFuel(item)) {
            if (SharedConstants.IS_RUNNING_IN_IDE) {
                throw (IllegalStateException) Util.pauseInIde(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.getName((ItemStack)null).getString() + " That will not work in the Grinder!"));
            }
        } else {
            map.put(item, burnTime);
        }

    }

    private boolean isLit() {
        return this.litTime > 0;
    }

    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(tag, this.items, registries);
        this.litTime = tag.getInt("BurnTime");
        this.cookingProgress = tag.getInt("CookTime");
        this.cookingTotalTime = tag.getInt("CookTimeTotal");
        this.litDuration = this.getBurnDuration((ItemStack)this.items.get(1));
        CompoundTag compoundtag = tag.getCompound("RecipesUsed");

        for(String s : compoundtag.getAllKeys()) {
            this.recipesUsed.put(ResourceLocation.parse(s), compoundtag.getInt(s));
        }

    }

    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("BurnTime", this.litTime);
        tag.putInt("CookTime", this.cookingProgress);
        tag.putInt("CookTimeTotal", this.cookingTotalTime);
        ContainerHelper.saveAllItems(tag, this.items, registries);
        CompoundTag compoundtag = new CompoundTag();
        this.recipesUsed.forEach((p_187449_, p_187450_) -> compoundtag.putInt(p_187449_.toString(), p_187450_));
        tag.put("RecipesUsed", compoundtag);
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, AbstractGrinderBlockEntity blockEntity) {
        boolean flag = blockEntity.isLit();
        boolean flag1 = false;
        if (blockEntity.isLit()) {
            --blockEntity.litTime;
        }

        ItemStack itemstack = (ItemStack)blockEntity.items.get(1);
        ItemStack itemstack1 = (ItemStack)blockEntity.items.get(0);
        boolean flag2 = !itemstack1.isEmpty();
        boolean flag3 = !itemstack.isEmpty();
        if (blockEntity.isLit() || flag3 && flag2) {
            RecipeHolder<?> recipeholder;
            if (flag2) {
                recipeholder = (RecipeHolder)blockEntity.quickCheck.getRecipeFor(new SingleRecipeInput(itemstack1), level).orElse((Object)null);
            } else {
                recipeholder = null;
            }

            int i = blockEntity.getMaxStackSize();
            if (!blockEntity.isLit() && canBurn(level.registryAccess(), recipeholder, blockEntity.items, i, blockEntity)) {
                blockEntity.litTime = blockEntity.getBurnDuration(itemstack);
                blockEntity.litDuration = blockEntity.litTime;
                if (blockEntity.isLit()) {
                    flag1 = true;
                    if (itemstack.hasCraftingRemainingItem()) {
                        blockEntity.items.set(1, itemstack.getCraftingRemainingItem());
                    } else if (flag3) {
                        Item item = itemstack.getItem();
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            blockEntity.items.set(1, itemstack.getCraftingRemainingItem());
                        }
                    }
                }
            }

            if (blockEntity.isLit() && canBurn(level.registryAccess(), recipeholder, blockEntity.items, i, blockEntity)) {
                ++blockEntity.cookingProgress;
                if (blockEntity.cookingProgress == blockEntity.cookingTotalTime) {
                    blockEntity.cookingProgress = 0;
                    blockEntity.cookingTotalTime = getTotalCookTime(level, blockEntity);
                    if (burn(level.registryAccess(), recipeholder, blockEntity.items, i, blockEntity)) {
                        blockEntity.setRecipeUsed(recipeholder);
                    }

                    flag1 = true;
                }
            } else {
                blockEntity.cookingProgress = 0;
            }
        } else if (blockEntity.cookingProgress > 0) {
            blockEntity.cookingProgress = Mth.clamp(blockEntity.cookingProgress - 2, 0, blockEntity.cookingTotalTime);
        }

        if (flag != blockEntity.isLit()) {
            flag1 = true;
            state = (BlockState)state.setValue(AbstractFurnaceBlock.LIT, blockEntity.isLit());
            level.setBlock(pos, state, 3);
        }

        if (flag1) {
            setChanged(level, pos, state);
        }

    }

    private static boolean canBurn(RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, AbstractGrinderBlockEntity furnace) {
        if (!((ItemStack)inventory.get(0)).isEmpty() && recipe != null) {
            ItemStack itemstack = ((AbstractCookingRecipe)recipe.value()).assemble(new SingleRecipeInput(furnace.getItem(0)), registryAccess);
            if (itemstack.isEmpty()) {
                return false;
            } else {
                ItemStack itemstack1 = (ItemStack)inventory.get(2);
                if (itemstack1.isEmpty()) {
                    return true;
                } else if (!ItemStack.isSameItemSameComponents(itemstack1, itemstack)) {
                    return false;
                } else {
                    return itemstack1.getCount() + itemstack.getCount() <= maxStackSize && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize() || itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize();
                }
            }
        } else {
            return false;
        }
    }

    private static boolean burn(RegistryAccess registryAccess, @Nullable RecipeHolder<?> recipe, NonNullList<ItemStack> inventory, int maxStackSize, AbstractGrinderBlockEntity furnace) {
        if (recipe != null && canBurn(registryAccess, recipe, inventory, maxStackSize, furnace)) {
            ItemStack itemstack = (ItemStack)inventory.get(0);
            ItemStack itemstack1 = ((AbstractCookingRecipe)recipe.value()).assemble(new SingleRecipeInput(furnace.getItem(0)), registryAccess);
            ItemStack itemstack2 = (ItemStack)inventory.get(2);
            if (itemstack2.isEmpty()) {
                inventory.set(2, itemstack1.copy());
            } else if (ItemStack.isSameItemSameComponents(itemstack2, itemstack1)) {
                itemstack2.grow(itemstack1.getCount());
            }

            if (itemstack.is(Blocks.WET_SPONGE.asItem()) && !((ItemStack)inventory.get(1)).isEmpty() && ((ItemStack)inventory.get(1)).is(Items.BUCKET)) {
                inventory.set(1, new ItemStack(Items.WATER_BUCKET));
            }

            itemstack.shrink(1);
            return true;
        } else {
            return false;
        }
    }

    protected int getBurnDuration(ItemStack fuel) {
        return fuel.isEmpty() ? 0 : fuel.getBurnTime(this.recipeType);
    }

    private static int getTotalCookTime(Level level, AbstractGrinderBlockEntity blockEntity) {
        SingleRecipeInput singlerecipeinput = new SingleRecipeInput(blockEntity.getItem(0));
        return (Integer)blockEntity.quickCheck.getRecipeFor(singlerecipeinput, level).map((p_300840_) -> ((AbstractGrindingRecipe)p_300840_.value()).getCookingTime()).orElse(200);
    }

    public static boolean isFuel(ItemStack stack) {
        return stack.getBurnTime(null) > 0;
    }

    public int[] getSlotsForFace(Direction side) {
        if (side == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return side == Direction.UP ? SLOTS_FOR_UP : SLOTS_FOR_SIDES;
        }
    }

    public boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return this.canPlaceItem(index, itemStack);
    }

    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return direction != Direction.DOWN || index != 1 || stack.is(Items.WATER_BUCKET) || stack.is(Items.BUCKET);
    }

    public int getContainerSize() {
        return this.items.size();
    }

    protected NonNullList<ItemStack> getItems() {
        return this.items;
    }

    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    public void setItem(int index, ItemStack stack) {
        ItemStack itemstack = (ItemStack)this.items.get(index);
        boolean flag = !stack.isEmpty() && ItemStack.isSameItemSameComponents(itemstack, stack);
        this.items.set(index, stack);
        stack.limitSize(this.getMaxStackSize(stack));
        if (index == 0 && !flag) {
            this.cookingTotalTime = getTotalCookTime(this.level, this);
            this.cookingProgress = 0;
            this.setChanged();
        }

    }

    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 2) {
            return false;
        } else if (index != 1) {
            return true;
        } else {
            ItemStack itemstack = (ItemStack)this.items.get(1);
            return stack.getBurnTime(this.recipeType) > 0 || stack.is(Items.BUCKET) && !itemstack.is(Items.BUCKET);
        }
    }

    public void setRecipeUsed(@Nullable RecipeHolder<?> recipe) {
        if (recipe != null) {
            ResourceLocation resourcelocation = recipe.id();
            this.recipesUsed.addTo(resourcelocation, 1);
        }

    }

    @Nullable
    public RecipeHolder<?> getRecipeUsed() {
        return null;
    }

    public void awardUsedRecipes(Player player, List<ItemStack> items) {
    }

    public void awardUsedRecipesAndPopExperience(ServerPlayer player) {
        List<RecipeHolder<?>> list = this.getRecipesToAwardAndPopExperience(player.serverLevel(), player.position());
        player.awardRecipes(list);

        for(RecipeHolder<?> recipeholder : list) {
            if (recipeholder != null) {
                player.triggerRecipeCrafted(recipeholder, this.items);
            }
        }

        this.recipesUsed.clear();
    }

    public List<RecipeHolder<?>> getRecipesToAwardAndPopExperience(ServerLevel level, Vec3 popVec) {
        List<RecipeHolder<?>> list = Lists.newArrayList();
        ObjectIterator var4 = this.recipesUsed.object2IntEntrySet().iterator();

        while(var4.hasNext()) {
            Object2IntMap.Entry<ResourceLocation> entry = (Object2IntMap.Entry)var4.next();
            level.getRecipeManager().byKey((ResourceLocation)entry.getKey()).ifPresent((p_300839_) -> {
                list.add(p_300839_);
                createExperience(level, popVec, entry.getIntValue(), ((AbstractCookingRecipe)p_300839_.value()).getExperience());
            });
        }

        return list;
    }

    private static void createExperience(ServerLevel level, Vec3 popVec, int recipeIndex, float experience) {
        int i = Mth.floor((float)recipeIndex * experience);
        float f = Mth.frac((float)recipeIndex * experience);
        if (f != 0.0F && Math.random() < (double)f) {
            ++i;
        }

        ExperienceOrb.award(level, popVec, i);
    }

    public void fillStackedContents(StackedContents helper) {
        for(ItemStack itemstack : this.items) {
            helper.accountStack(itemstack);
        }

    }
}
