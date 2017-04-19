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
        shellCamera = new Camera(new Vector2f(actor.getShellStartPosition()));
    }
    
    public void setTank(Tank tank)
    {
        actor = tank;
    }
    
    public void setField(Field field)
    {
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
    
    public void setShellCollides(boolean collides)
    {
        actor.setShellCollides(collides);
    }
    
    public void setShooting(boolean fl)
    {
        actor.setShooting(fl);
    }
    
    public void setShellPosition(Vector2f pos)
    {
        actor.setShellPosition(pos);
        shellCamera.setX(pos.x - actor.getShellStartPositionX());
        shellCamera.setY(pos.y - actor.getShellStartPositionY());
    }
    
    public void setShellPosition(float x, float y)
    {
        actor.setShellPosition(x, y);
        shellCamera.setX(x - actor.getShellStartPositionX());
        shellCamera.setY(y - actor.getShellStartPositionY());
    }
    
    public void setShellX(float x)
    {
        actor.setShellX(x);
        shellCamera.setX(x - actor.getShellStartPositionX());
    }
    
    public void setShellY(float y)
    {
        actor.setShellY(y);
        shellCamera.setY(y - actor.getShellStartPositionY());
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
    
    public void setShotStartSpeed(float speed)
    {
        actor.setShotStartSpeed(speed);
    }
    
    public float getShotStartSpeed()
    {
        return actor.getShotStartSpeed();
    }
    
    public void setShotStartAngle(float angle)
    {
        actor.setShotStartAngle(angle);
    }
    
    public float getShellRotateAngle()
    {
        return actor.getShellRotateAngle();
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
    
    public void setShotDirection(float x, float y)
    {
        actor.setShotDirection(x, y);
    }
    
    public void setShotDirectionX(float x)
    {
        actor.setShotDirectionX(x);
    }
    
    public void setShotDirectionY(float y)
    {
        actor.setShotDirectionY(y);
    }
    
    public Vector2f getShotDirection()
    {
        return actor.getShotDirection();
    }
    
    public float getShotDirectionX()
    {
        return actor.getShotDirectionX();
    }
    
    public float getShotDirectionY()
    {
        return actor.getShotDirectionY();
    }
    
    public void addShotPathPoint(Vector2f pos)
    {
        actor.addShotPathPoint(pos);
    }
    
    public int getShotPathSize()
    {
        return actor.getShotPathSize();
    }
    
    public Vector2f getShotPathPoint(int index)
    {
        return actor.getShotPathPoint(index);
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
    
    public void shellRotate(float angle)
    {
        actor.shellRotate(angle);
    }
    
    public void shellRotate(float angle, float x, float y)
    {
        actor.shellRotate(angle, x, y);
    }
    
    public void setShellRotation(float angle)
    {
        actor.shellRotate(angle - actor.getShellRotateAngle());
    }
    
    public void setShellRotation(float angle, float x, float y)
    {
        actor.shellRotate(angle - actor.getShellRotateAngle(), x, y);
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
    
    public void tankRotate(float angle, float x, float y)
    {
        actor.rotate(angle, x, y);
        actor.setShotStartAngle(actor.getShotStartAngle() - angle);
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
    
    public float getShellStartWidth()
    {
        return actor.getShellStartWidth();
    }
    
    public float getShellStartHeight()
    {
        return actor.getShellStartHeight();
    }
    
    public float getShellX()
    {
        return actor.getShellBase().getX();
    }
    
    public float getShellY()
    {
        return actor.getShellBase().getY();
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
    
    public float getShellCenterX()
    {
        return actor.getShellBase().getCenterX();
    }
    
    public float getShellCenterY()
    {
        return actor.getShellBase().getCenterY();
    }
    
    public Vector2f getShellStartPosition()
    {
        return actor.getShellStartPosition();
    }
    
    public float getShellStartPositionX()
    {
        return actor.getShellStartPositionX();
    }
    
    public float getShellStartPositionY()
    {
        return actor.getShellStartPositionY();
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
    
    public Shape getShellBase()
    {
        return actor.getShellBase();
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
    
    public int getShellDamage()
    {
        return actor.getShellDamage();
    }

    public boolean isTankDamaged()
    {
        return actor.isDamaged();
    }

    public void setTankDamaged(boolean tankDamaged)
    {
        actor.setDamaged(tankDamaged);
    }
    
    public void setShellCollisionPoint(Vector2f point)
    {
        actor.setShellCollisionPoint(point);
    }
    
    public Vector2f getShellPathBack()
    {
        return actor.getShellPathBack();
    }
 
    public Camera getCamera()
    {
        if (actor.isShooting() && actor.isMoving() == Move.STOP)
            return shellCamera;
        
        return actorCamera;
    }
    
    public float getCameraX()
    {
        if (actor.isShooting() && actor.isMoving() == Move.STOP)
            return shellCamera.getX();
        
        return actorCamera.getX();
    }
    
    public float getCameraY()
    {
        if (actor.isShooting() && actor.isMoving() == Move.STOP)
            return shellCamera.getY();
        
        return actorCamera.getY();
    }
}
