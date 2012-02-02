package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the YCrCbtoRGB command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class YCrCbtoRGB extends UnaryExpression {

	public YCrCbtoRGB(String command, Expression operand1) {
		super(command, operand1);

	}

	private YCrCbtoRGB() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new YCrCbtoRGB(command, only);
	}

	public String getName() {

		return "yCrCbtoRGB";

	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		return ColorCombinations.yCrCbtoRGB(myOperand1.evaluate(x, y, map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new YCrCbtoRGB());

	}

}
