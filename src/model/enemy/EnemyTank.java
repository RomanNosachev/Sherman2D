package model.enemy;

import model.tank.Tank;

public class EnemyTank 
extends Tank
implements Enmity
{
    private static final long serialVersionUID = -1053473039218056292L;
    
    private float visibility;
    
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
}
