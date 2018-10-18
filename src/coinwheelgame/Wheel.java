package coinwheelgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The wheel class exposes the public API that an interface may
 * use to play the game.
 *
 * @author Jon Bowen, James Niedfeldt, Evan Ballinger, Doug McLaughlin, Levi Portenier
 * @version 0.0.1
 */
public class Wheel {

    private List<Slot> _slots;  // list of wheel slots
    private int _startPosition; // position that spin landed on

    //private long RANDOM_SEED = 2018;
    //private Random _rng = new Random(RANDOM_SEED);
    private Random _rng = new Random();

    /**
     * Constructor for wheel.
     *
     * @param numSlots The number of slots to include in the wheel
     */
    public Wheel(int numSlots) {
        _slots = new ArrayList<Slot>(numSlots);
        _startPosition = 0;

        // random initial coin configuration
        for (int i = 0; i < numSlots; i++) {
            Slot s = new Slot(new Coin(), false);
            _slots.add(s);
        }
    }

    /**
     * @return A string displaying the wheel state starting
     * from _startPosition
     */
    public String wheelInfoAsString() {
        String positionStr = "";
        String stateStr = "";

        int position = _startPosition;
        int slotsProcessed = 0;

        while (slotsProcessed < _slots.size()) {
            Slot s = _slots.get(position);
            if (s.isHidden()) {
                stateStr += "-";
            } else {
                CoinState state = s.getCoinState();
                stateStr += state.getAbbreviation();
            }

            slotsProcessed++;
            position = (position + 1) % _slots.size();
        }

        return stateStr;
    }

    /**
     * Randomly chooses a new _startPosition for the wheel
     */
    public void spin() {
        _startPosition = (int) (_rng.nextDouble() * _slots.size());
    }

    /**
     * Returns the coins that the player requests to see
     */
    public String getRevealedCoins(String requestPattern) {
        String revealedString = "";
        for (int i = 0; i < _slots.size(); i++) {
            if (requestPattern.charAt(i) == '?') {
                revealedString += _slots.get((i + _startPosition) % _slots.size()).getCoinState().getAbbreviation();
            } else {
                revealedString += '-';
            }
        }
        return revealedString;
    }

    /**
     * Changes the coins to the pattern that the player requests them to be
     */
    public void setNewCoinStates(String newCoinStates) {
        for (int i = 0; i < _slots.size(); i++) {
            if (newCoinStates.charAt(i) == '-') {
                continue;
            } else {
                if (newCoinStates.charAt(i) == _slots.get((i + _startPosition) % _slots.size()).getCoinState().getAbbreviation().charAt(0)) {
                    continue;
                } else {
                    _slots.get((i + _startPosition) % _slots.size()).flipCoin();
                }
            }
        }

    }
}
