package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.operations.Colors;
import model.operations.If;
import model.operations.Let;

/*
 * This class is responsible for recognizing and parsing commands
 * that take three expressions as their arguments
 * 
 * @author Prithvi Prabahar
 */

abstract public class TernaryExpression extends Expression {

	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-z]+)");

	protected String myCommand;
	protected Expression myOperand1;
	protected Expression myOperand2;
	protected Expression myOperand3;

	abstract public String getName();

	abstract public TernaryExpression getType(String command, Expression left,
			Expression middle, Expression right);

	abstract public RGBColor evaluate(double x, double y, double t,
			HashMap<String, Expression> map);

	public TernaryExpression(String command, Expression operand1,
			Expression operand2, Expression operand3) {

		myCommand = command;
		myOperand1 = operand1;
		myOperand2 = operand2;
		myOperand3 = operand3;

	}

	protected TernaryExpression() {
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(myOperand1.toString());
		result.append(myOperand2.toString());
		result.append(myOperand3.toString());
		result.append(")");

		return result.toString();
	}

	public static ArrayList<ExpressionFactory> getSubtypes() {

		ArrayList<ExpressionFactory> list = new ArrayList<ExpressionFactory>();
		list.add(Colors.getFactory());
		list.add(Let.getFactory());
		list.add(If.getFactory());

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

		parser.skipWhiteSpace();
		Expression left = parser.parseExpression(parseableString, parser);

		parser.skipWhiteSpace();
		Expression middle = parser.parseExpression(parseableString, parser);

		parser.skipWhiteSpace();
		Expression right = parser.parseExpression(parseableString, parser);

		parser.skipWhiteSpace();
		if (parser.currentCharacter() == ')') {
			parser.setCurrentPosition(parser.getCurrentPosition() + 1);
			return getType(commandName, left, middle, right);
		} else {
			throw new ParserException("Expected close paren, instead found "
					+ parseableString.substring(parser.getCurrentPosition()));
		}
	}

}
