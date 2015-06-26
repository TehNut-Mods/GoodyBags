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

    /**
     * To create a bag, use {@link BagBuilder}
     *
     * @param type      - Type of bag. Determines how the bag is handled.
     * @param name      - The name of the bag. Displays on tooltip.
     * @param customTip - Custom tooltip to display on the bag.
     * @param chance    - Chance for the bag to generate in a loot chest. Only needs to be set if the type is LOOT.
     * @param rarity    - Rarity of the bag. Determines the color of the item name. Affects nothing else.
     * @param stacks    - List of ItemStacks to give to the player when they right click.
     */
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
