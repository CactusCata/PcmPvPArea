package fr.m4z00t.pcmpvparea.commands.staff;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cactuscata.pcmevent.api.UUIDFetcher;
import fr.m4z00t.pcmpvparea.commands.CCommandExecutor;
import fr.m4z00t.pcmpvparea.commands.other.Vanish;
import fr.m4z00t.pcmpvparea.utils.PlayerPcm;
import fr.m4z00t.pcmpvparea.utils.PrefixMessage;
import fr.m4z00t.pcmpvparea.utils.sql.Sql;

/**
 * <p>
 * Cette classe permet d'executer la commande /setstaff, cette dernière permet
 * de mettre un {@link RankList grade} à joueur, connecté ou {@link UUIDFetcher
 * non}.
 * </p>
 * <p>
 * <strong>Il faut un mot de passe pour mettre un grade.</strong>
 * </p>
 * <p>
 * Met par ailleurs à jour le systeme de {@link Vanish}, c'est à dire si le
 * grade prend en considération la visibilité du {@link Vanish} via
 * {@link RankList#isStaff()}.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 */

public final class SetRank extends CCommandExecutor {

	private final Sql sql;

	public SetRank(final Sql sql) {
		super(false, PrefixMessage.PREFIX, "Veuillez préciser le joueur !", "Veuillez préciser le grade !",
				"Il manque le mot de passe");
		this.sql = sql;
	}

	@Override
	protected final void onCommand(final CommandSender sender, final String[] args) {

		final RankList rankList = RankList.getStaff(args[1]);

		if (rankList == RankList.NULL) {
			sender.sendMessage(PrefixMessage.PREFIX + "Le grade " + args[1] + " n'existe pas !");
			return;
		}

		if (!(args[2].equals("cocacola"))) {
			sender.sendMessage(PrefixMessage.PREFIX + "Mot de passe incorrect !");
			return;
		}

		final Player target = Bukkit.getPlayerExact(args[0]);

		if (target != null && target.isOnline()) {

			final PlayerPcm playerPcm = PlayerPcm.getPlayersPcm().get(target);
			playerPcm.setStaff(rankList);

			if (sender instanceof Player)
				target.sendMessage(PrefixMessage.PREFIX + ((Player) sender).getDisplayName() + "§e vous a mis le grade "
						+ rankList.getNameOfStaff() + "§e !");
			else
				target.sendMessage(
						PrefixMessage.PREFIX + "La console vous a mis le grade " + rankList.getNameOfStaff() + "§e !");

			if (!rankList.isStaff()) {
				if (Vanish.getVanished().contains(target)) {
					Vanish.getVanished().remove(target);
					Bukkit.getOnlinePlayers().forEach(x -> x.showPlayer(target));
				}

				Vanish.getVanished().forEach(x -> target.hidePlayer(x));

			} else

				Vanish.getVanished().forEach(x -> target.showPlayer(x));

		} else {

			try {
				final UUID uuid = UUIDFetcher.getUUIDOf(args[0]);
				if (this.sql.hasAcccount(uuid)) {
					this.sql.updateOfflinePlayerStaff(uuid, rankList);
				} else {
					sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " ne c'est jamais connecté !");
					return;
				}

			} catch (NullPointerException e) {
				sender.sendMessage(PrefixMessage.PREFIX + "Le joueur n'existe pas !");
				return;
			}

		}

		sender.sendMessage(PrefixMessage.PREFIX + "Vous avez mis le grade " + rankList.getNameOfStaff() + " au joueur "
				+ rankList.getPrefix() + args[0] + rankList.getSuffix() + "§e !");

	}

	@Override
	protected final List<String> onTabCompletation(final String[] args) {
		if (args.length == 1)
			return Bukkit.getOnlinePlayers().stream().map(p -> p.getName()).collect(Collectors.toList());
		else if (args.length == 2)
			return Arrays.asList(RankList.values()).stream().filter(staff -> staff != RankList.NULL)
					.map(staff -> staff.getNameOfStaff()).collect(Collectors.toList());
		return Arrays.asList("");
	}

}
