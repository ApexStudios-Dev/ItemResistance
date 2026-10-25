package dev.apexstudios.itemresistance.mixin;

import dev.apexstudios.itemresistance.extensions.BlockExplosionExtension;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Block.class)
public abstract class BlockMixin implements BlockExplosionExtension {
}
