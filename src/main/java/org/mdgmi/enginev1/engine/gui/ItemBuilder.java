package org.mdgmi.enginev1.engine.gui;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    private final ItemStack item;
    private final ItemMeta meta;
    private final JavaPlugin plugin;

    public ItemBuilder(
            JavaPlugin plugin,
            Material material
    ) {

        this.plugin = plugin;
        this.item = new ItemStack(material);
        this.meta = item.getItemMeta();

    }

    public static ItemBuilder paper(
            JavaPlugin plugin
    ) {

        return new ItemBuilder(
                plugin,
                Material.PAPER
        );

    }

    public ItemBuilder name(
            String name
    ) {

        meta.setDisplayName(name);

        return this;

    }

    public ItemBuilder lore(
            String... lore
    ) {

        List<String> list =
                new ArrayList<>();

        for (String line : lore) {

            list.add(line);

        }

        meta.setLore(list);

        return this;

    }

    public ItemBuilder tag(
            String key,
            String value
    ) {

        meta.getPersistentDataContainer().set(

                new NamespacedKey(
                        plugin,
                        key
                ),

                PersistentDataType.STRING,

                value

        );

        return this;

    }

    public ItemBuilder hideFlags() {

        meta.addItemFlags(
                ItemFlag.values()
        );

        return this;

    }

    public ItemStack build() {

        item.setItemMeta(meta);

        return item;

    }
    public ItemBuilder glow() {

        meta.addEnchant(
                Enchantment.UNBREAKING,
                1,
                true
        );

        meta.addItemFlags(
                ItemFlag.HIDE_ENCHANTS
        );

        return this;

    }
    public ItemBuilder amount(
            int amount
    ) {

        item.setAmount(amount);

        return this;

    }

    public ItemBuilder modelData(
            int model
    ) {

        meta.setCustomModelData(model);

        return this;

    }
    public ItemBuilder flag(
            ItemFlag... flags
    ) {

        meta.addItemFlags(flags);

        return this;

    }
    public static ItemBuilder head(
            JavaPlugin plugin,
            OfflinePlayer player
    ) {

        ItemBuilder builder =
                new ItemBuilder(
                        plugin,
                        Material.PLAYER_HEAD
                );

        SkullMeta meta =
                (SkullMeta) builder.meta;

        meta.setOwningPlayer(player);

        return builder;

    }

}