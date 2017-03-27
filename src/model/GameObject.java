package model;

import org.newdawn.slick.geom.Shape;

public interface GameObject {	
	Shape getBase();
	boolean collidesWith(Shape s) 			throws NullPointerException;
	boolean collidesWith(GameObject object) throws NullPointerException;
	boolean isContains(Shape s) 			throws NullPointerException;
	boolean isContains(GameObject object) 	throws NullPointerException;
}
