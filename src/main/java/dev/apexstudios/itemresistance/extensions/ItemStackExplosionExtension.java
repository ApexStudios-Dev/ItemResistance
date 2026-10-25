package dev.apexstudios.itemresistance.extensions;

import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ServerExplosion;

public interface ItemStackExplosionExtension {
    /// Returns whether or not this itemstack is immune to explosions
    ///
    /// By default this your block is marked as immune if
    /// - It is tagged with [ItemResistance#EXPLOSION_IMMUNE]
    /// - [IBlockStateExtension#canDropFromExplosion(BlockGetter , BlockPos , Explosion)] returns false
    /// - Has high enough explosion resistance
    ///
    /// @param level The level this explosions resides inside of
    /// @param entity The item entity being exploded
    /// @param explosion The explosion
    /// @return Whether or not this block state is immune to explosions
    /// @apiNote This itemstack is extracted from the dropped item entity
    default boolean isImmuneToExplosion$ItemResistance(ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        return self().getItem().isImmuneToExplosion$ItemResistance(self(), level, entity, explosion);
    }

    private ItemStack self() {
        return (ItemStack) this;
    }
}
