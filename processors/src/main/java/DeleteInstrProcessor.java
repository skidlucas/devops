import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;
import spoon.support.reflect.code.CtBinaryOperatorImpl;

import java.util.Random;

/**
 * Created by lucas on 04/03/16.
 */
public class DeleteInstrProcessor extends AbstractProc {

    private static final int K_LIKELIHOOD_DELETE = 10;

    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtStatement;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtStatement) || candidate instanceof CtBinaryOperatorImpl) {
            return;
        }
        CtStatement state = (CtStatement) candidate;
        if(!checkSelector() && !isToBeDeleted()){
            CtCodeSnippetStatement newStatement = getFactory().Core().createCodeSnippetStatement();
            newStatement.setValue("");
            state.replace(newStatement);
            super.printLogMutation(state.getPosition().toString());
        }
    }

    private boolean isToBeDeleted(){
        Random rand = new Random();
        return (K_LIKELIHOOD_DELETE < rand.nextInt(100));
    }
}