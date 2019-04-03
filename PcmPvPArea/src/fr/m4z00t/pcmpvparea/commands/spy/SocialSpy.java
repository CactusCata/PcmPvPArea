package fr.m4z00t.pcmpvparea.commands.spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.m4z00t.pcmpvparea.commands.CCommandExecutor;
import fr.m4z00t.pcmpvparea.utils.PrefixMessage;

/**
 * <p>
 * Cette classe permet d'executer la commande /socialspy, cette derni�re permet
 * de voir les messages priv�s du {@link Msg /msg} et du {@link R /r}.
 * </p>
 * <p>
 * La commande fonctionne sous forme de 'toggle', c'est � dire qu'une fois
 * activ� vous serez mis dans une {@link SocialSpy#social liste} et une fois la
 * commande r�activ�e, vous serez retir� de la {@link SocialSpy#social liste}.
 * </p>
 * <p>
 * La liste de tous les joueurs ex�cutant la commande est r�cup�rable via
 * {@link SocialSpy#getSocial()}. Elle est notamment utilis� pour la classe
 * {@link SpyList}.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 */

public final class SocialSpy extends CCommandExecutor {

	private static final List<Player> social = new ArrayList<Player>();

	public SocialSpy() {
		super(true);
	}

	@Override
	protected final void onCommand(final CommandSender sender, final String[] args) {

		final Player playerSender = (Player) sender;
		if (social.contains(playerSender)) {
			playerSender.sendMessage(PrefixMessage.PREFIX + "Vous ne voyez plus les messages priv�s !");
			social.remove(playerSender);
		} else {
			playerSender.sendMessage(PrefixMessage.PREFIX + "Vous voyez les messages priv�s !");
			social.add(playerSender);
		}
	}

	@Override
	protected final List<String> onTabCompletation(final String[] args) {
		return Arrays.asList("");
	}

	public static final List<Player> getSocial() {
		return social;
	}

}
