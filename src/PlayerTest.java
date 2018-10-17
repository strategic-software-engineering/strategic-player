import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        player.beginGame(10,4,5);
        assertEquals(player.getSlotsToReveal(),"????------");
    }

    @Test
    public void getNewCoinStatesTest(){
        Player player = new Player();
        player.beginGame(6,3,10);
        assertEquals(player.getNewCoinStates("TTH---"),"HHH---");
    }
    @Test
    public void getNewCoinStatesFourTwoFirstTest(){
        Player player = new Player();
        player.beginGame(4,2,10);
        assertEquals(player.getNewCoinStates("HT--"),"HH--");
    }
    @Test
    public void getNewCoinStatesFourTwoSecondTest(){
        Player player = new Player();
        player.beginGame(4,2,10);
        assertEquals(player.getNewCoinStates("HH--"),"TT--");
    }
    @Test
    public void getNewCoinStatesFourTwoThirdTest(){
        Player player = new Player();
        player.beginGame(4,2,10);
        assertEquals(player.getNewCoinStates("TT--"),"HH--");
    }
    @Test
    public void getSlotsToRevealFourTwoSecondTest(){
        Player player = new Player();
        player.beginGame(4,2,5);
        player.getSlotsToReveal();
        assertEquals(player.getSlotsToReveal(),"?-?-");
    }
    @Test
    public void getSlotsToRevealFourTwoFirstTest(){
        Player player = new Player();
        player.beginGame(4,2,5);
        assertEquals(player.getSlotsToReveal(),"??--");
    }
}