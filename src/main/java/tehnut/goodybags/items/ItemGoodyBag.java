package tehnut.goodybags.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import tehnut.goodybags.GoodyBags;
import tehnut.goodybags.ModInformation;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.enums.BagType;
import tehnut.goodybags.util.BagRegistry;

import java.util.List;

public class ItemGoodyBag extends Item {

    public ItemGoodyBag() {
        setUnlocalizedName(ModInformation.ID);
        setCreativeTab(GoodyBags.tabBag);
        setMaxStackSize(1);
        setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        if (isValidBag(stack)) {
            Bag bag = BagRegistry.getBag(stack.getItemDamage());
            return String.format(StatCollector.translateToLocal("item.GoodyBags.bag.name"), StatCollector.translateToLocal("info.GoodyBags." + bag.getType().toString()));
        } else {
            return String.format(StatCollector.translateToLocal("item.GoodyBags.bag.name"), StatCollector.translateToLocal("info.GoodyBags.null"));
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && isValidBag(stack)) {
            player.inventory.decrStackSize(player.inventory.currentItem, 1);

            for (ItemStack goodyStack : BagRegistry.getBag(stack.getItemDamage()).getStacks())
                if (goodyStack != null)
                    world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, goodyStack));
        }

        return stack;
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(Item item, CreativeTabs par2CreativeTabs, List list) {
        if (!BagRegistry.isEmpty())
            for (int i = 0; i < BagRegistry.getSize(); i++)
                list.add(new ItemStack(this, 1, i));

        list.add(new ItemStack(this, 1, BagRegistry.getSize() + 1));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int pass) {
        if (isValidBag(stack))
            return BagRegistry.getBag(stack.getItemDamage()).getType().getBagColor().getRGB();
        else
            return super.getColorFromItemStack(stack, pass);
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean held) {

        if (isValidBag(stack)) {
            Bag bag = BagRegistry.getBag(stack.getItemDamage());
            String customUnloc = "type.GoodyBags.bag." + bag.getName();

            list.add(bag.getType().getTextColor() + (StatCollector.canTranslate(customUnloc) ? StatCollector.translateToLocal(customUnloc) : bag.getName()));

            if (bag.getType() == BagType.SPAWN || bag.getType() == BagType.PRIZE) {
                if (isShiftDown()) {
                    list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("info.GoodyBags.contents"));
                    for (ItemStack giveStack : bag.getStacks())
                        list.add(String.format(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("info.GoodyBags.stacks"), giveStack.stackSize, giveStack.getDisplayName()));
                } else {
                    list.add(StatCollector.translateToLocal("info.GoodyBags.hold"));
                }
            }

        } else {
            list.add(EnumChatFormatting.RED + StatCollector.translateToLocal("info.GoodyBags.warning"));
        }
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        if (isValidBag(stack))
            return BagRegistry.getBag(stack.getItemDamage()).getRarity();

        return EnumRarity.COMMON;
    }

    public static boolean isValidBag(ItemStack stack) {
        return !BagRegistry.isEmpty() && !(BagRegistry.getSize() < stack.getItemDamage());
    }

    public static boolean isShiftDown() {
        return Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT);
    }
}
