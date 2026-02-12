package dev.apexstudios.itemresistance;

import dev.apexstudios.itemresistance.mixin.ServerExplosionAccessor;
import dev.apexstudios.registree.Registree;
import dev.apexstudios.registree.holder.DeferredGameRule;
import dev.apexstudios.registree.registrar.GameRuleRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.BlockItemStateProperties;
import net.minecraft.world.level.ServerExplosion;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.ExplosionEvent;

@Mod(ItemResistance.ID)
public final class ItemResistance {
    public static final String ID = "itemresistance";
    public static final Registree REGISTREE = Registree.create(ID);
    public static final GameRuleRegistrar GAME_RULES = REGISTREE.gameRules();
    public static final DeferredGameRule<Boolean> ALLOW_EXPLOSION = GAME_RULES.registerBoolean("allow_explosions", GameRuleCategory.MISC, true);

    public ItemResistance(IEventBus modBus) {
        REGISTREE.registerEvents(modBus);

        NeoForge.EVENT_BUS.addListener(ExplosionEvent.Start.class, event -> {
            var level = (ServerLevel) event.getLevel();
            var gameRules = level.getGameRules();

            if(!gameRules.get(ALLOW_EXPLOSION.value()))
                event.setCanceled(true);
        });
    }

    public static boolean shouldExplode(BlockPos pos, ItemStack stack, ServerExplosion explosion) {
        var level = explosion.level();
        var block = Block.byItem(stack.getItem());
        var calculator = ((ServerExplosionAccessor) explosion).getDamageCalculator();
        var blockStateProperties = stack.getOrDefault(DataComponents.BLOCK_STATE, BlockItemStateProperties.EMPTY);
        var blockState = blockStateProperties.isEmpty() ? block.defaultBlockState() : blockStateProperties.apply(block.defaultBlockState());

        // similar logic as `ServerExplosion#calculateExplosionPositions` but for single point
        var power = explosion.radius() * (.7F + level.getRandom().nextFloat() * .6F);

        for(; power > 0F; power -= .22500001F) {
            var resistance = calculator.getBlockExplosionResistance(explosion, level, pos, blockState, level.getFluidState(pos));

            if(resistance.isPresent())
                power -= (resistance.get() + .3F) * .3F;
            if(power > 0F && calculator.shouldBlockExplode(explosion, level, pos, blockState, power))
                return true;
        }

        return false;
    }
}
