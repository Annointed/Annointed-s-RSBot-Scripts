package org.annointed.scripts.walking;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.wrappers.Tile;

public enum AdvancedPath {

	MAGGIE(Paths.MAGGIE.getPath(), Teleports.DRAYNOR.getTeleports(), null,
			PathObjects.NONE.getPathObjects()), LUMBRIDGE_DUKE(
			Paths.LUMBRIDGE_DUKE.getPath(), Teleports.LUMBRIDGE.getTeleports(),
			null, PathObjects.LUMBRIDGE_DUKE.getPathObjects()), DUKE_WIZARD(
			Paths.DUKE_WIZARD.getPath(), Teleports.LUMBRIDGE.getTeleports(),
			null, PathObjects.DUKE_WIZARD.getPathObjects()), WIZARD_VARROCK(
			Paths.WIZARD_VARROCK.getPath(), Teleports.LUMBRIDGE.getTeleports(),
			null, PathObjects.NONE.getPathObjects()), START_COOK(
			Paths.START_COOK.getPath(), Teleports.LUMBRIDGE.getTeleports(),
			null, PathObjects.NONE.getPathObjects()), COOK_BUCKET(
			Paths.COOK_BUCKET.getPath(), null, null, PathObjects.COOK_BUCKET
					.getPathObjects()), BUCKET_COW(Paths.BUCKET_COW.getPath(),
			Teleports.LUMBRIDGE.getTeleports(), null, PathObjects.BUCKET_COW
					.getPathObjects()), COW_EGG(Paths.COW_EGG.getPath(), null,
			null, PathObjects.COW_EGG.getPathObjects()), EGG_MILL(
			Paths.EGG_MILL.getPath(), null, null, PathObjects.GRAIN_MILL
					.getPathObjects()), EGG_GRAIN(Paths.EGG_GRAIN.getPath(),
			null, null, PathObjects.EGG_GRAIN.getPathObjects()),

	CHURCH(Paths.START_FATHER.getPath(), Teleports.LUMBRIDGE.getTeleports(),
			null, PathObjects.CHURCH.getPathObjects()),

	FATHER(Paths.FATHER_FATHER2.getPath(), null, null, PathObjects.FATHER
			.getPathObjects()), GHOST(Paths.FATHER2_GHOST.getPath(), null,
			null, PathObjects.NONE.getPathObjects()), HEAD(Paths.GHOST_SKULL
			.getPath(), null, null, PathObjects.NONE.getPathObjects()), GHOST_2(
			Paths.SKULL_GHOST.getPath(), null, null, PathObjects.NONE
					.getPathObjects()), MORGAN_BAR(Paths.MORGAN_BAR.getPath(),
			Teleports.LUMBRIDGE.getTeleports(), null, PathObjects.BAR
					.getPathObjects()), BAR_MANOR(Paths.BAR_MANOR.getPath(),
			null, null, PathObjects.MANOR.getPathObjects()), START_MORGAN(
			Paths.START_MORGAN.getPath(), Teleports.LUMBRIDGE.getTeleports(),
			null, PathObjects.MORGAN.getPathObjects()), GE(Paths.LUMBRIDGE_GE
			.getPath(), Teleports.LUMBRIDGE.getTeleports(), null,
			PathObjects.NONE.getPathObjects()), HAS(Paths.LUMBRIDGE_HAS
			.getPath(), Teleports.LUMBRIDGE.getTeleports(), null,
			PathObjects.GATE.getPathObjects()), OSM(Paths.HAS_OS.getPath(),
			null, null, PathObjects.NONE.getPathObjects()), LEEL(Paths.OS_LEEL
			.getPath(), Teleports.LUMBRIDGE.getTeleports(), null,
			PathObjects.NONE.getPathObjects()), KEL_OS(Paths.KELI_OS.getPath(),
			Teleports.LUMBRIDGE.getTeleports(), null, PathObjects.GATE
					.getPathObjects()), HAS_2(Paths.LUMBRIDGE_HAS.getPath(),
			Teleports.LUMBRIDGE.getTeleports(), null, PathObjects.GATE_2
					.getPathObjects()), LUMB_NED(Paths.LUMB_NED.getPath(),
			Teleports.LUMBRIDGE.getTeleports(), null, PathObjects.NED
					.getPathObjects()), NED_AG(Paths.NED_AG.getPath(), null,
			null, PathObjects.AG.getPathObjects()), AG_LAD(Paths.AG_KELI
			.getPath(), null, null, PathObjects.LAD.getPathObjects()), LUMB_LOGS(
			Paths.LUMB_LOGS.getPath(), Teleports.LUMBRIDGE.getTeleports(),
			null, PathObjects.NONE.getPathObjects()), ERNEST_START(
			Paths.ERNEST_START.getPath(), Teleports.DRAYNOR.getTeleports(),
			null, PathObjects.NONE.getPathObjects()), START_DORIC(
			Paths.START_DORIC.getPath(), Teleports.FALADOR.getTeleports(),
			null, PathObjects.START_DORIC.getPathObjects()), DORIC_MINE(
			Paths.DORIC_MINE.getPath(), Teleports.FALADOR.getTeleports(), null,
			PathObjects.DORIC_MINE.getPathObjects()), IRON_CLAY(
			Paths.COPPER_CLAY.getPath(), null, null, PathObjects.NONE
					.getPathObjects()), START_BLOODPACT(Paths.START_BLOODPACT
			.getPath(), Teleports.LUMBRIDGE.getTeleports(), null,
			PathObjects.NONE.getPathObjects()), GUNNAR_EDGE(Paths.GUNNAR_EDGE
			.getPath(), null, null, PathObjects.NONE.getPathObjects()), GUNNAR_GUD(
			Paths.GUNNAR_GUDRUN.getPath(), null, null, PathObjects.NONE
					.getPathObjects()), GUD_FATHER(Paths.GUDRUN_FATHER
			.getPath(), null, null, PathObjects.GUDRUN_FATHER.getPathObjects());

	private final Tile[] path;
	private final PathObject[] objects;
	private final Teleport[] teleport;
	private final InteractableNPC npc;

	AdvancedPath(Tile[] path, Teleport[] teleport, InteractableNPC npc,
			PathObject... objects) {
		this.path = path;
		this.objects = objects;
		this.teleport = teleport;
		this.npc = npc;
	}

	public boolean walkPath() {
		return WalkMethods.walkPath(path, teleport, npc, objects);
	}

	public boolean walkPathReversed() {
		return WalkMethods.walkPath(WalkMethods.reversePath(path), teleport,
				npc, objects);
	}

	public boolean atEnd() {
		return Calculations.distanceTo(path[path.length - 1]) < 5
				&& path[path.length - 1].canReach();
	}

	public boolean atStart() {
		return Calculations.distanceTo(path[0]) < 5 && path[0].canReach();
	}

	public PathObject[] getObjects() {
		return objects;
	}

}
