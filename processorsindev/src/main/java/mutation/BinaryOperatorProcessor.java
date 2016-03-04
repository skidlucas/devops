package mutation;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

public class BinaryOperatorProcessor extends AbstractProc {
	@Override
	public boolean isToBeProcessed(CtElement candidate) {
		return candidate instanceof CtBinaryOperator;
	}

	public void process(CtElement candidate) {
		if (!(candidate instanceof CtBinaryOperator)) {
			return;
		}
		if(!checkSelector())
			return;
		CtBinaryOperator op = (CtBinaryOperator)candidate;
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
