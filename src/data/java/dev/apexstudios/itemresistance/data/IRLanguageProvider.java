package dev.apexstudios.itemresistance.data;

import dev.apexstudios.itemresistance.ItemResistance;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

final class IRLanguageProvider extends LanguageProvider {
    IRLanguageProvider(PackOutput output) {
        super(output, ItemResistance.ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        addGameRule(ItemResistance.ALLOW_EXPLOSION, "Allow Explosions", "Disables all explosions when disabled");
    }
}
