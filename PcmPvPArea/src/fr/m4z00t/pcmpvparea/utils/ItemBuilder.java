package fr.m4z00t.pcmpvparea.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * <p>
 * Cette classe qui fonctionne sous forme de buildPatern permet de créer
 * facilement des items.
 * </p>
 * 
 * @author CactusCata
 */

public final class ItemBuilder {

	private ItemStack item;
	private ItemMeta itemM;

	public ItemBuilder(Material material, int amount, byte damageValue) {
		this.item = new ItemStack(material, amount, damageValue);
		this.itemM = this.item.getItemMeta();
	}

	public ItemBuilder(Material material, int amount) {
		this(material, amount, (byte) 0);
	}

	public ItemBuilder(Material material, byte damageValue) {
		this(material, 1, damageValue);
	}

	public ItemBuilder(Material material) {
		this(material, 1, (byte) 0);
	}

	public static final ItemStack createSkull(ItemStack item, String playerName) {
		SkullMeta meta = (SkullMeta) Bukkit.getItemFactory().getItemMeta(Material.SKULL_ITEM);
		meta.setOwner(playerName);
		item.setItemMeta(meta);
		return item;
	}

	public final ItemBuilder color(int red, int green, int blue) {
		if (this.item.getType() != Material.LEATHER_BOOTS && this.item.getType() != Material.LEATHER_LEGGINGS
				&& this.item.getType() != Material.LEATHER_CHESTPLATE && this.item.getType() != Material.LEATHER_HELMET)
			throw new IllegalAccessError("L'item de base n'est pas de cuir !");
		((LeatherArmorMeta) this.itemM).setColor(Color.fromBGR(red, green, blue));

		return this;
	}

	public final ItemBuilder setLore(List<String> lore) {
		this.itemM.setLore(lore);
		return this;
	}

	public final ItemBuilder setDisplayName(String displayName) {
		this.itemM.setDisplayName(displayName);
		return this;
	}

	public final ItemBuilder setUnbreakable() {
		this.itemM.spigot().setUnbreakable(true);
		return this;
	}

	public final ItemBuilder addEnchantement(Enchantment enchentement, int level) {
		this.itemM.addEnchant(enchentement, level, true);
		return this;
	}

	public final ItemBuilder addItemFlags(ItemFlag... itemFlags) {
		this.itemM.addItemFlags(itemFlags);
		return this;
	}

	public final ItemBuilder setGlowing() {
		addEnchantement(Enchantment.DAMAGE_UNDEAD, 0);
		addItemFlags(ItemFlag.HIDE_ENCHANTS);
		return this;
	}

	public final ItemBuilder setGlowing(boolean wantGlowing) {
		if (wantGlowing)
			setGlowing();
		return this;
	}

	public final ItemStack build() {
		this.item.setItemMeta(this.itemM);
		return this.item;
	}

	public final CraftItemStack toCraftItemStack() {
		return CraftItemStack.asCraftCopy(build());
	}

	public final net.minecraft.server.v1_8_R3.ItemStack toNMSItemStack() {
		return CraftItemStack.asNMSCopy(this.build());
	}

}
