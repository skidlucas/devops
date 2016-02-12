import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Lucas MARTINEZ
 * @version 05/02/16
 */
public class MainTest {

    @Test
    public void testGetInt(){
        Main main = new Main();
        int var = main.getInt();
        Assert.assertEquals(10, var);
    }
}
