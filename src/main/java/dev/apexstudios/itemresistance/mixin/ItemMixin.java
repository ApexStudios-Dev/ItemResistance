package dev.apexstudios.itemresistance.mixin;

import dev.apexstudios.itemresistance.extensions.ItemExplosionExtension;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemExplosionExtension {
}
