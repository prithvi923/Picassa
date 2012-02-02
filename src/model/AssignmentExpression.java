package model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is responsible for recognizing and parsing variables set by the let command
 * 
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class AssignmentExpression extends Expression {

	private String myVar;

	private static final Pattern ASSIGNMENT_BEGIN_REGEX = Pattern
			.compile("[a-zA-Z]+");

	public AssignmentExpression(String varName) {

		myVar = varName;

	}

	private AssignmentExpression() {
	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append(myVar);

		return result.toString();
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new AssignmentExpression());
	}

	public boolean isThisKindOfThing(String parseableString, Parser parser) {

		Matcher varMatcher = ASSIGNMENT_BEGIN_REGEX.matcher(parseableString
				.substring(parser.getCurrentPosition()));
		return varMatcher.lookingAt();
	}

	public Expression parseExpression(String parseableString, Parser parser) {

		Matcher varMatcher = ASSIGNMENT_BEGIN_REGEX.matcher(parseableString);
		varMatcher.find(parser.getCurrentPosition());
		String var = varMatcher.group(0);

		parser.setCurrentPosition(varMatcher.end());

		return new AssignmentExpression(var);
	}

	public RGBColor evaluate(double x, double y, double t, HashMap<String, Expression> map) {
		if (map == null)
			throw new ParserException("Undefined variable: " + myVar);

		if (map.containsKey(myVar))
			return map.get(myVar).evaluate(x, y, t, map);
		else {
			throw new ParserException("Undefined variable: " + myVar);
		}
	}

}
