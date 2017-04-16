package model.tank;

import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.shell.Shell;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Tank extends DynamicGameObject {
    private Shell       ammo;
    private Cannon      gun;      

    private float       speed;
    private float       minAimingAngle;
    private float       maxAimingAngle;
    private Move        isMoving = Move.STOP;
    private boolean     isDamaged = false;
    
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
    
    @Override
    public void rotate(float angle)
    {
        rotateAngle += angle;
        base = new Polygon(base.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F,
                simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());
        simpleBase = new Polygon(simpleBase.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F,
                simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());
        
        float[] coordArray = {gun.getRotationPointX(), gun.getRotationPointY()};
        Shape newRotationPoint = new Polygon(coordArray);
        
        gun.setRotationPoint(newRotationPoint.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, 
                simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());
    }
    
    @Override
    public void rotate(float angle, float x, float y)
    {
        rotateAngle += angle;
        base = new Polygon(
                base.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, x, y)).getPoints());
        simpleBase = new Polygon(
                simpleBase.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, x, y)).getPoints());
        
        float[] coordArray = {gun.getRotationPointX(), gun.getRotationPointY()};
        Shape newRotationPoint = new Polygon(coordArray);
        
        gun.setRotationPoint(newRotationPoint.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, x, y)).getPoints());
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
    
    public void setShellX(float x)
    {
        ammo.setX(x);
    }
    
    public void setShellY(float y)
    {
        ammo.setY(y);
    }
    
    public float getShellCenterX()
    {
        return ammo.getCenterX();
    }
    
    public float getShellCenterY()
    {
        return ammo.getCenterY();
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
    
    public void setShooting(boolean fl)
    {
        ammo.setFlying(fl);
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
        setShooting(true);
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
    
    public int getShotPathSize()
    {
        return ammo.getPathSize();
    }
    
    public Vector2f getShotPathPoint(int index)
    {
        return ammo.getPathPoint(index);
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
    
    public float getShellStartAngle()
    {
        return ammo.getStartAngle();
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
        return ammo.getStartX();
    }
    
    public float getShellStartPositionY()
    {
        return ammo.getStartY();
    }
    
    public float getShellSimpleCenterX()
    {
        return ammo.getSimpleCenterX();
    }
    
    public float getShellSimpleCenterY()
    {
        return ammo.getSimpleCenterY();
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
    
    public void setCannon(Cannon gun)
    {
        this.gun = gun;
    }
    
    public Vector2f getCannonStartPosition()
    {
        return gun.getStartPosition();
    }
    
    public Vector2f getCannonPosition()
    {
        return gun.getPosition();
    }
    
    public float getCannonStartX()
    {
        return gun.getStartX();
    }
    
    public float getCannonStartY()
    {
        return gun.getStartY();
    }

    public float getCannonX()
    {
        return gun.getX();
    }
    
    public float getCannonY()
    {
        return gun.getY();
    }
    
    public float getCannonCenterX()
    {
        return gun.getCenterX();
    }
    
    public float getCannonCenterY()
    {
        return gun.getCenterY();
    }
    
    public void setCannonX(float x)
    {
        gun.setX(x);
    }
    
    public void setCannonY(float y)
    {
        gun.setY(y);
    }
    
    public void setCannonPosition(Vector2f pos)
    {
        gun.setPosition(pos);
    }

    public void setCannonStartPosition(Vector2f cannonPos)
    {
        gun.setStartPosition(cannonPos);
    }
    
    public float getCannonSimpleCenterX()
    {
        return gun.getSimpleCenterX();
    }
    
    public float getCannonSimpleCenterY()
    {
        return gun.getSimpleCenterY();
    }
    
    public float getCannonStartWidth()
    {
        return gun.getStartWidth();
    }
    
    public float getCannonStartHeight()
    {
        return gun.getStartHeight();
    }
    
    public void cannonRotate(float angle)
    {
        gun.rotate(angle);
    }
    
    public void cannonRotate(float angle, float x, float y)
    {
        gun.rotate(angle, x, y);
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

    public Shape getCannonBase()
    {
        return gun.getBase();
    }

    public void setCannonBase(Shape cannonBase)
    {
        gun.setBase(cannonBase);
    }

    public Vector2f getCannonRotationPoint()
    {
        return gun.getRotationPoint();
    }

    public float getCannonRotationPointX()
    {
        return gun.getRotationPointX();
    }
    
    public float getCannonRotationPointY()
    {
        return gun.getRotationPointY();
    }

    public void setCannonRotationPointX(float x)
    {
        gun.setRotationPointX(x);
    }
    
    public void setCannonRotationPointY(float y)
    {
        gun.setRotationPointY(y);
    }
    
    public void setCannonRotationPoint(Vector2f cannonRotationPoint)
    {
        gun.setRotationPoint(cannonRotationPoint);
    }
    
    public int getShellDamage()
    {
        return ammo.getDamage();
    }

    public boolean isDamaged()
    {
        return isDamaged;
    }

    public void setDamaged(boolean isDamaged)
    {
        this.isDamaged = isDamaged;
    }
      
    public void setShellCollides(boolean collides)
    {
        ammo.setCollides(collides);
    }
    
    public void setShellCollisionPoint(Vector2f point)
    {
        ammo.setCollisionPoint(point);
    }
    
    public Vector2f getShellPathBack()
    {
        return ammo.getPathPoint(ammo.getPathSize() - 1);
    }
}
