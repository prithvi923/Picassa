package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the absolute value command
 * 
 * @author Prithvi Prabahar
 */
public class Abs extends UnaryExpression {

	public Abs(String command, Expression operand1) {
		super(command, operand1);

	}

	private Abs() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Abs(command, only);
	}

	public String getName() {

		return "abs";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.abs(myOperand1.evaluate(x, y, t, map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Abs());

	}

}
