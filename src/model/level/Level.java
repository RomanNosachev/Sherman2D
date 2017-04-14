package model.level;

import org.newdawn.slick.geom.Vector2f;

import model.field.Field;
import model.tank.Move;
import model.tank.Tank;

import org.newdawn.slick.geom.Shape;

public class Level {
    private Tank        actor;
    private Field field;
    
    private boolean     isShellLeftTank = false;
    
    public Level()
    {
        actor = new Tank();
        field = new Field();
    }
    
    public Level(Tank actor, Field field)
    {
        this.actor = actor;
        this.field = field;
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
    
    public void setIsShooting(boolean fl)
    {
        actor.setIsShooting(fl);
    }
    
    public void setShellPosition(Vector2f pos)
    {
        actor.setShellPosition(pos);
    }
    
    public void setShellPosition(float x, float y)
    {
        actor.setShellPosition(x, y);
    }
    
    public void setShellX(float x)
    {
        actor.setShellX(x);
    }
    
    public void setShellY(float y)
    {
        actor.setShellY(y);
    }
    
    public void setPosition(Vector2f pos)
    {
        actor.setPosition(pos);
    }
    
    public void setPosition(float x, float y)
    {
        actor.setPosition(x, y);
    }
    
    public void setPositionX(float x)
    {
        actor.setX(x);
    }
    
    public void setPositionY(float y)
    {
        actor.setY(y);
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
    
    public Vector2f getShotRouteVector()
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
    
    public void setTankCannonRotationX(float x)
    {
        actor.setCannonRotationX(x);
    }
    
    public void setTankCannonRotationY(float y)
    {
        actor.setCannonRotationY(y);
    }
    
    public float getTankCannonRotationX()
    {
        return actor.getCannonRotationX();
    }
    
    public float getTankCannonRotationY()
    {
        return actor.getCannonRotationY();
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
    
    public boolean isShellLeftTank()
    {
        return isShellLeftTank;
    }
    
    public void setShellLeftTank(boolean isShellLeftTank)
    {
        this.isShellLeftTank = isShellLeftTank;
    }
}
