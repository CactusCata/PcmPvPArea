package fr.m4z00t.pcmpvparea.utils;

/**
 * <p>
 * Cette classe énumère la liste de tous les préfix de message. Celui utilisé
 * par défaut sur la casi-totalité des commandes est le prefix
 * {@link PrefixMessage#PREFIX}.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 */

public enum PrefixMessage {

	PREFIX("§1[§bPvP Area§1]§e "),
	PREFIX_SENDER_BE_PLAYER(PrefixMessage.PREFIX + "§4La commande ne peut etre execute que par un joueur !"),
	SPY("§1[§3Spy§1]§7"),
	NOT_ENOUGHT_PERMISSION(
			"§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error."),
	VANISH("§f[§bVanish§f]§e ");

	private final String text;

	private PrefixMessage(final String text) {
		this.text = text;
	}

	@Override
	public final String toString() {
		return text;
	}

}