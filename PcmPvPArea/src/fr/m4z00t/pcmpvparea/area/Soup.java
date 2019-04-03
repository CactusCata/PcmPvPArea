package fr.m4z00t.pcmpvparea.area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.m4z00t.pcmpvparea.utils.ItemBuilder;

public class Soup extends Area {

	@Override
	public Location getSpawnLocation() {
		return new Location(Bukkit.getWorlds().get(0), 100.0d, 50.0d, 100.0d, 0.0f, 5.0f);
	}

	@Override
	public String getName() {
		return "PvP Soup";
	}

	@Override
	public ItemStack[] getArmor() {
		return new ItemStack[] { new ItemBuilder(Material.IRON_HELMET).build(),
				new ItemBuilder(Material.IRON_CHESTPLATE).build(), 
				new ItemBuilder(Material.IRON_LEGGINGS).build(),
				new ItemBuilder(Material.IRON_BOOTS).build() };
	}

	@Override
	public Inventory getItems() {
		Inventory inventory = Bukkit.createInventory(null, 36);
		inventory.setItem(0, new ItemBuilder(Material.DIAMOND_SWORD).setUnbreakable()
				.addItemFlags(ItemFlag.HIDE_UNBREAKABLE).setDisplayName("§aSoup").build());
		ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP);
		for (int i = 1; i < 35; i++)
			inventory.setItem(i, soup);

		return inventory;
	}

}
