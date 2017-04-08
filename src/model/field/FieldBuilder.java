package model.field;

import org.newdawn.slick.geom.Polygon;

import model.staticGameObject.StaticGameObject;
import model.staticGameObject.StaticGameObjectBuilder;

public class FieldBuilder implements StaticGameObjectBuilder {
    private Field field;
    
    public void buildObject()
    {
        field = new Field();
    }
    
    public void buildBase(float[] basePolygonPoints)
    {
        field.setBase(new Polygon(basePolygonPoints));
    }
    
    public void buildFloorHeight(float floorHeight)
    {
        field.setFloorHeight(floorHeight);
    }
    
    public StaticGameObject getObject()
    {
        return field;
    }
}
