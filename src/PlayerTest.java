import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class PlayerTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Test
    public void beginGameTest(){

    }
    @Test
    public void exceptionGameTestArgsInvalid(){
        thrown.expect(IllegalArgumentException.class);
        Player player = new Player();
        player.beginGame(-1,-1,-1);
    }

    @Test
    public void getSlotsToRevealTest(){

    }

    @Test
    public void getNewCoinStatesTest(){
        
    }


}