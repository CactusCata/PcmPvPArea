package fr.m4z00t.pcmpvparea.commands.spy;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.m4z00t.pcmpvparea.commands.CCommandExecutor;
import fr.m4z00t.pcmpvparea.utils.PrefixMessage;

/**
 * <p>
 * Cette classe permet d'executer la commande /spylist, cette dernière est
 * utilisé pour récuperer la liste de toutes les personnes qui ont utilisé la
 * commande {@link SocialSpy /socialspy}.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 */

public final class SpyList extends CCommandExecutor {

	public SpyList() {
		super(false);
	}

	@Override
	protected final void onCommand(final CommandSender sender, final String[] args) {

		if (SocialSpy.getSocial().isEmpty()) {
			sender.sendMessage(PrefixMessage.PREFIX + "Personne n'a le spy d'activé !");
			return;
		}

		final StringBuilder msg = new StringBuilder(
				PrefixMessage.PREFIX + "Les personnes qui ont le /spy d'activé sont :");

		SocialSpy.getSocial().stream().forEach(x -> msg.append("\n" + x.getDisplayName()));

		sender.sendMessage(msg.toString());

	}

	@Override
	protected final List<String> onTabCompletation(final String[] args) {
		return Arrays.asList("");
	}

}
