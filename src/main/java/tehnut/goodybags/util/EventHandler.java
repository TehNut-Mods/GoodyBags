package tehnut.goodybags.util;

import com.google.common.base.Strings;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import tehnut.goodybags.ModInformation;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.enums.BagType;
import tehnut.goodybags.registry.BagRegistry;

public class EventHandler {

    @SubscribeEvent
    public void onDrop(LivingDropsEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityLiving) {

            EntityLiving living = (EntityLiving) event.entity;
            World world = living.worldObj;

            bagSearch: {
                for (Bag bag : BagRegistry.getBagList()) {
                    if (bag.getType() == BagType.MOB) {
                        for (String mob : bag.getMobs()) {

                            String foundEnt = EntityList.getEntityString(living);

                            if (!Strings.isNullOrEmpty(foundEnt) && foundEnt.equalsIgnoreCase(mob)) {
                                if (world.rand.nextDouble() <= Utils.getRateForRarity(bag.getRarity()))
                                    event.drops.add(new EntityItem(world, living.posX, living.posY, living.posZ, BagRegistry.getItemStackForBag(bag)));

                                break bagSearch;
                            }
                        }
                    }
                }
            }
        }
    }

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
