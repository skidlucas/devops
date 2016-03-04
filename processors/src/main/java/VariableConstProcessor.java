import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtVariable;

/**
 * Created by lucas on 27/02/16.
 */
public class VariableConstProcessor extends AbstractProc {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtVariable;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtVariable)) {
            return;
        }
        if(checkSelector())
            return;
        CtCodeSnippetExpression newExpression = getFactory().Core().createCodeSnippetExpression();
        CtVariable var = (CtVariable)candidate;
        if(var.getModifiers().toString().contains("static")){
            if(var.getType().toString().contains("int"))
                newExpression.setValue("0x7fffffff");
            else if(var.getType().toString().contains("double"))
                newExpression.setValue("1.7976931348623157E308");
            else if(var.getType().toString().contains("String"))
                newExpression.setValue("\"JE SUIS UN MUTANT\"");
            else if(var.getType().toString().contains("boolean"))
                newExpression.setValue("true");
            else {
                newExpression.setValue(var.getDefaultExpression().toString());
                System.out.println(newExpression);
            }
            var.setDefaultExpression(newExpression);
        }

    }
}
