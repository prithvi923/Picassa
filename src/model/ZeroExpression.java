package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.operations.Randoms;

/*
 * This class is responsible for recognizing and parsing commands
 * that take no expressions as their arguments
 * 
 * @author Prithvi Prabahar
 */

abstract public class ZeroExpression extends Expression {

	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-z]+)");

	private String myCommand;

	abstract public String getName();

	abstract public ZeroExpression getType(String command);

	abstract public RGBColor evaluate(double x, double y, double t,
			HashMap<String, Expression> map);

	public ZeroExpression(String command) {

		myCommand = command;

	}

	protected ZeroExpression() {
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(")");

		return result.toString();
	}

	public static ArrayList<ExpressionFactory> getSubtypes() {

		ArrayList<ExpressionFactory> list = new ArrayList<ExpressionFactory>();
		list.add(Randoms.getFactory());

		return list;
	}

	public boolean isThisKindOfThing(String parseableString, Parser parser) {
		int index = parseableString.substring(parser.getCurrentPosition())
				.indexOf(")");
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

		parser.skipWhiteSpace();
		if (parser.currentCharacter() == ')') {
			parser.setCurrentPosition(parser.getCurrentPosition() + 1);
			return getType(commandName);
		} else {
			throw new ParserException("Expected close paren, instead found "
					+ parseableString.substring(parser.getCurrentPosition()));
		}
	}

}
