package org.annointed.scripts.anairrunner;

import org.annointed.scripts.anairrunner.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Banker extends Strategy implements Runnable {

	@Override
	public void run() {
		NPC banker = NPCs.getNearest(Constants.BANKER_ID);
		if (Inventory.getItem(Constants.RUNE_ESSENCE_ID) == null) {
			if (Bank.isOpen()) {
				Bank.withdraw(Constants.RUNE_ESSENCE_ID, 28);
			} else {
				if (banker != null) {
					if (banker.isOnScreen()) {
						banker.interact("Bank");
						Time.sleep(1000, 1500);
					}
				}
			}
		} else {
			Constants.bankNow = false;
		}
	}

	@Override
	public boolean validate() {
		return Constants.bankNow
				&& Constants.slaveScript
				&& Constants.bankArea
						.contains(Players.getLocal().getLocation())
				&& !Constants.guiWait;
	}

}