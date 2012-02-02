package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the wrap command
 * 
 * @author Prithvi Prabahar
 */
public class Wrap extends UnaryExpression {

	public Wrap(String command, Expression operand1) {
		super(command, operand1);

	}

	private Wrap() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Wrap(command, only);
	}

	public String getName() {

		return "wrap";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.wrap(myOperand1.evaluate(x, y, t,map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Wrap());

	}

}
