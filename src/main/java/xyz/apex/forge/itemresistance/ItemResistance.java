package xyz.apex.forge.itemresistance;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

import java.util.Iterator;

@Mod(ItemResistance.MOD_ID)
public class ItemResistance
{
	public static final String MOD_ID = "itemresistance";

	// tag to mark blocks as always exploding
	// if bedrock has this tag, tnt can blow it up in item form
	public static final Tag.Named<Block> FORCE_EXPLODE = tag("force_explode");
	// tag to mark blocks always resisting
	// if dirt has this tag, tnt can not blow it up in item form
	public static final Tag.Named<Block> FORCE_RESIST = tag("force_resist");

	public ItemResistance()
	{
		// register event listener for detonate events
		MinecraftForge.EVENT_BUS.addListener(this::onExplosionDetonate);
	}

	// wrapper method to create block tags
	private static Tag.Named<Block> tag(String name)
	{
		return BlockTags.createOptional(new ResourceLocation(MOD_ID, name));
	}

	public static float getExplosionSize(Explosion explosion)
	{
		// explosion size is private final field inside of
		// net.minecraft.world.Explosion
		// we use reflection to obtain the value of this field
		// ObfuscationReflectionHelper since Minecraft code is obfuscated outside of development
		// (maps human readable name to obfuscated name)
		Float explosionSize = ObfuscationReflectionHelper.getPrivateValue(Explosion.class, explosion, "f_46017_");
		// getPrivateValue() is marked nullable
		// simple null check to default to 0F
		return explosionSize == null ? 0F : explosionSize;
	}

	private void onExplosionDetonate(ExplosionEvent.Detonate event)
	{
		Level world = event.getWorld();
		Explosion explosion = event.getExplosion();
		float explosionSize = getExplosionSize(explosion);
		Iterator<Entity> itr = event.getAffectedEntities().iterator();

		// iterate over all entities
		while(itr.hasNext())
		{
			Entity entity = itr.next();

			// check if entity is dropped item stack
			if(entity instanceof ItemEntity itemEntity)
			{
				ItemStack stack = itemEntity.getItem();
				Item item = stack.getItem();

				// check if dropped item is a block
				if(item instanceof BlockItem blockItem)
				{
					Block block = blockItem.getBlock();

					// if block marked as always resisting
					// keep the exploded item
					if(FORCE_RESIST.contains(block))
					{
						itr.remove();
						continue;
					}

					// if block marked as always exploding
					// explode the item
					if(FORCE_EXPLODE.contains(block))
						continue;

					// if we get here, block does not have our custom tags
					// check for block resistance instead
					// we keep item if block would not normally blow up

					float resistance = block.getExplosionResistance();

					// calculate explosion size
					// same calculation as in
					// net.minecraft.world.Explosion#doExplosionA:L148
					float f = explosionSize * (.7F + world.random.nextFloat() * .6F);

					// decrement explosion size
					// by blocks explosion resistance amount
					// same calculation as in
					// same calculation as in net.minecraft.world.Explosion#doExplosionA:L159
					if(resistance > 0F)
						f -= (resistance + .3F) * .3f;

					// if explosion size is less than 0
					// that means this block can not be exploded
					// remove entity from list
					// of entities to be exploded
					if(f <= 0F)
						itr.remove();
				}
			}
		}
	}
}