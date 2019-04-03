package fr.m4z00t.pcmpvparea.area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.m4z00t.pcmpvparea.utils.ItemBuilder;

public class Combo extends Area {

	@Override
	public Location getSpawnLocation() {
		return new Location(Bukkit.getWorlds().get(0), 100.0d, 50.0d, 100.0d, 0.0f, 5.0f);
	}
	
	@Override
	public String getName() {
		return "PvP Combo";
	}

	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[] { new ItemBuilder(Material.DIAMOND_HELMET).addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 5).setUnbreakable().build(),
				new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 5).setUnbreakable().build(),
				new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 5).setUnbreakable().build(),
				new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantement(Enchantment.PROTECTION_ENVIRONMENTAL, 5).setUnbreakable().build() };
	}

	@Override
	public Inventory getItems() {
			Inventory inventory = Bukkit.createInventory(null, 36);
			inventory.setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setUnbreakable().addEnchantement(Enchantment.DAMAGE_ALL, 5).addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setDisplayName("§eCombo").build());
			inventory.setItem(1, new ItemBuilder(Material.GOLDEN_APPLE, 64, (byte) 1).build());
			return inventory;

	}

	
}
