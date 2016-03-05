
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;
import spoon.support.reflect.code.CtForEachImpl;
import spoon.support.reflect.code.CtLocalVariableImpl;
import spoon.support.reflect.declaration.CtFieldImpl;
import spoon.support.reflect.declaration.CtParameterImpl;

/**
 * Created by lucas on 25/02/16.
 */
public class VariableNullProcessor extends AbstractProc {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtLocalVariableImpl;
    }

    @Override
    public void process(CtElement candidate) {
        if (!(candidate instanceof CtLocalVariableImpl)) {
            return;
        }
        if(checkSelector())
            return;
        CtLocalVariableImpl state = (CtLocalVariableImpl) candidate;
        if(state.getParent() instanceof CtForEachImpl)
            return;
        CtCodeSnippetExpression newStatement = getFactory().Core().createCodeSnippetExpression();
        String[] str = state.toString().split("=");
        if(str[0].contains("int") ||
                str[0].contains("double") ||
                str[0].contains("boolean") ||
                str[0].contains("char") ||
                str[0].contains("long"))
            return;
        //System.out.println(str[0]);
        String newDeclare = "null";
        newStatement.setValue(newDeclare);
        state.setAssignment(newStatement);
    }
}
