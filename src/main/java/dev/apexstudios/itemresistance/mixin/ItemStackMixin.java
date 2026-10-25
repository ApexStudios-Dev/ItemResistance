package dev.apexstudios.itemresistance.mixin;

import dev.apexstudios.itemresistance.extensions.ItemStackExplosionExtension;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements ItemStackExplosionExtension {
}
