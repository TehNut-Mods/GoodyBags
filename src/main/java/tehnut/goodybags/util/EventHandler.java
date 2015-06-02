package tehnut.goodybags.util;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
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
                    event.world.spawnEntityInWorld(new EntityItem(event.world, event.entity.posX, event.entity.posY, event.entity.posZ, BagRegistry.getItemStackForBag(bag)));
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
