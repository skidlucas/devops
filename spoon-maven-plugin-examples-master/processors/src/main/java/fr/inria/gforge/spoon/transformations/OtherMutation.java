package fr.inria.gforge.spoon.transformations;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.*;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.reference.CtVariableReference;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.filter.ReturnOrThrowFilter;
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
