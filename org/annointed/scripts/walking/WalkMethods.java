package org.annointed.scripts.walking;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class WalkMethods {

	public static Tile[] reversePath(Tile tiles[]) {
		Tile r[] = new Tile[tiles.length];
		int i;
		for (i = 0; i < tiles.length; i++) {
			r[i] = tiles[(tiles.length - 1) - i];
		}
		return r;
	}

	public static boolean walkReversed(final Tile[] path, Teleport[] teleport,
			InteractableNPC npc, PathObject... objects) {
		return walkPath(reversePath(path), teleport, npc, objects);
	}

	public static boolean walkPath(final Tile[] path, Teleport[] teleports,
			InteractableNPC npc, final PathObject... objects) {
		boolean a = false;
		final Tile next = getNext(path);
		final Tile start = getStart(path);
		final Tile dest = Walking.getDestination();
		final Tile myTile = Players.getLocal().getLocation();
		final PathObject nextObject = nextPO(objects);
		SceneObject object = null;

		if (nextObject != null) {
			object = nextObject.getInstance();
		}

		if (Walking.getEnergy() > 20 && !Walking.isRunEnabled()) {
			Walking.setRun(true);
		}

		if (Walking.getEnergy() < 5) {
			if (Players.getLocal().getAnimation() == -1) {
				if (Widgets.get(750, 0).click(false)) {
					if (Menu.select("Rest")) {
						Time.sleep(7000);
						while (Walking.getEnergy() < 90) {
							Time.sleep(200);
						}
					}
				}
			}
		} else if (next == null && object == null
				&& (npc == null || npc.getInstance() == null)) {
			Time.sleep(2500);
			if (getNext(path) == null && nextPO(objects) == null) {
				for (Teleport teleport : teleports) {
					if (teleport.canUse()) {
						teleport.teleport();
						break;
					}
				}
			}
		} else if (object != null) {
			Walking.walk(object);
			Camera.turnTo(object);
			if (object.isOnScreen()) {
				if (object.interact(nextObject.getAction())) {
					Time.sleep(2000);
					while (Players.getLocal().isMoving()) {
						Time.sleep(50);
					}
					nextObject.setComplete(true);
				} else {
					Camera.turnTo(object);
				}

			}
		} else if (npc != null && npc.getInstance() != null
				&& Calculations.distanceTo(npc.getLocation()) < 2) {
			if (npc.getLocation().canReach()) {
				npc.talk();
			}
		} else {
			if (myTile == path[path.length - 1]) {
				return true;
			}

			if ((!Players.getLocal().isMoving() || Calculations
					.distanceTo(dest) < 5) && object == null) {
				if (!Walking.walk(next)) {
					if (Walking.walk(start)) {
						Time.sleep(500);
						a = true;
					} else {
						walkTile(getClosestTileOnMap(next));

						Time.sleep(500);
					}
				} else {
					Time.sleep(500);
					a = true;
				}
			}
		}

		return a;
	}

	public static Tile getClosestTileOnMap(final Tile tile) {
		if (Game.isLoggedIn()) {
			final Tile loc = Players.getLocal().getLocation();
			final Tile walk = new Tile((loc.getX() + tile.getX()) / 2,
					(loc.getY() + tile.getY()) / 2, Game.getPlane());
			return Calculations.distance(loc, walk) < 15 ? walk
					: getClosestTileOnMap(walk);
		}
		return tile;
	}

	public static boolean walkTile(final Tile tile) {
		if ((Walking.getDestination() == null || (Calculations.distance(Players
				.getLocal().getLocation(), Walking.getDestination()) < 6 && Calculations
				.distance(tile, Walking.getDestination()) > 3))) {
			Time.sleep(500);
			if (tile.isOnScreen()) {
				return tile.click(true);
			} else if (Calculations.distance(Players.getLocal().getLocation(),
					tile) < 15) {
				return Walking.walk(tile);
			} else {
				return walkTile(getClosestTileOnMap(tile));
			}
		}
		return false;
	}

	public static Tile getStart(final Tile[] tiles) {
		return tiles[0];
	}

	public static Tile getNext(final Tile[] tiles) {
		for (int i = tiles.length - 1; i >= 0; --i) {
			if (Calculations.distanceTo(tiles[i]) < 15
					&& tiles[i].getPlane() == Game.getPlane()) {
				return tiles[i];
			}
		}
		return null;
	}

	public static PathObject nextPO(PathObject... pathobjects) {
		if (pathobjects != null && pathobjects.length > 0) {
			PathObject nearest = pathobjects[0];
			if (nearest != null) {
				for (PathObject p : pathobjects) {
					if (p != null
							&& Calculations.distanceTo(p.getLocation()) < Calculations
									.distanceTo(nearest.getLocation())) {
						nearest = p;
					}
				}
			}
			if (nearest != null && nearest.getInstance() != null
					&& nearest.getLocation().getPlane() == Game.getPlane()
					&& Calculations.distanceTo(nearest.getLocation()) < 10
					&& nearest.getLocation().canReach()
					&& !nearest.hasCompleted()) {
				return nearest;
			}
		}
		return null;
	}

}