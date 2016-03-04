import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

/**
 * Created by lucas on 26/02/16.
 */
public class LogicalExpressionProcessor extends AbstractProc {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtBinaryOperator;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtBinaryOperator)) {
            return;
        }
        //On applique le selecteur
        if(checkSelector())
            return;
        CtBinaryOperator op = (CtBinaryOperator)candidate;
        if(op.getKind().equals(BinaryOperatorKind.NE))// operator !=
            op.setKind(BinaryOperatorKind.EQ); //transformation en ==
        else if (op.getKind().equals(BinaryOperatorKind.EQ))// operator ==
            op.setKind(BinaryOperatorKind.NE); //transformation en !=
        else if(op.getKind().equals(BinaryOperatorKind.GE))// operator>=
            op.setKind(BinaryOperatorKind.GT); //transformation en >
        else if(op.getKind().equals(BinaryOperatorKind.GT))//operator >
            op.setKind(BinaryOperatorKind.GE); //transformation >=
        else if(op.getKind().equals(BinaryOperatorKind.LE))// <=
            op.setKind(BinaryOperatorKind.LT);//<
        else if (op.getKind().equals(BinaryOperatorKind.LT))//>
            op.setKind(BinaryOperatorKind.LE);//=>

    }
}
