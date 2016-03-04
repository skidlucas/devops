import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

/**
 * Created by lucas on 27/02/16.
 */
public class UnaryOperatorProcessor extends AbstractProc {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        return candidate instanceof CtUnaryOperator;
    }

    public void process(CtElement candidate) {
        if (!(candidate instanceof CtUnaryOperator)) {
            return;
        }
        if(checkSelector())
            return;
        CtUnaryOperator op = (CtUnaryOperator) candidate;
        if(op.getKind().equals(UnaryOperatorKind.NEG)) //si on trouve un - unaire on remet un +
            op.setKind(UnaryOperatorKind.POS);
        else if(op.getKind().equals(UnaryOperatorKind.POSTDEC) || op.getKind().equals(UnaryOperatorKind.PREDEC)) //si on a un -- on met ++
            op.setKind(UnaryOperatorKind.POSTINC);
        else if(op.getKind().equals(UnaryOperatorKind.PREINC) || op.getKind().equals(UnaryOperatorKind.POSTINC)) //si on a un ++ on met --
            op.setKind(UnaryOperatorKind.POSTDEC);

    }
}
