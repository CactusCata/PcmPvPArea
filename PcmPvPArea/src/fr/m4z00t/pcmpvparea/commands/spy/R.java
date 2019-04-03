package fr.m4z00t.pcmpvparea.commands.spy;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.m4z00t.pcmpvparea.commands.CCommandExecutor;
import fr.m4z00t.pcmpvparea.utils.PrefixMessage;

/**
 * <p>
 * Cette classe permet l'execution de la commande /r, une fois cette dernière
 * utilisé avec en argument le message, elle envois au dernier joueur qui l'a
 * {@link Msg /msg} le message.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 */

public final class R extends CCommandExecutor {

	public R() {
		super(true, PrefixMessage.PREFIX, "Veuillez préciser le message");
	}

	@Override
	protected final void onCommand(final CommandSender sender, final String[] args) {

		final Player playerSender = (Player) sender, playerGetter = Msg.getRep().get(playerSender);

		if (playerGetter == null || !playerGetter.isOnline()) {
			playerSender.sendMessage(PrefixMessage.PREFIX + "Veuillez d'abord parler à quelqu'un !");
			return;
		}

		final StringBuilder message = new StringBuilder();
		final int argsSize = args.length;
		for (int i = 0; i < argsSize; i++)
			message.append(" " + args[i]);

		final String newMessage = "§7[" + playerSender.getDisplayName() + "§7]->[" + playerGetter.getDisplayName()
				+ "§7]" + message;

		playerSender.sendMessage(newMessage);
		playerGetter.sendMessage(newMessage);

		SocialSpy.getSocial().stream().filter(playerSpy -> playerSpy != playerSender && playerSpy != playerGetter)
				.forEach(playerSpy -> playerSpy.sendMessage(PrefixMessage.SPY + newMessage));

		Msg.getRep().put(playerGetter, playerSender);

	}

	@Override
	protected final List<String> onTabCompletation(final String[] args) {
		return Arrays.asList("");
	}

}
