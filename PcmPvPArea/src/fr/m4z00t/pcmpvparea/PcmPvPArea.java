package fr.m4z00t.pcmpvparea;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.m4z00t.pcmpvparea.area.Spawn;
import fr.m4z00t.pcmpvparea.commands.other.Vanish;
import fr.m4z00t.pcmpvparea.commands.spy.Msg;
import fr.m4z00t.pcmpvparea.commands.spy.R;
import fr.m4z00t.pcmpvparea.commands.spy.SocialSpy;
import fr.m4z00t.pcmpvparea.commands.staff.SetRank;
import fr.m4z00t.pcmpvparea.listeners.JoinGame;
import fr.m4z00t.pcmpvparea.listeners.QuitGame;
import fr.m4z00t.pcmpvparea.listeners.Weather;
import fr.m4z00t.pcmpvparea.utils.sql.Sql;

public class PcmPvPArea extends JavaPlugin {

	private Sql sql;
	
	@Override
	public void onEnable() {
		
		this.sql = new Sql("alpha.pcm.ninja", "link", "pvparea", "?????", this);

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new Weather(), this);
		pm.registerEvents(new JoinGame(), this);
		pm.registerEvents(new QuitGame(), this);

		getCommand("setRank").setExecutor(new SetRank(this.sql));
		getCommand("openinv").setExecutor(new OpenInv());
		getCommand("openender").setExecutor(new OpenEnder());
		getCommand("vanish").setExecutor(new Vanish());
		getCommand("spawn").setExecutor(new Spawn());
		getCommand("msg").setExecutor(new Msg());
		getCommand("r").setExecutor(new R());
		getCommand("socialspy").setExecutor(new SocialSpy());
		getCommand("spylist").setExecutor(new Spylist());

	}

	@Override
	public void onDisable() {

	}

}
