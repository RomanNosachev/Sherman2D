package model;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public abstract class DynamicGameObject implements GameObject {
	protected Shape base;
	protected float boundingRadius;
	
	public void setPosition(Point pos) throws NullPointerException
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

	@Override
	public boolean collidesWith(GameObject object) throws NullPointerException
	{
		return base.intersects(object.getBase());
	}
	
	@Override
	public boolean collidesWith(Shape s) throws NullPointerException
	{
		return base.intersects(s);
	}
	
	@Override
	public boolean isContains(GameObject object) throws NullPointerException {
		return base.contains(object.getBase());
	}
	
	@Override
	public boolean isContains(Shape s) throws NullPointerException
	{
		return base.contains(s);
	}
	
	public boolean boundingWith(Shape s) throws NullPointerException
	{
		return getBoundingCircle().intersects(s) || getBoundingCircle().contains(s);
	}
	
	public boolean boundingWith(DynamicGameObject object) throws NullPointerException
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

	public void setBase(Shape base) throws NullPointerException
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
