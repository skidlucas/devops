import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Lucas MARTINEZ
 * @version 05/02/16
 */
public class AppTest {

    @Test
    public void calcTest(){
        int i = App.calc();
        Assert.assertEquals(10, i);
    }
}
