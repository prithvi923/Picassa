package model.operations;

import java.util.HashMap;

import model.BinaryExpression;
import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the Perlin Color command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class PerlinColor extends BinaryExpression {

	public PerlinColor(String command, Expression operand1, Expression operand2) {
		super(command, operand1, operand2);
	}

	private PerlinColor() {
		super();
	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		return ColorCombinations.perlinColor(
				myOperand1.evaluate(x, y, map),
				myOperand2.evaluate(x, y, map));

	}

	public String getName() {
		return "perlinColor";
	}
	
	public String getSym() {
		return getName();
	}

	public BinaryExpression getType(String command, Expression left,
			Expression right) {
		return new PerlinColor(command, left, right);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new PerlinColor());

	}

}
