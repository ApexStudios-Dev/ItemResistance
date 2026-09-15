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
        var key = ItemResistance.ALLOW_EXPLOSION.value().getDescriptionId();
        add(key, "Allow Explosions");
        add(key + ".description", "Disables all explosions when disabled");
    }
}
