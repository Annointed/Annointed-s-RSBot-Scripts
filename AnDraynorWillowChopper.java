import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.bot.event.listener.PaintListener;

@Manifest(authors = { "Annointed" }, name = "AnDraynorWillowChopper", description = "Cuts willow trees in Draynor", version = 0.1)
public class AnDraynorWillowChopper extends ActiveScript implements
		PaintListener {

	public static final int WILLOW_LOG_ID = 1519;
	public static final int[] WILLOW_TREE_IDS = { 38616, 38627, 58006 };
	public static final int[] AXE_IDS = { 1351, 1357, 1361, 6739, 1349, 1355,
			1359, 1353 };

	public static String Status = "";
	public static long startTime = System.currentTimeMillis();
	public static int startWoodcuttingExp;
	public static int currentWcExp;
	public static int woodcuttingExpGained;

	@Override
	protected void setup() {
		if (Game.isLoggedIn()) {
			startWoodcuttingExp = Skills.getExperience(Skills.WOODCUTTING);
		}
		provide(new Chopper());
		provide(new Dropper());
	}

	private class Chopper extends Strategy implements Runnable {

		boolean interact(Entity entity, int lowerBound, int upperBound,
				String action) {
			while (!Mouse.isReady())
				;
			if (entity == null || !entity.validate()) {
				return false;
			}
			Point entityPoint = new Point(entity.getCentralPoint().x
					+ Random.nextInt(lowerBound, upperBound),
					entity.getCentralPoint().y
							+ Random.nextInt(lowerBound, upperBound));
			while (!Mouse.getLocation().equals(entityPoint)) {
				Mouse.hop(
						entity.getCentralPoint().x
								+ Random.nextInt(lowerBound, upperBound),
						entity.getCentralPoint().y
								+ Random.nextInt(lowerBound, upperBound));
			}
			if (Mouse.getLocation().equals(entityPoint)) {
				Mouse.click(false);
				if (Menu.contains(action)) {
					Menu.select(action);
				}
			}
			return true;
		}

		@Override
		public boolean validate() {
			return !Inventory.isFull()
					&& Players.getLocal().getAnimation() == -1;
		}

		@Override
		public void run() {
			SceneObject willowTree = SceneEntities.getNearest(WILLOW_TREE_IDS);
			if (willowTree != null) {
				if (willowTree.isOnScreen()) {
					interact(willowTree, 1, 7, "Chop");
					Status = "Chopping";
					Time.sleep(1000, 1500);
				} else {
					Camera.turnTo(willowTree);
					Walking.walk(willowTree);
				}
			}
		}

	}

	private class Dropper extends Strategy implements Runnable {

		void dropAllExcept(int lowerBound, int upperBound, int... dontDropThis) {
			for (Item i : Inventory.getItems()) {
				for (int id : dontDropThis) {
					if (!(i.getId() == id)) {
						Mouse.hop(
								i.getWidgetChild().getAbsoluteX()
										+ Random.nextInt(lowerBound, upperBound),
								i.getWidgetChild().getAbsoluteY()
										+ Random.nextInt(lowerBound, upperBound));
						i.getWidgetChild().interact("Drop");
						Status = "Dropping logs";
					}
				}
			}
		}

		@Override
		public boolean validate() {
			return Inventory.isFull();
		}

		@Override
		public void run() {
			dropAllExcept(1, 3, AXE_IDS);
			Time.sleep(500);
		}

	}

	// Credits to phl0w
	public class Antiban extends Strategy implements Runnable {

		@Override
		public boolean validate() {
			return Game.isLoggedIn();
		}

		@Override
		public void run() {
			if (Random.nextInt(1, 250) < 14) {
				executeAntiban();
			}
			Time.sleep(Random.nextInt(1000, 2000));
		}

		public void executeAntiban() {
			int dx, dy;
			int r = Random.nextInt(0, 4);
			switch (r) {
			case 0:
			case 1:
			default:
				Status = "AntiBan";
				Camera.setAngle(Random.nextInt(20, 300));
				break;
			case 2:
			case 3:
				Status = "AntiBan";
				dx = Random.nextInt(-30, 30);
				dy = Random.nextInt(-30, 30);
				Mouse.move(Mouse.getX() + dx, Mouse.getY() + dy);
				Time.sleep(Random.nextInt(20, 150));
				break;
			}
		}

	}

	@Override
	public void onRepaint(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;

		final Color color1 = new Color(213, 214, 175);
		final Color color2 = new Color(0, 0, 0);
		final Color color3 = new Color(1, 1, 1);

		final BasicStroke stroke1 = new BasicStroke(1);

		final Font font1 = new Font("Verdana", 3, 18);
		final Font font2 = new Font("Verdana", 1, 12);

		long runTime = 0;
		long seconds = 0;
		long minutes = 0;
		long hours = 0;
		runTime = System.currentTimeMillis() - startTime;
		seconds = runTime / 1000;
		if (seconds >= 60) {
			minutes = seconds / 60;
			seconds -= (minutes * 60);
		}

		if (minutes >= 60) {
			hours = minutes / 60;
			minutes -= (hours * 60);
		}

		if (Game.isLoggedIn()) {
			currentWcExp = Skills.getExperience(Skills.WOODCUTTING);
			woodcuttingExpGained = currentWcExp - startWoodcuttingExp;

			g.setColor(color1);
			g.fillRect(8, 394, 487, 114);
			g.setColor(color2);
			g.setStroke(stroke1);
			g.drawRect(8, 394, 487, 114);
			g.setFont(font1);
			g.setColor(color3);
			g.drawString("AnDraynorWillowChopper v0.1", 219, 421);
			g.setFont(font2);
			g.drawString("Status: " + Status, 18, 433);
			g.drawString(
					"Run Time: "
							+ Time.format(System.currentTimeMillis()
									- startTime), 18, 452);
			g.drawString("Xp gained: " + woodcuttingExpGained, 19, 472);
			g.drawString(
					"WoodCutting Level: " + Skills.getLevel(Skills.WOODCUTTING),
					18, 491);
		}
	}
}
