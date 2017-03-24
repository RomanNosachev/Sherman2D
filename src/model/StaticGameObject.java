package model;

import org.newdawn.slick.geom.Shape;

public abstract class StaticGameObject implements GameObject {
	protected Shape base;

	public boolean collidesWith(Shape s)
	{
		return base.intersects(s);
	}
	
	public boolean isContains(Shape s)
	{
		return base.contains(s);
	}

	public Shape getBase() {
		return base;
	}

	public void setBase(Shape base) {
		this.base = base;
	}
}
