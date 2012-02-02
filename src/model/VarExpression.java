package model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.util.ColorCombinations;

/*This class is responsible for recognizing and parsing the x and y variables
 * 
 * @author Prithvi Prabahar
 */

public class VarExpression extends Expression {

	private static final Pattern VAR_REGEX = Pattern.compile("[xyt]");

	private String myVar;

	public VarExpression(String var) {

		myVar = var;

	}

	private VarExpression() {
	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		if (myVar != null) {
			if (myVar.equals("x"))
				return ColorCombinations.var(x);
			else
				return ColorCombinations.var(y);
		} else
			return null;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append(" " + myVar + " ");

		return result.toString();
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new VarExpression());
	}

	public boolean isThisKindOfThing(String parseableString, Parser parser) {
		Matcher varMatcher = VAR_REGEX.matcher(parseableString.substring(parser
				.getCurrentPosition()));
		return varMatcher.lookingAt();
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		Matcher varMatcher = VAR_REGEX.matcher(parseableString);
		varMatcher.find(parser.getCurrentPosition());
		String var = varMatcher.group(0);

		parser.setCurrentPosition(varMatcher.end());

		return new VarExpression(var);
	}

}
