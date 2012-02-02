package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the arctangent command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class Atan extends UnaryExpression {

	public Atan(String command, Expression operand1) {
		super(command, operand1);

	}

	private Atan() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Atan(command, only);
	}

	public String getName() {

		return "atan";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.atan(myOperand1.evaluate(x, y, t,map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Atan());

	}

}
