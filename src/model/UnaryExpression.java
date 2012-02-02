package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.operations.*;

/*
 * This class is responsible for recognizing and parsing commands
 * that take one expression as their arguments
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */

abstract public class UnaryExpression extends Expression {

	private static final Pattern EXPRESSION_BEGIN_REGEX = Pattern
			.compile("\\(([a-zA-Z!]+)");

	private String myCommand;
	protected Expression myOperand1;

	abstract public String getName();
	
	protected String getSym() {
		return getName();
	}

	abstract public UnaryExpression getType(String command, Expression only);

	abstract public RGBColor evaluate(double x, double y, double t,
			HashMap<String, Expression> map);

	public UnaryExpression(String command, Expression operand1) {

		myCommand = command;
		myOperand1 = operand1;

	}

	protected UnaryExpression() {
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append("(");
		result.append(" " + myCommand + " ");
		result.append(myOperand1.toString());
		result.append(")");

		return result.toString();
	}

	public static ArrayList<ExpressionFactory> getSubtypes() {

		ArrayList<ExpressionFactory> list = new ArrayList<ExpressionFactory>();
		list.add(Neg.getFactory());
		list.add(Floor.getFactory());
		list.add(Ceil.getFactory());
		list.add(Abs.getFactory());
		list.add(Clamp.getFactory());
		list.add(Wrap.getFactory());
		list.add(Sin.getFactory());
		list.add(Cos.getFactory());
		list.add(Tan.getFactory());
		list.add(Atan.getFactory());
		list.add(Log.getFactory());
		list.add(RgbToYCrCb.getFactory());
		list.add(YCrCbtoRGB.getFactory());

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
		Expression only = parser.parseExpression(parseableString, parser);

		parser.skipWhiteSpace();
		if (parser.currentCharacter() == ')') {
			parser.setCurrentPosition(parser.getCurrentPosition() + 1);
			return getType(commandName, only);
		} else {
			throw new ParserException("Expected close paren, instead found "
					+ parseableString.substring(parser.getCurrentPosition()));
		}
	}

}
