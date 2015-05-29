package tehnut.goodybags.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.enums.BagType;

public class LootGenerator {

    public static void generateLoot() {
        if (!BagRegistry.isEmpty()) {
            for (Bag bag : BagRegistry.getBagList()) {
                ItemStack stack = BagRegistry.getItemStackForBag(bag);

                if (bag.getType() == BagType.LOOT)
                    ChestGenHooks.addItem(ChestGenHooks.DUNGEON_CHEST, new WeightedRandomChestContent(stack, 1, 1, bag.getChance()));
            }
        }
    }
}
