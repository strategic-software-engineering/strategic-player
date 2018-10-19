package coinwheelgame;

/**
 * Represents possible states of coins in the game.
 *
 * @author Team Obelisk
 * @version 0.0.1
 */
public enum CoinState {
    /**
     * Coin is heads (true) or tails (false).
     */
    HEADS("H", true), TAILS("T", false);

    /**
     * Abbreviations are H or T.
     */
    private String abbreviation;
    /**
     * The state of the coin (heads or tails).
     */
    private Boolean state;

    /**
     * Returns the abbreviation of the current state.
     * @return abbreviation for the current state
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Returns the current state.
     * @return current state
     */
    public Boolean getState() {
        return state;
    }

    /**
     * Constructor for the CoinState.
     * @param newAbbreviation abbreviation of the coin state
     * @param newState state of the coin
     */
    CoinState(final String newAbbreviation, final Boolean newState) {
        this.abbreviation = newAbbreviation;
        this.state = newState;
    }

    /**
     * Allows us to find a coin state by its associated boolean value.
     * @param state The boolean we are looking for
     * @return A CoinState, or null
     */
    public static CoinState findByState(final Boolean state) {
        for (CoinState coinState : values()) {
            if (state == coinState.state) {
                return coinState;
            }
        }
        return null;
    }
}
