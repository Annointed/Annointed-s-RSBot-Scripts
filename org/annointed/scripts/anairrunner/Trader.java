package org.annointed.scripts.anairrunner;

import org.annointed.scripts.anairrunner.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Time;

public class Trader extends Strategy implements Runnable {

	@Override
	public void run() {
		if (Constants.slaveScript
				&& Inventory.getItem(Constants.RUNE_ESSENCE_ID) != null) {
			Trade.tradePlayer(Constants.mainName, 3500);
			if (Trade.inTrade()) {
				Trade.offer(Constants.RUNE_ESSENCE_ID, 0);
				Time.sleep(1000, 1500);
				if (Trade.isWealthOffered()) {
					Trade.acceptTrade();
					Time.sleep(50);
				}
			}
		}
		if (Constants.masterScript
				&& Inventory.getItem(Constants.RUNE_ESSENCE_ID) == null) {
			Trade.tradePlayer(Constants.slaveName, 3500);
			if (Trade.inTrade()) {
				Trade.acceptTrade();
				Time.sleep(1500);
			}
		}
	}

	@Override
	public boolean validate() {
		return !Constants.bankNow && !Constants.guiWait;
	}

}
