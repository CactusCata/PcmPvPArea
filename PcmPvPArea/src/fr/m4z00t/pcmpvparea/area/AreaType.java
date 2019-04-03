package fr.m4z00t.pcmpvparea.area;

public enum AreaType {

	SPAWN(Spawn.class),
	COMBO(Combo.class),
	HARD(Hard.class),
	TANK(Tank.class),
	SOUP(Soup.class),
	SHOT(Shot.class);

	private final Class<? extends Area> area;

	private AreaType(final Class<? extends Area> area) {
		this.area = area;
	}

	public Class<? extends Area> getArea() {
		return area;
	}

	public Area createArea() {
		try {
			return this.area.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

}
