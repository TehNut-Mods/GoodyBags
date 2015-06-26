package tehnut.goodybags.base;

import com.google.common.collect.Lists;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tehnut.goodybags.enums.BagType;

import java.util.List;

public class Bag {

    private BagType type;
    private String name;
    private String customTip;
    private int chance;
    private EnumRarity rarity;
    private List<ItemStack> stacks;

    protected Bag(BagType type, String name, String customTip, int chance, EnumRarity rarity, List<ItemStack> stacks) {
        this.type = type;
        this.name = name;
        this.customTip = customTip;
        this.chance = chance;
        this.rarity = rarity;
        this.stacks = stacks;
    }

    public BagType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getCustomTip() {
        return customTip;
    }

    public int getChance() {
        return chance;
    }

    public EnumRarity getRarity() {
        return rarity;
    }

    public List<ItemStack> getStacks() {
        List<ItemStack> ret = Lists.newArrayList();

        for (ItemStack stack : stacks)
            ret.add(stack.copy());

        return ret;
    }
}
