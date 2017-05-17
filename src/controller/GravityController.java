package controller;

import model.PhysicConstants;
import model.dynamicGameObject.stateEnum.Climb;
import model.dynamicGameObject.stateEnum.Direction;
import model.level.Level;

public class GravityController 
{
    private Level model;
    
    public GravityController(Level model)
    {
        this.model = model;
    }
    
    public void gravity(int delta)
    {
        float movement = PhysicConstants.GRAVITY * delta / PhysicConstants.CLOCK_PER_SEC;
        
        for (int shellIndex = 0; shellIndex < model.getShellCount() - 1; shellIndex++)
        {
            if (model.isShellFlying(shellIndex))
            {
                model.setShotDirectionY(shellIndex, model.getShotDirectionY(shellIndex) - PhysicConstants.GRAVITY * delta / PhysicConstants.CLOCK_PER_SEC);
            }
        }
        
        for (int i = 0; i < movement; i++)
        {
            model.moveTankY(1);
            
            if (model.tankCollidesWithLevel())
            {
                model.moveTankY(-1);                
            }
            else
            {
                if (model.getTankClimbing() != Climb.UP)
                    model.setTankClimbing(Climb.DOWN);
            }
            
            for (int objectIndex = 0; objectIndex < model.getObjectsCount(); objectIndex++)
            {
                model.moveObjectY(objectIndex, 1);
                
                if (model.objectCollidesWithLevel(objectIndex))
                {
                    model.moveObjectY(objectIndex, -1);
                }
                else
                {
                    for (int secObjectIndex = 0; secObjectIndex < model.getObjectsCount(); secObjectIndex++)
                    {
                        if (model.objectCollidesWithObject(objectIndex, secObjectIndex) && (objectIndex != secObjectIndex))
                        {
                            model.moveObjectY(objectIndex, -1);
                        }
                    }
                }
            }
            
            for (int enemyIndex = 0; enemyIndex < model.getEnemiesCount(); enemyIndex++)
            {
                model.moveEnemyY(enemyIndex, 1);
                
                if (model.enemyCollidesWithLevel(enemyIndex))
                {
                    model.moveEnemyY(enemyIndex, -1);
                }
                else
                {
                    if (model.getEnemyClimbing(enemyIndex) != Climb.UP)
                        model.setEnemyClimbing(enemyIndex, Climb.DOWN);
                }
            }
        }       
        
        stabilize(delta);
    }
    
    private boolean rotateTank(float rotateAngle, int delta)
    {
        if (rotateAngle < 0)
        {
            for (int i = 0; i < Math.abs(rotateAngle); i++)
            {
                model.rotateTank(-1);
                
                if (model.tankCollidesWithLevel() || model.tankCollidesWithEnemies())
                {
                    model.rotateTank(1);
                    return false;
                }
            }            
        }
        else
        {            
            for (int i = 0; i < rotateAngle; i++)
            {
                model.rotateTank(1);
                
                if (model.tankCollidesWithLevel() || model.tankCollidesWithEnemies())
                {
                    model.rotateTank(-1);
                    return false;
                }
            }            
        }
        
        return true;
    }
 
    private boolean rotateEnemy(int index, float rotateAngle, int delta)
    {
        if (rotateAngle < 0)
        {
            for (int i = 0; i < Math.abs(rotateAngle); i++)
            {
                model.rotateEnemy(index, -1);
                
                if (model.enemyCollidesWithLevel(index) || model.tankCollidesWithEnemy(index) || model.enemyCollidesWithEnemies(index))
                {
                    model.rotateEnemy(index, 1);
                    return false;
                }
            }            
        }        
        else
        {
            for (int i = 0; i < rotateAngle; i++)
            {
                model.rotateEnemy(index, 1);
                
                if (model.enemyCollidesWithLevel(index) || model.tankCollidesWithEnemy(index) || model.enemyCollidesWithEnemies(index))
                {                    
                    model.rotateEnemy(index, -1);
                    return false;
                }
            }     
        }
        
        return true;
    }
    
    private void stabilize(int delta)
    {
        float rotateAngle = 90F * delta / PhysicConstants.CLOCK_PER_SEC;
        
        if ((model.getTankMoving() == Direction.FORTH && model.getTankClimbing() == Climb.UP)
                || (model.getTankMoving() == Direction.BACK && model.getTankClimbing() == Climb.DOWN))
        {            
            rotateTank(-rotateAngle, delta);
        }
        else
        {            
            if ((model.getTankMoving() == Direction.BACK && model.getTankClimbing() == Climb.UP)
                    || (model.getTankMoving() == Direction.FORTH && model.getTankClimbing() == Climb.DOWN))
            {
                rotateTank(rotateAngle, delta);
            }
            else
            {
                if (Float.compare(model.getTankRotateAngle(), -2) > 0)
                {
                    rotateTank(-rotateAngle, delta);
                }
                else 
                {
                    if (Float.compare(model.getTankRotateAngle(), 2) < 0)
                        rotateTank(rotateAngle, delta);
                }
            }
        }
        
        for (int enemyIndex = 0; enemyIndex < model.getEnemiesCount(); enemyIndex++)
        {
            if ((model.getEnemyMoving(enemyIndex) == Direction.FORTH && model.getEnemyClimbing(enemyIndex) == Climb.UP)
                    || (model.getEnemyMoving(enemyIndex) == Direction.BACK && model.getEnemyClimbing(enemyIndex) == Climb.DOWN))
            {            
                rotateEnemy(enemyIndex, -rotateAngle, delta);        
            }
            else
            {            
                if ((model.getEnemyMoving(enemyIndex) == Direction.BACK && model.getEnemyClimbing(enemyIndex) == Climb.UP)
                        || (model.getEnemyMoving(enemyIndex) == Direction.FORTH && model.getEnemyClimbing(enemyIndex) == Climb.DOWN))
                {
                    rotateEnemy(enemyIndex, rotateAngle, delta);
                }
                else
                {                                            
                    if (Float.compare(model.getEnemyRotateAngle(enemyIndex), -2) > 0)
                    {
                        rotateEnemy(enemyIndex, -rotateAngle, delta);
                    }
                    else
                    {
                        if ((Float.compare(model.getEnemyRotateAngle(enemyIndex), 2) < 0))
                        {
                            rotateEnemy(enemyIndex, rotateAngle, delta);
                        }
                    }
                }
            }
        }
    }
}
