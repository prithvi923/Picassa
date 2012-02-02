package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the ceiling command
 * 
 * @author Prithvi Prabahar
 */

public class Ceil extends UnaryExpression {

	public Ceil(String command, Expression operand1) {
		super(command, operand1);

	}

	private Ceil() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Ceil(command, only);
	}

	public String getName() {

		return "ceil";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.ceil(myOperand1.evaluate(x, y, t, map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Ceil());

	}

}
