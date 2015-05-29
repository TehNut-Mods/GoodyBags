package tehnut.goodybags.util;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import tehnut.goodybags.ModInformation;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.enums.BagType;

public class EventHandler {

    @SubscribeEvent
    public void onInitialJoin(EntityJoinWorldEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entity;
            NBTTagCompound tag = getModTag(player, ModInformation.ID);

            for (Bag bag : BagRegistry.getBagList()) {
                if (bag.getType() == BagType.SPAWN && !tag.getBoolean("hasInitial" + BagRegistry.getIndexOf(bag))) {
                    player.inventory.addItemStackToInventory(BagRegistry.getItemStackForBag(bag));
                    player.inventoryContainer.detectAndSendChanges();
                    tag.setBoolean("hasInitial" + BagRegistry.getIndexOf(bag), true);
                }
            }
        }
    }

    public NBTTagCompound getModTag(EntityPlayer player, String modName) {
        NBTTagCompound tag = player.getEntityData();
        NBTTagCompound persistTag;

        if (tag.hasKey(EntityPlayer.PERSISTED_NBT_TAG))
            persistTag = tag.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
        else {
            persistTag = new NBTTagCompound();
            tag.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistTag);
        }

        NBTTagCompound modTag;
        if (persistTag.hasKey(modName)) {
            modTag = persistTag.getCompoundTag(modName);
        } else {
            modTag = new NBTTagCompound();
            persistTag.setTag(modName, modTag);
        }

        return modTag;
    }
}
