package coinwheelgame;

import java.util.Random;

/**
 * A Coin object contains reference to a CoinState enum, which has a value of HEADS or TAILS
 *
 * @author Jon Bowen, James Niedfeldt, Evan Ballinger, Doug McLaughlin, Levi Portenier
 * @version 0.0.1
 */
public class Coin {

    private CoinState state;

    /**
     * Constructor for a coin object using a given CoinState object
     * @param state
     */
    public Coin(CoinState state){
        this.state = state;
    }

    /**
     * Default constructor creates a coin object with a randomly generated CoinState
     * */
    public Coin() {
        this.state = CoinState.findByState(new Random().nextBoolean());
    }

    /**
     * @return current value of CoinState
     * */
    public CoinState getState() {
        return state;
    }

    /**
     * Toggles the value of internal CoinState*/
    public void flip() {

        if (state == CoinState.HEADS)
            state = CoinState.TAILS;
        else
            state = CoinState.HEADS;
    }
}
