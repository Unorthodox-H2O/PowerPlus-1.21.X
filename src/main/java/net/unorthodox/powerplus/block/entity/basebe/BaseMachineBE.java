package net.unorthodox.powerplus.block.entity.basebe;


import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.unorthodox.powerplus.util.AreaAffectingData;
import net.unorthodox.powerplus.util.RedstoneControlData;

import java.util.Map;

public class BaseMachineBE extends BlockEntity {
    public int MACHINE_SLOTS = 0;
    public int ANYSIZE_FILTER_SLOTS = 0;
    protected int direction = 0;
    protected int tickSpeed = 20;
    protected int operationTicks = -1;
    protected final Map<ChunkPos, Boolean> chunkTestCache = new Object2BooleanOpenHashMap<>();

    public BaseMachineBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public void tickClient() {
    }

    /*public void tickServer() {
        handleTicks();
        clearProtectionCache();
        if (this instanceof RedstoneControlledBE redstoneControlledBE)
            redstoneControlledBE.evaluateRedstone();
    }

     */

    public void clearProtectionCache() {
        chunkTestCache.clear();
    }

    public void handleTicks() {
        if (operationTicks <= 0)
            operationTicks = tickSpeed;
        operationTicks--;
    }

    public int getTickSpeed() {
        return tickSpeed;
    }

    public void setTickSpeed(int newTickSpeed) {
        this.tickSpeed = newTickSpeed;
        if (operationTicks > tickSpeed)
            operationTicks = tickSpeed;
        markDirtyClient();
    }
/*
    public boolean canRun() {
        if (this instanceof RedstoneControlledBE redstoneControlledBE)
            return operationTicks == 0 || redstoneControlledBE.getRedstoneControlData().redstoneMode.equals(MiscHelpers.RedstoneMode.PULSE);
        return operationTicks == 0;
    }

 */

    public int getDirection() {
        return direction;
    }

    public Direction getDirectionValue() {
        return Direction.values()[direction];
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        // Vanilla uses the type parameter to indicate which type of tile entity (command block, skull, or beacon?) is receiving the packet, but it seems like Forge has overridden this behavior
        return ClientboundBlockEntityDataPacket.create(this);
    }
/*
    public ItemStackHandler getMachineHandler() {
        return getData(Registration.MACHINE_HANDLER);
    }

 */

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        this.loadAdditional(tag, lookupProvider);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, provider);
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {
        super.onDataPacket(net, pkt, lookupProvider);
        if (this instanceof AreaAffectingBE areaAffectingBE)
            areaAffectingBE.getAreaAffectingData().area = null; //Clear this cache when a packet comes in, so it can redraw properly if the area was changed
    }

    public void markDirtyClient() {
        setChanged();
        if (level != null) {
            BlockState state = level.getBlockState(getBlockPos());
            level.sendBlockUpdated(getBlockPos(), state, state, 3);
        }
    }


    public RedstoneControlData getDefaultRedstoneData() {
        return new RedstoneControlData();
    }

    public AreaAffectingData getDefaultAreaData(AreaAffectingBE areaAffectingBE) {
        return areaAffectingBE.getDefaultAreaData(getBlockState().getValue(BlockStateProperties.FACING));
    }

    public boolean isDefaultSettings() {
        if (tickSpeed != 20)
            return false;
        if (direction != 0)
            return false;
        if (this instanceof AreaAffectingBE areaAffectingBE && !areaAffectingBE.getAreaAffectingData().equals(getDefaultAreaData(areaAffectingBE)))
            return false;

        return !(this instanceof PoweredMachineBE poweredMachineBE) || poweredMachineBE.getEnergyStored() <= 0;
       // if (this instanceof RedstoneControlledBE redstoneControlledBE && !redstoneControlledBE.getRedstoneControlData().equals(getDefaultRedstoneData()))
         //   return false;
    }

    @Override
    public void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.putInt("tickspeed", tickSpeed);
        tag.putInt("direction", direction);
        if (this instanceof AreaAffectingBE areaAffectingBE)
            areaAffectingBE.saveAreaSettings(tag);
       // if (this instanceof RedstoneControlledBE redstoneControlledBE)
         //   redstoneControlledBE.saveRedstoneSettings(tag);
    }

    @Override
    public void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        if (tag.contains("direction"))
            direction = tag.getInt("direction");
        if (tag.contains("tickspeed"))
            tickSpeed = tag.getInt("tickspeed");
        if (this instanceof AreaAffectingBE areaAffectingBE)
            areaAffectingBE.loadAreaSettings(tag);
      //  if (this instanceof RedstoneControlledBE redstoneControlledBE)
        //    redstoneControlledBE.loadRedstoneSettings(tag);
        super.loadAdditional(tag, provider);
    }
}
