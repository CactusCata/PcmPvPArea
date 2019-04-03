package fr.m4z00t.pcmpvparea.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.m4z00t.pcmpvparea.utils.PlayerPcm;
import fr.m4z00t.pcmpvparea.utils.SetTab;

public class JoinGame implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();

		PlayerPcm playerPcm = new PlayerPcm(player);

		event.setJoinMessage(null);
		player.teleport(playerPcm.getArea().getSpawnLocation());
		player.setGameMode(GameMode.ADVENTURE);
		player.setLevel(0);
		player.setExp(0.0f);
		player.setFoodLevel(20);
		player.setHealth(20.0d);
		player.getActivePotionEffects().forEach(effect -> player.removePotionEffect(effect.getType()));
		player.getInventory().clear();
		player.getInventory().setArmorContents(new ItemStack[] { null, null, null, null });
		player.updateInventory();
		
		SetTab.sendtab(player);

	}

}
