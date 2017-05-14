package model.level;

import org.newdawn.slick.geom.Vector2f;

import model.camera.Camera;
import model.dynamicGameObject.DynamicGameObject;
import model.dynamicGameObject.behavior.Drivable;
import model.dynamicGameObject.behavior.Enmity;
import model.dynamicGameObject.behavior.MeleeDamager;
import model.dynamicGameObject.stateEnum.Climb;
import model.dynamicGameObject.stateEnum.Direction;
import model.field.Field;
import model.tank.Tank;
import java.util.LinkedList;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Shape;

public class Level 
{    
    private Tank                            actor;
    private Field                           field;
    private LinkedList<DynamicGameObject>   objects;
    private LinkedList<DynamicGameObject>   enemies;
    
    private Camera                          actorCamera;
    private Camera                          shellCamera;
            
    public Level()
    {
        actor = new Tank();
        field = new Field();
        actorCamera = new Camera(new Vector2f(0, 0));
        shellCamera = new Camera(new Vector2f(0, 0));
        objects = new LinkedList<>();
        enemies = new LinkedList<>();
    }
    
    public Level(Tank actor, Field field)
    {
        this.actor = actor;
        this.field = field;
        actorCamera = new Camera(new Vector2f(actor.getStartPosition()));
        shellCamera = new Camera(new Vector2f(actor.getShellStartPosition(0)));
        objects = new LinkedList<>();
        enemies = new LinkedList<>();
    }
    
    public void setTank(Tank tank)
    {
        actor = tank;
        actorCamera = new Camera(new Vector2f(actor.getStartPosition()));
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
    
    public void setTankMoving(Direction fl)
    {
        actor.setMoving(fl);
    }
    
    public Direction getTankMoving()
    {
        return actor.isMoving();
    }
    
    public void setTankClimbing(Climb fl)
    {
        actor.setClimbing(fl);
    }
    
    public Climb getTankClimbing()
    {
        return actor.isClimbing();
    }
    
    public void setShellCollides(int index, boolean collides)
    {
        actor.setShellCollides(index, collides);
    }
    
    public boolean isShellCollides(int index)
    {
        return actor.isShellCollides(index);
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
    
    public float getMovePoints()
    {
        return actor.getMovePoints();
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
    
    public boolean shellCollidesWithObject(int shellIndex, int objectIndex)
    {
        return objects.get(objectIndex).collidesWith(actor.getShellBase(shellIndex));
    }
    
    public boolean tankCollidesWithObject(int objectIndex)
    {
        return actor.collidesWith(objects.get(objectIndex));
    }
    
    public boolean tankCollidesWithEnemy(int enemyIndex)
    {
        return actor.collidesWith(enemies.get(enemyIndex));
    }
    
    public boolean tankCollidesWithEnemies()
    {
        for (DynamicGameObject enemy : enemies)
        {
            if (actor.collidesWith(enemy))
                return true;
        }
        
        return false;
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
    
    public boolean objectCollidesWithLevel(int index)
    {
        return objects.get(index).collidesWith(field);
    }
    
    public boolean objectCollidesWithObject(int lIndex, int rIndex)
    {
        return objects.get(lIndex).collidesWith(objects.get(rIndex));
    }
        
    public boolean shellCollidesWithEnemy(int shellIndex, int enemyIndex)
    {
        return enemies.get(enemyIndex).collidesWith(actor.getShell(shellIndex));
    }
    
    public boolean enemyCollidesWithEnemy(int lIndex, int rIndex)
    {
        return enemies.get(lIndex).collidesWith(enemies.get(rIndex));
    }
    
    public boolean enemyCollidesWithEnemies(int index)
    {
        for (int i = 0; i < enemies.size(); i++)
        {
            if (enemyCollidesWithEnemy(index, i))
            {
                if (i != index)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean enemyCollidesWithLevel(int index)
    {
        return field.collidesWith(enemies.get(index));
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
        return !actor.collidesWith(actor.getShellBase(index)) && !actor.isContains(actor.getShell(index).getBoundingCircle());
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
    
    public float getTankStartX()
    {
        return actor.getStartX();
    }
    
    public float getTankStartY()
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
        return actor.isShellLeft();
    }
    
    public void setShellLeftTank(boolean fl)
    {
        actor.setShellLeft(fl);
    }
    
    public void setTankHitPoints(float f)
    {
        actor.setHitPoints(f);
    }
    
    public float getTankHitPoints()
    {
        return actor.getHitPoints();
    }
    
    public float getShellDamage(int index)
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
        return actorCamera;
    }
    
    public float getCameraX()
    {
        return actorCamera.getX();
    }
    
    public float getCameraY()
    {
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
    
    public void moveTankX(float movement)
    {
        actor.moveX(movement);
        actorCamera.setX(actorCamera.getX() + movement);
    }
    
    public void moveTankY(float movement)
    {
        actor.moveY(movement);
        actorCamera.setY(actorCamera.getY() + movement);
    }
    
    public void rotateTank(float rotateAngle)
    {
        actor.rotate(rotateAngle);
    }
    
    public float getTankMinX()
    {
        return actor.getMinX();
    }
    
    public float getTankMinY()
    {
        return actor.getMinY();
    }
    
    public float getTankMaxX()
    {
        return actor.getMaxX();
    }
    
    public float getTankMaxY()
    {
        return actor.getMaxY();
    }
    
    public LinkedList<DynamicGameObject> getObjects()
    {
        return objects;
    }
    
    public void addObject(DynamicGameObject object)
    {
        objects.add(object);
    }
    
    public void removeObject(int index)
    {
        objects.remove(index);
    }
    
    public int getObjectsCount()
    {
        return objects.size();
    }
    
    public void moveObjectX(int index, float movement)
    {
        objects.get(index).setX(objects.get(index).getX() + movement);
    }
    
    public void moveObjectY(int index, float movement)
    {
        objects.get(index).setY(objects.get(index).getY() + movement);
    }
    
    public LinkedList<DynamicGameObject> getEnemies()
    {
        return enemies;
    }
    
    public void addEnemies(DynamicGameObject enemy)
    {
        enemies.add(enemy);
    }
    
    public void removeEnemies(int index)
    {
        enemies.remove(index);
    }
    
    public int getEnemiesCount()
    {
        return enemies.size();
    }
    
    public DynamicGameObject getEnemy(int index)
    {
        return enemies.get(index);
    }
    
    public void damageTank(float damage)
    {
        actor.setHitPoints(actor.getHitPoints() - damage);
    }
    
    public void damageEnemy(int index, float damage)
    {
        enemies.get(index).setHitPoints(enemies.get(index).getHitPoints() - damage);
    }
    
    public float getEnemyHitPoints(int index)
    {
        return enemies.get(index).getHitPoints();
    }
    
    public void moveEnemyX(int index, float movement)
    {
        enemies.get(index).moveX(movement);
    }
    
    public void moveEnemyY(int index, float movement)
    {
        enemies.get(index).moveY(movement);
    }
    
    public void tankReverse(boolean horizontal, boolean vertical)
    {
        actor.reverse(horizontal, vertical);
    }
    
    public Direction getTankDirection()
    {
        return actor.getDirection();
    }
    
    public float getTankCannonRotateAngle()
    {
        return actor.getCannonRotateAngle();
    }

    public void upTankCannon(float upAngle)
    {
        actor.upCannon(upAngle);
    }
    
    public boolean isTankCollides()
    {
        return actor.isCollides();
    }
    
    public void setTankCollides(boolean fl)
    {
        actor.setCollides(fl);
    }
    
    public void addTankShotPower(float power)
    {
        actor.addShotPower(power);
    }
    
    public Direction getEnemyDirection(int index)
    {
        return enemies.get(index).getDirection();
    }
    
    public void enemyReverse(int index, boolean horizontal, boolean vertical)
    {
        enemies.get(index).reverse(horizontal, vertical);
    }
    
    public float getEnemyMovePoints(int index)
    {
        if (enemies.get(index) instanceof Drivable)
        {
            return ((Drivable) enemies.get(index)).getMovePoints();
        }
        
        return 0;
    }

    public void setEnemyClimbing(int index, Climb fl)
    {
        enemies.get(index).setClimbing(fl);
    }
    
    public void setEnemyMoving(int index, Direction fl)
    {
        enemies.get(index).setMoving(fl);
    }

    public Climb isEnemyClimbing(int index)
    {
        return enemies.get(index).isClimbing();
    }

    public float getEnemyVisibility(int index)
    {
        if (enemies.get(index) instanceof Enmity)
        {
            return ((Enmity) enemies.get(index)).getVisibility();
        }
        
        return 0;
    }
    
    public boolean tankVisibleToEnemy(int index)
    {
        float visibility = getEnemyVisibility(index);
        
        if (visibility > 0)
        {
            Ellipse visionCircle = new Ellipse(enemies.get(index).getCenterX(), enemies.get(index).getCenterY(), 
                getEnemyVisibility(index), getEnemyVisibility(index));
            
            return actor.collidesWith(visionCircle) || visionCircle.contains(actor.getBase());
        }
        else
        {
            return false;
        }
    }

    public Direction isEnemyMoving(int index)
    {
        return enemies.get(index).isMoving();
    }
    
    public void rotateEnemy(int index, float angle)
    {
        enemies.get(index).rotate(angle);
    }

    public float getEnemyRotateAngle(int index)
    {
        return enemies.get(index).getRotateAngle();
    }
    
    public float getEnemyX(int index)
    {
        return enemies.get(index).getX();
    }

    public void moveEnemy(int index, float movement)
    {
        Climb newClimbing = Climb.STRAIGHT;

        if (movement > 0)
        {
            if (getEnemyDirection(index) == Direction.BACK)
                enemyReverse(index, true, false);
                                 
            setEnemyMoving(index, Direction.FORTH);
            
            for (int i = 0; i < movement; i++)
            {
                moveEnemyX(index, 1);
                
                if (enemyCollidesWithLevel(index) || enemyCollidesWithEnemies(index) 
                        || tankCollidesWithEnemy(index))
                {
                    moveEnemyX(index, -1);
                    
                    newClimbing = Climb.UP;
                    moveEnemyY(index, -1);
                    
                    if (enemyCollidesWithLevel(index) || enemyCollidesWithEnemies(index) 
                            || tankCollidesWithEnemy(index))
                    {
                        newClimbing = Climb.STRAIGHT;
                        moveEnemyY(index, 1);
                    }
                }
            }
        }
        else
        {
            if (getEnemyDirection(index) == Direction.FORTH)
                enemyReverse(index, true, false);
                                    
            setEnemyMoving(index, Direction.BACK);
            
            for (int i = 0; i < Math.abs(movement); i++)
            {
                moveEnemyX(index, -1);
                
                if (enemyCollidesWithLevel(index) || enemyCollidesWithEnemies(index) 
                        || tankCollidesWithEnemy(index))
                {
                    moveEnemyX(index, 1);
                    
                    newClimbing = Climb.UP;
                    moveEnemyY(index, -1);
                    
                    if (enemyCollidesWithLevel(index) || enemyCollidesWithEnemies(index) 
                            || tankCollidesWithEnemy(index))
                    {
                        newClimbing = Climb.STRAIGHT;
                        moveEnemyY(index, 1);
                    }
                }
            }
        }
        
        setEnemyClimbing(index, newClimbing);
    }
    
    public void moveTank(float movement)
    {
        Climb newClimbing = Climb.STRAIGHT;
        
        if (movement > 0)
        {
            if (actor.getDirection() == Direction.BACK)
                actor.reverse(true, false);
                                    
            setTankMoving(Direction.FORTH);
            
            for (int i = 0; i < movement; i++)
            {                
                moveTankX(1);
                
                if (tankCollidesWithLevel() || tankCollidesWithEnemies())
                {
                    moveTankX(-1);
                                            
                    newClimbing = Climb.UP;
                    moveTankY(-1);
                            
                    if (tankCollidesWithLevel() || tankCollidesWithEnemies())
                    {
                        newClimbing = Climb.STRAIGHT;
                        moveTankY(1);
                    }
                }              
            }
        }
        else
        {
            if (actor.getDirection() == Direction.FORTH)
                actor.reverse(true, false);
                             
            setTankMoving(Direction.BACK);
            
            for (int i = 0; i < Math.abs(movement); i++)
            {                
                moveTankX(-1);
                
                if (tankCollidesWithLevel() || tankCollidesWithEnemies())
                {
                    moveTankX(1);
                                            
                    newClimbing = Climb.UP;
                    moveTankY(-1);
                            
                    if (tankCollidesWithLevel() || tankCollidesWithEnemies())
                    {
                        newClimbing = Climb.STRAIGHT;
                        moveTankY(1);
                    }
                }
            } 
        }
        
        setTankClimbing(newClimbing);
    }
    
    public float getTankMeleeDamage()
    {
        return actor.getMeleeDamage();
    }
    
    public float getEnemyMeleeDamage(int index)
    {
        if (enemies.get(index) instanceof MeleeDamager)
            return ((MeleeDamager) enemies.get(index)).getMeleeDamage();
        
        return 0;
    }
}
