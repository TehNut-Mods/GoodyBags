package tehnut.goodybags.util.serialization;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameData;
import org.apache.commons.io.filefilter.FileFilterUtils;
import tehnut.goodybags.GoodyBags;
import tehnut.goodybags.base.Bag;
import tehnut.goodybags.enums.BagType;
import tehnut.goodybags.util.BagBuilder;
import tehnut.goodybags.util.BagRegistry;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class BagCreator {

    public static void registerJsonBags(GsonBuilder gsonBuilder) {
        File folder = new File(GoodyBags.getConfigDir().getPath());

        File[] files = folder.listFiles((FileFilter) FileFilterUtils.suffixFileFilter(".json"));
        for (File file : files)
            BagRegistry.registerBag(BagCreator.createBagFromJson(gsonBuilder, file));
    }

    public static Bag createBagFromJson(GsonBuilder gsonBuilder, File file) {
        try {
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            return gson.fromJson(new FileReader(file), Bag.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void createJsonFromBag(GsonBuilder gsonBuilder, Bag bag) {
        try {
            Gson gson = gsonBuilder.setPrettyPrinting().create();
            String reverse = gson.toJson(bag, Bag.class);
            FileWriter fw = new FileWriter(new File(GoodyBags.getConfigDir().getPath(), bag.getName() + ".json"));
            fw.write(reverse);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registerCustomSerializers(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(ItemStack.class, new CustomItemStackJson());
        gsonBuilder.registerTypeAdapter(Bag.class, new CustomBagJson());
    }

    public static class CustomItemStackJson implements JsonDeserializer<ItemStack>, JsonSerializer<ItemStack> {

        @Override
        public ItemStack deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String name = json.getAsJsonObject().get("name").getAsString();
            int meta = json.getAsJsonObject().get("metadata").getAsInt();
            int size = json.getAsJsonObject().get("amount").getAsInt();

            return new ItemStack(GameData.getItemRegistry().getObject(name), size, meta);
        }

        @Override
        public JsonElement serialize(ItemStack src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", GameData.getItemRegistry().getNameForObject(src.getItem()).toString());
            jsonObject.addProperty("metadata", src.getItemDamage());
            jsonObject.addProperty("amount", src.stackSize);

            return jsonObject;
        }
    }

    public static class CustomBagJson implements JsonDeserializer<Bag>, JsonSerializer<Bag> {

        @Override
        public Bag deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String bagType = json.getAsJsonObject().get("bagType").getAsString().toUpperCase();
            String name = json.getAsJsonObject().get("name").getAsString();
            int chance = BagType.valueOf(bagType) == BagType.LOOT ? json.getAsJsonObject().get("lootChance").getAsInt() : 0;
            String rarity = json.getAsJsonObject().get("rarityType").getAsString().toUpperCase();
            List<ItemStack> stacks = context.deserialize(json.getAsJsonObject().get("stackList"), new TypeToken<List<ItemStack>>() {
            }.getType());

            BagBuilder builder = new BagBuilder();
            builder.setType(BagType.valueOf(bagType));
            builder.setName(name);
            builder.setChance(chance);
            builder.setRarity(EnumRarity.valueOf(rarity));
            builder.setStacks(stacks);

            return builder.build();
        }

        @Override
        public JsonElement serialize(Bag src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("bagType", context.serialize(src.getType()));
            jsonObject.add("name", context.serialize(src.getName()));
            if (src.getType() == BagType.LOOT)
                jsonObject.add("lootChance", context.serialize(src.getChance()));
            jsonObject.add("rarityType", context.serialize(src.getRarity()));
            jsonObject.add("stackList", context.serialize(src.getStacks()));
            return jsonObject;
        }
    }
}
