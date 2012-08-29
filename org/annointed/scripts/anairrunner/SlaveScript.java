package org.annointed.scripts.anairrunner;

import org.annointed.scripts.anairrunner.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class SlaveScript extends Strategy implements Runnable {

	@Override
	public void run() {
		SceneObject mysteriousRuins = SceneEntities
				.getNearest(Constants.MYSTERIOUS_RUINS_ID);
		if (Constants.bankArea.contains(Players.getLocal().getLocation())
				&& Inventory.getItem(Constants.RUNE_ESSENCE_ID) != null) {
			Walking.newTilePath(Constants.pathToAltar).traverse();
		}
		if (Inventory.getItem(Constants.RUNE_ESSENCE_ID) != null) {
			if (mysteriousRuins != null) {
				if (mysteriousRuins.isOnScreen()) {
					mysteriousRuins.interact("Enter");
					Time.sleep(1000, 1500);
				} else {
					Camera.turnTo(mysteriousRuins);
				}
			}
		}
	}

	@Override
	public boolean validate() {
		return !Constants.bankNow && Constants.slaveScript
				&& !Constants.guiWait;
	}

}
