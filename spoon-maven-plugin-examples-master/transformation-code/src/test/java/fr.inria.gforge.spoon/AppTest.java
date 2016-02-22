package fr.inria.gforge.spoon;

import junit.framework.Assert;
import org.junit.Test;

/**
 * @author Lucas MARTINEZ
 * @version 05/02/16
 */
public class AppTest {

    @Test
    public void calcTest(){
        String s = "ll";
        int i = App.calc(s);
        Assert.assertEquals(10, i);
    }
}
