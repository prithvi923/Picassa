package model.operations;

import java.util.HashMap;
import java.util.Random;
import model.Expression;
import model.ExpressionFactory;
import model.RGBColor;
import model.ZeroExpression;
import model.util.ColorCombinations;

/*
 * This subclass is responsible for recognizing and parsing 
 * the random command
 * 
 * @author Prithvi Prabahar
 */
public class Randoms extends ZeroExpression {

	Random myRand;
	RGBColor myRed;
	RGBColor myGreen;
	RGBColor myBlue;

	public Randoms(String command) {
		super(command);

		myRand = new Random();
		myRed = new RGBColor(myRand.nextDouble());
		myGreen = new RGBColor(myRand.nextDouble());
		myBlue = new RGBColor(myRand.nextDouble());

	}

	private Randoms() {
		super();
	}

	public ZeroExpression getType(String command) {

		return new Randoms(command);
	}

	public String getName() {

		return "random";

	}

	public RGBColor evaluate(double x, double y, double t,HashMap<String, Expression> map) {

		return ColorCombinations.random(myRed, myGreen, myBlue);

	}

	public static ExpressionFactory getFactory() {

		return new ExpressionFactory(new Randoms());

	}

}
