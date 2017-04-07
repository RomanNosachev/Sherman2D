package model;

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

public class Tank extends DynamicGameObject {
	private Shell		ammo;
	
	private	float 		speed;
	private float 		minAimingAngle;
	private float 		maxAimingAngle;
	private Move 		isMoving = Move.STOP;

	public Tank()
	{
		base = new Polygon();
		ammo = new Shell();
	}
	
	public Tank(Shape base) throws IllegalArgumentException
	{
		setBase(base);
		ammo = new Shell(new Polygon());
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(Shape base, Vector2f position) throws IllegalArgumentException
	{
		setBase(base);
		base.setLocation(position);
		ammo = new Shell(new Polygon());
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
		ammo.setStartAngle(angle % 360);
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
	
	public float getShellRotateAngle()
	{
		return ammo.getRotateAngle();
	}
	
	public Shape getShellBase()
	{
		return ammo.getBase();
	}
	
	public Shape getShellBoundingCircle()
	{
		return ammo.getBoundingCircle();
	}

	public float getShellStartWidth()
	{
		return ammo.getStartWidth();
	}
	
	public float getShellStartHeight()
	{
		return ammo.getStartHeight();
	}
	
	public Vector2f getShellStartPosition()
	{
		return ammo.getStartPosition();
	}
	
	public float getShellStartPositionX()
	{
		return ammo.getStartPositionX();
	}
	
	public float getShellStartPositionY()
	{
		return ammo.getStartPositionY();
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
	
	public void shellRotate(float angle)
	{
		ammo.rotate(angle);
	}
	
	public void shellRotate(float angle, float x, float y)
	{
		ammo.rotate(angle, x, y);
	}

	public void setShellRotateAngle(float angle) 
	{
		ammo.setRotateAngle(angle);
	}
}
