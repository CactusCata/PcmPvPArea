package fr.m4z00t.pcmpvparea.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Weather implements Listener {

	@EventHandler
	public void clearWeather(WeatherChangeEvent event) {
		event.setCancelled(true);
	}

}
