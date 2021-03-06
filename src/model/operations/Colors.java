package model.operations;

import java.util.HashMap;

import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.TernaryExpression;
import model.util.ColorCombinations;

public class Colors extends TernaryExpression {

	public Colors(String command, Expression operand1, Expression operand2, Expression operand3) {
		super(command, operand1, operand2, operand3);
		
	}
	
	private Colors() {
		super();
	}
	
	public TernaryExpression getType(String command, Expression left, Expression middle, Expression right) {
		
		return new Colors(command, left, middle, right);
	}
	
	public String getName() {
		
		return "color";
		
	}
	
	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {
		
		return ColorCombinations.color(myOperand1.evaluate(x, y, t, map), myOperand2.evaluate(x, y, t, map), myOperand3.evaluate(x,y, t,map));
			
	}
	
	public static ExpressionFactory getFactory() {
		
		return new ExpressionFactory(new Colors());
		
	}
	

}
