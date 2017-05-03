package view.dynamicRenderer;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

import model.dynamicGameObject.DynamicGameObject;
import view.renderer.Renderer;

public abstract class DynamicRenderer 
implements Renderer 
{   
    protected DynamicGameObject renderingObject;  
    protected Image             sprite;
    protected float             boundingRadius;
    protected float             infoStringHeight;
        
    public DynamicRenderer()
    {
    }
    
    public DynamicRenderer(DynamicGameObject object)
    {
        renderingObject = object;
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        sprite.drawCentered(renderingObject.getCenterX(), renderingObject.getCenterY());
    }
    
    public void setRenderingObject(DynamicGameObject object)
    {
        renderingObject = object;
    }
    
    public void setSprite(Image sprite)
    {
        this.sprite = sprite;
    }
    
    public void drawBoundingSphere(Graphics g, DynamicGameObject object)
    {
        g.setColor(Color.lightGray);
        g.draw(object.getBoundingCircle());
    }
    
    public void drawBase(Graphics g, Shape base)
    {
        g.setColor(Color.darkGray);
        g.draw(base);
    }
    
    public Class<? extends DynamicGameObject> getRenderingObjectClass()
    {
        return renderingObject.getClass();
    }
}
