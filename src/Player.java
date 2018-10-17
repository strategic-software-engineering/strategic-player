/**
 * A player of the Spin-the-Wheel Coin Matching Game.
 *
 * @author CS4250 Fall 2018
 * @version 1.2.2 (20181004)
 */
public class Player implements StrategicPlayer {
    private int coinsPerWheel;
    private int revealsPerSpin;
    private int maxNumSpins;

    /**
     * Establishes that the player is beginning a new game.
     * @param coinsPerWheel the number of coins in the wheel
     * @param revealsPerSpin the number of coins revealed per turn/spin
     * @param maxNumSpins the maximum number of spin allowed for the game
     */
    public void beginGame(int coinsPerWheel, int revealsPerSpin, int maxNumSpins){
        this.coinsPerWheel = coinsPerWheel;
        this.revealsPerSpin = revealsPerSpin;
        this.maxNumSpins = maxNumSpins;
    }

    /**
     * Provides the request pattern for the current turn.
     * The returned pattern is of proper length, contains only
     * '?' and '-', and has exactly the number of '?'
     * as the permitted reveals per spin.
     * @return a proper reveal-pattern consisting of '-' and '?' with
     *         exactly the number of '?' as permitted by reveals-per-spin
     */
    public CharSequence getSlotsToReveal(){
        int count = revealsPerSpin;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0; i<coinsPerWheel; i++){
            if (count>0) {
                stringBuilder.append('?');
                count--;
            }
            else
                stringBuilder.append('-');
        }
    return stringBuilder.toString();
    }

    /**
     * Provides the coin-state set pattern for the current turn.
     * The returned pattern is a copy of the parameter
     * in which all '-' are preserved and each location of
     * 'H' and 'T' may be replaced by either 'H' or 'T'.
     * @param revealedPattern a proper-length pattern
     *                        consisting of '-', 'H', and 'T'
     * @return a proper set-pattern consisting of '-', 'H', and 'T'
     */
    public CharSequence getNewCoinStates(CharSequence revealedPattern){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(revealedPattern);
        for (int i=0; i<coinsPerWheel; i++){
            if (stringBuilder.charAt(i) == 'T' )
                stringBuilder.replace(i,(i+1),"H");
        }
    return stringBuilder.toString();
    }

}
