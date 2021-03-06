package coinwheelgame;
/**
 * A Slot object simulates one slot on the spinning wheel, and
 * interacts with one coin object.
 *
 * @author Team Obelisk
 * @version 0.0.1
 * */
public class Slot {

    /**
     * Whether or not the slot is hidden.
     */
    private boolean hidden;
    /**
     * The coin contained within the slot.
     */
    private Coin coin;

/**
 * Slot object constructor.
 *
 * @param aCoin reference to a coin object
 * @param isHidden boolean initial state of coin
 * */
    public Slot(final Coin aCoin, final boolean isHidden) {
        coin = aCoin;
        hidden = isHidden;
    }

    /**
     * @return current status of coin in the slot.
     * */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Sets hidden status of coin to true.
     * */
    public void hide() {
        hidden = true;
    }

    /**
     * Sets hidden status of coin to false.
     * */
    public void show() {
        hidden = false;
    }

    /**
     * Toggles state of coin from heads to tails, or vice versa.
     * */
    public void flipCoin() {
        coin.flip();
    }

    /**
     * @return current state (heads or tails) of coin).
     * */
    public CoinState getCoinState() {
        return coin.getState();
    }

}
