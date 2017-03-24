package model;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class StaticLevel extends StaticGameObject {	
	
	private float floorHeight;

	public StaticLevel(float floorHeight)
	{
		this.floorHeight = floorHeight;
		
		float [] polygonPoint = new float[]
		{
			0, 0,
			Display.getWidth(), 0,
			Display.getWidth(), Display.getHeight() - floorHeight,
			Display.getWidth() - floorHeight, Display.getHeight() - floorHeight,
			Display.getWidth() - floorHeight, Display.getHeight() - floorHeight * 2,
			Display.getWidth() - floorHeight * 2, Display.getHeight() - floorHeight * 2,
			Display.getWidth() - floorHeight * 2, Display.getHeight() - floorHeight,
			0, Display.getHeight() - floorHeight
		};
				
		setBase(new Polygon(polygonPoint));
	}
	
	public StaticLevel(Shape polygonShape)
	{
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
