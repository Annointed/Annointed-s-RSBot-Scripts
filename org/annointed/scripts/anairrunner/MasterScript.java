package org.annointed.scripts.anairrunner;

import org.annointed.scripts.anairrunner.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class MasterScript extends Strategy implements Runnable {

	@Override
	public void run() {
		SceneObject airAltar = SceneEntities.getNearest(Constants.AIR_ALTAR_ID);
		while (Inventory.getItem(Constants.RUNE_ESSENCE_ID) == null) {
			Time.sleep(50);
		}
		if (airAltar != null) {
			if (airAltar.isOnScreen()) {
				airAltar.interact("Craft-Rune");
				Time.sleep(1000, 1500);
			} else {
				Camera.turnTo(airAltar);
			}
		}
	}

	@Override
	public boolean validate() {
		return Constants.masterScript && !Constants.guiWait;
	}

}
