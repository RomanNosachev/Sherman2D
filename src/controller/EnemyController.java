package controller;

import model.PhysicConstants;
import model.dynamicGameObject.stateEnum.Direction;
import model.level.Level;

public class EnemyController 
{
    private Level model;
    
    public EnemyController(Level model)
    {
        this.model = model;
    }
    
    public void control(int delta)
    {
        for (int enemyIndex = 0; enemyIndex < model.getEnemiesCount(); enemyIndex++)
        {                 
            if (model.isEnemyCollides(enemyIndex))
            {
                if (!model.isTankDamaged())
                {
                    model.damageTank(model.getEnemyMeleeDamage(enemyIndex));
                }
                
                model.setTankDamaged(true);
            }
            
            if (model.tankVisibleToEnemy(enemyIndex))
            {
                if (model.isEnemyPatrolled(enemyIndex))
                    model.setEnemyPatrol(enemyIndex, false);
                
                if (model.getTankX() < model.getEnemyX(enemyIndex))
                {
                    model.moveEnemy(enemyIndex, -model.getEnemyMovePoints(enemyIndex) * delta / PhysicConstants.CLOCK_PER_SEC);
                }
                else
                {
                    model.moveEnemy(enemyIndex, model.getEnemyMovePoints(enemyIndex) * delta / PhysicConstants.CLOCK_PER_SEC);
                }                
            }
            else
            {                     
                model.setEnemyMoving(enemyIndex, Direction.STOP);
                
                if (model.isEnemyPatrolled(enemyIndex))
                {  
                    if (model.getEnemyPatrolDirection(enemyIndex) == Direction.FORTH)
                    {
                        if (model.getEnemyX(enemyIndex) < model.getEnemyPatrolFinishX(enemyIndex))
                        {
                            model.moveEnemy(enemyIndex, model.getEnemyMovePoints(enemyIndex) * delta / PhysicConstants.CLOCK_PER_SEC);
                        }
                        else
                        {
                            model.setEnemyPatrolDirection(enemyIndex, Direction.BACK);
                        }
                    }
                    else
                    {
                        if (model.getEnemyX(enemyIndex) > model.getEnemyPatrolStartX(enemyIndex))
                        {
                            model.moveEnemy(enemyIndex, -model.getEnemyMovePoints(enemyIndex) * delta / PhysicConstants.CLOCK_PER_SEC);
                        }
                        else
                        {
                            model.setEnemyPatrolDirection(enemyIndex, Direction.FORTH);
                        }
                    }
                }
                else
                {
                    model.setEnemyMoving(enemyIndex, Direction.STOP);
                }
            }
        }
    }
}
