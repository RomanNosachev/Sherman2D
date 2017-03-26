package model;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public abstract class DynamicGameObject implements GameObject {
	protected Shape base;
	protected float boundingRadius;
	
	public void setPosition(Point pos)
	{
		base.setX(pos.getX());
		base.setY(pos.getY());
	}
	
	public void setPosition(float x, float y)
	{
		base.setLocation(x, y);
	}
	
	public void setPositionX(float x)
	{
		base.setX(x);
	}
	
	public void setPositionY(float y)
	{
		base.setY(y);
	}

	public boolean collidesWith(DynamicGameObject object)
	{
		return base.intersects(object.getBase());
	}
	
	public boolean collidesWith(Shape s)
	{
		return base.intersects(s);
	}
	
	public boolean isContains(Shape s)
	{
		return base.contains(s);
	}
	
	public boolean boundingWith(Shape s)
	{
		return getBoundingCircle().intersects(s) || getBoundingCircle().contains(s);
	}
	
	public boolean boundingWith(DynamicGameObject object)
	{
		return getBoundingCircle().intersects(object.getBoundingCircle()) || getBoundingCircle().contains(object.getBoundingCircle());
	}
 
	public Shape getBase() 
	{
		return base;
	}
	
	public Shape getBoundingCircle()
	{
		return new Ellipse(base.getCenterX(), base.getCenterY(), boundingRadius, boundingRadius);
	}

	public void setBase(Shape base) 
	{
		this.base = base;
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public float getBoundingCircleRadius()
	{
		return base.getBoundingCircleRadius();
	}
	
	public float getBaseX()
	{
		return base.getX();
	}
	
	public float getBaseY()
	{
		return base.getY();
	}
	
	public float getBaseCenterX()
	{
		return base.getCenterX();
	}
	
	public float getBaseCenterY()
	{
		return base.getCenterY();
	}
	
	public float getBaseMinX()
	{
		return base.getMinX();
	}
	
	public float getBaseMinY()
	{
		return base.getMinY();
	}
	
	public float getBaseMaxX()
	{
		return base.getMaxX();
	}
	
	public float getBaseMaxY()
	{
		return base.getMaxY();
	}
	
	public float getHeight()
	{
		return base.getHeight();
	}
	
	public float getWidth()
	{
		return base.getWidth();
	}
}
