package model;

import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Shape;

public class Level {
	private Tank 		actor;
	private StaticLevel field;
	
	public Level(Tank actor, StaticLevel field)
	{
		this.actor = actor;
		this.field = field;
	}

	public boolean isShooting()
	{
		return actor.isShooting();
	}
	
	public void setIsMoving(Move fl)
	{
		actor.setIsMoving(fl);
	}
	
	public Move isMoving()
	{
		return actor.isMoving();
	}
	
	public void setIsShooting(boolean fl)
	{
		actor.setIsShooting(fl);
	}

	public void setShellPosition(Point pos)
	{
		actor.setShellPosition(pos);
	}
	
	public void setShellPosition(float x, float y)
	{
		actor.setShellPosition(x, y);
	}
	
	public void setShellPositionX(float x)
	{
		actor.setShellPositionX(x);
	}
	
	public void setShellPositionY(float y)
	{
		actor.setShellPositionY(y);
	}
	
	public void setPosition(Point pos)
	{
		actor.setPosition(pos);
	}
	
	public void setPosition(float x, float y)
	{
		actor.setPosition(x, y);
	}
	
	public void setPositionX(float x)
	{
		actor.setPositionX(x);
	}
	
	public void setPositionY(float y)
	{
		actor.setPositionY(y);
	}
	
	public float getMinAimingAngle()
	{
		return actor.getMinAimingAngle();
	}
	
	public float getMaxAimingAngle()
	{
		return actor.getMaxAimingAngle();
	}
	
	public void setShotStartSpeed(float speed)
	{
		actor.setShotStartSpeed(speed);
	}
	
	public float getShortStartSpeed()
	{
		return actor.getShotStartSpeed();
	}
	
	public void setShotStartAngle(float angle)
	{
		actor.setShotStartAngle(angle);
	}
	
	public float getShotStartAngle()
	{
		return actor.getShotStartAngle();
	}
	
	public void shot()
	{
		actor.shot();
	}
	
	public void setSpeed(float speed)
	{
		actor.setSpeed(speed);
	}
	
	public float getMovePoint()
	{
		return actor.getMovePoint();
	}
	
	public void setShotRouteVector(float x, float y)
	{
		actor.setShotRouteVector(x, y);
	}
	
	public void setShotRouteVectorX(float x)
	{
		actor.setShotRouteVectorX(x);
	}
	
	public void setShotRouteVectorY(float y)
	{
		actor.setShotRouteVectorY(y);
	}
	
	public Point getShotRouteVector()
	{
		return actor.getShotRouteVector();
	}
	
	public float getShotRouteVectorX()
	{
		return actor.getShotRouteVectorX();
	}
	
	public float getShotRouteVectorY()
	{
		return actor.getShotRouteVectorY();
	}

	public void addShotPathPoint(Point pos)
	{
		actor.addShotPathPoint(pos);
	}
	
	public void addShotPathPoint(float x, float y)
	{
		actor.addShotPathPoint(x, y);
	}

	public boolean shellBoundingWithTank()
	{
		return actor.boundingWith(actor.getShellBoundingCircle());
	}
	
	public boolean shellCollidesWithTank()
	{
		return actor.collidesWith(actor.getShellBase());
	}
	
	public boolean tankBoundingWithLevel()
	{
		return actor.boundingWith(field.getBase());
	}
	
	public boolean shellBoundingWithLevel()
	{
		return field.collidesWith(actor.getShellBoundingCircle());
	}
	
	public boolean tankCollidesWithLevel()
	{
		return actor.collidesWith(field.getBase());
	}
	
	public boolean shellCollidesWithLevel()
	{
		return field.collidesWith(actor.getShellBase());
	}
	
	public boolean tankContainsShell()
	{
		return actor.isContains(actor.getShellBase());
	}
		
	public boolean levelContainsShell()
	{
		return field.isContains(actor.getShellBase());
	}
	
	public boolean tankExcludesShell()
	{
		return !actor.collidesWith(actor.getShellBase()) && !actor.isContains(getShellBase());
	}
	
	public Shape getTankShape()
	{
		return actor.getBase();
	}
	
	public Shape getShellBase()
	{
		return actor.getShellBase();
	}
}

