package fr.m4z00t.pcmpvparea.area;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Area {

	public abstract Location getSpawnLocation();
	
	public abstract String getName();
	
	public abstract ItemStack[] getArmor();
	
	public abstract Inventory getItems();

	public void teleportToSpawnLocation(Player player) {
		player.teleport(getSpawnLocation());
	}

}
