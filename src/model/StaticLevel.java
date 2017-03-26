package model;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class StaticLevel extends StaticGameObject {	
	
	private float floorHeight;

	public StaticLevel(float floorHeight)
	{
		this.floorHeight = floorHeight;
		setBase(new Rectangle(0, 0, 0, 0));
	}
	
	public StaticLevel(float floorHeight, Shape polygonShape)
	{
		this.floorHeight = floorHeight;
		setBase(polygonShape);
	}
	
	public StaticLevel(float[] polygonPoint)
	{
		if (polygonPoint.length % 2 == 0)
			setBase(new Polygon(polygonPoint));
	}

	public float getFloorHeight() {
		return floorHeight;
	}

	public void setFloorHeight(float floorHeight) {
		this.floorHeight = floorHeight;
	}
}
