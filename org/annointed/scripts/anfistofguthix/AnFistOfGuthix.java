package org.annointed.scripts.anfistofguthix;

import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;

@Manifest(authors = { "Annointed" }, name = "AnFistOfGuthix", description = "Plays Fist of Guthix", version = 0.1)
public class AnFistOfGuthix extends ActiveScript {

	@Override
	protected void setup() {
		provide(new WaitingLobby());
		provide(new DetermineRole());
		provide(new Hunting());
	}

}
