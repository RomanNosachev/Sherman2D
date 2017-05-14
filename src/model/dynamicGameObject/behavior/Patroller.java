package model.dynamicGameObject.behavior;

public interface Patroller 
{
    public float getPatrolStartX();
    public float getPatrolFinishX();
    
    public float setPatrol(float startX, float startY);
    
    public void  patrol(boolean fl);
}
