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
    public void fourCoinTwoRevealStrategyTest() {
        int numberOfTestGames = 1000; //change this if you want to run more or less games for this test
        int maxSpinsForTest = 9;
        int gamesWonAfterMaxSpins = 0;

        for (int i = 0; i < numberOfTestGames; i++) {
            Player player = new Player();
            player.beginGame(4, 2, 20);

            Wheel wheel = new Wheel(4);

            //Assuming the game will not deal the player a winning set of coins, throw out any winning patterns right away
            if (wheel.wheelInfoAsString().equals("HHHH") || wheel.wheelInfoAsString().equals("TTTT")) {
                i--;
                continue;
            }

            int spinCount = 0;
            boolean victorious = false;

            while(!victorious) {
                wheel.spin();
                spinCount++;
                //a single play
                wheel.setNewCoinStates(player.getNewCoinStates(wheel.getRevealedCoins(player.getSlotsToReveal().toString())).toString());

                //check to see if the player won
                if (wheel.wheelInfoAsString().equals("HHHH") || wheel.wheelInfoAsString().equals("TTTT")) {
                    victorious = true;
                    if (spinCount <= maxSpinsForTest) {
                        gamesWonAfterMaxSpins++;
                    }
                }
            }
        }
        double ratioOfWinsToGames = (double)gamesWonAfterMaxSpins/(double)numberOfTestGames;
        assertTrue(ratioOfWinsToGames > 0.99);
    }

    @Test
    public void getNewCoinStatesFourTwoFirstTest() {
        Player player = new Player();
        player.beginGame(4, 2, 10);
        assertEquals(player.getNewCoinStates("HT--"), "HH--");
    }
    @Test
    public void getNewCoinStatesFourTwoSecondTest() {
        Player player = new Player();
        player.beginGame(4, 2, 10);
        assertEquals(player.getNewCoinStates("HH--"), "TT--");
    }
    @Test
    public void getNewCoinStatesFourTwoThirdTest() {
        Player player = new Player();
        player.beginGame(4, 2, 10);
        assertEquals(player.getNewCoinStates("TT--"), "HH--");
    }
    @Test
    public void getSlotsToRevealFourTwoSecondTest() {
        Player player = new Player();
        player.beginGame(4, 2, 5);
        player.getSlotsToReveal();
        assertEquals(player.getSlotsToReveal(), "?-?-");
    }
    @Test
    public void getSlotsToRevealFourTwoFirstTest() {
        Player player = new Player();
        player.beginGame(4, 2, 5);
        assertEquals(player.getSlotsToReveal(), "??--");
    }
}

