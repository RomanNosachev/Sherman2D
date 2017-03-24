package model;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Shell extends DynamicGameObject {
	private ArrayList<Point> path;
	
	private float 	startSpeed;
	private float 	startAngle;
	
	private Point	routeVector;

	private	boolean isFlying = false;
	
	public Shell(Shape base)
	{
		setBase(base);
		path = new ArrayList<Point>();
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Shell(Point pos, float width, float height)
	{
		setBase(new Rectangle(pos.getX(), pos.getY(), width, height));
		path = new ArrayList<Point>();
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Shell(float x, float y, float width, float height)
	{
		setBase(new Rectangle(x, y, width, height));
		path = new ArrayList<Point>();
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Shell(Point pos, Image baseImg)
	{
		setBase(new Rectangle(pos.getX(), pos.getY(), baseImg.getWidth(), baseImg.getHeight()));
		path = new ArrayList<Point>();
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Shell(float x, float y, Image imgBase)
	{
		setBase(new Rectangle(x, y, imgBase.getWidth(), imgBase.getHeight()));
		path = new ArrayList<Point>();
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
		routeVector = new Point(x, y);
	}
	
	public void setRouteVectorX(float x)
	{
		routeVector.setX(x);
	}
	
	public void setRouteVectorY(float y)
	{
		routeVector.setY(y);
	}
	
	public Point getRouteVector()
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
		routeVector = new Point((float)(startSpeed * Math.cos(startAngle * Math.PI / 180)),
								(float)(startSpeed * Math.sin(startAngle * Math.PI / 180)));
	}

	public void addPathPoint(Point pos)
	{
		path.add(pos);
	}
	
	public void addPathPoint(float x, float y)
	{
		path.add(new Point(x, y));
	}
	
	public int getPathSize()
	{
		return path.size();
	}
	
	public Point getPathPoint(int p)
	{
		return path.get(p);
	}
	
	public void clearPath()
	{
		path.clear();
	}
}
