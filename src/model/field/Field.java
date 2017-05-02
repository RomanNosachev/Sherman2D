package model.field;

import java.util.LinkedList;

import org.newdawn.slick.geom.Shape;

import model.staticGameObject.StaticGameObject;

public class Field extends StaticGameObject {
    private static final long serialVersionUID = 6584930782547834554L;
    
    private LinkedList<StaticGameObject> objects;
    private float floorHeight;
    
    public Field()
    {
        super();
        objects = new LinkedList<>();
    }
    
    public Field(Shape PolygonShape)
    {
       super(PolygonShape);
    }

    public LinkedList<StaticGameObject> getObjects()
    {
        return objects;
    }
    
    public void addObject(StaticGameObject object)
    {
        objects.add(object);
    }
    
    public void removeObject(int index)
    {
        objects.remove(index);
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
