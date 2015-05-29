package tehnut.goodybags.util;

import com.google.gson.GsonBuilder;
import net.minecraft.item.ItemStack;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.items.ItemRegistry;

import java.util.ArrayList;
import java.util.List;

public class BagRegistry {

    public static GsonBuilder bagBuilder;
    private static List<Bag> bagList = new ArrayList<Bag>();

    public static void registerBag(Bag bag) {
        bagList.add(bag);
    }

    public static Bag getBag(int index) {
        return bagList.get(index);
    }

    public static int getIndexOf(Bag bag) {
        return bagList.indexOf(bag);
    }

    public static int getSize() {
        return bagList.size();
    }

    public static boolean isEmpty() {
        return bagList.isEmpty();
    }

    public static List<Bag> getBagList() {
        return new ArrayList<Bag>(bagList);
    }

    public static ItemStack getItemStackForBag(Bag bag) {
        return new ItemStack(ItemRegistry.bag, 1, getIndexOf(bag));
    }
}
