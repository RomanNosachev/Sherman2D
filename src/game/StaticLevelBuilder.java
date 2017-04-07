package game;

import org.newdawn.slick.geom.Polygon;

import model.StaticGameObject;
import model.StaticLevel;

public class StaticLevelBuilder implements StaticGameObjectBuilder {
	private StaticLevel staticLevel;
	
	public void buildObject() 
	{
		staticLevel = new StaticLevel();
	}

	public void buildBase(float[] basePolygonPoints)
	{
		staticLevel.setBase(new Polygon(basePolygonPoints));
	}
	
	public void buildFloorHeight(float floorHeight)
	{
		staticLevel.setFloorHeight(floorHeight);
	}
	
	public StaticGameObject getObject() 
	{
		return staticLevel;
	}
}
