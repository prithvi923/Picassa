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
 * the product command
 * 
 * @author Prithvi Prabahar
 */
public class Product extends InfiniteExpression {

	public Product(String command, ArrayList<Expression> expressions) {
		super(command, expressions);
	}

	private Product() {
		super();
	}

	public RGBColor evaluate(double x, double y, HashMap<String, Expression> map) {

		return ColorCombinations.prod(myExpressions, x, y, map);

	}

	public String getName() {
		return "product";
	}

	public InfiniteExpression getType(String command, ArrayList<Expression> expressions) {
		return new Product(command, expressions);
	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Product());

	}

}
