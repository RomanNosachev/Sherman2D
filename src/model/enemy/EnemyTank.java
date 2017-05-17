package model.enemy;

import model.dynamicGameObject.behavior.Enmity;
import model.dynamicGameObject.behavior.Patroller;
import model.dynamicGameObject.stateEnum.Direction;
import model.tank.Tank;

public class EnemyTank 
extends Tank
implements Enmity,
           Patroller
{
    private static final long serialVersionUID = -1053473039218056292L;
    
    private float   visibility;
    private float   meleeDamage;

    private Direction           patrolDirection = Direction.BACK;
    private float               patrolStartX;
    private float               patrolFinishX;  
    private boolean             patrol;
    
    public EnemyTank()
    {
        super();
    }

    @Override
    public float getVisibility()
    {
        return visibility;
    }

    @Override
    public void setVisibility(float vis)
    {
        visibility = vis;
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
    

    @Override
    public float getPatrolStartX()
    {
        return patrolStartX;
    }

    @Override
    public float getPatrolFinishX()
    {
        return patrolFinishX;
    }

    @Override
    public void setPatrolStartX(float startX)
    {
        patrolStartX = startX;
    }
    
    @Override
    public void setPatrolFinishX(float finishX)
    {
        patrolFinishX = finishX;
    }

    @Override
    public void setPatrol(boolean fl)
    {
        patrol = fl;
    }
    
    @Override
    public float getMovePoints()
    {
        if (patrol)
            return super.getMovePoints() / 2;
        
        return super.getMovePoints();
    }
    
    @Override
    public boolean isPatrolled()
    {
        return patrol;
    }

    @Override
    public Direction getPatrolDirection()
    {
        return patrolDirection;
    }

    @Override
    public void setPatrolDirection(Direction dir)
    {
        patrolDirection = dir;
    }

    @Override
    public void setPatrolRadius(float rad)
    {
        setPatrolStartX(base.getCenterX() - rad);
        setPatrolFinishX(base.getCenterX() + rad);
    }
}
