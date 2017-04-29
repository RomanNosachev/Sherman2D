package model.tank;

import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.shell.Shell;

import java.util.ArrayList;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Tank extends DynamicGameObject {
    private static final long serialVersionUID = -3579494870824274339L;
    
    private ArrayList<Shell>    ammo;
    private Cannon              gun;      

    private float               speed;
    private float               minAimingAngle;
    private float               maxAimingAngle;
    
    private Move                moving = Move.STOP;
    private Climb               climbing = Climb.STRAIGHT;
    private boolean             damaged = false;
    
    public Tank()
    {
        base = new Polygon();
        ammo = new ArrayList<Shell>();
    }
        
    public Tank(Shape base) throws IllegalArgumentException
    {
        setBase(base);
        ammo = new ArrayList<Shell>(); 
        ammo.add(new Shell(new Polygon()));
        boundingRadius = base.getBoundingCircleRadius();
    }
    
    public Tank(Shape base, Vector2f position) throws IllegalArgumentException
    {
        setBase(base);
        base.setLocation(position);
        ammo = new ArrayList<Shell>(); 
        ammo.add(new Shell(new Polygon()));
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
    
    public void addShell(Shell shell) throws IllegalArgumentException
    {
        ammo.add(shell);
    }
    
    public void setShellBase(int index, Shape ammoBase) throws IllegalArgumentException
    {
       ammo.set(index, new Shell(ammoBase)); 
    }
    
    public void setShellPosition(int index, Vector2f pos) throws IllegalArgumentException
    {
        ammo.get(index).setPosition(pos);
    }
    
    public void setShellPosition(int index, float x, float y)
    {
        ammo.get(index).setPosition(new Vector2f(x, y));
    }
    
    public void setShellX(int index, float x)
    {
        ammo.get(index).setX(x);
    }
    
    public void setShellY(int index, float y)
    {
        ammo.get(index).setY(y);
    }
    
    public float getShellCenterX(int index)
    {
        return ammo.get(index).getCenterX();
    }
    
    public float getShellCenterY(int index)
    {
        return ammo.get(index).getCenterY();
    }
    
    public void setSpeed(float speed)
    {
        this.speed = speed;
    }
    
    public float getMovePoint()
    {
        return speed;
    }

    public boolean isShellFlying(int index)
    {
        return ammo.get(index).isFlying();
    }
    
    public Move isMoving()
    {
        return moving;
    }

    public void setShellFlying(int index, boolean fl)
    {
        ammo.get(index).setFlying(fl);
    }
    
    public void setMoving(Move fl)
    {
        moving = fl;
    }
    
    public void setShotStartSpeed(int index, float speed)
    {
        ammo.get(index).setStartSpeed(speed);
    }
    
    public float getShotStartSpeed(int index)
    {
        return ammo.get(index).getStartSpeed();
    }
    
    public void setShotStartAngle(int index, float angle)
    {
        ammo.get(index).setStartAngle(angle);
    }
    
    public float getShotStartAngle(int index)
    {
        return ammo.get(index).getStartAngle();
    }
    
    public void shot()
    {        
        Shell buffer = (Shell) ammo.get(getShellCount() - 1).clone();
                        
        ammo.get(getShellCount() - 1).setFlying(true);
        ammo.get(getShellCount() - 1).setDirection();
        ammo.get(getShellCount() - 1).clearPath();
        ammo.get(getShellCount() - 1).addPathPoint(ammo.get(getShellCount() - 1).getCenterX(), ammo.get(getShellCount() - 1).getCenterY());
        ammo.add(buffer);                
    }
    
    public void addShotPathPoint(int index, Vector2f pos)
    {
        ammo.get(index).addPathPoint(pos);
    }
    
    public void addShotPathPoint(int index, float x, float y)
    {
        ammo.get(index).addPathPoint(x, y);
    }
    
    public void setShotDirection(int index, float x, float y)
    {
        ammo.get(index).setDirection(x, y);
    }
    
    public void setShotDirectionX(int index, float x)
    {
        ammo.get(index).setDirectionX(x);
    }
    
    public void setShotDirectionY(int index, float y)
    {
        ammo.get(index).setDirectionY(y);
    }
    
    public int getShotPathSize(int index)
    {
        return ammo.get(index).getPathSize();
    }
    
    public Vector2f getShotPathPoint(int index, int pointIndex)
    {
        return ammo.get(index).getPathPoint(pointIndex);
    }
    
    public Vector2f getShotDirection(int index)
    {
        return ammo.get(index).getDirection();
    }
    
    public float getShotDirectionX(int index)
    {
        return ammo.get(index).getDirectionX();
    }
    
    public float getShotDirectionY(int index)
    {
        return ammo.get(index).getDirectionY();
    }
    
    public float getShellRotateAngle(int index)
    {
        return ammo.get(index).getRotateAngle();
    }
    
    public float getShellStartAngle(int index)
    {
        return ammo.get(index).getStartAngle();
    }
    
    public Shape getShellBase(int index)
    {
        return ammo.get(index).getBase();
    }
    
    public Shape getShellBoundingCircle(int index)
    {
        return ammo.get(index).getBoundingCircle();
    }
    
    public float getShellStartWidth(int index)
    {
        return ammo.get(index).getStartWidth();
    }
    
    public void setShellStartPosition(int index, Vector2f pos)
    {
        ammo.get(index).setStartPosition(pos);
    }
    
    public void setShellStartPosition(int index, float x, float y)
    {
        ammo.get(index).setStartPosition(new Vector2f(x, y));
    }
    
    public float getShellStartHeight(int index)
    {
        return ammo.get(index).getStartHeight();
    }
    
    public Vector2f getShellStartPosition(int index)
    {
        return ammo.get(index).getStartPosition();
    }
    
    public float getShellStartPositionX(int index)
    {
        return ammo.get(index).getStartX();
    }
    
    public float getShellStartPositionY(int index)
    {
        return ammo.get(index).getStartY();
    }
    
    public float getShellSimpleCenterX(int index)
    {
        return ammo.get(index).getSimpleCenterX();
    }
    
    public float getShellSimpleCenterY(int index)
    {
        return ammo.get(index).getSimpleCenterY();
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
    
    public void shellRotate(int index, float angle)
    {
        ammo.get(index).rotate(angle);
    }
    
    public void shellRotate(int index, float angle, float x, float y)
    {
        ammo.get(index).rotate(angle, x, y);
    }
    
    public void setShellRotateAngle(int index, float angle)
    {
        ammo.get(index).setRotateAngle(angle);
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
    
    public int getShellDamage(int index)
    {
        return ammo.get(index).getDamage();
    }

    public boolean isDamaged()
    {
        return damaged;
    }

    public void setDamaged(boolean isDamaged)
    {
        this.damaged = isDamaged;
    }
      
    public void setShellCollides(int index, boolean collides)
    {
        ammo.get(index).setCollides(collides);
    }
    
    public boolean isShellCollides(int index)
    {
        return ammo.get(index).isCollides();
    }
    
    public void setShellCollisionPoint(int index, Vector2f point)
    {
        ammo.get(index).setCollisionPoint(point);
    }
    
    public Vector2f getShellPathBack(int index)
    {
        return ammo.get(index).getPathPoint(ammo.get(index).getPathSize() - 1);
    }
    
    public int getShellCount()
    {
        return ammo.size();
    }
    
    public Shell getShell(int index)
    {
        return ammo.get(index);
    }
    
    public int getShellBackIndex()
    {
        return ammo.size() - 1;
    }
    
    public Shell getShellBack()
    {
        return ammo.get(ammo.size() - 1);
    }
    
    public void setShellBackY(float y)
    {
        ammo.get(ammo.size() - 1).setY(y);
    }
    
    public void setShellBackX(float x)
    {
        ammo.get(ammo.size() - 1).setX(x);
    }
    
    public float getShellBackX()
    {
        return ammo.get(ammo.size() - 1).getX();
    }
    
    public float getShellBackY()
    {
        return ammo.get(ammo.size() - 1).getY();
    }
    
    public void removeShell(int index)
    {
        ammo.remove(index);
    }

    public Climb isClimbing()
    {
        return climbing;
    }

    public void setClimbing(Climb climbing)
    {
        this.climbing = climbing;
    }
}
