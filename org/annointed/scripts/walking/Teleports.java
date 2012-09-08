package org.annointed.scripts.walking;

public enum Teleports {

	EDGEVILLE(new Teleport[] { EquipmentTeleport.AMULET_OF_GLORY_EDGEVILLE,
			Lodestone.EDGEVILLE }), LUMBRIDGE(
			new Teleport[] { Lodestone.LUMBRIDGE }), VARROCK(
			new Teleport[] { Lodestone.VARROCK }), FALADOR(
			new Teleport[] { Lodestone.FALADOR }), TAVERLY(
			new Teleport[] { Lodestone.TAVERLY }), SEERS_VILLAGE(
			new Teleport[] { Lodestone.SEERS_VILLAGE }), ARDOUGNE(
			new Teleport[] { Lodestone.ARDOUGNE }), PORT_SARIM(
			new Teleport[] { Lodestone.PORT_SARIM }), AL_KHARID(new Teleport[] {
			EquipmentTeleport.AMULET_OF_GLORY_AL_KHARID, Lodestone.AL_KHARID }), DRAYNOR(
			new Teleport[] { Lodestone.DRAYNOR });

	private final Teleport[] teleports;

	Teleports(Teleport[] teleports) {
		this.teleports = teleports;
	}

	public Teleport[] getTeleports() {
		return teleports;
	}
}
