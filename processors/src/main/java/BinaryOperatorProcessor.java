import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;
import spoon.support.reflect.code.CtBinaryOperatorImpl;

public class BinaryOperatorProcessor extends AbstractProc {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		return candidate instanceof CtBinaryOperatorImpl;
	}

	public void process(CtElement candidate) {
		if (!(candidate instanceof CtBinaryOperatorImpl)) {
			return;
		}
		if(checkSelector())
			return;
		CtBinaryOperatorImpl op = (CtBinaryOperatorImpl) candidate;
		if(op.getKind().equals(BinaryOperatorKind.PLUS))// si c'est un + on met un -
			op.setKind(BinaryOperatorKind.MINUS);
		else if(op.getKind().equals(BinaryOperatorKind.MINUS))// si c'est un - on met un +
			op.setKind(BinaryOperatorKind.PLUS);
		else if(op.getKind().equals(BinaryOperatorKind.DIV))// si c'est un / on met un *
			op.setKind(BinaryOperatorKind.MUL);
		else if(op.getKind().equals(BinaryOperatorKind.MUL) // si c'est un * ou % on met un /
				|| op.getKind().equals(BinaryOperatorKind.MOD)){
			op.setKind(BinaryOperatorKind.DIV);
		}
	}
}
