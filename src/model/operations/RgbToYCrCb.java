package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the RgbToYCrCb command
 * 
 * @author Prithvi Prabahar
 */
public class RgbToYCrCb extends UnaryExpression {

	public RgbToYCrCb(String command, Expression operand1) {
		super(command, operand1);

	}

	private RgbToYCrCb() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new RgbToYCrCb(command, only);
	}

	public String getName() {

		return "rgbToYCrCb";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.rgbToYCrCb(myOperand1.evaluate(x, y, t,map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new RgbToYCrCb());

	}

}
