package model.field;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import model.staticGameObject.StaticGameObject;

public class Field extends StaticGameObject {
    private static final long serialVersionUID = 6584930782547834554L;
    
    private float floorHeight;
    
    public Field()
    {
        base = new Rectangle(0, 0, 0, 0);
    }
    
    public Field(float floorHeight)
    {
        this.floorHeight = floorHeight;
        base = new Rectangle(0, 0, 0, 0);
    }
    
    public Field(float floorHeight, Shape PolygonShape)
    {
        this.floorHeight = floorHeight;
        base = PolygonShape;
    }
    
    public Field(float floorHeigt, float[] PolygonPoints)
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
