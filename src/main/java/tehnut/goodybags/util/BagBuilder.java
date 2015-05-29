package tehnut.goodybags.util;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.enums.BagType;

import java.util.List;

public class BagBuilder {

    private BagType type;
    private String name;
    private int chance;
    private EnumRarity rarity;
    private List<ItemStack> stacks;

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

    public Bag build() {
        return new Bag(type, name, chance, rarity, stacks);
    }
}
