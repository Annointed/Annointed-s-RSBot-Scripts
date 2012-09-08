package org.annointed.scripts.walking;

import org.annointed.scripts.sleep.Sleep;
import org.annointed.scripts.sleep.Sleeper;
import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public enum Lodestone implements Teleport {

	LUMBRIDGE(47), EDGEVILLE(45), PORT_SARIM(48), YANILLE(52), AL_KHARID(40), FALADOR(
			46), VARROCK(51), BURTHOPE(42), TAVERLY(50), DRAYNOR(44), CATHERBY(
			43), SEERS_VILLAGE(49), ARDOUGNE(41);

	private final int widgetchild;
	private final int main = 1092;

	private final static int interfaceMain = 192;
	private final static int interfaceChild = 24;

	private final static int interfaceMain2 = 193;
	private final static int interfaceChild2 = 48;

	Lodestone(int widgetchild) {
		this.widgetchild = widgetchild;
	}

	public static boolean openInterface() {
		if (!Tabs.MAGIC.isOpen()) {
			Tabs.MAGIC.open();
		}

		WidgetChild theChild = Widgets.get(interfaceMain, interfaceChild);
		WidgetChild theChild2 = Widgets.get(interfaceMain2, interfaceChild2);

		if (theChild.validate()) {
			return theChild.click(true);

		} else {
			return theChild2.click(true);
		}
	}

	public boolean click() {
		return Widgets.get(main, widgetchild).click(true);
	}

	@Override
	public void teleport() {
		if (openInterface()) {
			Time.sleep(1000);
			if (click()) {
				Sleep.conditionally(5000, new Sleeper() {

					@Override
					public boolean kill() {
						return Players.getLocal().getAnimation() != -1;
					}

				});
				Time.sleep(1000);
				while (Players.getLocal().getAnimation() != -1) {
					Time.sleep(50);
				}
			}
		}

	}

	@Override
	public boolean canUse() {
		return true;
	}

	@Override
	public int[] getID() {
		return null;
	}
}
