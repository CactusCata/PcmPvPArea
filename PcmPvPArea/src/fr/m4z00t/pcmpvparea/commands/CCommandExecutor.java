package fr.m4z00t.pcmpvparea.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.m4z00t.pcmpvparea.utils.PrefixMessage;

/**
 * <p>
 * Cette classe permet de gerer de manière optimisé et protégé toutes les
 * commandes du plugin et avec cela, le {@link TabCompleter}.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 * @see {@link CommandExecutor}, {@link TabCompleter}
 */

public abstract class CCommandExecutor implements CommandExecutor, TabCompleter {

	protected abstract void onCommand(final CommandSender sender, final String[] args);

	protected abstract List<String> onTabCompletation(final String[] args);

	private final boolean wantPlayer, helpStructureCommand;
	private final String[] args;
	private final PrefixMessage prefix;

	protected CCommandExecutor(final boolean wantPlayer, final PrefixMessage prefix, final String... args) {
		this(wantPlayer, prefix, true, args);
	}

	protected CCommandExecutor(final boolean wantPlayer) {
		this(wantPlayer, null, false, "");
	}

	private CCommandExecutor(final boolean wantPlayer, final PrefixMessage prefix, final boolean helpStructureCommand,
			final String... args) {
		this.prefix = prefix;
		this.wantPlayer = wantPlayer;
		this.args = args;
		this.helpStructureCommand = helpStructureCommand;
	}

	@Override
	public final boolean onCommand(final CommandSender sender, final Command cmd, final String label,
			final String[] args) {
		if (this.wantPlayer && (!(sender instanceof Player))) {
			sender.sendMessage(PrefixMessage.PREFIX_SENDER_BE_PLAYER.toString());
			return true;
		}

		if (this.helpStructureCommand && args.length < this.args.length) {
			sender.sendMessage(this.prefix + this.args[args.length]);
			return true;
		}

		this.onCommand(sender, args);
		return true;
	}

	@Override
	public final List<String> onTabComplete(final CommandSender sender, final Command cmd, final String label,
			final String[] args) {
		return this.onTabCompletation(args);

	}

}
