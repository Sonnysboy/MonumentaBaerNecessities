package com.tristian.monumentabaernecessities.items;

import com.tristian.monumentabaernecessities.MonumentaBaerNecessities;
import com.tristian.monumentabaernecessities.utils.CharmUtils;
import com.tristian.monumentabaernecessities.utils.NbtUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a zenith charm.
 * Zenith charm nbt looks something like:
 * <pre>
 * {
 *   "Monumenta": {
 *     "PlayerModified": {
 *       "DEPTHS_CHARM_WILDCARD_TREE_CAP": 6,
 *       "DEPTHS_CHARM_ACTIONS5": "Uncommon",
 *       "DEPTHS_CHARM_ACTIONS4": "Common",
 *       "DEPTHS_CHARM_ACTIONS3": "Uncommon",
 *       "DEPTHS_CHARM_ACTIONS2": "Uncommon",
 *       "DEPTHS_CHARM_ACTIONS1": "Uncommon",
 *       "DEPTHS_CHARM_UUID": 992618503725597927,
 *       "DEPTHS_CHARM_ACTIONS8": "Uncommon",
 *       "DEPTHS_CHARM_ACTIONS7": "Common",
 *       "DEPTHS_CHARM_ACTIONS6": "Common",
 *       "DEPTHS_CHARM_EFFECT6": "Sidearm Cooldown",
 *       "DEPTHS_CHARM_ROLLS9": 0.631627300959164,
 *       "DEPTHS_CHARM_EFFECT7": "Chaos Dagger Damage Multiplier",
 *       "DEPTHS_CHARM_EFFECT4": "Flamestrike Cooldown",
 *       "DEPTHS_CHARM_ROLLS7": 0.7973521438978883,
 *       "DEPTHS_CHARM_EFFECT5": "Piercing Cold Damage",
 *       "DEPTHS_CHARM_ROLLS8": 0.03702835610324129,
 *       "DEPTHS_CHARM_EFFECT2": "Crushing Earth Stun Duration",
 *       "DEPTHS_CHARM_EFFECT3": "Rapid Fire Damage",
 *       "DEPTHS_CHARM_EFFECT1": "Guarding Bolt Radius",
 *       "DEPTHS_CHARM_ROLLS1": 0.30899883741935474,
 *       "DEPTHS_CHARM_ROLLS2": 0.8233654682086198,
 *       "DEPTHS_CHARM_ROLLS5": 0.887193021250557,
 *       "DEPTHS_CHARM_ROLLS6": 0.2580332858696379,
 *       "DEPTHS_CHARM_ROLLS3": 0.8436768344092801,
 *       "DEPTHS_CHARM_ROLLS4": 0.24710170042104895,
 *       "DEPTHS_CHARM_RARITY": 1,
 *       "DEPTHS_CHARM_EFFECT8": "Shadow Slam Damage",
 *       "DEPTHS_CHARM_EFFECT9": "Blade Flurry Silence Duration"
 *     },
 *     "Tier": "zenithcharm",
 *     "CharmPower": 5,
 *     "Region": "ring",
 *     "CharmText": [
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"+0.66 Crushing Earth Stun Duration\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"-13.03% Flamestrike Cooldown\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+18% Piercing Cold Damage\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"+0.82 Blade Flurry Silence Duration\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+16% Chaos Dagger Damage Multiplier\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+1% Shadow Slam Damage\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"+22% Rapid Fire Damage\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"-12.9% Sidearm Cooldown\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+16% Guarding Bolt Radius\"}"
 *     ],
 *     "Location": "zenith"
 *   },
 *   "HideFlags": 1,
 *   "plain": {
 *     "display": {
 *       "Lore": [
 *         "Architect's Ring : Zenith Charm",
 *         "Charm Power :  - Common",
 *         "The Celestial Zenith",
 *         "",
 *         "When in Charm Slot:",
 *         "+0.66 Crushing Earth Stun Duration",
 *         "-13.03% Flamestrike Cooldown",
 *         "+18% Piercing Cold Damage",
 *         "+0.82 Blade Flurry Silence Duration",
 *         "+16% Chaos Dagger Damage Multiplier",
 *         "+1% Shadow Slam Damage",
 *         "+22% Rapid Fire Damage",
 *         "-12.9% Sidearm Cooldown",
 *         "+16% Guarding Bolt Radius"
 *       ],
 *       "Name": "Winged Rose of Serendipity"
 *     }
 *   },
 *   "Enchantments": [
 *     {
 *       "lvl": 1,
 *       "id": "minecraft:power"
 *     }
 *   ],
 *   "display": {
 *     "Lore": [
 *       "{\"italic\":false,\"color\":\"dark_gray\",\"extra\":[{\"italic\":false,\"color\":\"#FF9CF0\",\"text\":\"Zenith Charm\"}],\"text\":\"Architect\'s Ring : \"}",
 *       "{\"italic\":false,\"color\":\"dark_gray\",\"extra\":[{\"italic\":false,\"color\":\"#FFFA75\",\"text\":\"★★★★★\"},{\"italic\":false,\"color\":\"dark_gray\",\"text\":\" - \"},{\"italic\":false,\"obfuscated\":false,\"color\":\"#9F929C\",\"text\":\"Common\"}],\"text\":\"Charm Power : \"}",
 *       "{\"italic\":false,\"color\":\"#FF9CF0\",\"text\":\"The Celestial Zenith\"}",
 *       "{\"text\":\"\"}",
 *       "{\"italic\":false,\"color\":\"gray\",\"text\":\"When in Charm Slot:\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"+0.66 Crushing Earth Stun Duration\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"-13.03% Flamestrike Cooldown\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+18% Piercing Cold Damage\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"+0.82 Blade Flurry Silence Duration\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+16% Chaos Dagger Damage Multiplier\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+1% Shadow Slam Damage\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"+22% Rapid Fire Damage\"}",
 *       "{\"italic\":false,\"color\":\"#70BC6D\",\"text\":\"-12.9% Sidearm Cooldown\"}",
 *       "{\"italic\":false,\"color\":\"#9F929C\",\"text\":\"+16% Guarding Bolt Radius\"}"
 *     ],
 *     "Name": "{\"bold\":true,\"italic\":false,\"underlined\":false,\"color\":\"#9F929C\",\"text\":\"Winged Rose of Serendipity\"}"
 *   }
 * }
 * </pre>
 */
public class ZenithCharm {

    private final ItemStack item;
    private final CharmData data;

    //    as in charmpower
    private final int power;


    private ZenithCharm(ItemStack item, CharmData data, int power) {
        this.item = item;
        this.data = data;
        this.power = power;
    }


    /** Parses an item into a ZenithCharm instance.
     *
     * @param item The item to parse the charm of
     * @return A ZenithCharm instance, or <code>Optional.empty()</code> if anything goes wrong. (i.e not a charm).
     */
    public static Optional<ZenithCharm> fromItem(ItemStack item) {
        if (!CharmUtils.isZenithCharm(item)) return Optional.empty(); // obviously if it's not a charm we shouldn't return anything
        return NbtUtils.getMonumentaDataFromItem(item)
                .map(x -> {
                    final int count = countRolls(item); // roll amount
                    return new ZenithCharm(item, CharmData.parse(rollDataFromNbt(x), count), x.getInt("CharmPower"));
                });
    }

    /**
     * Returns the "plain"."display".Lore list
     * I have no idea if this is deafult in later versions so im not gonna make it like a regular method i use
     *
     * @param item The item to get
     * @return see above.
     */
    private static Optional<List<String>> plainLore(ItemStack item) {
        return NbtUtils.getNbt(item) // { plain : { display : { Lore : {[
                .map(x -> x.getCompound("plain")) // { display : { Lore : {]
                .map(x -> x.getCompound("display")) // { Lore : {[
                .map(x -> x.getList("Lore", 8)) // values.
                .map(x -> x.stream().map(NbtElement::asString).collect(Collectors.toList()));

    }

    // roll count of an item, or 0 if none.
    private static int countRolls(ItemStack item) {
        return plainLore(item).map(x -> x.size() - 5).orElse(0);
    }

    // gets the actual stats of the rolls
    private static NbtCompound rollDataFromNbt(NbtCompound nbt) {
        return nbt.getCompound("PlayerModified");
    }


    public CharmData getData() {
        return data;
    }

    public int getPower() {
        return power;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "ZenithCharm{" +
                "item=" + item +
                ", data=" + data +
                ", power=" + power +
                '}';
    }
    public static class CharmData {
        private final long uuid;
        private final CharmRolls rolls;

        private CharmData(long uuid, CharmRolls rolls) {
            this.uuid = uuid;
            this.rolls = rolls;
        }

        public static CharmData parse(NbtCompound charmData, int rollCount) {
            return new CharmData(charmData.getLong("DEPTHS_CHARM_UUID"), CharmRolls.parseNbtCompound(charmData, rollCount));
        }

        /**
         *
         * @return The actual rolls, i.e. the stats the charm
         */
        public CharmRolls getRolls() {
            return rolls;
        }

        /**
         *
         * @return The uuid of the charm.
         */
        public long getUuid() {
            return uuid;
        }
        @Override
        public String toString() {
            return "CharmData{" +
                    "uuid=" + uuid +
                    ", rolls=" + rolls +
                    '}';
        }
    }
    public static class CharmRolls extends HashMap<String, Double> {

        /**
         * Charm rolls are 1-indexed on monu.
         *
         * @param charmData The data to parse out
         * @param amount    The amount of things to get
         * @return The rolls of the charm as an object.
         */
        public static CharmRolls parseNbtCompound(NbtCompound charmData, int amount) {
            CharmRolls rolls = new CharmRolls();
            for (; amount >= 1; amount--) {
                if (MonumentaBaerNecessities.options.debugOptionsEnabled) {
                    MinecraftClient.getInstance().player.sendMessage(Text.of(charmData.getString("DEPTHS_CHARM_EFFECT"+amount) + ":" + charmData.get("DEPTHS_CHARM_ROLLS"+amount)));
                }
                rolls.put(charmData.getString("DEPTHS_CHARM_EFFECT" + amount),
                        charmData.getDouble("DEPTHS_CHARM_ROLLS" + amount));
            }
            return rolls;
        }
    }

}
