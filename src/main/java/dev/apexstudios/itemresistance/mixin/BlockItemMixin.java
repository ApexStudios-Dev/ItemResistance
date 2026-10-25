package dev.apexstudios.itemresistance.mixin;

import dev.apexstudios.itemresistance.extensions.ItemExplosionExtension;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin implements ItemExplosionExtension {
    @Shadow
    @Final
    private Block block;

    @Override
    public boolean isImmuneToExplosion$ItemResistance(ItemStack stack, ServerLevel level, ItemEntity entity, ServerExplosion explosion) {
        if(ItemExplosionExtension.super.isImmuneToExplosion$ItemResistance(stack, level, entity, explosion)) {
            return true;
        }

        return stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY)
                .apply(block.defaultBlockState())
                .isImmuneToExplosion$ItemResistance(level, entity, explosion);
    }
}
