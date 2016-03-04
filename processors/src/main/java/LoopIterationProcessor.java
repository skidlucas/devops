import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtLoop;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtElement;
import spoon.support.reflect.code.CtForImpl;
import spoon.support.reflect.code.CtWhileImpl;

/**
 * Created by lucas on 26/02/16.
 */
public class LoopIterationProcessor extends AbstractProc {

    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtLoop;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtLoop)) {
            return;
        }
        if(checkSelector())
            return;
        CtLoop loop = (CtLoop) candidate;
        CtCodeSnippetStatement newStatement = getFactory().Core().createCodeSnippetStatement();
        CtCodeSnippetStatement newDeclare = getFactory().Core().createCodeSnippetStatement();
        CtCodeSnippetStatement endLoop = getFactory().Core().createCodeSnippetStatement();
        CtStatement body = loop.getBody();//je recupere le corps de la boucle
        newDeclare.setValue("{int Round100Loop = 0");
        loop.insertBefore(newDeclare);
        if(loop instanceof CtForImpl){
            CtForImpl forLoop = (CtForImpl) loop;
            CtCodeSnippetExpression newExpr = getFactory().Core().createCodeSnippetExpression();
            newExpr.setValue("");
            forLoop.setExpression(newExpr);//je supprime la condition
        } else if (loop instanceof CtWhileImpl){
            CtWhileImpl whileLoop = (CtWhileImpl) loop;
            whileLoop.getLoopingExpression();//je supprime la condition
        }
        newStatement.setValue("if(Round100Loop++ == 100)" +
                "break");
        body.insertAfter(newStatement);
        endLoop.setValue("}//");
        loop.insertAfter(endLoop);
    }
}


