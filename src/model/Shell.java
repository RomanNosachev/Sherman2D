package model;

import java.util.ArrayList;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Shape;

public class Shell extends DynamicGameObject {
	private ArrayList<Vector2f> path;
	
	private float 	startSpeed;
	private float 	startAngle;
	
	private Vector2f	routeVector;

	private	boolean isFlying = false;
	
	public Shell(Shape base)
	{
		setBase(base);
		path = new ArrayList<Vector2f>();
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public boolean isFlying()
	{
		return isFlying;
	}
	
	public void setIsFlying(boolean fl)
	{
		isFlying = fl;
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
		routeVector = new Vector2f((float)(startSpeed * Math.cos(startAngle * Math.PI / 180)),
							       (float)(startSpeed * Math.sin(startAngle * Math.PI / 180)));
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
}
