package org.annointed.scripts.anfistofguthix.wrappers;

import java.awt.Point;

import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Entity;

public class Methods {

	public static void interact(final Entity entity, final String action) {
		int random = Random.nextInt(1, 3);
		Point entityPoint = new Point(entity.getCentralPoint().x + random,
				entity.getCentralPoint().y + random);
		Mouse.hop(entityPoint.x, entityPoint.y);
		if (Menu.contains(action)) {
			Menu.select(action);
			Time.sleep(1000, 1500);
		} else {
			return;
		}
	}

}