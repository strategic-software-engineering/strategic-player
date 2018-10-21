/**
 * A player of the Spin-the-Wheel Coin Matching Game.
 * <br><br>
 * Strategy for a 4 coin, 2 reveal game: <br>
 * 1st turn:
 *  getSlotsToReveal() is called and it returns a pattern "??--".
 *  getNewCoinStates(revealedPattern) is called then and it
 *  returns "HH--".
 * 2nd step:
 *  getSlotsToReveal() is called and it returns a pattern "?-?-".
 *  getNewCoinStates(revealedPattern) is called next and it
 *  returns "H-H-".
 * 3rd step:
 *  getSlotsToReveal() is called and it returns a pattern "?-?-"
 *  again.
 *  getNewCoinStates(revealedPattern) is called next and it
 *  returns "T-H-".
 * 4th step:
 *  getSlotsToReveal() is called and it returns a pattern "??--".
 *  getNewCoinStates(revealedPatter) is called and it returns
 *  the flipped version of what revealedPattern is (example:
 *  "HT--" becomes "TH--").
 * 5th step:
 *  getSlotsToReveal() is called and it returns a pattern "?-?-".
 *  getNewCoinStates(revealedPattern) is called and it returns
 *  the flipped version of revealedPattern (example: "H-H-" becomes
 *  "T-T-". And it is a guaranteed win after this step.
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
     * holds a number of reveals for the four coins two reveal strategy.
     */
    private final int strategicRevealValue = 2;
    /**
     * keeps track of a current turn.
     */
    private int turn;
    /**
     * turn One.
     */
    private final int turnOne = 1;
    /**
     * turn Two.
     */
    private final int turnTwo = 2;
    /**
     * turn Three.
     */
    private final int turnThree = 3;
    /**
     * turn Four.
     */
    private final int turnFour = 4;
    /**
     * turn Five.
     */
    private final int turnFive = 5;
    /**
     * number three.
     */
    private final int three = 3;
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
     * */
    private void fourTwoStrategy(final StringBuilder stringBuilder,
                                 final int turnCounter) {
        switch (turnCounter) {
            case turnOne:
                loopThroughElements("H", 'T', strategicCoinValue,
                        stringBuilder);
                turn = turnTwo;
                break;
            case turnTwo:
                loopThroughElements("H", 'T', strategicCoinValue,
                        stringBuilder);
                turn = turnThree;
                break;
            case turnThree:
                if (stringBuilder.toString().equals("H-H-")) {
                    reverseDifferentElement(stringBuilder, 2, three);
                } else {
                    loopThroughElements("H", 'T', strategicCoinValue,
                            stringBuilder);
                }
                turn = turnFour;
                break;
            case turnFour:
                if (stringBuilder.toString().equals("HT--")) {
                    reverseDifferentElement(stringBuilder, 1, 2);
                } else if (stringBuilder.toString().equals("TH--")) {
                    reverseDifferentElement(stringBuilder, 1, 2);
                } else {
                    reverseSameElements(stringBuilder);
                }
                turn = turnFive;
                break;
            case turnFive:
                reverseSameElements(stringBuilder);
                turn = turnOne;
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
    private void fourTwoStrategyReqPattern(final StringBuilder stringBuilder,
                                           final int turnCounter) {
        switch (turnCounter) {
            case turnOne:
                stringBuilder.append("??--");
                break;
            case turnTwo:
                stringBuilder.append("?-?-");
                break;
            case turnThree:
                stringBuilder.append("?-?-");
                break;
            case turnFour:
                stringBuilder.append("??--");
                break;
            case turnFive:
                stringBuilder.append("?-?-");
                break;
            default:
                break;
        }
    }

    /**
     * Changes the sides of both coins to the opposite side when
     * they are different.
     * @param stringBuilder holds the sequence
     * @param firstIndex first index that is used to help choose
     *                   which is location of the second character
     *                   that needs to be changed.
     * @param secondIndex second index that is used to help choose
     *                    which is location of the second character
     *                    that needs to be changed.
     */
    private void reverseDifferentElement(final StringBuilder stringBuilder,
                                         final int firstIndex,
                                         final int secondIndex) {
        if (stringBuilder.charAt(0) == 'H') {
            stringBuilder.replace(0, 1, "T");
            stringBuilder.replace(firstIndex, secondIndex, "H");
        } else {
            stringBuilder.replace(0, 1, "H");
            stringBuilder.replace(firstIndex, secondIndex, "T");
        }
    }

    /**
     * Changes the sides of both coins to the opposite side when
     * they are the same.
     * @param stringBuilder holds the sequence.
     */
    private void reverseSameElements(final StringBuilder stringBuilder) {
        if (stringBuilder.charAt(0) == 'H') {
            loopThroughElements("T", 'H', strategicCoinValue,
                    stringBuilder);
        } else {
            loopThroughElements("H", 'T', strategicCoinValue,
                    stringBuilder);
        }
    }
}
