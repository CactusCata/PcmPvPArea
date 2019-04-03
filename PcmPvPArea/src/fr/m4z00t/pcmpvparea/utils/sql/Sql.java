package fr.m4z00t.pcmpvparea.utils.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.cactuscata.pcmevent.PcmEvent;
import fr.m4z00t.pcmpvparea.commands.staff.RankList;
import fr.m4z00t.pcmpvparea.utils.PlayerPcm;

/**
 * <p>
 * Lorsque le plugin s'allume, la classe principale ({@link PcmEvent}) utilise
 * la méthode {@link Sql#connect(String, String, String, String)} pour se
 * connecter à la base de donnée. Cette dernière chargera les informations des
 * joueurs qui se connecteront, principalement grace à la méthode
 * {@link Sql#getInformation(UUID, SqlColumn)} et mettra à jour les informations
 * avec la méthode {@link Sql#updateSqlInQuit(PlayerPcm, int, long, boolean)} en
 * asynchrone.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 2.4.0
 * @see {@link PlayerPcm}, {@link SqlColumn}.
 */

public final class Sql {

	private Connection connection;
	private final String host, bddName, userName, password;
	private final Plugin plugin;

	public Sql(final String host, final String bddName, final String userName, final String password,
			final Plugin plugin) {

		this.plugin = plugin;
		this.host = host;
		this.bddName = bddName;
		this.userName = userName;
		this.password = password;
		connect();
	}

	private final void checkConnectAndReconnectIfWant() {
		if (!isConnect())
			connect();
	}

	private final void connect() {
		try {
			this.connection = DriverManager.getConnection(
					"jdbc:mysql://" + this.host + "/" + this.bddName + "?autoReconnect=true", this.userName,
					this.password);
		} catch (final SQLException e) {
			System.out.println("Erreur lors de la tentative de connection à la bdd: " + e.getMessage());
		}
	}

	public final void disconnect() {
		if (isConnect())
			try {
				this.connection.close();
			} catch (final SQLException e) {
				System.out.println("Erreur lors de la tentative de deconnection a la bdd : " + e.getMessage());
			}
	}

	private final boolean isConnect() {
		try {
			return this.connection != null && !this.connection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public final void createAccount(final UUID uuid, String playerName) {
		checkConnectAndReconnectIfWant();
		try {
			final PreparedStatement ps = this.connection
					.prepareStatement("INSERT INTO EVENT__players(Uuid,Pseudo) VALUES (?,?)");
			ps.setString(1, uuid.toString());
			ps.setString(2, playerName);
			ps.execute();
			ps.close();
		} catch (final SQLException e) {
			System.out.println("Erreur lors de la tentative de creation de compte a la bdd (uuid : " + uuid.toString()
					+ ", playerName: " + playerName + "): " + e.getMessage());
		}

	}

	public final boolean hasAcccount(final UUID uuid) {
		checkConnectAndReconnectIfWant();
		try {
			final PreparedStatement ps = this.connection
					.prepareStatement("SELECT Uuid FROM `EVENT__players` WHERE Uuid = ?");
			ps.setString(1, uuid.toString());
			final ResultSet result = ps.executeQuery();
			final boolean hasAccount = result.next();
			ps.close();
			return hasAccount;
		} catch (final SQLException e) {
			System.out.println("Erreur lors de la tentative de verification de l'existance du compte dans la bdd : "
					+ e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	public final Map<String, Integer> getHighScore() {
		checkConnectAndReconnectIfWant();
		try {

			final PreparedStatement ps = this.connection
					.prepareStatement("SELECT Level,Uuid FROM `EVENT__players` ORDER BY Level DESC LIMIT 7");
			final ResultSet res = ps.executeQuery();
			final Map<String, Integer> map = new HashMap<>();

			while (res.next())
				map.put(res.getString("Uuid"), res.getInt("Level"));
			ps.close();
			return map;
		} catch (final SQLException e) {
			System.out.println("Erreur lors de la tentative de recuperation des sept meilleurs score dans la bdd : "
					+ e.getMessage());
			return null;
		}

	}

	public final Object getInformation(UUID uuid, SqlColumn sqlColum) {
		checkConnectAndReconnectIfWant();
		try {
			final String sqlColumnName = sqlColum.toString();
			final PreparedStatement ps = this.connection
					.prepareStatement("SELECT " + sqlColumnName + " FROM `EVENT__players` WHERE Uuid = ?");
			ps.setString(1, uuid.toString());
			Object obj = null;
			final ResultSet set = ps.executeQuery();
			while (set.next()) {
				obj = set.getObject(sqlColumnName);
			}
			ps.close();
			return obj;
		} catch (final SQLException e) {
			System.out.println("Erreur lors de la tentative de recuperation d'une information dans la bdd (uuid : "
					+ uuid.toString() + ",sqlColumn " + sqlColum.toString() + "): " + e.getMessage());
			return null;
		}

	}

	public final ResultSet getAllInformation(final UUID uuid) {
		checkConnectAndReconnectIfWant();
		try {
			final PreparedStatement ps = this.connection
					.prepareStatement("SELECT * FROM `EVENT__players` WHERE Uuid = ?");
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			rs.first();
			return rs;
		} catch (final SQLException e) {
			System.out.println(
					"Erreur lors de la tentative de récupération de la liste de toutes les informations (Uuid: '"
							+ uuid.toString() + "'): " + e.getMessage());
			return null;
		}
	}

	public final int getLevel(final ResultSet rs) {
		checkConnectAndReconnectIfWant();
		try {
			return rs.getInt(SqlColumn.LEVEL.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public final int getLevel(final UUID uuid) {
		return (int) getInformation(uuid, SqlColumn.LEVEL);
	}

	public final long getExperience(final ResultSet rs) {
		try {
			return rs.getLong(SqlColumn.EXPERIENCE.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return 0L;
		}
	}

	public final long getExperience(final UUID uuid) {
		return (long) getInformation(uuid, SqlColumn.EXPERIENCE);
	}

	public final RankList getStaff(final ResultSet rs) {
		try {
			return RankList.getStaff(rs.getString(SqlColumn.RANK.toString()));
		} catch (SQLException e) {
			e.printStackTrace();
			return RankList.NULL;
		}
	}

	public final RankList getStaff(final UUID uuid) {
		return RankList.getStaff((String) getInformation(uuid, SqlColumn.RANK));
	}

	public final boolean isVanishOnConnect(final ResultSet rs) {
		try {
			return rs.getBoolean(SqlColumn.VANISH_ON_CONNECT.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public final boolean isVanishOnConnect(final UUID uuid) {
		return (int) getInformation(uuid, SqlColumn.VANISH_ON_CONNECT) == 1;
	}

	public final String getPlayerName(final ResultSet rs) {
		try {
			return rs.getString(SqlColumn.PSEUDO.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
	}

	public final String getPlayerName(final UUID uuid) {
		return (String) getInformation(uuid, SqlColumn.PSEUDO);
	}

	public final void resetExperience(final UUID uuid) {
		checkConnectAndReconnectIfWant();
		try {
			final PreparedStatement ps = this.connection.prepareStatement(
					"UPDATE `link`.`EVENT__players` SET `Level` = '1', `Experience` = '0' WHERE `EVENT__players`.`Uuid` = ?;");
			ps.setString(1, uuid.toString());
			ps.executeUpdate();
			ps.close();
		} catch (final SQLException e) {
			System.out.println("Erreur lors de la tentative de reset de l'experience dans la bdd (uuid : "
					+ uuid.toString() + "): " + e.getMessage());
		}
	}

	/*
	 * Update seulement pour les joueurs déco
	 */
	public final void updateOfflinePlayerStaff(final UUID uuid, final RankList staff) {
		checkConnectAndReconnectIfWant();
		if (hasAcccount(uuid))
			try {
				final PreparedStatement ps = this.connection
						.prepareStatement("UPDATE `EVENT__players` SET `Rank` = ? WHERE `EVENT__players`.`Uuid` = ?;");
				ps.setString(1, staff.getNameOfStaff());
				ps.setString(2, uuid.toString());
				ps.executeUpdate();
				ps.close();
			} catch (final SQLException e) {
				e.printStackTrace();
			}
	}

	public final void updateSqlInQuit(final PlayerPcm playerPcm, final int level, final long experience,
			final boolean vanishOnConnect, final boolean asyn) {

		checkConnectAndReconnectIfWant();

		if (asyn) {

			Bukkit.getScheduler().runTaskAsynchronously(this.plugin, new Runnable() {
				@Override
				public final void run() {
					updateStatsSql(playerPcm, playerPcm.getPlayer(), level, experience, vanishOnConnect);
				}
			});
		} else {
			updateStatsSql(playerPcm, playerPcm.getPlayer(), level, experience, vanishOnConnect);
		}

	}

	private void updateStatsSql(PlayerPcm playerPcm, Player player, int level, long experience,
			boolean vanishOnConnect) {
		try {
			PreparedStatement ps = Sql.this.connection.prepareStatement(
					"UPDATE `EVENT__players` SET `Pseudo` = ?,LastLogin = CURRENT_TIMESTAMP(), `Rank` = ?, `Ips` = ?,"
							+ (playerPcm.getStaff().isStaff()
									? "`VanishOnConnect` = '" + (vanishOnConnect ? 1 : 0) + "'," : "")
							+ "`Level` = ?, `Experience` = ? WHERE `EVENT__players`.`Uuid` = ?;");
			ps.setString(1, player.getName());
			ps.setString(2, playerPcm.getStaff().getNameOfStaff());
			ps.setString(3, playerPcm.getIP());
			ps.setInt(4, level);
			ps.setLong(5, experience);
			ps.setString(6, player.getUniqueId().toString());
			ps.executeUpdate();
			ps.close();
		} catch (final SQLException e) {
			System.out.println("Erreur lors de la tentative d'update lors de la déconnection (uuid : "
					+ player.getUniqueId().toString() + "): " + e.getMessage());
		}
	}

}
