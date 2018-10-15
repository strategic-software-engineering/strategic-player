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
        player.beginGame(4,2,5);
        assertEquals(player.getSlotsToReveal(),"??--");
    }

    @Test
    public void getNewCoinStatesTest(){
        Player player = new Player();
        player.beginGame(4,2,10);
        assertEquals(player.getNewCoinStates("HT--"),"HH--");
    }
}