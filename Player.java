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
 * The probability for success for this strategy in the event that the
 * first two coins match is: P=1/3 for the first move, and P=1-(1/2)^n,
 * where n equals the number of moves after the first one, for all
 * subsequent moves. The probability of winning in 9 moves is 99.6%<br>
 * The probability for success for this strategy when the first two
 * coins do not match is: P=1/4 for the first move, and P=1-(1/2)^n,
 * where n equals the number of moves after the first one, for all
 * subsequent moves. The probability of winning in 9 moves is 99.2%<br>
 *
 * @author CS4250 Fall 2018
 * @version 1.2.2 (20181004)
 */
public class Player implements StrategicPlayer {
    /**
     * holds a value of the number of coins in the current game.
     */
    private int coinsPerWheel;
    /**
     * holds a value of the number of reveals allowed in the current game.
     */
    private int revealsPerSpin;
    /**
     * holds a value of the maximum number of spins allowed in the current game.
     */
    private int maxNumSpins;
    /**
     * indicates if it is the first time getSlotsToReaveal() method is used.
     */
    private boolean newGameGetSlotsToReveal = false;
    /**
     * indicates if it is the first time getNewCoinStates() method is used.
     */
    private boolean newGameGetNewCoinStates = false;
    /**
     * hold the value of the target side for the win, H or T.
     */
    private char winSide = 'R';
    /**
     * holds a coin number for the four coins two reaveal strategy.
     */
    private final int strategicCoinValue = 4;
    /**
     * holds a number of reveals for the four coins two reaveal strategy.
     */
    private final int strategicRevealValue = 2;

    /**
     * Establishes that the player is beginning a new game.
     * @param coinsPerWheelParam the number of coins in the wheel
     * @param revealsPerSpinParam the number of coins revealed per turn/spin
     * @param maxNumSpinsParam the maximum number of spin allowed for the game
     */
    public void beginGame(final int coinsPerWheelParam,
                          final int revealsPerSpinParam,
                          final int maxNumSpinsParam) {
        this.coinsPerWheel = coinsPerWheelParam;
        this.revealsPerSpin = revealsPerSpinParam;
        this.maxNumSpins = maxNumSpinsParam;
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
    public CharSequence getSlotsToReveal() {
        int count = revealsPerSpin;
        StringBuilder stringBuilder = new StringBuilder();

        // four coins two reveals strategy
        if (coinsPerWheel == strategicCoinValue && revealsPerSpin
                == strategicRevealValue) {
            if (newGameGetSlotsToReveal) {
                stringBuilder.append("??--");
                newGameGetSlotsToReveal = false;
            } else {
                stringBuilder.append("?-?-");
            }
        } else { // any other game strategy
            for (int i = 0; i < coinsPerWheel; i++) {
                if (count > 0) {
                    stringBuilder.append('?');
                    count--;
                } else {
                    stringBuilder.append('-');
                }
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
    public CharSequence getNewCoinStates(final CharSequence
                                                 revealedPattern) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(revealedPattern);
        if (coinsPerWheel == strategicCoinValue && revealsPerSpin
                == strategicRevealValue) {
            if (newGameGetNewCoinStates && revealedPattern == "HH--") {
                loopThroughElements("T", 'H', coinsPerWheel, stringBuilder);
                newGameGetNewCoinStates = false;
                winSide = 'T';
            } else if (newGameGetNewCoinStates && revealedPattern == "TT--") {
                loopThroughElements("H", 'T', coinsPerWheel, stringBuilder);
                newGameGetNewCoinStates = false;
                winSide = 'H';
            } else {
                if (winSide == 'T') {
                    loopThroughElements("T", 'H', coinsPerWheel, stringBuilder);
                } else {
                    loopThroughElements("H", 'T', coinsPerWheel, stringBuilder);
                }
            }
        } else { // any other game strategy
            loopThroughElements("H", 'T', revealedPattern.length(),
                    stringBuilder);
        }
        return stringBuilder.toString();
    }

    /**
     * This method performs a universal loop that would let the developer to
     * change the values of what side they want to flip, as well as specify
     * what is the length of the sequence. It is also building a sequence for
     * the return.
     * @param sideWanted String that will change change the value to either
     *                   "H" or "T"
     * @param sideHave character that specifies if it is 'H' or 'T'
     * @param lengthOfTheSequence specifies the proper length of a sequence
     * @param stringBuilder builds up the string
     * @return a proper set-pattern consisting of '-', 'H', and 'T'
     */
    private CharSequence loopThroughElements(final String sideWanted,
                                             final char sideHave,
                                             final int lengthOfTheSequence,
                                             final StringBuilder
                                                     stringBuilder) {
        for (int i = 0; i < lengthOfTheSequence; i++) {
            if (stringBuilder.charAt(i) == sideHave) {
                stringBuilder.replace(i, (i + 1), sideWanted);
            }
        }
        return stringBuilder.toString();
    }
}
