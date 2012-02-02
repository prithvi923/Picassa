package model.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Expression;
import model.RGBColor;

/**
 * Combine two colors by combining their components.
 * 
 * This is a separate class from color since it is just one set of ways to
 * combine colors, many may exist and we do not want to keep modifying the
 * RGBColor class.
 * 
 * @author Robert C. Duvall
 */
public class ColorCombinations {
	/**
	 * Combine two colors by adding their components.
	 */
	public static RGBColor add(RGBColor left, RGBColor right) {
		return new RGBColor(left.getRed() + right.getRed(), left.getGreen()
				+ right.getGreen(), left.getBlue() + right.getBlue());
	}

	/**
	 * Combine two colors by subtracting their components.
	 */
	public static RGBColor subtract(RGBColor left, RGBColor right) {
		return new RGBColor(left.getRed() - right.getRed(), left.getGreen()
				- right.getGreen(), left.getBlue() - right.getBlue());
	}

	/**
	 * Combine two colors by multiplying their components.
	 */
	public static RGBColor multiply(RGBColor left, RGBColor right) {
		return new RGBColor(left.getRed() * right.getRed(), left.getGreen()
				* right.getGreen(), left.getBlue() * right.getBlue());
	}

	/**
	 * Combine two colors by dividing their components.
	 */
	public static RGBColor divide(RGBColor left, RGBColor right) {
		return new RGBColor(left.getRed() / right.getRed(), left.getGreen()
				/ right.getGreen(), left.getBlue() / right.getBlue());
	}

	/**
	 * Combine two colors by taking the modulus of their two components.
	 */
	public static RGBColor modulus(RGBColor left, RGBColor right) {
		return new RGBColor(left.getRed() % right.getRed(), left.getGreen()
				% right.getGreen(), left.getBlue() % right.getBlue());

	}

	/**
	 * Combine two colors by taking one component to the power of the other.
	 */
	public static RGBColor exponent(RGBColor left, RGBColor right) {
		return new RGBColor(Math.pow(left.getRed(), right.getRed()), Math.pow(
				left.getGreen(), right.getGreen()), Math.pow(left.getBlue(),
				right.getBlue()));

	}

	/**
	 * Combine two colors by negating the color.
	 */
	public static RGBColor negate(RGBColor only) {
		return new RGBColor(1 / only.getRed(), 1 / only.getGreen(),
				1 / only.getBlue());

	}

	/**
	 * Create a color.
	 */
	public static RGBColor color(RGBColor left, RGBColor middle, RGBColor right) {
		return new RGBColor(left.getRed(), middle.getGreen(), right.getBlue());

	}

	/**
	 * Create a color.
	 */
	public static RGBColor var(double x) {
		return new RGBColor(x);

	}

	public static RGBColor random(RGBColor red, RGBColor green, RGBColor blue) {

		return new RGBColor(red.getRed(), green.getGreen(), blue.getBlue());
	}

	public static RGBColor floor(RGBColor only) {
		return new RGBColor(Math.floor(only.getRed()), Math.floor(only
				.getGreen()), Math.floor(only.getBlue()));
	}

	public static RGBColor ceil(RGBColor only) {
		return new RGBColor(Math.ceil(only.getRed()),
				Math.ceil(only.getGreen()), Math.ceil(only.getBlue()));
	}

	public static RGBColor abs(RGBColor only) {
		return new RGBColor(Math.abs(only.getRed()), Math.abs(only.getGreen()),
				Math.abs(only.getBlue()));
	}

	public static RGBColor clamp(RGBColor only) {

		return new RGBColor(clamp(only.getRed()), clamp(only.getGreen()),
				clamp(only.getBlue()));
	}

	private static double clamp(double num) {

		if (num < -1.0)
			return -1.0;
		if (num > 1.0)
			return 1.0;
		else
			return num;

	}

	public static RGBColor wrap(RGBColor only) {

		return new RGBColor(wrap(only.getRed()), wrap(only.getGreen()),
				wrap(only.getBlue()));
	}

	private static double wrap(double num) {

		while (num < -1.0 || num > 1.0) {
			if (num < -1.0)
				num += 2;
			if (num > 1.0)
				num -= 2;
		}

		return num;
	}

	public static RGBColor sin(RGBColor only) {

		return new RGBColor(Math.sin(only.getRed()), Math.sin(only.getGreen()),
				Math.sin(only.getBlue()));
	}

	public static RGBColor cos(RGBColor only) {

		return new RGBColor(Math.cos(only.getRed()), Math.cos(only.getGreen()),
				Math.cos(only.getBlue()));
	}

	public static RGBColor tan(RGBColor only) {

		return new RGBColor(Math.tan(only.getRed()), Math.tan(only.getGreen()),
				Math.tan(only.getBlue()));
	}

	public static RGBColor atan(RGBColor only) {

		return new RGBColor(Math.atan(only.getRed()),
				Math.atan(only.getGreen()), Math.atan(only.getBlue()));
	}

	public static RGBColor log(RGBColor only) {

		return new RGBColor(Math.log(only.getRed()), Math.log(only.getGreen()),
				Math.log(only.getBlue()));
	}

	public static RGBColor rgbToYCrCb(RGBColor only) {

		return ColorModel.rgb2ycrcb(only);
	}

	public static RGBColor yCrCbtoRGB(RGBColor only) {

		return ColorModel.ycrcb2rgb(only);

	}

	public static RGBColor perlinColor(RGBColor left, RGBColor right) {

		return PerlinNoise.colorNoise(left, right);
	}

	public static RGBColor perlinBW(RGBColor left, RGBColor right) {

		return PerlinNoise.greyNoise(left, right);
	}

	public static RGBColor sum(ArrayList<Expression> expressions, double x,
			double y, double t, HashMap<String, Expression> map) {

		RGBColor sum = expressions.get(0).evaluate(x, y, t, map);

		for (int i = 1; i < expressions.size(); i++) {

			sum = add(sum, expressions.get(i).evaluate(x, y, t, map));

		}

		return sum;
	}

	public static RGBColor prod(ArrayList<Expression> expressions, double x,
			double y, double t,HashMap<String, Expression> map) {

		RGBColor product = expressions.get(0).evaluate(x, y, t, map);

		for (int i = 1; i < expressions.size(); i++) {

			product = multiply(product, expressions.get(i).evaluate(x, y, t, map));

		}

		return product;

	}

	public static RGBColor average(ArrayList<Expression> expressions, double x,
			double y, double t, HashMap<String, Expression> map) {
		
		RGBColor average = expressions.get(0).evaluate(x, y, t, map);

		for (int i = 1; i < expressions.size(); i++) {

			average = add(average, expressions.get(i).evaluate(x, y, t, map));

		}
		
		average = divide(average, new RGBColor(expressions.size()));

		return average;
	}

	public static RGBColor min(ArrayList<Expression> expressions, double x,
			double y, double t,HashMap<String, Expression> map) {
		
		ArrayList<RGBColor> colors = new ArrayList<RGBColor>();
		
		for (Expression expression : expressions) {
			
			colors.add(expression.evaluate(x, y, t, map));
			
		}
		
		Collections.sort(colors);
		
		return colors.get(0);
		
	}
	
	public static RGBColor max(ArrayList<Expression> expressions, double x,
			double y, double t,HashMap<String, Expression> map) {
		
		ArrayList<RGBColor> colors = new ArrayList<RGBColor>();
		
		for (Expression expression : expressions) {
			
			colors.add(expression.evaluate(x, y, t, map));
			
		}
		
		Collections.sort(colors);
		
		return colors.get(colors.size()-1);
		
	}

	public static RGBColor ifthen(RGBColor left, RGBColor middle,
			RGBColor right) {
		
		if (left.getRed() > 0)
			return middle;
		else
			return right;
		
	}

}
