package model;

/*
 * This class is responsible for
 * allowing different kinds of
 * expressions to be created
 * using the factory method
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class ExpressionFactory {

	// This Factory class allows different kinds of expressions to test if the
	// current expression is theirs

	private Expression myExpression;

	public ExpressionFactory(Expression expression) {
		myExpression = expression;
	}

	public boolean isThisKindOfExpression(String parseableString, Parser parser) {

		return myExpression.isThisKindOfThing(parseableString, parser);

	}

	public Expression parseExpression(String parseableString, Parser parser) {

		return myExpression.parseExpression(parseableString, parser);

	}

}
