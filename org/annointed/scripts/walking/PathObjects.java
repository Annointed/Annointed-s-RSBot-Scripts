package org.annointed.scripts.walking;

import org.powerbot.game.api.wrappers.Tile;

public enum PathObjects {

	LUMBRIDGE_DUKE(new PathObject[] {
			new PathObject("Climb", new Tile(3205, 3228, 0), 36776),
			new PathObject("Open", new Tile(3207, 3222, 1), 36844) }),

	DUKE_WIZARD(new PathObject[] {
			new PathObject("Open", new Tile(3109, 3167, 0), 11993),
			new PathObject("Open", new Tile(3107, 3163, 0), 11993),
			new PathObject("Climb", new Tile(3105, 3162, 0), 2147),
			new PathObject("Open", new Tile(3108, 9570, 0), 33060)

	}),

	WIZARD_VARROCK(new PathObject[] { new PathObject("Open", new Tile(3253,
			3398, 0), 24381) }),

	COOK_BUCKET(new PathObject[] { new PathObject("Climb", new Tile(3209, 3215,
			0), 36687) }),

	BUCKET_COW(new PathObject[] { new PathObject("Open",
			new Tile(3252, 3267, 0), 45212) }),

	COW_EGG(new PathObject[] { new PathObject("Open", new Tile(3189, 3280, 0),
			45208) }),

	EGG_GRAIN(new PathObject[] {
			new PathObject("Open", new Tile(3189, 3280, 0), 45208),
			new PathObject("Open", new Tile(3166, 3294, 0), 45212) }),

	GRAIN_MILL(new PathObject[] {
			new PathObject("Open", new Tile(3166, 3294, 0), 45212),
			new PathObject("Open", new Tile(3166, 3301, 0), 45964),

	}),

	CHURCH(new PathObject[] { new PathObject("Open", new Tile(3237, 3209, 0),
			37002) }),

	FATHER(new PathObject[] { new PathObject("Open", new Tile(3207, 3152, 0),
			45539) }),

	BAR(new PathObject[] { new PathObject("Open", new Tile(3216, 3395, 0),
			24376) }),

	MANOR(new PathObject[] {
			new PathObject("Open", new Tile(3216, 3395, 0), 24376),
			new PathObject("Open", new Tile(3108, 3353, 0), 47421),
			new PathObject("Open", new Tile(3112, 3360, 0), 47512),
			new PathObject("Walk-down", new Tile(3115, 3355, 0), 47643)

	}),

	MORGAN(new PathObject[] { new PathObject("Open", new Tile(3098, 3271, 0),
			1239) }),

	GATE(new PathObject[] { new PathObject("Pay", new Tile(3266, 3228, 0),
			35551), }),

	GATE_2(new PathObject[] { new PathObject("Open", new Tile(3266, 3228, 0),
			35551) }),

	NED(
			new PathObject[] { new PathObject("Open", new Tile(3103, 3257, 0),
					1239) }),

	AG(new PathObject[] {
			new PathObject("Open", new Tile(3102, 3257, 0), 1239),
			new PathObject("Open", new Tile(3088, 3259, 0), 1239) }),

	LAD(new PathObject[] {
			new PathObject("Open", new Tile(3128, 3247, 0), 3434),
			new PathObject("Open", new Tile(3087, 3259, 0), 1239) }),

	START_DORIC(new PathObject[] { new PathObject("Open", new Tile(2949, 3450,
			0), 1530) }),

	DORIC_MINE(new PathObject[] {
			new PathObject("Open", new Tile(3061, 3374, 0), 11714),
			new PathObject("down", new Tile(3061, 3376, 0), 30944) }),

	GUDRUN_FATHER(new PathObject[] { new PathObject("Open", new Tile(3078,
			3434, 0), 11621) }),

	NONE(new PathObject[] {});

	private final PathObject[] objects;

	PathObjects(PathObject[] objects) {
		this.objects = objects;
	}

	public PathObject[] getPathObjects() {
		return objects;
	}
}
