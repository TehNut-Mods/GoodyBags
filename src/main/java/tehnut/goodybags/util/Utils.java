package tehnut.goodybags.util;

import lombok.Getter;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.item.EnumRarity;
import tehnut.goodybags.ConfigHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    @Getter
    private static ArrayList<String> entityNames = new ArrayList<String>();

    @SuppressWarnings("unchecked")
    public static void setEntityList() {
        for (Map.Entry<Class, String> entry : ((HashMap<Class, String>) EntityList.classToStringMapping).entrySet()) {
            if (!entityNames.contains(entry.getValue())) {
                if (EntityLiving.class.isAssignableFrom(entry.getKey())) {
                    if (!IBossDisplayData.class.isAssignableFrom(entry.getKey()))
                        entityNames.add(entry.getValue());
                }
            }
        }
    }

    public static double getRateForRarity(EnumRarity rarity) {
        return ConfigHandler.rarityRates[rarity.ordinal()];
    }
}
