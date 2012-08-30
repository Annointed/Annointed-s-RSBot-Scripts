package org.annointed.scripts.anfistofguthix;

import org.annointed.scripts.anfistofguthix.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class Hunted extends Strategy implements Runnable {

	@Override
	public void run() {
		SceneObject stoneDispenser = SceneEntities
				.getNearest(Constants.STONE_DISPENSER_ID);
		if (Players.getLocal().getHpPercent() < 30) {
			Inventory.getItem(Constants.BANDAGE_ID).getWidgetChild()
					.interact("Heal");
			Time.sleep(1000, 1500);
		}
		if (Inventory.getItem(Constants.STONE_OF_POWER_ID) == null) {
			if (stoneDispenser != null) {
				if (stoneDispenser.isOnScreen()) {
					stoneDispenser.interact("Take");
					Time.sleep(1000, 1500);
				} else {
					Camera.turnTo(stoneDispenser);
				}
			}
		}
	}

	@Override
	public boolean validate() {
		return Constants.huntedStyle;
	}

}
