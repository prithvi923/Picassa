package model.operations;

import java.util.HashMap;

import model.BinaryExpression;
import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the multiply command
 * 
 * @author Prithvi Prabahar
 */
public class Mul extends BinaryExpression {

	public Mul(String command, Expression operand1, Expression operand2) {
		super(command, operand1, operand2);
	}

	private Mul() {
		super();
	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.multiply(
				myOperand1.evaluate(x, y, t,map),
				myOperand2.evaluate(x, y, t,map));

	}

	public String getName() {
		return "mul";
	}
	
	public String getSym() {
		return "*";
	}

	public BinaryExpression getType(String command, Expression left,
			Expression right) {
		return new Mul(command, left, right);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Mul());

	}

}
