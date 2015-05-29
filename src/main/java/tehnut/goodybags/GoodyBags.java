package tehnut.goodybags;

import com.google.gson.GsonBuilder;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tehnut.goodybags.items.ItemRegistry;
import tehnut.goodybags.proxies.CommonProxy;
import tehnut.goodybags.util.BagRegistry;
import tehnut.goodybags.util.EventHandler;
import tehnut.goodybags.util.LootGenerator;
import tehnut.goodybags.util.serialization.BagCreator;

import java.io.File;

@Mod(modid = ModInformation.ID, name = ModInformation.NAME, version = ModInformation.VERSION, dependencies = ModInformation.REQUIRED)
public class GoodyBags {

    @SidedProxy(clientSide = ModInformation.CLIENTPROXY, serverSide = ModInformation.COMMONPROXY)
    public static CommonProxy proxy;

    public static CreativeTabs tabBag = new CreativeTabs(ModInformation.ID + ".creativeTab") {
        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(ItemRegistry.bag);
        }

        @Override
        public Item getTabIconItem() {
            return ItemRegistry.bag;
        }
    };

    @Mod.Instance
    public static GoodyBags instance;
    public static Configuration config;

    private static File configDir;

    public static File getConfigDir() {
        return configDir;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        configDir = new File(event.getModConfigurationDirectory() + "/" + ModInformation.ID);
        configDir.mkdirs();

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
        LootGenerator.generateLoot();
        proxy.initRenders();
    }
}