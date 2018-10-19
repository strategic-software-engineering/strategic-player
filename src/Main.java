/**
 * A player of the Spin-the-Wheel Coin Matching Game.
 *
 * @author CS4250 Fall 2018
 * @version 1.2.2 (20181004)
 */
public class Main {
    /**
     *
     */
    private static final int coins = 10;
    /**
     *
     */
    private static final int reveal = 5;
    /**
     *
     */
    private static final int spin = 10;
    /**
     * Main method used for testing.
     * @param args arguments
     */
    public static void main(final String[] args) {
        int coinsPerWheel = coins;
        int revealsPerSpin = reveal;
        int maxNumSpins = spin;

        Player p = new Player();
        p.beginGame(coinsPerWheel, revealsPerSpin, maxNumSpins);
        System.out.println(p.getSlotsToReveal());
        System.out.println(p.getNewCoinStates(p.getSlotsToReveal()));
        System.out.println(p.getNewCoinStates("T-H-H-T-T-"));
   }
}
