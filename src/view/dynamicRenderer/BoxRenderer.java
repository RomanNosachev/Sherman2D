package view.dynamicRenderer;

import model.dynamicGameObject.DynamicGameObject;

public class BoxRenderer 
extends DynamicRenderer
{
    public BoxRenderer(DynamicGameObject object)
    {
        super(object);
    }
    
    public Class<? extends DynamicGameObject> getRenderingObjectClass()
    {
        return renderingObject.getClass();
    }
}
