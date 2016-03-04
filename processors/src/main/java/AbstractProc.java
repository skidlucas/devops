import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.util.Random;

/**
 * Created by lucas on 04/03/16.
 */
public abstract class AbstractProc extends AbstractProcessor<CtElement> {

    private static final int K_LIKELIHOOD = 100;
    private Random rand;

    /**
     * check if the mutation'll be done
     * @return
     */
    protected boolean checkSelector(){
        rand = new Random();
        return (K_LIKELIHOOD < rand.nextInt(101));
    }
}
