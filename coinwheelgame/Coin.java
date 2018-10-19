package coinwheelgame;

import java.util.Random;

/**
 * A Coin object contains reference to a CoinState enum, which has a
 * value of HEADS or TAILS.
 *
 * @author Team Obelisk
 * @version 0.0.1
 */
public class Coin {

    /**
     * Enumeration that represents the state of the coin (heads or tails).
     */
    private CoinState state;

    /**
     * Constructor for a coin object using a given CoinState object.
     * @param initialState The initial state of the coin.
     */
    public Coin(final CoinState initialState) {
        this.state = initialState;
    }

    /**
     * Default constructor creates a coin object with a randomly
     * generated CoinState.
     */
    public Coin() {
        this.state = CoinState.findByState(new Random().nextBoolean());
    }

    /**
     * @return current value of CoinState
     */
    public CoinState getState() {
        return state;
    }

    /**
     * Toggles the value of internal CoinState.
     */
    public void flip() {

        if (state == CoinState.HEADS) {
            state = CoinState.TAILS;
        } else {
            state = CoinState.HEADS;
        }
    }
}
