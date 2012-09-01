package org.annointed.scripts.anfistofguthix;

import org.annointed.scripts.anfistofguthix.constants.Constants;
import org.annointed.scripts.anfistofguthix.wrappers.Methods;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.SceneObject;

public class EnterLobby extends Strategy implements Runnable {

	@Override
	public void run() {
		SceneObject passageWay = SceneEntities
				.getNearest(Constants.ENTER_PASSAGEWAY_ID);
		if (passageWay.isOnScreen()) {
			Methods.interact(passageWay, "Go-through");
			Time.sleep(3000, 3500);
		}
	}

	@Override
	public boolean validate() {
		NPC fiara = NPCs.getNearest(Constants.FIARA_ID);
		SceneObject passageWay = SceneEntities
				.getNearest(Constants.ENTER_PASSAGEWAY_ID);
		return fiara != null && passageWay != null;
	}

}
