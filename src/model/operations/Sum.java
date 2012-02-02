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
 * the sum command
 * 
 * @author Prithvi Prabahar
 */
public class Sum extends InfiniteExpression {

	public Sum(String command, ArrayList<Expression> expressions) {
		super(command, expressions);
	}

	private Sum() {
		super();
	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.sum(myExpressions, x, y, t, map);

	}

	public String getName() {
		return "sum";
	}

	public InfiniteExpression getType(String command, ArrayList<Expression> expressions) {
		return new Sum(command, expressions);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Sum());

	}

}
