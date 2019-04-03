package fr.m4z00t.pcmpvparea.utils;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.cactuscata.pcmevent.enums.PrefixMessage;
import fr.cactuscata.pcmevent.utils.experience.ExperienceManager;
import fr.m4z00t.pcmpvparea.area.Area;
import fr.m4z00t.pcmpvparea.area.AreaType;
import fr.m4z00t.pcmpvparea.commands.staff.RankList;
import fr.m4z00t.pcmpvparea.utils.sql.Sql;

public class PlayerPcm {

	private static Map<Player, PlayerPcm> playersPcm = new HashMap<>();

	private Area area;
	private RankList rank;

	public PlayerPcm(Player player, Sql sql) {
		
		this.player = player;
		final UUID uuid = this.player.getUniqueId();
		this.sql = sql;
		if (!sql.hasAcccount(uuid)) {
			sql.createAccount(uuid, player.getName());
			this.staff = RankList.AUCUN;
			this.exp = new ExperienceManager(this, 1, 0);
			Bukkit.getOnlinePlayers().forEach(playerOnline -> playerOnline.sendMessage(
					PrefixMessage.PREFIX + "Veuillez souhaiter la bienvenue à §3" + player.getName() + "§e !"));
		} else {
			final ResultSet rs = this.sql.getAllInformation(uuid);
			this.staff = sql.getStaff(rs);
			this.exp = new ExperienceManager(this, sql.getLevel(rs), sql.getExperience(rs));
			if (staff.isStaff()) {
				this.vanishOnConnect = this.sql.isVanishOnConnect(rs);
				if (this.vanishOnConnect)
					setOnConnectVanish();
			}
		}

		this.setArea(AreaType.SPAWN);
		playersPcm.put(player, this);

	}

	public Area getArea() {
		return area;
	}

	public void setArea(AreaType area) {
		this.area = area.createArea();
	}

	public static Map<Player, PlayerPcm> getPlayersPcm() {
		return playersPcm;
	}

}
