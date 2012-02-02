package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.UnaryExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the log command
 * 
 * 
 * @author Prithvi Prabahar
 * 
 * 
 */
public class Log extends UnaryExpression {

	public Log(String command, Expression operand1) {
		super(command, operand1);

	}

	private Log() {
		super();
	}

	public UnaryExpression getType(String command, Expression only) {

		return new Log(command, only);
	}

	public String getName() {

		return "log";

	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		return ColorCombinations.log(myOperand1.evaluate(x, y, map));

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Log());

	}

}
