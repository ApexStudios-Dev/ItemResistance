package dev.apexstudios.itemresistance.data;

import dev.apexstudios.apexcore.lib.data.ProviderTypes;
import dev.apexstudios.apexcore.lib.data.ResourceGenerator;
import dev.apexstudios.itemresistance.ItemResistance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(value = ItemResistance.ID, dist = Dist.CLIENT)
public final class ItemResistanceDataEntryPoint {
    public ItemResistanceDataEntryPoint(IEventBus modBus) {
        ResourceGenerator.of(modBus, generator -> {
            generator.pack()
                    .providing(ProviderTypes.LANGUAGE, (context, provider) -> provider
                            .addGameRule(ItemResistance.ALLOW_EXPLOSION, "Allow Explosions", "Disables all explosions when disabled")
                    );
        });
    }
}
