package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the negate command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class Neg extends UnaryExpression {

	public Neg(String command, Expression operand1) {
		super(command, operand1);

	}

	private Neg() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Neg(command, only);
	}

	public String getName() {

		return "neg";

	}

	public String getSym() {

		return "!";

	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		return ColorCombinations.negate(myOperand1.evaluate(x, y, map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Neg());

	}

}