
import org.junit.Test;

public class Tests {
    @Test
    public void playerAmountTest() {
        Server s = new Server();
        try {
            s.start(true);
        } catch (Exception e) {
            assert(false);
            e.printStackTrace();
        }
        assert (s.players.size() == 2);
    }
}
