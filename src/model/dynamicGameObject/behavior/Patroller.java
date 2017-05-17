package model.dynamicGameObject.behavior;

import model.dynamicGameObject.stateEnum.Direction;

public interface Patroller 
{    
    public float        getPatrolStartX();
    public float        getPatrolFinishX();
    
    public void         setPatrolStartX(float startX);
    public void         setPatrolFinishX(float finishX);
    public void         setPatrolRadius(float rad);
    
    public void         setPatrol(boolean fl);
    public boolean      isPatrolled();
    
    public Direction    getPatrolDirection();
    public void         setPatrolDirection(Direction dir);    
}
