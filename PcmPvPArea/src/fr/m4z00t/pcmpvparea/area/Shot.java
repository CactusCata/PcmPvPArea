package fr.m4z00t.pcmpvparea.area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import fr.m4z00t.pcmpvparea.utils.ItemBuilder;

public class Shot extends Area {

	@Override
	public Location getSpawnLocation() {
		return new Location(Bukkit.getWorlds().get(0), 100.0d, 50.0d, 100.0d, 0.0f, 5.0f);
	}

	@Override
	public String getName() {
		return "PvP Shot";
	}

	@Override
	public ItemStack[] getArmor() {

		return new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
				new ItemStack(Material.AIR) };
	}

	@Override
	public Inventory getItems() {
		Inventory inventory = Bukkit.createInventory(null, 36);
		inventory.setItem(0,
				new ItemBuilder(Material.BOW).setUnbreakable().addEnchantement(Enchantment.ARROW_DAMAGE, 100)
						.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE).setDisplayName("§6Long Shot")
						.build());
		inventory.setItem(1, new ItemBuilder(Material.WOOD_AXE).setUnbreakable()
				.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE).setDisplayName("§6Axe Shot").build());

		inventory.setItem(8, new ItemBuilder(Material.ARROW).setDisplayName("§6Shot").build());

		return inventory;
	}

}
