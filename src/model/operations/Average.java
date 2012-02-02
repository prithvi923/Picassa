package model.operations;

import java.util.ArrayList;
import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.InfiniteExpression;
import model.RGBColor;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the average command
 * 
 * @author Prithvi Prabahar
 */
public class Average extends InfiniteExpression {

	public Average(String command, ArrayList<Expression> expressions) {
		super(command, expressions);
	}

	private Average() {
		super();
	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.average(myExpressions, x, y, t,map);

	}

	public String getName() {
		return "average";
	}

	public InfiniteExpression getType(String command, ArrayList<Expression> expressions) {
		return new Average(command, expressions);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Average());

	}

}
