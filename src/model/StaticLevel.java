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
	
	public StaticLevel(float floorHeight, Shape PolygonShape)
	{
		this.floorHeight = floorHeight;
		setBase(PolygonShape);
	}
	
	public StaticLevel(float[] PolygonPointss)
	{
		if (PolygonPointss.length % 2 == 0)
			setBase(new Polygon(PolygonPointss));
	}

	public float getFloorHeight() 
	{
		return floorHeight;
	}

	public void setFloorHeight(float floorHeight) 
	{
		this.floorHeight = floorHeight;
	}
}
