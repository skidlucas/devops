package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtVariableReference;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.ReturnOrThrowFilter;
import spoon.support.reflect.reference.CtVariableReferenceImpl;
import spoon.template.ExpressionTemplate;
import java.util.Set;

import java.util.EnumSet;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
/**
 * Created by user on 12/02/16.
 */
public class OtherMutation extends AbstractProcessor<CtElement> {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtVariable;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtVariable)) {
            return;
        }
        CtStatement state = (CtStatement) candidate;
     //   CtExpression expr = (CtExpression) state;
        CtCodeSnippetStatement newStatement = getFactory().Core().createCodeSnippetStatement();
        String[] str = state.toString().split("=");
        String newDeclare = str[0] + "= null";
        newStatement.setValue(newDeclare);
        state.replace(newStatement);
        //declareRoundStatement.setValue();
        //state.replace();

//        System.out.println(str);
        /*CtVariableAccessImpl op2 = (CtVariableAccessImpl) ;

        op2.setVariable();
        CtCodeSnippetStatement declareRoundStatement = getFactory().Core().createCodeSnippetStatement();
        op.setKind(BinaryOperatorKind.MUL);*/
    }
}
