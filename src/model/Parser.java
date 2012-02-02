package model;

import java.util.*;

/**
 * Parses a string into an expression tree based on rules for arithmetic.
 * 
 * Due to the nature of the language being parsed, a recursive descent parser is
 * used http://en.wikipedia.org/wiki/Recursive_descent_parser
 * 
 * @author former student solution
 * @author Robert C. Duvall (added comments, exceptions, some functions)
 */
public class Parser {

	// state of the parser
	private int myCurrentPosition;
	private String myInput;
	private ArrayList<ExpressionFactory> expressionKinds = new ArrayList<ExpressionFactory>();

	public Parser() {
		// I use the factory method to check what kind of expression the string
		// is
		// to avoid if statements
		// as well as making adding new types of expression easy
		expressionKinds.addAll(ZeroExpression.getSubtypes());
		expressionKinds.addAll(UnaryExpression.getSubtypes());
		expressionKinds.addAll(BinaryExpression.getSubtypes());
		expressionKinds.addAll(TernaryExpression.getSubtypes());
		expressionKinds.addAll(InfiniteExpression.getSubtypes());
		expressionKinds.add(NumberExpression.getFactory());
		expressionKinds.add(VarExpression.getFactory());
		expressionKinds.add(AssignmentExpression.getFactory());

	}

	/**
	 * Converts given string into expression tree.
	 * 
	 * @param input
	 *            expression given in the language to be parsed
	 * @return expression tree representing the given formula
	 */
	public Expression makeExpression(String input) {

		myInput = input;
		myCurrentPosition = 0;
		Expression result = parseExpression(myInput, this);
		skipWhiteSpace();
		if (notAtEndOfString()) {
			throw new ParserException(
					"Unexpected characters at end of the string: "
							+ myInput.substring(myCurrentPosition),
					ParserException.Type.EXTRA_CHARACTERS);
		}
		return result;
	}

	// Checks to see what type of expression is occurring at the current
	// position in the input string
	public Expression parseExpression(String input, Parser parser) {

		for (ExpressionFactory kind : expressionKinds) {

			if (kind.isThisKindOfExpression(myInput, this))
				return kind.parseExpression(myInput, this);

		}

		return null;

	}

	public int getCurrentPosition() {

		return myCurrentPosition;

	}

	public void setCurrentPosition(int newCurrentPosition) {

		myCurrentPosition = newCurrentPosition;

	}

	public void skipWhiteSpace() {
		while (notAtEndOfString() && Character.isWhitespace(currentCharacter())) {
			myCurrentPosition++;
		}
	}

	public char currentCharacter() {
		return myInput.charAt(myCurrentPosition);
	}

	private boolean notAtEndOfString() {
		return myCurrentPosition < myInput.length();
	}
}
