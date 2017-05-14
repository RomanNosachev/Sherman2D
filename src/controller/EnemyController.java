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
            if (model.tankVisibleToEnemy(enemyIndex))
            {
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
            }
        }
    }
}
