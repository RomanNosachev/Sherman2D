package model.camera;

import org.newdawn.slick.geom.Vector2f;

public class Camera {
    private Vector2f position;

    public Camera(Vector2f pos)
    {
        position = pos;
    }
    
    public Vector2f getPosition()
    {
        return position;
    }
    
    public float getX()
    {
        return position.x;
    }
    
    public float getY()
    {
        return position.y;
    }

    public void setX(float x)
    {
        position.x = x;
    }
    
    public void setY(float y)
    {
        position.y = y;
    }
    
    public void setPosition(Vector2f position)
    {
        this.position = position;
    }
}
