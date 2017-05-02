package model.shell;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.gameObject.Explosivable;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Shell 
extends DynamicGameObject 
implements Explosivable
{
    private static final long serialVersionUID = 5640440536160496891L;

    private ArrayList<Vector2f> path;
    
    private float               startSpeed;
    private float               startAngle;
    
    private Vector2f            direction;
    private Vector2f            collisionPoint;
    
    private boolean             flying = false;
    private boolean             collides = false;
    
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
            Shell clon = (Shell) super.clone();
            clon.setBase(new Polygon(base.getPoints()));
            return clon;
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
        direction = new Vector2f(x, y);
    }
    
    public void setDirectionX(float x)
    {
        direction.x = x;
    }
    
    public void setDirectionY(float y)
    {
        direction.y = y;
    }
    
    public Vector2f getDirection()
    {
        return direction;
    }
    
    public float getDirectionX()
    {
        return direction.getX();
    }
    
    public float getDirectionY()
    {
        return direction.getY();
    }
    
    public void setDirection()
    {
        direction = new Vector2f((float) (startSpeed * Math.cos(startAngle * Math.PI / 180)),
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

    public boolean isCollides()
    {
        return collides;
    }

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
