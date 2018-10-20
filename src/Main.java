import coinwheelgame.Wheel;

/**
 * A player of the Spin-the-Wheel Coin Matching Game.
 *
 * @author CS4250 Fall 2018
 * @version 1.2.2 (20181004)
 */
public class Main {
    public static void main (String[]args){
       fourCoinTwoRevealStrategyTest();
    }

    public static void fourCoinTwoRevealStrategyTest() {
        int numberOfTestGames = 100000; //change this if you want to run more or less games for this test
        int maxSpinsForTest = 4;
        int gamesWonAfterMaxSpins = 0;
        int gamesLost = 0;

        for (int i = 0; i < numberOfTestGames; i++) {
            Player player = new Player();
            player.beginGame(4, 2, 3);

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
            System.out.println("begin new game");
            while (!victorious) {
                //a single play
                temp = player.getSlotsToReveal().toString();
                System.out.println(temp);
                temp2 = wheel.getRevealedCoins(temp);
                System.out.println(temp2);
                temp3 = player.getNewCoinStates(temp2).toString();
                System.out.println(temp3);
                System.out.println();
                wheel.setNewCoinStates(temp3);
                //check to see if the player won
                if (wheel.wheelInfoAsString().equals("HHHH") || wheel.wheelInfoAsString().equals("TTTT")) {
                    victorious = true;
                    if (spinCount <= maxSpinsForTest) {
                        gamesWonAfterMaxSpins++;
                    } else {
                        gamesLost++;
                        System.out.println("LOST LOST LOST");
                    }
                }
                wheel.spin();
                spinCount++;
            }
        }
        System.out.println("Won: "+gamesWonAfterMaxSpins + "\nLost: "+gamesLost );
    }
}
