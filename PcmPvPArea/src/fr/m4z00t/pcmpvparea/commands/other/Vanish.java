package fr.m4z00t.pcmpvparea.commands.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.cactuscata.pcmevent.command.staff.RankList;
import fr.m4z00t.pcmpvparea.commands.CCommandExecutor;
import fr.m4z00t.pcmpvparea.utils.PlayerPcm;
import fr.m4z00t.pcmpvparea.utils.PrefixMessage;
import fr.m4z00t.pcmpvparea.utils.Reflections;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

/**
 * <p>
 * Cette classe permet l'éxecution de la commande /vanish, cette dernière permet
 * à un membre du staff du monde event d'être invisible, les personnes qui
 * pourront voir la personne en vanish est determiné via
 * {@link RankList#isStaff()}.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 */

public final class Vanish extends CCommandExecutor {

	private final Plugin plugin;
	private static final ArrayList<Player> vanish = new ArrayList<Player>();

	public Vanish(final Plugin plugin) {
		super(true);
		this.plugin = plugin;
	}

	@Override
	protected final void onCommand(final CommandSender sender, final String[] args) {

		final Player playerSender = (Player) sender;

		if (vanish.contains(playerSender)) {

			Bukkit.getOnlinePlayers().forEach(x -> x.showPlayer(playerSender));

			playerSender.sendMessage(PrefixMessage.VANISH + "Vous n'êtes plus en vanish");
			if (playerSender.getGameMode() == GameMode.SURVIVAL || playerSender.getGameMode() == GameMode.ADVENTURE)
				playerSender.setAllowFlight(false);
			playerSender.removePotionEffect(PotionEffectType.NIGHT_VISION);

			if (PlayerPcm.getPlayersPcm().get(playerSender).getStaff() == RankList.ORGANISATEUR)
				playerSender.setGameMode(GameMode.ADVENTURE);
			vanish.remove(playerSender);
			PlayerPcm.getPlayersPcm().get(playerSender).updatePlayerNameTab();

		} else {

			Bukkit.getOnlinePlayers().stream().filter(x -> !PlayerPcm.getPlayersPcm().get(x).getStaff().isStaff())
					.forEach(x -> x.hidePlayer(playerSender));

			Reflections.sendPacket(
					new PacketPlayOutChat(ChatSerializer
							.a("[\"\",{\"text\":\"[\"},{\"text\":\"Vanish\",\"color\":\"aqua\"},{\"text\":\"/\"},{\"text\":\"Config\",\"color\":\"gray\"},{\"text\":\"] \"},{\"text\":\"En clickant\",\"color\":\"yellow\"},{\"text\":\" \"},{\"text\":\"[\",\"bold\":\"true\",\"color\":\"dark_gray\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/config change Vanish.connect true\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Execute la commande:\",\"color\":\"yellow\"},{\"text\":\" \"},{\"text\":\"/config change Vanish.connect true\",\"italic\":\"true\",\"color\":\"gold\"}]}},{\"text\":\"ICI\",\"bold\":\"true\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/config change Vanish.connect true\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Execute la commande:\",\"color\":\"yellow\"},{\"text\":\" \"},{\"text\":\"/config change Vanish.connect true\",\"italic\":\"true\",\"color\":\"gold\"}]}},{\"text\":\"]\",\"bold\":\"true\",\"color\":\"dark_gray\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/config change Vanish.connect true\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":[\"\",{\"text\":\"Execute la commande:\",\"color\":\"yellow\"},{\"text\":\" \"},{\"text\":\"/config change Vanish.connect true\",\"italic\":true,\"color\":\"gold\"}]}},{\"text\":\" \"},{\"text\":\"vous serez mis en Vanish dès que vous vous connecterez !\",\"color\":\"yellow\"}]")),
					playerSender);
			if (PlayerPcm.getPlayersPcm().get(playerSender).getStaff() == RankList.ORGANISATEUR)
				playerSender.setGameMode(GameMode.SURVIVAL);

			playerSender.sendMessage(PrefixMessage.VANISH + "Vous êtes maintenant en vanish");

			playerSender.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999, 0, true));
			playerSender.setAllowFlight(true);
			vanish.add(playerSender);
			PlayerPcm.getPlayersPcm().get(playerSender).updatePlayerNameTab();
			actionMessageVanish(playerSender);
		}

	}

	public static final ArrayList<Player> getVanished() {
		return Vanish.vanish;
	}

	private final void actionMessageVanish(final Player playersender) {

		new BukkitRunnable() {

			private int valuemin = 4, valuemax = 22;
			private final String corps = "    Vous êtes maintenant en Vanish !    ";

			@Override
			public final void run() {

				if (vanish.contains(playersender)) {

					if (valuemin == 41)
						valuemin = 0;

					if (valuemax == 41)
						valuemax = 0;

					final String message;

					if (valuemin < valuemax)
						message = corps.substring(valuemin, valuemax);
					else
						message = corps.substring(valuemin, corps.length()) + corps.substring(0, valuemax);

					valuemin++;
					valuemax++;

					Reflections.sendPacket(
							new PacketPlayOutChat(
									IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + message + "\"}"), (byte) 2),
							playersender);

				} else
					this.cancel();
			}
		}.runTaskTimer(this.plugin, 0L, 40L);
	}

	@Override
	protected final List<String> onTabCompletation(final String[] args) {
		return Arrays.asList("");
	}

}
