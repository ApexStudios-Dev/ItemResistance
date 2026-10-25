package dev.apexstudios.itemresistance.api;

import dev.apexstudios.itemresistance.extensions.BlockExplosionExtension;
import dev.apexstudios.itemresistance.extensions.ItemExplosionExtension;
import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holders.DeferredGameRule;
import dev.apexstudios.registree.api.util.GameRuleRegistrations;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;

public interface ItemResistance {
    String ID = "itemresistance";
    Registree REGISTREE = Registree.create(ID);
    DeferredGameRule<Boolean> ALLOW_EXPLOSIONS = GameRuleRegistrations.registerBoolean(REGISTREE, "allow_explosions", properties -> properties.defaultValue(true));
    BlockItemTagId EXPLOSION_IMMUNE = BlockItemTagId.create(REGISTREE.identifier("explosion_immune"), REGISTREE.identifier("explosion_immune"));

    /// Returns whether or not this itemstack is immune to explosions
    ///
    /// By default this your block is marked as immune if
    /// - It is tagged with [ItemResistance#EXPLOSION_IMMUNE]
    /// - Is a BlockItem and [#isImmuneToExplosion(BlockState, ServerLevel, ItemEntity, ServerExplosion)] returns true
    ///
    /// @param stack The itemstack being exploded
    /// @param level The level this explosions resides inside of
    /// @param entity The item entity being exploded
    /// @param explosion The explosion
    /// @return Whether or not this itemstack is immune to explosions
    /// @apiNote This itemstack is extracted from the dropped item entity
    static boolean isImmuneToExplosion(ItemStack stack, ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        if(stack.getItem() instanceof ItemExplosionExtension extension) {
            return extension.isImmuneToExplosion(stack, level, entity, explosion);
        }

        return ItemExplosionExtension.isImmuneToExplosionDefault(stack, level, entity, explosion);
    }

    /// Returns whether or not this block is immune to explosions
    ///
    /// By default this your block is marked as immune if
    /// - It is tagged with [ItemResistance#EXPLOSION_IMMUNE]
    /// - [IBlockStateExtension#canDropFromExplosion(BlockGetter, BlockPos, Explosion)] returns false
    /// - Has high enough explosion resistance
    ///
    /// @param blockState This block state
    /// @param level The level this explosions resides inside of
    /// @param entity The item entity being exploded
    /// @param explosion The explosion
    /// @return Whether or not this block state is immune to explosions
    /// @apiNote This block state is extracted from the dropped item entity via [DataComponents#BLOCK_STATE]
    static boolean isImmuneToExplosion(BlockState blockState, ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        if(blockState.getBlock() instanceof BlockExplosionExtension extension) {
            return extension.isImmuneToExplosion(blockState, level, entity, explosion);
        }

        return BlockExplosionExtension.isImmuneToExplosionDefault(blockState, level, entity, explosion);
    }
}
