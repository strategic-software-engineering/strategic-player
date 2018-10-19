package coinwheelgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The wheel class exposes the public API that an interface may
 * use to play the game.
 *
 * @author Team Obelisk
 * @version 0.0.1
 */
public class Wheel {

    /**
     * The slots of the wheel.
     */
    private List<Slot> slots;
    /**
     * The position that the spin landed on.
     */
    private int startPosition;

    /**
     * The random number generator for making new coins.
     */
    private Random rng = new Random();

    /**
     * Constructor for wheel.
     *
     * @param numSlots The number of slots to include in the wheel
     */
    public Wheel(final int numSlots) {
        slots = new ArrayList<Slot>(numSlots);
        startPosition = 0;

        // random initial coin configuration
        for (int i = 0; i < numSlots; i++) {
            Slot s = new Slot(new Coin(), false);
            slots.add(s);
        }
    }

    /**
     * @return A string displaying the wheel state starting
     * from startPosition
     */
    public String wheelInfoAsString() {
        String positionStr = "";
        String stateStr = "";

        int position = startPosition;
        int slotsProcessed = 0;

        while (slotsProcessed < slots.size()) {
            Slot s = slots.get(position);
            if (s.isHidden()) {
                stateStr += "-";
            } else {
                CoinState state = s.getCoinState();
                stateStr += state.getAbbreviation();
            }

            slotsProcessed++;
            position = (position + 1) % slots.size();
        }

        return stateStr;
    }

    /**
     * Randomly chooses a new startPosition for the wheel.
     */
    public void spin() {
        startPosition = (int) (rng.nextDouble() * slots.size());
    }

    /**
     * Returns the coins that the player requests to see.
     * @param requestPattern The pattern of coins the player wants to see
     * @return revealedString The pattern with requested coins revealed
     */
    public String getRevealedCoins(final String requestPattern) {
        String revealedString = "";
        for (int i = 0; i < slots.size(); i++) {
            if (requestPattern.charAt(i) == '?') {
                revealedString += slots.get((i + startPosition) % slots.size())
                        .getCoinState().getAbbreviation();
            } else {
                revealedString += '-';
            }
        }
        return revealedString;
    }

    /**
     * Changes the coins to the pattern that the player requests them to be.
     * @param newCoinStates The pattern with player's changes to revealed coins
     */
    public void setNewCoinStates(final String newCoinStates) {
        for (int i = 0; i < slots.size(); i++) {
            if (newCoinStates.charAt(i) == '-') {
                continue;
            } else {
                if (newCoinStates.charAt(i) == slots.get(
                        (i + startPosition) % slots.size())
                        .getCoinState().getAbbreviation().charAt(0)) {
                    continue;
                } else {
                    slots.get((i + startPosition) % slots.size()).flipCoin();
                }
            }
        }

    }
}
