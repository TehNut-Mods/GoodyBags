package tehnut.goodybags.base;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tehnut.goodybags.enums.BagType;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating Bags.
 * Documentation for each field can be found in {@link Bag}
 */
public class BagBuilder {

    private BagType type = BagType.PRIZE;
    private String name = type.toString() + " Bag";
    private String customTip = "";
    private int chance = 0;
    private EnumRarity rarity = EnumRarity.common;
    private List<ItemStack> stacks = new ArrayList<ItemStack>();
    private String[] mobs = { };

    public BagBuilder() {

    }

    public BagBuilder setType(BagType type) {
        this.type = type;
        return this;
    }

    public BagBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BagBuilder setCustomTip(String customTip) {
        this.customTip = customTip;
        return this;
    }

    public BagBuilder setChance(int chance) {
        this.chance = chance;
        return this;
    }

    public BagBuilder setRarity(EnumRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public BagBuilder setStacks(List<ItemStack> stacks) {
        this.stacks = stacks;
        return this;
    }

    public BagBuilder setMobs(String[] mobs) {
        this.mobs = mobs;
        return this;
    }

    public Bag build() {
        return new Bag(type, name, customTip, chance, rarity, stacks, mobs);
    }
}
