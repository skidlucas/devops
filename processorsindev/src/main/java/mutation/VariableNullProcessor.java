package processorsindev;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;

/**
 * Created by lucas on 25/02/16.
 */
public class VariableNullProcessor extends AbstractProcessor<CtElement> {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtVariable;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtVariable)) {
            return;
        }
        CtStatement state = (CtStatement) candidate;
        CtCodeSnippetStatement newStatement = getFactory().Core().createCodeSnippetStatement();
        String[] str = state.toString().split("=");
        String newDeclare = str[0] + "= null";
        newStatement.setValue(newDeclare);
        state.replace(newStatement);

    }
}
