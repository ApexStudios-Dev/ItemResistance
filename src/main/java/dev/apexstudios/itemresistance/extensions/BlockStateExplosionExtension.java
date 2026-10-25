package dev.apexstudios.itemresistance.extensions;

import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;

public interface BlockStateExplosionExtension {
    /// Returns whether or not this block state is immune to explosions
    ///
    /// By default this your block is marked as immune if
    /// - It is tagged with [ItemResistance#EXPLOSION_IMMUNE]
    /// - [IBlockStateExtension#canDropFromExplosion(BlockGetter, BlockPos, Explosion)] returns false
    /// - Has high enough explosion resistance
    ///
    /// @param level The level this explosions resides inside of
    /// @param entity The item entity being exploded
    /// @param explosion The explosion
    /// @return Whether or not this block state is immune to explosions
    /// @apiNote This block state is extracted from the dropped item entity via [DataComponents#BLOCK_STATE]
    default boolean isImmuneToExplosion$ItemResistance(ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        return self().getBlock().isImmuneToExplosion$ItemResistance(self(), level, entity, explosion);
    }

    private BlockState self() {
        return (BlockState) this;
    }
}
