package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.TernaryExpression;

/*
 * This subclass is responsible for recognizing and parsing 
 * the let command
 * 
 * @author Prithvi Prabahar
 */
public class Let extends TernaryExpression {

	public Let(String command, Expression operand1, Expression operand2,
			Expression operand3) {
		super(command, operand1, operand2, operand3);

	}

	private Let() {
		super();
	}

	public TernaryExpression getType(String command, Expression left,
			Expression middle, Expression right) {

		return new Let(command, left, middle, right);
	}

	public String getName() {

		return "let";

	}

	public RGBColor evaluate(double x, double y, double t, HashMap<String, Expression> map) {
		
		if (map == null)
			map = new HashMap<String, Expression>();
		
		map.put(myOperand1.toString(), myOperand2);
		
		return myOperand3.evaluate(x, y, t, map);

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Let());

	}

}
