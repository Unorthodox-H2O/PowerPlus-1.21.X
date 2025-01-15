package net.unorthodox.powerplus.api.base;

import net.unorthodox.powerplus.api.base.Use;
import net.minecraft.core.Direction;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.neoforged.fml.LogicalSide;
import org.jetbrains.annotations.Nullable;


public interface Wrenchable {
    @Use(LogicalSide.SERVER)
    ItemInteractionResult onWrenched(@Nullable Player player, @Nullable Direction side);
}
