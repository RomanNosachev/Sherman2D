package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Tank extends DynamicGameObject {
	private Shell		ammo;
	
	private	float 		speed;
	private float 		minAimingAngle;
	private float 		maxAimingAngle;
	private Move 		isMoving = Move.STOP;

	public Tank(Shape base) throws IllegalArgumentException
	{
		setBase(base);
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(Vector2f pos, float width, float height) throws IllegalArgumentException
	{
		float[] polygonPoint = {pos.getX(), pos.getY(), 
								pos.getX() + width, pos.getY(),
								pos.getX() + width, pos.getY() + height,
								pos.getX(), pos.getY() + height};
				
		setBase(new Polygon(polygonPoint));
		
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(float x, float y, float width, float height) throws IllegalArgumentException
	{
		float[] polygonPoint = {x, y, 
								x + width, y,
								x + width, y + height,
								x, y + height};
		
		setBase(new Polygon(polygonPoint));
		
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(Vector2f pos, Image baseImg) throws IllegalArgumentException
	{
		float[] polygonPoint = {pos.getX(), pos.getY(), 
								pos.getX() + baseImg.getWidth(), pos.getY(),
								pos.getX() + baseImg.getWidth(), pos.getY() + baseImg.getHeight(),
								pos.getX(), pos.getY() + baseImg.getHeight()};
		
		setBase(new Polygon(polygonPoint));
		
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(float x, float y, Image baseImg) throws IllegalArgumentException
	{
		float[] polygonPoint = {x, y, 
								x + baseImg.getWidth(), y,
								x + baseImg.getWidth(), y + baseImg.getHeight(),
								x, y + baseImg.getHeight()};
		
		setBase(new Polygon(polygonPoint));
		
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public void setShell(Shell ammo) throws IllegalArgumentException
	{
		this.ammo = ammo;
	}
	
	public void setShellBase(Shape ammoBase) throws IllegalArgumentException
	{
		ammo = new Shell(ammoBase);
	}
	
	public void setShellBase(Vector2f pos, float width, float height) throws IllegalArgumentException
	{
		ammo = new Shell(pos.getX(), pos.getY(), width, height);
	}
	
	public void setShellBase(Vector2f pos, Image imgBase) throws IllegalArgumentException
	{
		ammo = new Shell(pos.getX(), pos.getY(), imgBase);
	}
	
	public void setShellBase(float x, float y, float width, float height) throws IllegalArgumentException
	{
		ammo = new Shell(x, y, width, height);
	}
	
	public void setShellBase(float x, float y, Image imgBase) throws IllegalArgumentException
	{
		ammo = new Shell(x, y, imgBase);
	}
	
	public void setShellPosition(Vector2f pos) throws IllegalArgumentException
	{
		ammo.setPosition(pos);
	}
	
	public void setShellPosition(float x, float y) 
	{
		ammo.setPosition(x, y);
	}
	
	public void setShellPositionX(float x)
	{
		ammo.setPositionX(x);
	}
	
	public void setShellPositionY(float y)
	{
		ammo.setPositionY(y);
	}
	
	public void setSpeed(float speed)
	{
		this.speed = speed;
	}
	
	public float getMovePoint()
	{
		return speed;
	}
	
	public boolean isShooting()
	{
		return ammo.isFlying();
	}
	
	public Move isMoving()
	{
		return isMoving;
	}
	
	public void setIsShooting(boolean fl)
	{
		ammo.setIsFlying(fl);
	}
	
	public void setIsMoving(Move fl)
	{
		isMoving = fl;
	}

	public void setShotStartSpeed(float speed)
	{
		ammo.setStartSpeed(speed);
	}
	
	public float getShotStartSpeed()
	{
		return ammo.getStartSpeed();
	}
	
	public void setShotStartAngle(float angle)
	{
		ammo.setStartAngle(angle);
	}
	
	public float getShotStartAngle()
	{
		return ammo.getStartAngle();
	}
	
	public void shot()
	{
		setIsShooting(true);
		ammo.setRouteVector();
		ammo.clearPath();
		ammo.addPathPoint(ammo.getBase().getCenterX(), ammo.getBase().getCenterY());
	}
	
	public void addShotPathPoint(Vector2f pos)
	{
		ammo.addPathPoint(pos);
	}
	
	public void addShotPathPoint(float x, float y)
	{
		ammo.addPathPoint(x, y);
	}
	
	public void setShotRouteVector(float x, float y)
	{
		ammo.setRouteVector(x, y);
	}
	
	public void setShotRouteVectorX(float x)
	{
		ammo.setRouteVectorX(x);
	}
	
	public void setShotRouteVectorY(float y)
	{
		ammo.setRouteVectorY(y);
	}
	
	public Vector2f getShotRouteVector()
	{
		return ammo.getRouteVector();
	}
	
	public float getShotRouteVectorX()
	{
		return ammo.getRouteVectorX();
	}
	
	public float getShotRouteVectorY()
	{
		return ammo.getRouteVectorY();
	}

	public Shape getShellBase()
	{
		return ammo.getBase();
	}
	
	public Shape getShellBoundingCircle()
	{
		return ammo.getBoundingCircle();
	}

	public float getMinAimingAngle() 
	{
		return minAimingAngle;
	}

	public void setMinAimingAngle(float minAimingAngle) 
	{
		this.minAimingAngle = minAimingAngle;
	}

	public float getMaxAimingAngle() 
	{
		return maxAimingAngle;
	}

	public void setMaxAimingAngle(float maxAimingAngle)
	{
		this.maxAimingAngle = maxAimingAngle;
	}
}
