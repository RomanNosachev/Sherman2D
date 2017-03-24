package model;

import org.newdawn.slick.geom.Shape;

public interface GameObject {	
	Shape getBase();
	boolean collidesWith(Shape s);
	boolean isContains(Shape s);
}
