package fr.m4z00t.pcmpvparea.commands.spy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.cactuscata.pcmevent.command.vanish.Vanish;
import fr.m4z00t.pcmpvparea.commands.CCommandExecutor;
import fr.m4z00t.pcmpvparea.utils.PrefixMessage;

/**
 * <p>
 * Cette classe permet l'execution de la commande /msg, qui une fois execut�,
 * permettra d'envoyer un message priv� � joueur.
 * </p>
 * <p>
 * Il permettra de mettre � jour le {@link R /r} ainsi qu'envoyer le pr�c�dant
 * message � tout ceux qui ont le {@link SocialSpy} d'activ�.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 */

public final class Msg extends CCommandExecutor {

	private static final Map<Player, Player> rep = new HashMap<Player, Player>();

	public Msg() {
		super(true, PrefixMessage.PREFIX, "Veuillez pr�ciser le joueur !", "Veuillez pr�ciser le message !");
	}

	@Override
	protected final void onCommand(final CommandSender sender, final String[] args) {

		final Player target = Bukkit.getPlayerExact(args[0]);
		if (target == null || !target.isOnline() || Vanish.getVanished().contains(target)) {
			sender.sendMessage(PrefixMessage.PREFIX + "Le joueur " + args[0] + " n'a pas �t� trouv� !");
			return;
		}

		if (target == sender) {
			sender.sendMessage(PrefixMessage.PREFIX + "Vous ne pouvez pas vous envoyer des messages � vous m�me !");
			return;
		}

		final Player playersender = (Player) sender;
		final StringBuilder message = new StringBuilder("�7[" + playersender.getDisplayName() + "�7]->[" + target.getDisplayName() + "�7]");
		final int argsSize = args.length;
		for (int i = 1; i < argsSize; i++)
			message.append(" " + args[i]);

		playersender.sendMessage(message.toString());
		target.sendMessage(message.toString());

		rep.put(target, playersender);

		SocialSpy.getSocial().stream().filter(playerSpy -> playerSpy != playersender && playerSpy != target)
				.forEach(playerSpy -> playerSpy.sendMessage(PrefixMessage.SPY + message.toString()));

	}

	@Override
	protected final List<String> onTabCompletation(final String[] args) {
		if (args.length == 1)
			return Bukkit.getOnlinePlayers().stream().map(x -> x.getName()).collect(Collectors.toList());
		return Arrays.asList("");
	}

	public static final Map<Player, Player> getRep() {
		return Msg.rep;
	}

}
