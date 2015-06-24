package tehnut.goodybags.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import tehnut.goodybags.items.ItemBag;

public class ItemRegistry {

    public static Item bag;

    public static void registerItems() {
        bag = new ItemBag();
        GameRegistry.registerItem(bag, "ItemGoodyBag");
    }
}
