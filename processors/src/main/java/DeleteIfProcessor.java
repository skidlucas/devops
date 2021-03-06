import spoon.reflect.code.*;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtEnum;
import spoon.support.reflect.code.CtBinaryOperatorImpl;
import spoon.support.reflect.code.CtCodeSnippetStatementImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by lucas on 04/03/16.
 */
public class DeleteIfProcessor extends AbstractProc {

    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtIf;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtIf) ) {
            return;
        }
        if(!checkSelector()){
            CtCodeSnippetStatement newStatement = getFactory().Core().createCodeSnippetStatement();
            newStatement.setValue("");
            ((CtIf) candidate).replace(newStatement);// supprime le if
            super.printLogMutation(candidate.getPosition().toString());
        }
    }
}