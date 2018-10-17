/**
 * A player of the Spin-the-Wheel Coin Matching Game.
 *<br><br>
 * Strategy for a 4 coin, 2 reveal game: <br>
 * The player will first request that two adjacent coins are revealed, 
 * which will be "??--". If these two coins are the same, getNewCoinStates
 * will flip both of them. Since the game cannot begin with a winning
 * spin, this gives us an immediate 1/3 chance of victory if the two
 * hidden coins are the same. If they are not the same, it ensures that
 * at least 3 of the coins now match.<br>
 * If the coins are not the same, getNewCoinStates will flip one so that
 * the pattern is "HH". This ensures that at least 2 coins match in the
 * worst case scenario, and has a 1/4 chance of victory.<br>
 * For the second move, the player will request that two opposite coins
 * are revealed in the pattern "?-?-". This ensures, in the worst case
 * scenario, that three coins match, and has a 1/2 chance of victory.
 * For all subsequent moves, the same "?-?-" pattern will be requested,
 * which gives each of these moves a 1/2 chance of victory. <br>
 * This strategy does not guarantee a victory, but does ensure a 
 * geometric probability of victory, which is fairly reliable. <br>
 * The probability for success for this strategy in the event that the
 * first two coins match is: P=1/3 for the first move, and P=1-(1/2)^n,
 * where n equals the number of moves after the first one, for all
 * subsequent moves.<br>
 * The probability for success for this strategy when the first two
 * coins do not match is: P=1/4 for the first move, and P=1-(1/2)^n,
 * where n equals the number of moves after the first one, for all 
 * subsequent moves.<br>
 *
 * @author CS4250 Fall 2018
 * @version 1.2.2 (20181004)
 */
public class Player implements StrategicPlayer {
    private int coinsPerWheel;
    private int revealsPerSpin;
    private int maxNumSpins;
    private boolean newGameGetSlotsToReveal = false;
    private boolean newGameGetNewCoinStates = false;
    private char winSide='R';

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
		newGameGetSlotsToReveal = true;
        newGameGetNewCoinStates = true;
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

        // 4 coins 2 reveals strategy
        if (coinsPerWheel == 4 && revealsPerSpin == 2) {
            if (newGameGetSlotsToReveal) {
                stringBuilder.append("??--");
                newGameGetSlotsToReveal = false;
            }
            else
                stringBuilder.append("?-?-");
        }
        // any other game strategy
        else {
            for (int i = 0; i < coinsPerWheel; i++) {
                if (count > 0) {
                    stringBuilder.append('?');
                    count--;
                } else
                    stringBuilder.append('-');
            }
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
        // 4 coins 2 reveals strategy
        if (coinsPerWheel == 4 && revealsPerSpin == 2) {
            if (newGameGetNewCoinStates && revealedPattern == "HH--") {
                for (int i = 0; i < coinsPerWheel; i++) {
                    if (stringBuilder.charAt(i) == 'H')
                        stringBuilder.replace(i, (i + 1), "T");
                }
                newGameGetNewCoinStates = false;
                winSide = 'T';
            }
            else if (newGameGetNewCoinStates && revealedPattern == "TT--") {
                for (int i = 0; i < coinsPerWheel; i++) {
                    if (stringBuilder.charAt(i) == 'T')
                        stringBuilder.replace(i, (i + 1), "H");
                }
                newGameGetNewCoinStates = false;
                winSide = 'H';
            } else {
                if (winSide == 'T'){
                    for (int i = 0; i < coinsPerWheel; i++) {
                        if (stringBuilder.charAt(i) == 'H')
                            stringBuilder.replace(i, (i + 1), "T");
                    }
                }else{
                    for (int i = 0; i < coinsPerWheel; i++) {
                        if (stringBuilder.charAt(i) == 'T')
                            stringBuilder.replace(i, (i + 1), "H");
                    }
                }
            }
        }
        // any other game strategy
        else {
            for (int i = 0; i < revealedPattern.length(); i++) {
                if (stringBuilder.charAt(i) == 'T')
                    stringBuilder.replace(i, (i + 1), "H");
            }
        }
		return stringBuilder.toString();
    }

}
