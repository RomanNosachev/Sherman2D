package model.shell;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Shell extends DynamicGameObject {
    private static final long serialVersionUID = 5640440536160496891L;

    private ArrayList<Vector2f> path;
    
    private float               startSpeed;
    private float               startAngle;
    
    private Vector2f            routeVector;
    private Vector2f            collisionPoint;
    
    private boolean             flying = false;
    private boolean             collides = false;
    
    private int                 damage;
    
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
    
    public void setRouteVector(float x, float y)
    {
        routeVector = new Vector2f(x, y);
    }
    
    public void setRouteVectorX(float x)
    {
        routeVector.x = x;
    }
    
    public void setRouteVectorY(float y)
    {
        routeVector.y = y;
    }
    
    public Vector2f getRouteVector()
    {
        return routeVector;
    }
    
    public float getRouteVectorX()
    {
        return routeVector.getX();
    }
    
    public float getRouteVectorY()
    {
        return routeVector.getY();
    }
    
    public void setRouteVector()
    {
        routeVector = new Vector2f((float) (startSpeed * Math.cos(startAngle * Math.PI / 180)),
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

    public int getDamage()
    {
        return damage;
    }

    public void setDamage(int damage)
    {
        this.damage = damage;
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
}
