package tehnut.goodybags;

import com.google.gson.GsonBuilder;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.registry.ItemRegistry;
import tehnut.goodybags.proxies.CommonProxy;
import tehnut.goodybags.registry.BagRegistry;
import tehnut.goodybags.util.EventHandler;
import tehnut.goodybags.util.LootGenerator;
import tehnut.goodybags.util.cache.PermanentCache;
import tehnut.goodybags.util.serialization.BagCreator;

import java.io.File;
import java.util.ArrayList;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.REQUIRED)
public class GoodyBags {

    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabBag = new CreativeTabs(ModInformation.ID + ".creativeTab") {
        @Override
        public ItemStack getIconItemStack() {
            return BagRegistry.getItemStackForNullBag();
        }

        @Override
        public Item getTabIconItem() {
            return ItemRegistry.bag;
        }
    };

    public static Logger logger = LogManager.getLogger(ModInformation.NAME);

    @Mod.Instance
    public static GoodyBags instance;
    private static PermanentCache<Bag> bagCache;

    private static File configDir;

    public static File getConfigDir() {
        return configDir;
    }

    public static PermanentCache<Bag> getBagCache() {
        return bagCache;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory() + File.pathSeparator + ModInformation.ID);
        configDir.mkdirs();

        bagCache = new PermanentCache<Bag>(ModInformation.ID + "Cache");

        ItemRegistry.registerItems();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.load();

        FMLCommonHandler.instance().bus().register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new EventHandler());

        BagRegistry.bagBuilder = new GsonBuilder();
        BagCreator.registerCustomSerializers(BagRegistry.bagBuilder);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        BagCreator.registerJsonBags(BagRegistry.bagBuilder);
        BagRegistry.setBagList(new ArrayList<Bag>(getBagCache().getEnumeratedObjects().valueCollection()));
        LootGenerator.generateLoot();
    }
}