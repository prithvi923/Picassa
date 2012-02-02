package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.TernaryExpression;
import model.util.ColorCombinations;
/*
 * This subclass is responsible for recognizing and parsing 
 * the if command
 * 
 * @author Prithvi Prabahar
 */
public class If extends TernaryExpression {

	public If(String command, Expression operand1, Expression operand2, Expression operand3) {
		super(command, operand1, operand2, operand3);
		
	}
	
	private If() {
		super();
	}
	
	public TernaryExpression getType(String command, Expression left, Expression middle, Expression right) {
		
		return new If(command, left, middle, right);
	}
	
	public String getName() {
		
		return "if";
		
	}
	
	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {
		
		return ColorCombinations.ifthen(myOperand1.evaluate(x, y, t,map), myOperand2.evaluate(x, y, t,map), myOperand3.evaluate(x,y, t,map));
			
	}
	
	public static ExpressionFactory getFactory() {
		
		return new ExpressionFactory(new If());
		
	}
	

}
