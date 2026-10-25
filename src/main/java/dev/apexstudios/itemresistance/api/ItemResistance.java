package dev.apexstudios.itemresistance.api;

import dev.apexstudios.registree.api.Registree;
import dev.apexstudios.registree.api.holders.DeferredGameRule;
import dev.apexstudios.registree.api.util.GameRuleRegistrations;
import net.minecraft.tags.BlockItemTagId;

public interface ItemResistance {
    String ID = "itemresistance";
    Registree REGISTREE = Registree.create(ID);
    DeferredGameRule<Boolean> ALLOW_EXPLOSIONS = GameRuleRegistrations.registerBoolean(REGISTREE, "allow_explosions", properties -> properties.defaultValue(true));
    BlockItemTagId EXPLOSION_IMMUNE = BlockItemTagId.create(REGISTREE.identifier("explosion_immune"), REGISTREE.identifier("explosion_immune"));
}
