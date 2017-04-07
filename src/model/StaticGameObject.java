package model;

import org.newdawn.slick.geom.Shape;

public class StaticGameObject implements GameObject {
	protected Shape base;

	@Override
	public boolean collidesWith(Shape s) throws IllegalArgumentException
	{
		return base.intersects(s);
	}
	
	@Override
	public boolean collidesWith(GameObject object) throws IllegalArgumentException 
	{
		return base.intersects(object.getBase());
	}
	
	@Override
	public boolean isContains(Shape s) throws IllegalArgumentException
	{
		return base.contains(s);
	}
	
	@Override
	public boolean isContains(GameObject object) throws IllegalArgumentException 
	{
		return base.contains(object.getBase());
	}

	public Shape getBase() 
	{
		return base;
	}

	public void setBase(Shape base) 
	{
		this.base = base;
	}
}
