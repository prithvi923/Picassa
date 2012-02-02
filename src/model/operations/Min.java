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
 * the minimum command
 * 
 * @author Prithvi Prabahar
 */
public class Min extends InfiniteExpression {

	public Min(String command, ArrayList<Expression> expressions) {
		super(command, expressions);
	}

	private Min() {
		super();
	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		return ColorCombinations.min(myExpressions, x, y, map);

	}

	public String getName() {
		return "min";
	}

	public InfiniteExpression getType(String command, ArrayList<Expression> expressions) {
		return new Min(command, expressions);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Min());

	}

}
