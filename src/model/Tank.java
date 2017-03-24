package model;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Tank extends DynamicGameObject {
	private Shell		ammo;
	
	private	float 		speed;
	private float 		minAimingAngle;
	private float 		maxAimingAngle;
	private Move 		isMoving = Move.STOP;

	public Tank(Shape base)
	{
		setBase(base);
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(Point pos, float width, float height)
	{
		setBase(new Rectangle(pos.getX(), pos.getY(), width, height));
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(float x, float y, float width, float height)
	{
		setBase(new Rectangle(x, y, width, height));
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(Point pos, Image baseImg)
	{
		setBase(new Rectangle(pos.getX(), pos.getY(), baseImg.getWidth(), baseImg.getHeight()));
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public Tank(float x, float y, Image baseImg)
	{
		setBase(new Rectangle(x, y, baseImg.getWidth(), baseImg.getHeight()));
		ammo = new Shell(getBase().getCenterX(), getBase().getY(), 0, 0);
		boundingRadius = base.getBoundingCircleRadius();
	}
	
	public void setShell(Shell ammo)
	{
		this.ammo = ammo;
	}
	
	public void setShellBase(Shape ammoBase)
	{
		ammo = new Shell(ammoBase);
	}
	
	public void setShellBase(Point pos, float width, float height)
	{
		ammo = new Shell(pos.getX(), pos.getY(), width, height);
	}
	
	public void setShellBase(Point pos, Image imgBase)
	{
		ammo = new Shell(pos.getX(), pos.getY(), imgBase);
	}
	
	public void setShellBase(float x, float y, float width, float height)
	{
		ammo = new Shell(x, y, width, height);
	}
	
	public void setShellBase(float x, float y, Image imgBase)
	{
		ammo = new Shell(x, y, imgBase);
	}
	
	public void setShellPosition(Point pos)
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
	
	public void addShotPathPoint(Point pos)
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
	
	public Point getShotRouteVector()
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