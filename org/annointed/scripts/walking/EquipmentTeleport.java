package org.annointed.scripts.walking;

import org.powerbot.game.api.methods.Tabs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Time;

public enum EquipmentTeleport implements Teleport {

	AMULET_OF_GLORY_EDGEVILLE(new int[] { 1712, 1710, 1708, 1706 },
			"Edgeville", Equipment.NECK), AMULET_OF_GLORY_AL_KHARID(new int[] {
			1712, 1710, 1708, 1706 }, "kharid", Equipment.NECK);

	private final int[] id;
	private final String action;
	private final Equipment slot;

	EquipmentTeleport(int[] id, String action, Equipment slot) {
		this.id = id;
		this.action = action;
		this.slot = slot;
	}

	@Override
	public void teleport() {
		if (!Tabs.EQUIPMENT.open()) {
			Tabs.EQUIPMENT.open();
		}
		if (slot.interact(action)) {
			Time.sleep(3000);
			while (Players.getLocal().getAnimation() != -1) {
				Time.sleep(50);
			}
		}
	}

	@Override
	public boolean canUse() {
		return arrayHasInt(id, slot.getEquipped());
	}

	@Override
	public int[] getID() {
		return id;
	}

	public Equipment getSlot() {
		return slot;
	}

	public static boolean arrayHasInt(int[] array, int i) {
		for (int k : array) {
			if (k == i) {
				return true;
			}
		}
		return false;
	}
}
