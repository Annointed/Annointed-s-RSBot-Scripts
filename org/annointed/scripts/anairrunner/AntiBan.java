package org.annointed.scripts.anairrunner;

//Credits to _phl0w
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;

public class AntiBan extends Strategy implements Runnable {

	@Override
	public void run() {
		if (Random.nextInt(1, 250) < 14) {
			executeAntiban();
		}
		Time.sleep(Random.nextInt(1000, 2000));
	}

	@Override
	public boolean validate() {
		return Game.isLoggedIn();
	}

	public void executeAntiban() {
		int dx, dy;
		int r = Random.nextInt(0, 5);
		switch (r) {
		case 0:
		case 1:
		default:
			Camera.setAngle(Random.nextInt(20, 300));
			break;
		case 2:
		case 3:
			dx = Random.nextInt(-30, 30);
			dy = Random.nextInt(-30, 30);
			Mouse.move(Mouse.getX() + dx, Mouse.getY() + dy);
			Time.sleep(Random.nextInt(20, 150));
			break;
		}
	}
}