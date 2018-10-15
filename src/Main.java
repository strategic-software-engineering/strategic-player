/**
 * A player of the Spin-the-Wheel Coin Matching Game.
 *
 * @author CS4250 Fall 2018
 * @version 1.2.2 (20181004)
 */
public class Main {
    public static void main (String[]args){
        int coinsPerWheel = 10;
        int revealsPerSpin = 5;
        int maxNumSpins = 10;

        Player p = new Player();
        p.beginGame(coinsPerWheel,revealsPerSpin,maxNumSpins);
        System.out.println(p.getSlotsToReveal());
        System.out.println(p.getNewCoinStates(p.getSlotsToReveal()));
        System.out.println(p.getNewCoinStates("T-H-H-T-T-"));
   }
}
