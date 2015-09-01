package tehnut.goodybags;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {

    public static Configuration config;

    // Settings
    private static double[] rarityRatesDefault = { 0.8, 0.5, 0.3, 0.1 };
    public static Double[] rarityRates = new Double[4];

    public static void init(File file) {
        config = new Configuration(file);
        syncConfig();
    }

    public static void syncConfig() {
        for (int i = 0; i < rarityRates.length; i++) {
            String rarityName = EnumRarity.values()[i].rarityName;
            rarityRates[i] = config.get("Rarity Settings", "rarityRate" + rarityName, rarityRatesDefault[i], "Drop chance of a Mob Bag for " + rarityName + " bags.").getDouble();
        }

        config.save();
    }
}
