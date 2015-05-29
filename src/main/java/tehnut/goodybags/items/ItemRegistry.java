package tehnut.goodybags.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemRegistry {

    public static Item bag;

    public static void registerItems() {
        bag = new ItemBag();
        GameRegistry.registerItem(bag, "ItemGoodyBag");
    }
}
