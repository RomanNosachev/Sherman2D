package model.shell;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.dynamicGameObject.behavior.Detonable;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Shell 
extends DynamicGameObject 
implements Detonable
{
    private static final long serialVersionUID = 5640440536160496891L;

    private ArrayList<Vector2f> path;
    
    private float               startSpeed;
    private float               startAngle;
    
    private Vector2f            directionVector;
    private Vector2f            collisionPoint;
    
    private boolean             flying = false;
    
    private float               damage;
    private float               explosionRadius;
        
    public Shell()
    {
        base = new Polygon();
        path = new ArrayList<Vector2f>();
        boundingRadius = base.getBoundingCircleRadius();
    }
    
    public Shell(Shape base)
    {
        this.base = base;
        path = new ArrayList<Vector2f>();
        boundingRadius = base.getBoundingCircleRadius();
    }
    
    @Override
    public Shell clone()
    {
        try 
        {
            Shell clone = (Shell) super.clone();
            clone.setBase(new Polygon(base.getPoints()));
            return clone;
        }
        catch (CloneNotSupportedException e) 
        {
            throw new InternalError();
        }
    }
    
    public boolean isFlying()
    {
        return flying;
    }
    
    public void setFlying(boolean fl)
    {
        flying = fl;
    }
    
    public void setStartSpeed(float speed)
    {
        startSpeed = speed;
    }
    
    public float getStartSpeed()
    {
        return startSpeed;
    }
    
    public void setStartAngle(float angle)
    {
        startAngle = angle;
    }
    
    public float getStartAngle()
    {
        return startAngle;
    }
    
    public void setDirection(float x, float y)
    {
        directionVector = new Vector2f(x, y);
    }
    
    public void setDirectionX(float x)
    {
        directionVector.x = x;
    }
    
    public void setDirectionY(float y)
    {
        directionVector.y = y;
    }
    
    public Vector2f getDirectionVector()
    {
        return directionVector;
    }
    
    public float getDirectionX()
    {
        return directionVector.getX();
    }
    
    public float getDirectionY()
    {
        return directionVector.getY();
    }
    
    public void setDirection()
    {
        directionVector = new Vector2f((float) (startSpeed * Math.cos(startAngle * Math.PI / 180)),
                (float) (startSpeed * Math.sin(startAngle * Math.PI / 180)));
    }
    
    public void addPathPoint(Vector2f pos)
    {
        path.add(pos);
    }
    
    public void addPathPoint(float x, float y)
    {
        path.add(new Vector2f(x, y));
    }
    
    public int getPathSize()
    {
        return path.size();
    }
    
    public Vector2f getPathPoint(int p)
    {
        return path.get(p);
    }
    
    public void clearPath()
    {
        path.clear();
    }

    @Override
    public boolean isCollides()
    {
        return collides;
    }

    @Override
    public void setCollides(boolean collides)
    {
        this.collides = collides;
    }

    public Vector2f getCollisionPoint()
    {
        return collisionPoint;
    }

    public void setCollisionPoint(Vector2f collisionPoint)
    {
        this.collisionPoint = collisionPoint;
    }

    @Override
    public float getRadius()
    {
        return explosionRadius;
    }

    @Override
    public void setRadius(float radius)
    {
        explosionRadius = radius;
    }

    @Override
    public float getPower()
    {
        return damage;
    }

    @Override
    public void setPower(float power)
    {
        damage = power;
    }
}
