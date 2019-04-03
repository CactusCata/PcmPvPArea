package fr.m4z00t.pcmpvparea.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.m4z00t.pcmpvparea.utils.PlayerPcm;

public class QuitGame implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		
		Player player = event.getPlayer();
		
		PlayerPcm.getPlayersPcm().remove(player);
		
	}
	
}
