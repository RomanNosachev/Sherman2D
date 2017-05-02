package model.staticGameObject;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import model.gameObject.GameObject;

public class StaticGameObject implements GameObject {
    private static final long serialVersionUID = -7882741326275027819L;
    
    protected Shape base;
    
    public StaticGameObject()
    {
        base = new Rectangle(0, 0, 0, 0);
    }
    
    public StaticGameObject(Shape base)
    {
        this.base = base;
    }
    
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
