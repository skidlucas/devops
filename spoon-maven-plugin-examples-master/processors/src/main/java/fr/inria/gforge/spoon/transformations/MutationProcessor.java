package fr.inria.gforge.spoon.transformations;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

/**
 * Mutates randomly some parts of the abstract syntax tree (currently only the arithmetic operators)
 *
 * @author Martin Monperrus
 */
public class MutationProcessor extends AbstractProcessor<CtElement> {

	private static final double MUTATION_PROBABILITY = 1;
	private static final Random random = new Random();

	public void process(CtElement element) {
		// for instance we can mutate operators
		if (element instanceof CtBinaryOperator<?>) {
			CtBinaryOperator<?> op = (CtBinaryOperator<?>) element;

			List<BinaryOperatorKind> arithmeticOperators =
					Arrays.asList(
							BinaryOperatorKind.PLUS,
							BinaryOperatorKind.MINUS,
							BinaryOperatorKind.MUL,
							BinaryOperatorKind.DIV
					);

			// we may mutate with a certain probability
			if (random.nextFloat() < MUTATION_PROBABILITY) {
				// let's mutate arithmethic operators
				if (arithmeticOperators.contains(op.getKind())) {
					op.setKind(arithmeticOperators.get(random.nextInt(arithmeticOperators.size())));
				}
			}
		}
	}
}