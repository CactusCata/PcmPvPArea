package fr.m4z00t.pcmpvparea.commands.staff;

/**
 * <p>
 * Cette classe énumère tous les grades existants sur le monde event.
 * </p>
 * <p>
 * La mise en place d'un de ces grades se fait via la commande {@link SetRank
 * /setstaff}.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 1.0.0
 * @see SetRank
 */

public enum RankList {

	FONDATEUR("§c§l[Fondateur]", "§b", "Fondateur", true),
	ADMINISTRATEUR("§c[Admin]", "§b", "Administrateur", true),
	DEVELOPPEUR("§2[Dev]", "§b", "Developpeur", true),
	RESPONSABLE_EVENT("§5[Resp.Event]", "§3", "Resp.Event", true),
	RESPONSABLE_RECRUTEMENT("§5[Resp.Recru]", "§3", "Resp.Recrutements", true),
	RESPONSABLE_BUILDER("§5[Resp.Builder]", "§3", "Resp.Builders", true),
	ANIMATEUR("§9[Animateur]", "§b", "Animateur", true),
	ANIMATRICE("§9[Animatrice]", "§b", "Animatrice", true),
	ORGANISATEUR("§9[Organisateur]", "§d", "Organisateur", true),
	ORGANISATRICE("§9[Organisatrice]", "§d", "Organisatrice", true),
	COMMUNITY_MANAGER("§1[Commu.M]", "§d", "CommunityM", true),
	MODERATEUR("§d[Modo]", "§3", "Moderateur", true),
	MODERATEUR_FORUM("§d[Modo.Forum]", "§3", "ModerateurForum", true),
	GUARDIAN("§a[Guardian]", "§3", "Guardian", true),
	GUIDE("§7[Guide]", "§3", "Guide", false),
	YOUTUBEUR("§f[You§4Tube]", "§3", "Youtubeur", false),
	ANIMATEUR_TS("§7[Animateur§fTS§7]", "§3", "AnimateurTS", false),
	ANIMATRICE_TS("§7[Animatrice§fTS§7]", "§3", "AnimatriceTS", false),
	ANCIEN("§8[Ancien]", "§3", "Ancien", false),
	ANCIENNE("§8[Ancienne]", "§3", "Ancienne", false),
	PCT("§2[PCT]", "§3", "Pct", false),
	AMI("§6[Ami]", "§3", "Ami", false),
	PROPLAYERPVP2K17SOUPNOPOT("§6[§2P§4P§bPvP§d2k17§9Soup§eNoPot§6]§f", "§6", "PPPvP2k17SoupNoPot", false),
	AUCUN("§e", "§f", "Aucun", false),
	NULL("null", "null", "null", false);

	private final String prefix, suffix, nameOfStaff;
	private final boolean isStaff;

	private RankList(final String prefix, final String suffix, final String nameOfStaff, final boolean isStaff) {

		this.prefix = prefix;
		this.suffix = suffix;
		this.nameOfStaff = nameOfStaff;
		this.isStaff = isStaff;

	}

	public final String getPrefix() {
		return this.prefix;
	}

	public final String getSuffix() {
		return this.suffix;
	}

	public final String getNameOfStaff() {
		return this.nameOfStaff;
	}

	public final boolean isStaff() {
		return this.isStaff;
	}

	public final static RankList getStaff(final String str) {
		for (final RankList staff : RankList.values())
			if (staff.getNameOfStaff().equals(str))
				return staff;
		return RankList.NULL;
	}

}
