package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the color command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class Cos extends UnaryExpression {

	public Cos(String command, Expression operand1) {
		super(command, operand1);

	}

	private Cos() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Cos(command, only);
	}

	public String getName() {

		return "cos";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.cos(myOperand1.evaluate(x, y, t,map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Cos());

	}

}
