package tehnut.goodybags.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tehnut.goodybags.util.InventoryRender;

public class ItemRegistry {

    public static Item bag;

    public static void registerItems() {
        bag = new ItemGoodyBag();
        GameRegistry.registerItem(bag, "ItemGoodyBag");
    }

    public static void registerRenders() {
        InventoryRender.inventoryItemRenderAll(bag);
    }
}
