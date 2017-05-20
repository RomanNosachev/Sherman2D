package model.tank;

import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.Cannon;
import model.dynamicGameObject.DynamicGameObject;
import model.dynamicGameObject.behavior.Drivable;
import model.dynamicGameObject.behavior.MeleeDamager;
import model.dynamicGameObject.stateEnum.Direction;
import model.shell.Shell;

import java.util.LinkedList;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

public class Tank 
extends DynamicGameObject
implements Drivable,
           MeleeDamager
{
    private static final long serialVersionUID = -3579494870824274339L;
    
    private LinkedList<Shell>   ammo;
    private Cannon              gun;      

    private float               speed;
    private float               minAimingAngle;
    private float               maxAimingAngle;
    private float               meleeDamage;

    private boolean             shellLeft = false;

    public Tank()
    {
        base = new Polygon();
        ammo = new LinkedList<Shell>();
    }
        
    public Tank(Shape base) throws IllegalArgumentException
    {
        setBase(base);
        ammo = new LinkedList<Shell>(); 
        ammo.add(new Shell(new Polygon()));
        boundingRadius = base.getBoundingCircleRadius();
    }
    
    public Tank(Shape base, Vector2f position) throws IllegalArgumentException
    {
        setBase(base);
        base.setLocation(position);
        ammo = new LinkedList<Shell>(); 
        ammo.add(new Shell(new Polygon()));
        boundingRadius = base.getBoundingCircleRadius();
    }
    
    @Override
    public void setPosition(float x, float y)
    {
        gun.setPosition(gun.getX() - (base.getX() - x), gun.getY() - (base.getY() - y));
        super.setPosition(x, y);
    }
    
    @Override
    public void setPosition(Vector2f pos) throws IllegalArgumentException
    {
        gun.setPosition(gun.getX() - (base.getX() - pos.x), gun.getY() - (base.getY() - pos.y));
        super.setPosition(pos);
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
        
        gun.rotate(angle, simpleBase.getCenterX(), simpleBase.getCenterY());        
        gun.setRotationPoint(newRotationPoint.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, 
                simpleBase.getCenterX(), simpleBase.getCenterY())).getPoints());
        
        ammo.getLast().setStartAngle(ammo.getLast().getStartAngle() - angle);
        ammo.getLast().rotate(angle, simpleBase.getCenterX(), simpleBase.getCenterY());
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
        
        gun.rotate(angle, base.getCenterX(), base.getCenterY());        
        gun.setRotationPoint(newRotationPoint.transform(Transform.createRotateTransform(angle * (float) Math.PI / 180F, x, y)).getPoints());
        
        ammo.getLast().setStartAngle(ammo.getLast().getStartAngle() - angle);
        ammo.getLast().rotate(angle, simpleBase.getCenterX(), simpleBase.getCenterY());
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
    
    public float getMovePoints()
    {
        return speed;
    }

    public boolean isShellFlying(int index)
    {
        return ammo.get(index).isFlying();
    }
    
    public void setShellFlying(int index, boolean fl)
    {
        ammo.get(index).setFlying(fl);
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
        
        ammo.getLast().setFlying(true);
        ammo.getLast().setDirection();
        ammo.getLast().clearPath();
        ammo.getLast().addPathPoint(ammo.get(getShellCount() - 1).getCenterX(), ammo.get(getShellCount() - 1).getCenterY());
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
        return ammo.get(index).getDirectionVector();
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
    
    public float getShellDamage(int index)
    {
        return ammo.get(index).getPower();
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

    @Override
    public void moveX(float movement)
    {
        base.setX(base.getX() + movement);
        simpleBase.setX(simpleBase.getX() + movement);
        gun.setX(gun.getX() + movement);
        gun.setRotationPointX(gun.getRotationPointX() + movement);
        ammo.getLast().setX(ammo.getLast().getX() + movement); 
    }
    
    @Override
    public void moveY(float movement)
    {
        base.setY(base.getY() + movement);
        simpleBase.setY(simpleBase.getY() + movement);
        gun.setY(gun.getY() + movement);
        gun.setRotationPointY(gun.getRotationPointY() + movement);
        ammo.getLast().setY(ammo.getLast().getY() + movement);
    }
    
    @Override
    public void reverse(boolean horizontal, boolean vertical)
    {
        float prevRotateAngle = rotateAngle;
        
        if (horizontal)
        {
            float prevCannonRotateAngle = gun.getRotateAngle();
            
            rotate(-getRotateAngle());
            
            float[] cannonPolygonPoints = new float[gun.getBase().getPointCount() * 2];
            float[] polygonPoints = new float[base.getPointCount() * 2];
            
            for (int i = 0, j = 0; j < base.getPointCount(); i+=2, j++)
            {
                if (base.getPoint(j)[0] > simpleBase.getCenterX())
                {
                    polygonPoints[i] = simpleBase.getCenterX() - Math.abs(base.getPoint(j)[0] - simpleBase.getCenterX());
                }
                else
                {
                    if (base.getPoint(j)[0] < simpleBase.getCenterX())
                    {
                        polygonPoints[i] = simpleBase.getCenterX() + Math.abs(base.getPoint(j)[0] - simpleBase.getCenterX());
                    }
                    else
                    {
                        polygonPoints[i] = base.getPoint(j)[0];
                    }
                }
                
                polygonPoints[i + 1] = base.getPoint(j)[1];
            }
            
            base = new Polygon(polygonPoints);
            
            for (int i = 0, j = 0; j < gun.getBase().getPointCount(); i+=2, j++)
            {
                if (gun.getBase().getPoint(j)[0] > simpleBase.getCenterX())
                {
                    cannonPolygonPoints[i] = simpleBase.getCenterX() - Math.abs(gun.getBase().getPoint(j)[0] - simpleBase.getCenterX());
                }
                else
                {
                    if (gun.getBase().getPoint(j)[0] < simpleBase.getCenterX())
                    {
                        cannonPolygonPoints[i] = simpleBase.getCenterX() + Math.abs(gun.getBase().getPoint(j)[0] - simpleBase.getCenterX());
                    }
                    else
                    {
                        cannonPolygonPoints[i] = gun.getBase().getPoint(j)[0];
                    }
                }
                
                cannonPolygonPoints[i + 1] = gun.getBase().getPoint(j)[1];
            }
                        
            Vector2f newRotationPoint = gun.getRotationPoint();
            
            if (newRotationPoint.x > simpleBase.getCenterX())
            {
                newRotationPoint.x = simpleBase.getCenterX() - Math.abs(newRotationPoint.x - simpleBase.getCenterX());
            }
            else
            {
                newRotationPoint.x = simpleBase.getCenterX() + Math.abs(newRotationPoint.x - simpleBase.getCenterX());
            }
            
            gun.setBase(new Polygon(cannonPolygonPoints));
            gun.setRotationPoint(newRotationPoint);
            gun.setRotateAngle(-prevCannonRotateAngle + prevRotateAngle);
            
            ammo.getLast().setPosition(gun.getCenterX() - ammo.getLast().getStartWidth() / 2,
                    gun.getCenterY() - ammo.getLast().getStartHeight() / 2);
            
            if (ammo.getLast().getStartAngle() <= 90)
                ammo.getLast().setStartAngle((90 - ammo.getLast().getStartAngle()) + 90);
            else
                ammo.getLast().setStartAngle((90 - ammo.getLast().getStartAngle()) - 270);      
            
            rotate(prevRotateAngle);
        }
    }

    public void upCannon(float upAngle)
    {
        float aimingCheckAngle;
        float angle;
        
        if (direction == Direction.FORTH)
        {
            angle = -upAngle;
            aimingCheckAngle = gun.getRotateAngle() - rotateAngle;
        }
        else 
        {
            angle = upAngle;
            aimingCheckAngle = (-gun.getRotateAngle()) + rotateAngle;
        }
        
        if (upAngle > 0)
        {         
            if (Float.compare(maxAimingAngle, 90 - aimingCheckAngle) > 0)
            {
                ammo.getLast().setStartAngle(ammo.getLast().getStartAngle() - angle);
                gun.rotate(angle, gun.getRotationPointX(), gun.getRotationPointY());
                ammo.getLast().rotate(angle, gun.getRotationPointX(), gun.getRotationPointY());       
            }
        }
        else
        {
            if (Float.compare((90 - minAimingAngle), aimingCheckAngle) > 0)
            {
                ammo.getLast().setStartAngle(ammo.getLast().getStartAngle() - angle);
                gun.rotate(angle, gun.getRotationPointX(), gun.getRotationPointY());
                ammo.getLast().rotate(angle, gun.getRotationPointX(), gun.getRotationPointY());
            }
        }  
    }
    
    public float getCannonRotateAngle()
    {
        return gun.getRotateAngle();
    }

    public boolean isShellLeft()
    {
        return shellLeft;
    }

    public void setShellLeft(boolean shellLeft)
    {
        this.shellLeft = shellLeft;
    }
    
    public void addShotPower(float power)
    {
        if (power > 0)
        {
            if (Float.compare(ammo.getLast().getStartSpeed(), 5000) < 0)
            {
                ammo.getLast().setStartSpeed(ammo.getLast().getStartSpeed() + power);
            } 
            else
            {
                ammo.getLast().setStartSpeed(5000);
            }
        }
        else
        {
            if (Float.compare(ammo.getLast().getStartSpeed(), 200) > 0)
            {
                ammo.getLast().setStartSpeed(ammo.getLast().getStartSpeed() + power);
            } 
            else
            {
                ammo.getLast().setStartSpeed(200);
            }
        }
    }

    @Override
    public void setMeleeDamage(float damage)
    {
        meleeDamage = damage;
    }

    @Override
    public float getMeleeDamage()
    {
        return meleeDamage;
    }
}
