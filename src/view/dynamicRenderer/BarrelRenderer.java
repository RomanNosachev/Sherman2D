package view.dynamicRenderer;

import model.dynamicGameObject.DynamicGameObject;

public class BarrelRenderer 
extends DynamicRenderer
{
    public BarrelRenderer(DynamicGameObject object)
    {
        super(object);
    }
    
    public Class<? extends DynamicGameObject> getRenderingObjectClass()
    {
        return renderingObject.getClass();
    }
}
