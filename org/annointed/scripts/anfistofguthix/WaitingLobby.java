package org.annointed.scripts.anfistofguthix;

import org.annointed.scripts.anfistofguthix.constants.Constants;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.util.Time;

public class WaitingLobby extends Strategy implements Runnable {

	@Override
	public void run() {
		Constants.inWaitingLobby = true;
		while (Constants.inWaitingLobby) {
			Time.sleep(50);
		}
	}

	@Override
	public boolean validate() {
		return Widgets.get(731, 16).validate();
	}

}
