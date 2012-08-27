package org.annointed.scripts.anairrunner;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.input.Keyboard;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.Player;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.widget.Widget;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

/**
 * Trade handling. Ported from old code. Original Authors:
 * 
 * @author Timer
 * @author kyleshay
 * 
 *         Port for RSBot v4
 * @author kyleshay
 */
public class Trade {
	public static final int WIDGET_TRADE_MAIN = 335;
	public static final int WIDGET_TRADE_SECOND = 334;
	public static final int WIDGET_TRADE_MAIN_NAME = 15;
	public static final int WIDGET_TRADE_SECOND_NAME = 54;
	public static final int WIDGET_TRADE_MAIN_OUR = 30;
	public static final int WIDGET_TRADE_MAIN_THEIR = 33;
	public static final int WIDGET_TRADE_MAIN_ACCEPT = 17;
	public static final int WIDGET_TRADE_MAIN_DECLINE = 19;
	public static final int WIDGET_TRADE_SECOND_ACCEPT = 36;
	public static final int WIDGET_TRADE_SECOND_DECLINE = 37;
	public static final int WIDGET_TRADE_OUR_AMOUNT = 43;
	public static final int WIDGET_TRADE_THEIR_AMOUNT = 44;

	private final static int WIDGET_TRADE_MAIN_INV_SLOTS = 21;

	public static final int TRADE_TYPE_MAIN = 0;
	public static final int TRADE_TYPE_SECONDARY = 1;
	public static final int TRADE_TYPE_NONE = 2;

	/**
	 * Are we in the first stage of a trade?
	 * 
	 * @return <tt>true</tt> if in first stage.
	 */
	public static boolean inTradeMain() {
		final Widget Trade = Widgets.get(WIDGET_TRADE_MAIN);
		return Trade != null && Trade.validate();
	}

	/**
	 * Are we in the second stage of a trade?
	 * 
	 * @return <tt>true</tt> if in second stage.
	 */
	public static boolean inTradeSecond() {
		final Widget Trade = Widgets.get(WIDGET_TRADE_SECOND);
		return Trade != null && Trade.validate();
	}

	/**
	 * Checks if you're in a trade.
	 * 
	 * @return <tt>true</tt> if you're trading; otherwise <tt>false</tt>.
	 */
	public static boolean inTrade() {
		return inTradeMain() || inTradeSecond();
	}

	/**
	 * Trades a player.
	 * 
	 * @param playerName
	 *            The player's name.
	 * @param tradeWait
	 *            Timeout to wait for the trade.
	 * @return <tt>true</tt> if traded.
	 */
	public static boolean tradePlayer(final String playerName,
			final int tradeWait) {
		if (!inTrade()) {
			final Player targetPlayer = Players
					.getNearest(new Filter<Player>() {
						public boolean accept(Player loc) {
							return loc != null
									&& loc.getName().equalsIgnoreCase(
											playerName);
						}
					});
			return targetPlayer != null
					&& targetPlayer.interact("Trade with",
							targetPlayer.getName())
					&& waitForTrade(TRADE_TYPE_MAIN, tradeWait);
		} else {
			return isTradingWith(playerName);
		}
	}

	/**
	 * Trades a player.
	 * 
	 * @param playerName
	 *            The player's name.
	 * @return <tt>true</tt> if traded.
	 */
	public static boolean tradePlayer(final String playerName) {
		return tradePlayer(playerName, 15000);
	}

	/**
	 * Trades a player.
	 * 
	 * @param targetPlayer
	 *            The player you wish to trade.
	 * @param tradeWait
	 *            The time out for the trade.
	 * @return <tt>true</tt> if traded.
	 */
	public static boolean tradePlayer(final Player targetPlayer,
			final int tradeWait) {
		if (!inTrade()) {
			return targetPlayer != null
					&& targetPlayer.interact("Trade with",
							targetPlayer.getName())
					&& waitForTrade(TRADE_TYPE_MAIN, tradeWait);
		} else {
			return isTradingWith(targetPlayer.getName());
		}
	}

	/**
	 * Trades a player.
	 * 
	 * @param targetPlayer
	 *            The desired player.
	 * @return <tt>true</tt> if traded.
	 */
	public static boolean tradePlayer(final Player targetPlayer) {
		return tradePlayer(targetPlayer, 15000);
	}

	/**
	 * Accepts a trade
	 * 
	 * @return <tt>true</tt> on accept.
	 */
	public static boolean acceptTrade() {
		if (inTradeMain()) {
			return Widgets.get(WIDGET_TRADE_MAIN, WIDGET_TRADE_MAIN_ACCEPT)
					.interact("Accept");
		} else {
			return inTradeSecond()
					&& Widgets.get(WIDGET_TRADE_SECOND,
							WIDGET_TRADE_SECOND_ACCEPT).interact("Accept");
		}
	}

	/**
	 * Declines a trade
	 * 
	 * @return <tt>true</tt> on decline
	 */
	public static boolean declineTrade() {
		if (inTradeMain()) {
			return Widgets.get(WIDGET_TRADE_MAIN, WIDGET_TRADE_MAIN_DECLINE)
					.interact("Decline");
		} else {
			return inTradeSecond()
					&& Widgets.get(WIDGET_TRADE_SECOND,
							WIDGET_TRADE_SECOND_DECLINE).interact("Decline");
		}
	}

	/**
	 * Waits for Trade type to be true.
	 * 
	 * @param tradeType
	 *            The Trade type.
	 * @param timeOut
	 *            Time out of waiting.
	 * @return <tt>true</tt> if true, otherwise false.
	 */
	public static boolean waitForTrade(final int tradeType, final long timeOut) {
		final long timeCounter = System.currentTimeMillis() + timeOut;
		while (timeCounter - System.currentTimeMillis() > 0) {
			switch (tradeType) {
			case TRADE_TYPE_MAIN:
				if (inTradeMain()) {
					return true;
				}
				break;
			case TRADE_TYPE_SECONDARY:
				if (inTradeSecond()) {
					return true;
				}
				if (!inTrade()) {
					Time.sleep(1000);
					if (!inTrade()) {
						return false;
					}
				}
				break;
			case TRADE_TYPE_NONE:
				if (!inTrade()) {
					return true;
				}
				break;
			}
			Time.sleep(5);
		}
		return false;
	}

	/**
	 * Gets who you're trading with.
	 * 
	 * @return The person's name you're trading with.
	 */
	public static String getTradingWith() {
		if (inTradeMain()) {
			final String name = Widgets.get(WIDGET_TRADE_MAIN,
					WIDGET_TRADE_MAIN_NAME).getText();
			return name.substring(name.indexOf(": ") + 2);
		} else if (inTradeSecond()) {
			return Widgets.get(WIDGET_TRADE_SECOND, WIDGET_TRADE_SECOND_NAME)
					.getText();
		}
		return null;
	}

	/**
	 * Checks if you're trading with someone.
	 * 
	 * @param name
	 *            The person's name.
	 * @return <tt>true</tt> if true; otherwise <tt>false</tt>.
	 */
	public static boolean isTradingWith(final String name) {
		return getTradingWith().equals(name);
	}

	/**
	 * Returns the total number of items offered by another player
	 * 
	 * @return The number of items offered.
	 */
	public static int getNumberOfItemsOffered() {
		int number = 0;
		for (int i = 0; i < 28; i++) {
			if (Widgets.get(WIDGET_TRADE_MAIN, WIDGET_TRADE_MAIN_THEIR)
					.getChild(i).getChildStackSize() != 0) {
				++number;
			}
		}
		return number;
	}

	/**
	 * Returns the items offered by another player
	 * 
	 * @return The items offered.
	 */
	public static Item[] getItemsOffered() {
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < 28; i++) {
			WidgetChild component = Widgets.get(WIDGET_TRADE_MAIN,
					WIDGET_TRADE_MAIN_THEIR).getChild(i);
			if (component != null && component.getChildStackSize() != 0) {
				items.add(new Item(component));
			}
		}
		return items.toArray(new Item[items.size()]);
	}

	/**
	 * Returns the total number of free slots the other player has
	 * 
	 * @return The number of free slots.
	 */
	public static int getFreeSlots() {
		if (inTradeMain()) {
			String text = Widgets
					.get(WIDGET_TRADE_MAIN, WIDGET_TRADE_MAIN_INV_SLOTS)
					.getText().substring(4, 6);
			text = text.trim();
			try {
				return Integer.parseInt(text);
			} catch (final Exception ignored) {
			}
		}
		return 0;
	}

	/**
	 * Checks if you have offered any item.
	 * 
	 * @return <tt>true</tt> if something has been offered; otherwise
	 *         <tt>false</tt>.
	 */
	public static boolean isWealthOffered() {
		if (!inTradeMain())
			return false;
		return Widgets.get(WIDGET_TRADE_MAIN, WIDGET_TRADE_OUR_AMOUNT)
				.getText().indexOf("Nothing") == -1;
	}

	/**
	 * Checks if other player has offered any item.
	 * 
	 * @return <tt>true</tt> if something has been offered; otherwise
	 *         <tt>false</tt>.
	 */
	public static boolean isWealthReceived() {
		if (!inTradeMain())
			return false;
		return Widgets.get(WIDGET_TRADE_MAIN, WIDGET_TRADE_THEIR_AMOUNT)
				.getText().indexOf("Nothing") == -1;
	}

	/**
	 * If Trade main is open, offers specified amount of an item.
	 * 
	 * @param itemID
	 *            The ID of the item.
	 * @param number
	 *            The amount to offer. 0 deposits All. 1,5,10 offer
	 *            corresponding amount while other numbers offer X.
	 * @return <tt>true</tt> if successful; otherwise <tt>false</tt>.
	 */
	public static boolean offer(final int itemID, final int number) {
		if (!inTradeMain())
			return false;
		if (number < 0) {
			throw new IllegalArgumentException("number < 0 (" + number + ")");
		}
		Item item = Inventory.getItem(itemID);
		final int itemCount = Inventory.getCount(true, itemID);
		if (item == null) {
			return true;
		}
		switch (number) {
		case 0:
			item.getWidgetChild().interact(
					itemCount > 1 ? "Offer-All" : "Offer");
			break;
		case 1:
			item.getWidgetChild().interact("Offer");
			break;
		default:
			if (!item.getWidgetChild().interact("Offer-" + number)) {
				if (item.getWidgetChild().interact("Offer-X")) {
					Time.sleep(Random.nextInt(1000, 1300));
					Keyboard.sendText(String.valueOf(number), true);
				}
			}
			break;
		}
		Time.sleep(300);
		return (Inventory.getCount(itemID) < itemCount)
				|| (Inventory.getCount() == 0);
	}
}