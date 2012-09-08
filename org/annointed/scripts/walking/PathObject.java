package org.annointed.scripts.walking;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class PathObject {

	private final Tile location;
	private final int id;
	private final String interact;
	private boolean completed;

	public PathObject(String interact, Tile location, int id) {
		this.id = id;
		this.location = location;
		this.interact = interact;
	}

	public Tile getLocation() {
		return location;
	}

	public boolean hasCompleted() {
		return completed;
	}

	public void setComplete(boolean b) {
		completed = b;
	}

	public SceneObject getInstance() {
		return SceneEntities.getNearest(new Filter<SceneObject>() {
			@Override
			public boolean accept(SceneObject o) {
				return o != null && o.getId() == id
						&& Calculations.distance(o, getLocation()) < 3;
			}
		});
	}

	public int getID() {
		return id;
	}

	public String getAction() {
		return interact;
	}
}
