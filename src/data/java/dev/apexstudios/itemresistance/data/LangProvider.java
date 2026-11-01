package dev.apexstudios.itemresistance.data;

import dev.apexstudios.apexcore.api.data.LanguageProvider;
import dev.apexstudios.itemresistance.api.ItemResistance;
import net.minecraft.data.PackOutput;

final class LangProvider extends LanguageProvider {
    LangProvider(PackOutput output) {
        super(output, ItemResistance.ID);
    }

    @Override
    protected void addTranslations() {
        addTranslatableComponent(ItemResistanceDataEntryPoint.PACK_DESC, "ItemResistance resources");
        addGameRule(ItemResistance.ALLOW_EXPLOSIONS, "Allow Explosions", "Whether or not Explosions should be allowed");
        add(ItemResistance.EXPLOSION_IMMUNE, "Explosion Immune");
    }
}
