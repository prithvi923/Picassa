package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the floor command
 * 
 * @author Prithvi Prabahar
 */
public class Floor extends UnaryExpression {

	public Floor(String command, Expression operand1) {
		super(command, operand1);

	}

	private Floor() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Floor(command, only);
	}

	public String getName() {

		return "floor";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.floor(myOperand1.evaluate(x, y,t, map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Floor());

	}

}
