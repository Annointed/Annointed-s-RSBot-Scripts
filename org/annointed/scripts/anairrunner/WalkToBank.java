package org.annointed.scripts.anairrunner;

import org.annointed.scripts.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class WalkToBank extends Strategy implements Runnable {

	@Override
	public void run() {
		SceneObject portal = SceneEntities.getNearest(Constants.PORTAL_ID);
		SceneObject mysteriousRuins = SceneEntities
				.getNearest(Constants.MYSTERIOUS_RUINS_ID);
		if (Inventory.getItem(Constants.RUNE_ESSENCE_ID) == null) {
			if (portal != null) {
				if (portal.isOnScreen()) {
					DynamicInteract.interact(portal, "Enter");
					Time.sleep(1000, 1500);
				}
			} else if (mysteriousRuins != null) {
				Walking.newTilePath(Constants.pathToAltar).reverse().traverse();
			}
		} else {
			Constants.bankNow = false;
		}
	}

	@Override
	public boolean validate() {
		return Constants.bankNow && Constants.slaveScript && !Constants.guiWait;
	}

}
