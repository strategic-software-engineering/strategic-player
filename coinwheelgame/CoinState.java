package coinwheelgame;

/**
 * Represents possible states of coins in the game.
 *
 * @author Team Obelisk
 * @version 0.0.1
 */
public enum CoinState {
    HEADS("H", true),
    TAILS("T", false);

    private String abbreviation;
    private Boolean state;

    public String getAbbreviation() {
        return abbreviation;
    }

    public Boolean getState() {
        return state;
    }

    CoinState(String abbreviation, Boolean state) {
        this.abbreviation = abbreviation;
        this.state = state;
    }

    public static CoinState findByState(Boolean state)
    {
        for(CoinState coinState : values())
        {
            if(state == coinState.state)
            {
                return coinState;
            }
        }

        return null;
    }
}
