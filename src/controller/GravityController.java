package controller;

import model.PhysicConstants;
import model.level.Level;
import model.tank.Climb;
import model.tank.Move;

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
        
        for (int i = 0; i < movement; i++)
        {
            model.moveTankY(1);
            
            if (model.tankCollidesWithLevel())
            {
                model.moveTankY(-1);                
            }
            else
            {
                if (model.isClimbing() != Climb.UP)
                    model.setClimbing(Climb.DOWN);
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
            }
        }       
        
        stabilize(delta);
    }
    
    private boolean rotateLeft(int delta)
    {
        float rotateAngle = 90F * delta / PhysicConstants.CLOCK_PER_SEC;
        
        for (int i = 0; i < rotateAngle; i++)
        {
            model.rotateTank(-1);
            
            if (model.tankCollidesWithLevel())
            {
                model.rotateTank(1);
                return false;
            }
        }
        
        return true;
    }
    
    private boolean rotateRight(int delta)
    {
        float rotateAngle = 90F * delta / PhysicConstants.CLOCK_PER_SEC;
        
        for (int i = 0; i < rotateAngle; i++)
        {
            model.rotateTank(1);
            
            if (model.tankCollidesWithLevel())
            {
                model.rotateTank(-1);
                return false;
            }
        }
        
        return true;
    }
    
    private void stabilize(int delta)
    {
        if ((model.isMoving() == Move.FORTH && model.isClimbing() == Climb.UP)
                || (model.isMoving() == Move.BACK && model.isClimbing() == Climb.DOWN))
        {            
                rotateLeft(delta);            
        }
        else
        {            
            if ((model.isMoving() == Move.BACK && model.isClimbing() == Climb.UP)
                    || (model.isMoving() == Move.FORTH && model.isClimbing() == Climb.DOWN))
            {
                   rotateRight(delta);
            }
            else
            {
                if (Float.compare(model.getTankRotateAngle(), -2) > 0)
                    rotateLeft(delta);
                else if (Float.compare(model.getTankRotateAngle(), 2) < 0)
                    rotateRight(delta);
            }
        }
    }
}
