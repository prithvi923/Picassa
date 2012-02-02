package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the sine command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class Sin extends UnaryExpression {

	public Sin(String command, Expression operand1) {
		super(command, operand1);

	}

	private Sin() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Sin(command, only);
	}

	public String getName() {

		return "sin";

	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		return ColorCombinations.sin(myOperand1.evaluate(x, y, map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Sin());

	}

}
