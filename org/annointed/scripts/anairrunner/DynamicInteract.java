package org.annointed.scripts.anairrunner;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.node.Menu;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Entity;
import org.powerbot.game.api.wrappers.ViewportEntity;
import org.powerbot.game.api.wrappers.graphics.CapturedModel;
import org.powerbot.game.bot.Context;
import org.powerbot.game.bot.handler.input.a;
import org.powerbot.game.bot.handler.input.util.MouseNode;

/**
 * @author Bradsta
 * 
 *         A clicker that changes destinations as its moving to provide an
 *         accurate click.
 */
public class DynamicInteract {

	/**
	 * Interacts with an entity. Grabs a point from the {@link CapturedModel}.
	 * Uses a slightly modified version of the built in mouse method, changes
	 * the mouse destination as it is moving toward the target {@link Entity}.
	 * <p>
	 * 
	 * @param entity
	 *            Entity to interact with
	 * @return <tt>true</tt> if click was successful.
	 */
	public static boolean interact(final Entity entity, final String action) {
		if (entity.isOnScreen()) {
			Point destination = getCenterPoint(entity);

			if (destination != null) {
				return interact(entity, action, destination.x, destination.y);
			}
		}

		return false;
	}

	/**
	 * Returns <tt>true</tt> if the destination point is within the target
	 * entity bounds.
	 * <p>
	 * Uses Rectangle2D as a means of a more general area. But not by too much.
	 * 
	 * @param target
	 *            Target entity to interact with
	 * @param destination
	 *            Destination point calculated previously
	 * @return Returns <tt>true</tt> if the target pounds contains destination
	 */
	private static boolean containsPoint(final Entity target,
			final Point destination) {
		final Polygon p = target.getBounds()[0];

		if (p != null) {
			final Rectangle2D bounds = p.getBounds2D(); // More of a general
														// area

			if (bounds != null) {
				return bounds.contains(destination);
			}
		}

		return false;
	}

	// Copied from the API (All credits to Timer)
	// Removed randomX/Y
	private static MouseNode create(final int x, final int y) {
		return new MouseNode(new ViewportEntity() {
			private final Rectangle area = new Rectangle(x, y, 0, 0);

			public Point getCentralPoint() {
				return new Point(x, y);
			}

			public Point getNextViewportPoint() {
				return new Point(x, y);
			}

			public boolean contains(final Point point) {
				return area.contains(point);
			}

			public boolean validate() {
				return Calculations.isOnScreen(x, y);
			}
		}, new Filter<Point>() {
			public boolean accept(final Point point) {
				return true;
			}
		});
	}

	/**
	 * Returns a center point within the target's bounds used to interact.
	 * <p>
	 * Only a target point, not necessarily the point that it will click.
	 * 
	 * @param target
	 *            The target entity to interact with.
	 * @return A random point within the target's bounds.
	 */
	private static Point getCenterPoint(final Entity target) {
		final Polygon p = target.getBounds()[0];

		if (p != null) {
			final Rectangle2D bounds = p.getBounds2D();

			if (bounds != null) {
				return new Point((int) bounds.getCenterX(),
						(int) bounds.getCenterY());
			}
		}

		return null;
	}

	// Copied from the API (Credits: Timer)
	// A few minor modifications by Bradsta to change destinations as the mouse
	// is moving.
	private static boolean interact(final Entity target, final String action,
			final int x, final int y) {
		final a executor = Context.get().getExecutor();

		MouseNode node = create(x, y);
		Point destination = new Point(x, y);

		while (node.a().isRunning() && node.l()) { // processable
			// if (containsPoint(target, destination)
			// && Menu.select(action)) return true; //No need to move mouse to
			// center if mouse is already within polygon and action is
			// available.

			executor.a(node); // step

			if (!containsPoint(target, destination)
					&& (destination = getCenterPoint(target)) != null) {
				node = create(destination.x, destination.y);
			}
		}

		// node.f() completed()
		return Menu.select(action); // Called after executor should be completed
	}

}