/**
 * A player of the Spin-the-Wheel Coin Matching Game.
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
     * holds a coin number for the four coins two reaveal strategy.
     */
    private final int strategicCoinValue = 4;
    /**
     * holds a number of reveals for the four coins two reaveal strategy.
     */
    private final int strategicRevealValue = 2;

    private int turn;

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
        turn = 1;
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
            fourTwoStrategyReqPattern(stringBuilder, turn);
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
            fourTwoStrategy(stringBuilder, turn);
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
     */
    private void loopThroughElements(final String sideWanted,
                                             final char sideHave,
                                             final int lengthOfTheSequence,
                                             final StringBuilder
                                                     stringBuilder) {
        for (int i = 0; i < lengthOfTheSequence; i++) {
            if (stringBuilder.charAt(i) == sideHave) {
                stringBuilder.replace(i, (i + 1), sideWanted);
            }
        }
    }

    /**
     * Four coin Two reveal strategy that is used in getNewCoinStates() method.
     * It changes the sides of the coins that have been provided to follow a
     * specific strategy.
     * @param stringBuilder holds the sequence.
     * @param turnCounter specifies which turn it currently is.
     */
    private void fourTwoStrategy(final StringBuilder stringBuilder, final int turnCounter) {
        switch (turnCounter){
            case 1:
                loopThroughElements("H", 'T', strategicCoinValue,
                        stringBuilder);
                turn++;
                break;
            case 2:
                loopThroughElements("H", 'T', strategicCoinValue,
                        stringBuilder);
                turn++;
                break;
            case 3:
                if (stringBuilder.toString().equals("H-H-")) {
                    System.out.println("got in 1st");
                    reverseDifferentElement(stringBuilder, 2, 3 );
                } else {
                    System.out.println("got in 2nd");
                    loopThroughElements("H", 'T', strategicCoinValue,
                            stringBuilder);
                }
                turn++;
                break;
            case 4:
                if (stringBuilder.toString().equals("HT--")) {
                    reverseDifferentElement(stringBuilder, 1, 2 );
                } else if (stringBuilder.toString().equals("TH--")) {
                    reverseDifferentElement(stringBuilder, 1, 2 );
                } else {
                    reverseSameElements(stringBuilder);
                }
                turn++;
                break;
            case 5:
                reverseSameElements(stringBuilder);
                turn = 1;
                break;
            default:
                break;
        }
    }

    /**
     * Provides the specific pattern for a particular turn.
     * @param stringBuilder holds the sequence.
     * @param turnCounter specifies which turn it currently is.
     */
    private void fourTwoStrategyReqPattern(final StringBuilder stringBuilder, final int turnCounter) {
        switch (turnCounter) {
            case 1:
                stringBuilder.append("??--");
                break;
            case 2:
                stringBuilder.append("?-?-");
                break;
            case 3:
                stringBuilder.append("?-?-");
                break;
            case 4:
                stringBuilder.append("??--");
                break;
            case 5:
                stringBuilder.append("?-?-");
                break;
            default:
                break;
        }
    }

    /**
     * Changes the sides of both coins to the opposite side when they are different.
     * @param stringBuilder holds the sequence
     * @param firstIndex first index that is used to help choose which is location
     *                   of the second character that needs to be changed.
     * @param secondIndex second index that is used to help choose which is location
     *                    of the second character that needs to be changed.
     */
    private void reverseDifferentElement(final StringBuilder stringBuilder,
                                                 final int firstIndex,
                                                 final int secondIndex){
        if (stringBuilder.charAt(0) == 'H'){
            System.out.println("changing 1st");
            stringBuilder.replace(0,1, "T");
            stringBuilder.replace(firstIndex, secondIndex, "H");
        } else {
            System.out.println("changing 2nd");
            stringBuilder.replace(0,1, "H");
            stringBuilder.replace(firstIndex, secondIndex, "T");
        }
    }

    /**
     * Changes the sides of both coins to the opposite side when they are the same.
     * @param stringBuilder holds the sequence.
     */
    private void reverseSameElements(final StringBuilder stringBuilder){
        if (stringBuilder.charAt(0) == 'H'){
            loopThroughElements("T", 'H', strategicCoinValue,
                    stringBuilder);
        } else {
            loopThroughElements("H", 'T', strategicCoinValue,
                    stringBuilder);
        }
    }
}