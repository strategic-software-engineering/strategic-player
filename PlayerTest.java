import org.junit.Test;

import coinwheelgame.Wheel;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * The tests for the Player class.
 */
public class PlayerTest {
    /**
     * Tests that Player can be instantiated.
     */
    @Test
    public void beginGameNotNullTest() {
        Player player = new Player();
        assertNotNull(player);
    }

    /**
     * Tests that getSlotsToReveal requests a reveal pattern for a
     * game with arbitrary coins and reveals.
     */
    @Test
    public void getSlotsToRevealTest() {
        int coinsPerWheel = 10;
        int revealsPerSpin = 4;
        int maxNumSpins = 5;
        Player player = new Player();
        player.beginGame(coinsPerWheel, revealsPerSpin, maxNumSpins);
        assertEquals(player.getSlotsToReveal(), "????------");
    }

    @Test
    public void getNewCoinStatesTest() {
        Player player = new Player();
        player.beginGame(6, 3, 10);
        assertEquals(player.getNewCoinStates("TTH---"), "HHH---");
    }

    //Test the strategy for the 4 coin, 2 reveal game by running many games and collecting data
    //Should solve over 99% of games in less than 9 spins (as per user story)
    @Test
    public  void fourCoinTwoRevealStrategyTest() {
        int numberOfTestGames = 10000; //change this if you want to run more or less games for this test
        int maxSpinsForTest = 4;
        int gamesWonAfterMaxSpins = 0;
        int gamesLost = 0;

        for (int i = 0; i < numberOfTestGames; i++) {
            Player player = new Player();
            player.beginGame(4, 2, 5);

            Wheel wheel = new Wheel(4);

            //Assuming the game will not deal the player a winning set of coins, throw out any winning patterns right away
            if (wheel.wheelInfoAsString().equals("HHHH") || wheel.wheelInfoAsString().equals("TTTT")) {
                i--;
                continue;
            }

            int spinCount = 0;
            boolean victorious = false;
            String temp = "";
            String temp2 = "";
            String temp3 = "";

            while (!victorious) {
                //a single play
                temp = player.getSlotsToReveal().toString();
                temp2 = wheel.getRevealedCoins(temp);
                temp3 = player.getNewCoinStates(temp2).toString();
                wheel.setNewCoinStates(temp3);
                //check to see if the player won
                if (wheel.wheelInfoAsString().equals("HHHH") || wheel.wheelInfoAsString().equals("TTTT")) {
                    victorious = true;
                    if (spinCount <= maxSpinsForTest) {
                        gamesWonAfterMaxSpins++;
                    } else {
                        gamesLost++;
                    }
                }
                wheel.spin();
                spinCount++;
            }
        }
        assertTrue(gamesLost < 1);
    }
    //Tests all possible combinations (besides the winning ones) for a 4 coin, 2 reveal game
    @Test
    public void allPossibleGamesTest() {
        // all possible non-winning combinations for the wheel
        CharSequence[] allCombinations = {"TTTH", "TTHT", "TTHH", "THTT", "THTH", "THHT", "THHH",
                "HTTT", "HTTH", "HTHT", "HTHH", "HHTT", "HHTH", "HHHT"};
        int gamesLost = 0;

        for (int i = 0; i < allCombinations.length; i++) {
            Player player = new Player();
            player.beginGame(4, 2, 5);

            Wheel wheel = new Wheel(4, allCombinations[i].toString());
            int spinCount = 0;
            int maxSpinsForTest = 5;
            boolean victorious = false;
            String temp = "";
            String temp2 = "";
            String temp3 = "";

            while (!victorious) {
                //a single play
                temp = player.getSlotsToReveal().toString();
                temp2 = wheel.getRevealedCoins(temp);
                temp3 = player.getNewCoinStates(temp2).toString();
                wheel.setNewCoinStates(temp3);
                //check to see if the player won
                if (wheel.wheelInfoAsString().equals("HHHH") || wheel.wheelInfoAsString().equals("TTTT")) {
                    victorious = true;
                    if (spinCount > maxSpinsForTest) {
                        gamesLost++;
                    }
                } else { //if not, spin again
                    wheel.spin();
                    spinCount++;
                }
            }
        }
        assertTrue(gamesLost < 1);
    }
    @Test
    public void getNewCoinStatesFourTwoFirstTest() {
        Player player = new Player();
        player.beginGame(4, 2, 10);
        player.getSlotsToReveal();
        assertEquals(player.getNewCoinStates("HT--"), "HH--");
    }
    @Test
    public void getNewCoinStatesFourTwoSecondTest() {
        Player player = new Player();
        player.beginGame(4, 2, 10);
        player.getSlotsToReveal();
        assertEquals(player.getNewCoinStates("HH--"), "HH--");
    }
    @Test
    public void getNewCoinStatesFourTwoThirdTest() {
        Player player = new Player();
        player.beginGame(4, 2, 10);
        player.getSlotsToReveal();
        assertEquals(player.getNewCoinStates("TT--"), "HH--");
    }
    @Test
    public void getSlotsToRevealFourTwoFirstTest() {
        Player player = new Player();
        player.beginGame(4, 2, 5);
        assertEquals(player.getSlotsToReveal(), "??--");
    }
}
