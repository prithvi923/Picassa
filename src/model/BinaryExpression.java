package model;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.operations.*;

/*
 * This class is responsible for recognizing and parsing commands
 * that require two expressions as arguements
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
abstract public class BinaryExpression extends Expression {

	private String myCommand;
	protected Expression myOperand1;
	protected Expression myOperand2;

	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-zA-Z^%/+*-]+)");

	abstract public String getName();
	
	protected String getSym() {
		return getName();
	}

	abstract public BinaryExpression getType(String command, Expression left,
			Expression right);

	abstract public RGBColor evaluate(double x, double y, double t
			HashMap<String, Expression> map);

	public BinaryExpression(String command, Expression operand1,
			Expression operand2) {

		myCommand = command;
		myOperand1 = operand1;
		myOperand2 = operand2;

	}

	protected BinaryExpression() {
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(myOperand1.toString());
		result.append(myOperand2.toString());
		result.append(")");

		return result.toString();
	}

	public static ArrayList<ExpressionFactory> getSubtypes() {

		ArrayList<ExpressionFactory> list = new ArrayList<ExpressionFactory>();
		list.add(Plus.getFactory());
		list.add(Minus.getFactory());
		list.add(Exp.getFactory());
		list.add(Mod.getFactory());
		list.add(Div.getFactory());
		list.add(Mul.getFactory());
		list.add(PerlinColor.getFactory());
		list.add(PerlinBW.getFactory());

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

		if (commandName.equals(getName())||commandName.equals(getSym()))
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
		Expression right = parser.parseExpression(parseableString, parser);

		parser.skipWhiteSpace();
		if (parser.currentCharacter() == ')') {
			parser.setCurrentPosition(parser.getCurrentPosition() + 1);
			return getType(commandName, left, right);
		} else {
			throw new ParserException(
					"BinaryExpression: Expected close paren, instead found "
							+ parseableString.substring(parser
									.getCurrentPosition()));
		}
	}

}
