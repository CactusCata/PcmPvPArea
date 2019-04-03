package fr.m4z00t.pcmpvparea.area;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Hard extends Area {

	@Override
	public Location getSpawnLocation() {
		return new Location(Bukkit.getWorlds().get(0), 100.0d, 50.0d, 100.0d, 0.0f, 5.0f);
	}

	@Override
	public String getName() {
		return "PvP Hard";
	}

	@Override
	public ItemStack[] getArmor() {
		return null;
	}

	@Override
	public Inventory getItems() {

		return null;
	}

}
