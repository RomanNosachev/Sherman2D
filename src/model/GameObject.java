package model;

import org.newdawn.slick.geom.Shape;

public interface GameObject {	
	Shape getBase();
	boolean collidesWith(Shape s) 			throws IllegalArgumentException;
	boolean collidesWith(GameObject object) throws IllegalArgumentException;
	boolean isContains(Shape s) 			throws IllegalArgumentException;
	boolean isContains(GameObject object) 	throws IllegalArgumentException;
}
