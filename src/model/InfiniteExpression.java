package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.operations.*;

/*This class is responsible for recognizing and parsing commands
 * that take one expression as their arguments
 * 
 * @author Prithvi Prabahar
 */

abstract public class InfiniteExpression extends Expression {

	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-zA-Z]+)");

	private String myCommand;
	protected ArrayList<Expression> myExpressions = new ArrayList<Expression>();

	abstract public String getName();

	abstract public InfiniteExpression getType(String command, ArrayList<Expression> expressions);

	abstract public RGBColor evaluate(double x, double y, double t,
			HashMap<String, Expression> map);

	public InfiniteExpression(String command, ArrayList<Expression> expressions) {

		myCommand = command;
		myExpressions = expressions;

	}

	protected InfiniteExpression() {
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("(");
		result.append(" " + myCommand + " ");
		for (Expression exp : myExpressions) {
			result.append(exp.toString());
		}
		result.append(")");

		return result.toString();
	}

	public static ArrayList<ExpressionFactory> getSubtypes() {

		ArrayList<ExpressionFactory> list = new ArrayList<ExpressionFactory>();
		list.add(Sum.getFactory());
		list.add(Product.getFactory());
		list.add(Average.getFactory());
		list.add(Min.getFactory());
		list.add(Max.getFactory());

		return list;
	}

	public boolean isThisKindOfThing(String parseableString, Parser parser) {
		int index = parseableString.substring(parser.getCurrentPosition())
				.indexOf(" ");
		if (parseableString.substring(parser.getCurrentPosition()).indexOf("(") != 0)
			return false;
		String commandName = parseableString.substring(
				parser.getCurrentPosition() + 1, parser.getCurrentPosition()
						+ index);

		if (commandName.equals(getName()))
			return true;

		return false;
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		Matcher expMatcher = EXPRESSION_BEGIN_REGEX.matcher(parseableString);
		expMatcher.find(parser.getCurrentPosition());
		String commandName = expMatcher.group(1);
		parser.setCurrentPosition(expMatcher.end());

		ArrayList<Expression> expressions = new ArrayList<Expression>();
		
		while(parser.currentCharacter() != ')') {
			parser.skipWhiteSpace();
			expressions.add(parser.parseExpression(parseableString, parser));
		}

		parser.skipWhiteSpace();
		if (parser.currentCharacter() == ')') {
			parser.setCurrentPosition(parser.getCurrentPosition() + 1);
			return getType(commandName, expressions);
		} else {
			throw new ParserException("Expected close paren, instead found "
					+ parseableString.substring(parser.getCurrentPosition()));
		}
	}

}
