package tehnut.goodybags.registry;

import com.google.gson.GsonBuilder;
import net.minecraft.item.ItemStack;
import tehnut.goodybags.GoodyBags;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.registry.ItemRegistry;

import java.util.ArrayList;
import java.util.List;

public class BagRegistry {

    public static GsonBuilder bagBuilder;
    private static ArrayList<Bag> bagList;

    public static void registerBag(Bag bag) {
        try {
            GoodyBags.getBagCache().addObject(bag, bag.getName());
        } catch (IllegalArgumentException e) {
            GoodyBags.logger.error("Bag { " + bag.getName() + " } has been registered twice. Skipping the copy and continuing.");
        }
    }

    public static Bag getBag(int index) {
        return GoodyBags.getBagCache().getObject(index);
    }

    public static int getIndexOf(Bag bag) {
        return GoodyBags.getBagCache().getID(bag);
    }

    public static int getSize() {
        return getBagList().size();
    }

    public static boolean isEmpty() {
        return getBagList().isEmpty();
    }

    public static List<Bag> getBagList() {
        return new ArrayList<Bag>(bagList);
    }

    public static void setBagList(ArrayList<Bag> list) {
        bagList = list;
    }

    public static ItemStack getItemStackForBag(Bag bag) {
        return new ItemStack(ItemRegistry.bag, 1, getIndexOf(bag));
    }

    public static ItemStack getItemStackForNullBag() {
        return new ItemStack(ItemRegistry.bag, 1, Short.MAX_VALUE);
    }
}
