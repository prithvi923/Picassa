package model;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is responsible for recognizing and parsing numbers in the input string
 * 
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */

public class NumberExpression extends Expression {

	private RGBColor myValue;
	private static final Pattern DOUBLE_REGEX = Pattern
			.compile("(\\-?[0-9]+(\\.[0-9]+)?)|(\\.[0-9]+)");

	public NumberExpression(RGBColor number) {

		myValue = number;

	}

	private NumberExpression() {
	}

	public RGBColor evaluate(double x, double y, double t, HashMap<String, Expression> map) {

		return myValue;

	}

	public String toString() {
		StringBuffer result = new StringBuffer();

		result.append(" " + myValue + " ");

		return result.toString();
	}

	public static ExpressionFactory getFactory() {
		return new ExpressionFactory(new NumberExpression());
	}

	public boolean isThisKindOfThing(String parseableString, Parser parser) {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(parseableString
				.substring(parser.getCurrentPosition()));
		return doubleMatcher.lookingAt();
	}

	public Expression parseExpression(String parseableString, Parser parser) {
		Matcher doubleMatcher = DOUBLE_REGEX.matcher(parseableString);
		doubleMatcher.find(parser.getCurrentPosition());
		String numberMatch = parseableString.substring(doubleMatcher.start(),
				doubleMatcher.end());
		parser.setCurrentPosition(doubleMatcher.end());
		double value = Double.parseDouble(numberMatch);
		RGBColor gray = new RGBColor(value);
		return new NumberExpression(gray);
	}

}
