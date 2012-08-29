package org.annointed.scripts.anairrunner.constants;

import java.awt.RenderingHints;

import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;

public class Constants {

	public static final int BANKER_ID = 553;
	public static final int AIR_TIARA_ID = 5527;
	public static final int RUNE_ESSENCE_ID = 1436;
	public static final int MYSTERIOUS_RUINS_ID = 2452;
	public static final int AIR_ALTAR_ID = 2478;
	public static final int PORTAL_ID = 2465;

	public static boolean bankNow = false;
	public static boolean masterScript = false;
	public static boolean slaveScript = false;
	public static boolean guiWait = true;

	public static String mainName;
	public static String slaveName;

	public static Area bankArea = new Area(new Tile[] {
			new Tile(3178, 3446, 0), new Tile(3193, 3446, 0),
			new Tile(3193, 3431, 0), new Tile(3178, 3431, 0) });

	public static Tile[] pathToAltar = new Tile[] { new Tile(3186, 3433, 0),
			new Tile(3183, 3429, 0), new Tile(3178, 3429, 0),
			new Tile(3173, 3429, 0), new Tile(3168, 3428, 0),
			new Tile(3164, 3425, 0), new Tile(3161, 3421, 0),
			new Tile(3157, 3418, 0), new Tile(3152, 3417, 0),
			new Tile(3147, 3416, 0), new Tile(3143, 3413, 0),
			new Tile(3140, 3409, 0), new Tile(3135, 3407, 0),
			new Tile(3129, 3404, 0) };

	public static final RenderingHints ANTIALIASING = new RenderingHints(
			RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

}
