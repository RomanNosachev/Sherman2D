package model.level;

import org.newdawn.slick.geom.Vector2f;

import model.camera.Camera;
import model.field.Field;
import model.tank.Move;
import model.tank.Tank;

import org.newdawn.slick.geom.Shape;

public class Level {    
    private Tank        actor;
    private Field       field;
    private Camera      actorCamera;
    private Camera      shellCamera;
    
    private boolean     isShellLeftTank = false;
    
    public Level()
    {
        actor = new Tank();
        field = new Field();
        actorCamera = new Camera(new Vector2f(0, 0));
        shellCamera = new Camera(new Vector2f(0, 0));
    }
    
    public Level(Tank actor, Field field)
    {
        this.actor = actor;
        this.field = field;
        actorCamera = new Camera(new Vector2f(actor.getStartPosition()));
        shellCamera = new Camera(new Vector2f(actor.getShellStartPosition(0)));
    }
    
    public void setTank(Tank tank)
    {
        actor = tank;
    }
    
    public void setField(Field field)
    {
        this.field = field;
    }

    public boolean isShellFlying(int index)
    {
        return actor.isShellFlying(index);
    }
    
    public void setShellFlying(int index, boolean fl)
    {
        actor.setShellFlying(index, fl);
    }
    
    public void setIsMoving(Move fl)
    {
        actor.setIsMoving(fl);
    }
    
    public Move isMoving()
    {
        return actor.isMoving();
    }
    
    public void setShellCollides(int index, boolean collides)
    {
        actor.setShellCollides(index, collides);
    }

    public void setShellPosition(int index, Vector2f pos)
    {
        actor.setShellPosition(index, pos);
        shellCamera.setX(pos.x - actor.getShellStartPositionX(index));
        shellCamera.setY(pos.y - actor.getShellStartPositionY(index));
    }
    
    public void setShellPosition(int index, float x, float y)
    {
        actor.setShellPosition(index, x, y);
        shellCamera.setX(x - actor.getShellStartPositionX(index));
        shellCamera.setY(y - actor.getShellStartPositionY(index));
    }
    
    public void setShellX(int index, float x)
    {
        actor.setShellX(index, x);
        shellCamera.setX(x - actor.getShellStartPositionX(index));
    }
    
    public void setShellY(int index, float y)
    {
        actor.setShellY(index, y);
        shellCamera.setY(y - actor.getShellStartPositionY(index));
    }
    
    public void setPosition(Vector2f pos)
    {
        actor.setPosition(pos);
        actorCamera.setX(pos.x - actor.getStartX());
        actorCamera.setY(pos.y - actor.getStartY());
    }
    
    public void setPosition(float x, float y)
    {
        actor.setPosition(x, y);
        actorCamera.setX(x - actor.getStartX());
        actorCamera.setY(y - actor.getStartY());
    }
    
    public void setPositionX(float x)
    {
        actor.setX(x);
        actorCamera.setX(x - actor.getStartX());
    }
    
    public void setPositionY(float y)
    {
        actor.setY(y);
        actorCamera.setY(y - actor.getStartY());
    }
    
    public float getMinAimingAngle()
    {
        return actor.getMinAimingAngle();
    }
    
    public float getMaxAimingAngle()
    {
        return actor.getMaxAimingAngle();
    }
    
    public void setShotStartSpeed(int index, float speed)
    {
        actor.setShotStartSpeed(index, speed);
    }
    
    public float getShotStartSpeed(int index)
    {
        return actor.getShotStartSpeed(index);
    }
    
    public void setShotStartAngle(int index, float angle)
    {
        actor.setShotStartAngle(index, angle);
    }
    
    public float getShellRotateAngle(int index)
    {
        return actor.getShellRotateAngle(index);
    }
    
    public float getShotStartAngle(int index)
    {
        return actor.getShotStartAngle(index);
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
    
    public void setShotDirection(int index, float x, float y)
    {
        actor.setShotDirection(index, x, y);
    }
    
    public void setShotDirectionX(int index, float x)
    {
        actor.setShotDirectionX(index, x);
    }
    
    public void setShotDirectionY(int index, float y)
    {
        actor.setShotDirectionY(index, y);
    }
    
    public Vector2f getShotDirection(int index)
    {
        return actor.getShotDirection(index);
    }
    
    public float getShotDirectionX(int index)
    {
        return actor.getShotDirectionX(index);
    }
    
    public float getShotDirectionY(int index)
    {
        return actor.getShotDirectionY(index);
    }
    
    public void addShotPathPoint(int index, Vector2f pos)
    {
        actor.addShotPathPoint(index, pos);
    }
    
    public int getShotPathSize(int index)
    {
        return actor.getShotPathSize(index);
    }
    
    public Vector2f getShotPathPoint(int index, int pointIndex)
    {
        return actor.getShotPathPoint(index, pointIndex);
    }
    
    public void addShotPathPoint(int index, float x, float y)
    {
        actor.addShotPathPoint(index, x, y);
    }
    
    public boolean shellBoundingWithTank(int index)
    {
        return actor.boundingWith(actor.getShellBoundingCircle(index));
    }
    
    public boolean shellCollidesWithTank(int index)
    {
        return actor.collidesWith(actor.getShellBase(index));
    }
    
    public boolean tankBoundingWithLevel()
    {
        return actor.boundingWith(field.getBase());
    }
    
    public boolean shellBoundingWithLevel(int index)
    {
        return field.collidesWith(actor.getShellBoundingCircle(index));
    }
    
    public boolean tankCollidesWithLevel()
    {
        return actor.collidesWith(field.getBase());
    }
    
    public boolean shellCollidesWithLevel(int index)
    {
        return field.collidesWith(actor.getShellBase(index));
    }
    
    public boolean tankContainsShell(int index)
    {
        return actor.isContains(actor.getShellBase(index));
    }
    
    public boolean levelContainsShell(int index)
    {
        return field.isContains(actor.getShellBase(index));
    }
    
    public boolean tankExcludesShell(int index)
    {
        return !actor.collidesWith(actor.getShellBase(index)) && !actor.isContains(getShellBase(index));
    }
    
    public void shellRotate(int index, float angle)
    {
        actor.shellRotate(index, angle);
    }
    
    public void shellRotate(int index, float angle, float x, float y)
    {
        actor.shellRotate(index, angle, x, y);
    }
    
    public void setShellRotation(int index, float angle)
    {
        actor.shellRotate(index, angle - actor.getShellRotateAngle(index));
    }
    
    public void setShellRotation(int index, float angle, float x, float y)
    {
        actor.shellRotate(index, angle - actor.getShellRotateAngle(index), x, y);
    }
    
    public void tankCannonRotate(float angle)
    {
        actor.cannonRotate(angle);
    }
    
    public float getTankCannonCenterX()
    {
        return actor.getCannonCenterX();
    }
    
    public float getTankCannonCenterY()
    {
        return actor.getCannonCenterY();
    }
    
    public void tankCannonRotate(float angle, float x, float y)
    {
        actor.cannonRotate(angle, x, y);
    }
    
    public void setTankCannonRotationPointX(float x)
    {
        actor.setCannonRotationPointX(x);
    }
    
    public void setTankCannonRotationPointY(float y)
    {
        actor.setCannonRotationPointY(y);
    }
    
    public float getTankCannonRotationPointX()
    {
        return actor.getCannonRotationPointX();
    }
    
    public float getTankCannonRotationPointY()
    {
        return actor.getCannonRotationPointY();
    }
    
    public void tankRotate(float angle)
    {
        
        actor.rotate(angle);
    }
    
    public void tankRotate(int index, float angle, float x, float y)
    {
        actor.rotate(angle, x, y);
        actor.setShotStartAngle(index, actor.getShotStartAngle(index) - angle);
    }
    
    public float getTankRotateAngle()
    {
        return actor.getRotateAngle();
    }
    
    public float getTankHeight()
    {
        return actor.getHeight();
    }
    
    public float getTankWidth()
    {
        return actor.getWidth();
    }
    
    public float getShellStartWidth(int index)
    {
        return actor.getShellStartWidth(index);
    }
    
    public float getShellStartHeight(int index)
    {
        return actor.getShellStartHeight(index);
    }
    
    public float getShellX(int index)
    {
        return actor.getShellBase(index).getX();
    }
    
    public float getShellY(int index)
    {
        return actor.getShellBase(index).getY();
    }
    
    public float getTankX()
    {
        return actor.getX();
    }
    
    public float getTankY()
    {
        return actor.getY();
    }
    
    public float getTankStartWidth()
    {
        return actor.getStartWidth();
    }
    
    public float getTankStartHeight()
    {
        return actor.getStartHeight();
    }
    
    public float getTankSimpleCenterX()
    {
        return actor.getSimpleCenterX();
    }
    
    public Vector2f getTankStartPosition()
    {
        return actor.getStartPosition();
    }
    
    public float getTankStartPositionX()
    {
        return actor.getStartX();
    }
    
    public float getTankStartPositionY()
    {
        return actor.getStartY();
    }
    
    public float getTankCenterX()
    {
        return actor.getCenterX();
    }
    
    public float getTankCenterY()
    {
        return actor.getCenterY();
    }
    
    public Vector2f getTankCannonPosition()
    {
        return actor.getCannonStartPosition();
    }
    
    public void setTankCannonPosition(Vector2f cannonPos)
    {
        actor.setCannonStartPosition(cannonPos);
    }
    
    public float getShellCenterX(int index)
    {
        return actor.getShellCenterX(index);
    }
    
    public float getShellCenterY(int index)
    {
        return actor.getShellCenterY(index);
    }
    
    public Vector2f getShellStartPosition(int index)
    {
        return actor.getShellStartPosition(index);
    }
    
    public float getShellStartPositionX(int index)
    {
        return actor.getShellStartPositionX(index);
    }
    
    public float getShellStartPositionY(int index)
    {
        return actor.getShellStartPositionY(index);
    }
    
    public float getTankSimpleCenterY()
    {
        return actor.getSimpleCenterY();
    }
    
    public Shape getTankBase()
    {
        return actor.getBase();
    }
    
    public float[] getTankBaseNormal(int index)
    {
        return actor.getNormal(index);
    }
    
    public float[] getTankBasePoint(int index)
    {
        return actor.getPoint(index);
    }
    
    public boolean tankBaseIncludes(float x, float y)
    {
        return actor.includes(x, y);
    }
    
    public int getTankBaseIndexOf(float x, float y)
    {
        return actor.indexOf(x, y);
    }
    
    public Shape getTankSimpleBase()
    {
        return actor.getSimpleBase();
    }
    
    public Shape getShellBase(int index)
    {
        return actor.getShellBase(index);
    }
    
    public float getTankCannonX()
    {
        return actor.getCannonX();
    }
    
    public void setTankCannonX(float x)
    {
        actor.setCannonX(x);
    }
    
    public float getTankCannonY()
    {
        return actor.getCannonY();
    }

    public void setTankCannonY(float y)
    {
        actor.setCannonY(y);
    }
    
    public float getTankCannonSimpleCenterX()
    {
        return actor.getCannonSimpleCenterX();
    }
    
    public float getTankCannonSimpleCenterY()
    {
        return actor.getCannonSimpleCenterY();
    }
    
    public float getTankCannonStartWidth()
    {
        return actor.getCannonStartWidth();
    }
    
    public float getTankCannonStartHeight()
    {
        return actor.getCannonStartHeight();
    }
    
    public boolean isShellLeftTank()
    {
        return isShellLeftTank;
    }
    
    public void setShellLeftTank(boolean isShellLeftTank)
    {
        this.isShellLeftTank = isShellLeftTank;
    }
    
    public void setTankHitPoint(int hp)
    {
        actor.setHitPoint(hp);
    }
    
    public int getTankHitPoint()
    {
        return actor.getHitPoint();
    }
    
    public int getShellDamage(int index)
    {
        return actor.getShellDamage(index);
    }

    public boolean isTankDamaged()
    {
        return actor.isDamaged();
    }

    public void setTankDamaged(boolean tankDamaged)
    {
        actor.setDamaged(tankDamaged);
    }
    
    public void setShellCollisionPoint(int index, Vector2f point)
    {
        actor.setShellCollisionPoint(index, point);
    }
    
    public Vector2f getShellPathBack(int index)
    {
        return actor.getShellPathBack(index);
    }
 
    public Camera getCamera()
    {
        //if (actor.isShooting() && actor.isMoving() == Move.STOP)
        //    return shellCamera;
        
        return actorCamera;
    }
    
    public float getCameraX()
    {
        //if (actor.isShooting() && actor.isMoving() == Move.STOP)
        //    return shellCamera.getX();
        
        return actorCamera.getX();
    }
    
    public float getCameraY()
    {
        //if (actor.isShooting() && actor.isMoving() == Move.STOP)
        //    return shellCamera.getY();
        
        return actorCamera.getY();
    }
    
    public Vector2f getShellPathBegin(int index)
    {
        return actor.getShotPathPoint(index, 0);
    }
    
    public int getShellCount()
    {
        return actor.getShellCount();
    }
    
    public int getShellBackIndex()
    {
        return actor.getShellCount() - 1;
    }
    
    public void removeShell(int index)
    {
        actor.removeShell(index);
    }
}
