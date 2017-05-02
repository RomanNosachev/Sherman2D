package model.dynamicGameObject;

import model.gameObject.Explosivable;

public class Barrel 
extends DynamicGameObject 
implements Explosivable
{
    private static final long serialVersionUID = 5926740128855775983L;
    
    private float power;
    private float explosionRadius;

    @Override
    public float getRadius()
    {
        return explosionRadius;
    }

    @Override
    public void setRadius(float radius)
    {
        explosionRadius = radius;
    }

    @Override
    public float getPower()
    {
        return power;
    }

    @Override
    public void setPower(float power)
    {
        this.power = power;
    }
}
