package org.annointed.scripts.sleep;

import org.powerbot.game.api.util.Time;

public class Sleep {

	public static void conditionally(final int time, final Sleeper condition) {
		if (time > 10000) {
			System.out.println("Excessive sleep attempted.");
			return;
		}

		for (int i = 0; i < time / 50 && !condition.kill(); i++) {
			Time.sleep(50);
		}
	}
}
