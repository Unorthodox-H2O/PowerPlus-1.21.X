package net.unorthodox.powerplus.block.machines;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.unorthodox.powerplus.block.entity.ModBlockEntities;
import net.unorthodox.powerplus.block.entity.machines.GrinderBlockEntity;
import net.unorthodox.powerplus.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class Grinder extends BaseEntityBlock {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final MapCodec<Grinder> CODEC = simpleCodec(Grinder::new);

    public Grinder(Properties pProperties) {
        super (pProperties);
        
        
    }
    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
    
    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(2.0D, 0.0D, 2.0D, 14.0D, 10.0D, 14.0D); // Example dimensions for hitbox
    }

    /* FACING */
    @Override
    protected BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite()).setValue(LIT, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, LIT);
    }


    /* BLOCK ENTITY */

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new GrinderBlockEntity(pPos, pState);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    @Override
    public void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (pState.getBlock() != pNewState.getBlock()) {
            BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
            if (blockEntity instanceof GrinderBlockEntity GrinderBlockEntity) {
                GrinderBlockEntity.drops();
            }
        }

        super.onRemove(pState, pLevel, pPos, pNewState, pIsMoving);
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos,
                                              Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if (pLevel.isClientSide()) {
            return ItemInteractionResult.SUCCESS; // Skip logic on client side
        }

        if (pStack.is(ModItems.WRENCH.get()) && pPlayer != null) {
            if (pPlayer.isShiftKeyDown()) {
                // Safely drop the block and remove it
                Block.popResource(pLevel, pPos, new ItemStack(this));
                pLevel.removeBlock(pPos, false);
                return ItemInteractionResult.SUCCESS;
            }

            // Rotate with validation
            if (pState.hasProperty(FACING)) {
                pLevel.setBlockAndUpdate(pPos, pState.rotate(pLevel, pPos, Rotation.CLOCKWISE_90));
                return ItemInteractionResult.SUCCESS;
            } else {
                System.err.println("Error: Cannot rotate block state, FACING property missing.");
                return ItemInteractionResult.FAIL;
            }
        }

        BlockEntity entity = pLevel.getBlockEntity(pPos);
        if (entity instanceof GrinderBlockEntity grinderBlockEntity) {
            if (pPlayer instanceof ServerPlayer serverPlayer) {
                serverPlayer.openMenu(new SimpleMenuProvider(grinderBlockEntity, Component.literal("Grinder")), pPos);
            } else {
                System.err.println("Error: Player is not a ServerPlayer.");
            }
        } else {
            System.err.println("Error: Invalid or missing BlockEntity at " + pPos);
        }

        return ItemInteractionResult.sidedSuccess(pLevel.isClientSide());
    }



    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if(pLevel.isClientSide()) {
            return null;
        }

        return createTickerHelper(pBlockEntityType, ModBlockEntities.GRINDER_BE.get(),
                (pLevel1, pPos, pState1, pBlockEntity) -> pBlockEntity.tick(pLevel1, pPos, pState1));
    }

    /* LIT */

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (!state.getValue(LIT)) {
            return;
        }

        double xPos = (double)pos.getX() + 0.5;
        double yPos = pos.getY();
        double zPos = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.15) {
            level.playLocalSound(xPos, yPos, zPos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 1.0f, 1.0f, false);
        }

        Direction direction = state.getValue(FACING);
        Direction.Axis axis = direction.getAxis();

        double defaultOffset = random.nextDouble() * 0.6 - 0.3;
        double xOffsets = axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52 : defaultOffset;
        double yOffset = random.nextDouble() * 6.0 / 8.0;
        double zOffset = axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52 : defaultOffset;

        level.addParticle(ParticleTypes.SMOKE, xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);

        if(level.getBlockEntity(pos) instanceof GrinderBlockEntity GrinderBlockEntity && !GrinderBlockEntity.itemHandler.getStackInSlot(1).isEmpty()) {
            level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, GrinderBlockEntity.itemHandler.getStackInSlot(1)),
                    xPos + xOffsets, yPos + yOffset, zPos + zOffset, 0.0, 0.0, 0.0);
        }
    }
}