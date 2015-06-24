package tehnut.goodybags.base;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import tehnut.goodybags.enums.BagType;

import java.util.List;

public class Bag {

    @SerializedName("")
    private BagType type;
    private String name;
    private int chance;
    private EnumRarity rarity;
    private List<ItemStack> stacks;

    public Bag(BagType type, String name, int chance, EnumRarity rarity, List<ItemStack> stacks) {
        this.type = type;
        this.name = name;
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
