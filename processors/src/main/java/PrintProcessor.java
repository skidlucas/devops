import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;

/**
 * Created by user on 12/02/16.
 */
public class PrintProcessor extends AbstractProcessor<CtElement> {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtLoop;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtLoop)) {
            return;
        }
        CtLoop op = (CtLoop)candidate;
        String str = "System.out.println(\"je suis un mutant\")";
        CtCodeSnippetStatement declareRoundStatement = getFactory().Core().createCodeSnippetStatement();
        declareRoundStatement.setValue(str);
        ((CtLoop) candidate).insertAfter(declareRoundStatement);
    }
}
