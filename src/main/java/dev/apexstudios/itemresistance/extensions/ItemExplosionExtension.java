package dev.apexstudios.itemresistance.extensions;

import com.google.errorprone.annotations.ForOverride;
import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public interface ItemExplosionExtension {
    /// @see ItemResistance#isImmuneToExplosion(ItemStack, ServerLevel, ItemEntity, ServerExplosion)
    @ForOverride
    default boolean isImmuneToExplosion(ItemStack stack, ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        return isImmuneToExplosionDefault(stack, level, entity, explosion);
    }

    static boolean isImmuneToExplosionDefault(ItemStack stack, ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        // tagged as explosion immune
        if(stack.is(ItemResistance.EXPLOSION_IMMUNE.item())) {
            return true;
        }

        var block = Block.byItem(stack.getItem());

        if(block != Blocks.AIR) {
            var blockState = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY)
                    .apply(block.defaultBlockState());

            return ItemResistance.isImmuneToExplosion(blockState, level, entity, explosion);
        }

        return false;
    }
}
