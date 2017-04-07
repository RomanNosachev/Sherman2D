package model;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class StaticLevel extends StaticGameObject {	
	
	private float floorHeight;

	public StaticLevel() 
	{
		base = new Rectangle(0, 0, 0, 0);
	}
	
	public StaticLevel(float floorHeight)
	{
		this.floorHeight = floorHeight;
		base = new Rectangle(0, 0, 0, 0);
	}
	
	public StaticLevel(float floorHeight, Shape PolygonShape)
	{
		this.floorHeight = floorHeight;
		base = PolygonShape;
	}
	
	public StaticLevel(float floorHeigt, float[] PolygonPoints)
	{
		this.floorHeight = floorHeigt;
		
		if (PolygonPoints.length % 2 == 0)
			base = new Polygon(PolygonPoints);
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
