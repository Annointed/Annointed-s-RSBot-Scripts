package org.annointed.scripts.anfistofguthix;

import org.annointed.scripts.anfistofguthix.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.Player;

public class Hunting extends Strategy implements Runnable {

	@Override
	public void run() {
		if (Widgets.get(730, 18).validate()) {
			Constants.opponentName = Widgets.get(730, 18).getText();
			System.out.println("Opponent: " + Constants.opponentName);
		}
		Player opponent = Players.getNearest(new Filter<Player>() {
			@Override
			public boolean accept(Player player) {
				for (Player p : Players.getLoaded()) {
					if (p.getName().equals(Constants.opponentName)) {
						return true;
					}
				}
				return false;
			}
		});
		if (opponent != null) {
			if (opponent.isOnScreen()) {
				System.out.println(opponent.interact("attack") + "--"
						+ opponent.getName());
				Time.sleep(1000, 1500);
			} else {
				Camera.turnTo(opponent);
				Walking.findPath(opponent.getLocation()).traverse();
			}
		}
	}

	@Override
	public boolean validate() {
		return Constants.huntingStyle;
	}

}
