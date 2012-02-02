package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the clamp command
 * 
 * @author Prithvi Prabahar
 */
public class Clamp extends UnaryExpression {

	public Clamp(String command, Expression operand1) {
		super(command, operand1);

	}

	private Clamp() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Clamp(command, only);
	}

	public String getName() {

		return "clamp";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.clamp(myOperand1.evaluate(x, y, t,map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Clamp());

	}

}
