package fr.m4z00t.pcmpvparea.utils;

import org.bukkit.entity.Player;

import fr.m4z00t.pcmpvparea.listeners.JoinGame;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

/**
 * <p>
 * Cette classe, utilisé pour l'instant seulement dans la classe
 * {@link JoinGame},permet de mettre un tablist avec un description via packet
 * ({@link PacketPlayOutPlayerListHeaderFooter}).
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.5.0
 * @see {@link JoinGame}
 */

public final class SetTab {

	public static final void sendtab(final Player player) {

		final PacketPlayOutPlayerListHeaderFooter headerfooter = new PacketPlayOutPlayerListHeaderFooter();

		Reflections.setValue(headerfooter, "a", ChatSerializer.a(
				"[\"\",{\"text\":\"§r     §c§m----------------§8§m§l[-§r §7§k:§r §6§lPleaseCraftMe§r §7§k:§8§m§l-]§c§m----------------\n§ePvP Area\n\"}]"));
		Reflections.setValue(headerfooter, "b", ChatSerializer.a(
				"[\"\",{\"text\":\"\n§aSite & forum: §ehttp://pleasecraftme.fr\n§bIP TeamSpeak: §ets3.pleasecraftme.fr\n§dVote §d: §ehttp://vote.pleasecraftme.fr\n\"}]"));

		Reflections.sendPacket(headerfooter, player);

	}
}