package org.annointed.scripts.walking;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public enum InteractableNPC {

	Shantay(838, "Bribe", new String[] { "Yes", "Offer him" }, new Tile(3304,
			3118, 0));

	private final int id;
	private final String talkAction;
	private final String[] interfaceActions;
	private final Tile location;

	InteractableNPC(int id, String talkAction, String[] interfaceActions,
			Tile location) {
		this.id = id;
		this.talkAction = talkAction;
		this.interfaceActions = interfaceActions;
		this.location = location;
	}

	public Tile getLocation() {
		return location;
	}

	public void talk() {
		for (String g : interfaceActions) {
			WidgetChild wc = getWidgetChild(g);
			if (wc != null && wc.validate()) {
				if (wc.click(true)) {
					Time.sleep(2000);
				}
			}
		}
		NPC instance = NPCs.getNearest(id);
		if (instance.isOnScreen()) {
			if (instance.interact(talkAction)) {
				Time.sleep(1000);
			}
		}

	}

	public NPC getInstance() {
		return NPCs.getNearest(id);
	}

	private WidgetChild getWidgetChild(String text) {
		for (Widget w : Widgets.getLoaded()) {
			for (WidgetChild wc : w.getChildren()) {
				if (wc.getText().contains(text)) {
					return wc;
				}
			}
		}
		return null;
	}
}
