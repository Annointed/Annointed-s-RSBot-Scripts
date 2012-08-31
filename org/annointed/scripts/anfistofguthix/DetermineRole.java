package org.annointed.scripts.anfistofguthix;

import org.annointed.scripts.anfistofguthix.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;

public class DetermineRole extends Strategy implements Runnable {

	@Override
	public void run() {
		Constants.inGame = true;
		if (Widgets.get(730, 26).validate()) {
			Constants.huntingStyle = true;
		}
	}

	@Override
	public boolean validate() {
		return Widgets.get(730, 28).validate();
	}

}
