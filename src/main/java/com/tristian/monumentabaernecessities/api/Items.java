package com.tristian.monumentabaernecessities.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.StringNbtReader;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.tristian.monumentabaernecessities.MonumentaBaerNecessities.LOGGER;

public class Items {


    private static final List<MonumentaItem> items;

    //    basically everything's really FUCKING SLOW.
    private static final HashMap<NbtCompound, MonumentaItem> nbts;


    static void addItem(MonumentaItem item) {
        items.add(item);
        try {
            NbtCompound nbt = StringNbtReader.parse(item.getNbt());
            nbt.remove("Damage"); // you can go fuck yourself
            nbts.put(nbt, item);
        } catch (CommandSyntaxException e) {
            throw new RuntimeException(e);
        }


    }


    public static List<MonumentaItem> getAllItems() {
        return items;
    }


//    private static final HashMap<NbtCompound, Optional<MonumentaItem>> memo = new HashMap<>(); // yes i am memoizing this lmao

    public static Optional<MonumentaItem> fromNbt(NbtCompound compound) {

        if (compound == null) return Optional.empty();
        NbtCompound replaced = compound.copy();
        replaced.remove("Damage");
        if (!(nbts.containsKey(replaced))) {
            LOGGER.info(replaced + " is not a thing.");
            return Optional.empty();
        }
        return Optional.of(nbts.get(replaced));
    }

    static {
        items = new CopyOnWriteArrayList<>();
        nbts = new HashMap<>();

    }

    public static void load() {

        LOGGER.info("Fetching items...");
        String res = ApiRequests.fetchResponse();
        Gson g = new Gson();
        JsonObject o = g.fromJson(res, JsonObject.class);
        o.asMap().forEach((k, v) -> addItem(ItemParser.decode(k, v.getAsJsonObject())));
        LOGGER.info("Successfully loaded " + items.size() + " items!");
    }

}
