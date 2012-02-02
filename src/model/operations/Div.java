package model.operations;

import java.util.HashMap;

import model.BinaryExpression;
import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the divide command
 * 
 * @author Prithvi Prabahar
 */
public class Div extends BinaryExpression {

	public Div(String command, Expression operand1, Expression operand2) {
		super(command, operand1, operand2);
	}

	private Div() {
		super();
	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.divide(
				myOperand1.evaluate(x, y, t,map),
				myOperand2.evaluate(x, y, t,map));

	}

	public String getName() {
		return "div";
	}
	
	public String getSym() {
		return "/";
	}

	public BinaryExpression getType(String command, Expression left,
			Expression right) {
		return new Div(command, left, right);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Div());

	}

}
