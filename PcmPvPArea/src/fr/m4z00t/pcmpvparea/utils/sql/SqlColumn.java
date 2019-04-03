package fr.m4z00t.pcmpvparea.utils.sql;

import org.apache.commons.lang.WordUtils;

/**
 * <p>
 * Cette classe énumère la liste des colonnes de la base de donnée {@link Sql}
 * disponibles.
 * </p>
 * 
 * @author CactusCata
 * @version 2.4.1
 * @since 2.4.0
 * @see Sql
 */

public enum SqlColumn {

	UUID,
	PSEUDO,
	RANK,
	IPS,
	FIRST_LOGIN,
	LAST_LOGIN,
	VANISH_ON_CONNECT,
	LEVEL,
	EXPERIENCE;

	@Override
	public final String toString() {
		return WordUtils.capitalize(
				this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase().replace("_", ""));
	}

}
