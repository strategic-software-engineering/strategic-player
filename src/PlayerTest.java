import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coinwheelgame.*;

import static org.junit.Assert.*;

public class PlayerTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void beginGameNotNullTest(){
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    public void getSlotsToRevealTest(){
        Player player = new Player();
        player.beginGame(4,2,5);
        assertEquals(player.getSlotsToReveal(),"??--");
    }

    @Test
    public void getNewCoinStatesTest(){
        Player player = new Player();
        player.beginGame(4,2,10);
        assertEquals(player.getNewCoinStates("HT--"),"HH--");
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

}