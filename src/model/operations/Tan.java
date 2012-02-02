package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the tangent command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class Tan extends UnaryExpression {

	public Tan(String command, Expression operand1) {
		super(command, operand1);

	}

	private Tan() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Tan(command, only);
	}

	public String getName() {

		return "tan";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.tan(myOperand1.evaluate(x, y, t,map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Tan());

	}

}
