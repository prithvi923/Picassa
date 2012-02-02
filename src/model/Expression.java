package model;

import java.util.*;

/**
 * An Expression represents a mathematical expression as a tree.
 * 
 * In this format, the internal nodes represent mathematical 
 * functions and the leaves represent constant values.
 *
 * @author former student solution
 * @author Robert C. Duvall (added comments, some code)
 */
abstract public class Expression
{
    

    /**
     * Expression is a super class so that its subclasses are instances of it.
     * In this way, when the program runs the evaluate method on an Expression,
     * it does not matter what subtype it is, because it is also an Expression.
     * With interfaces, this would not work.
     * 
     */
    abstract public RGBColor evaluate (double x, double y, double t, HashMap<String, Expression> map);
    abstract public String toString ();
	abstract public boolean isThisKindOfThing(String parseableString, Parser parser);
	abstract public Expression parseExpression(String parseableString, Parser parser);

}
