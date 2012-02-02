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
 * the maximum command
 * 
 * @author Prithvi Prabahar
 */
public class Max extends InfiniteExpression {

	public Max(String command, ArrayList<Expression> expressions) {
		super(command, expressions);
	}

	private Max() {
		super();
	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.max(myExpressions, x, y, t,map);

	}

	public String getName() {
		return "max";
	}

	public InfiniteExpression getType(String command, ArrayList<Expression> expressions) {
		return new Max(command, expressions);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Max());

	}

}
