package view.tankRenderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import model.dynamicGameObject.DynamicGameObject;
import model.dynamicGameObject.stateEnum.Direction;
import model.tank.Tank;
import view.dynamicRenderer.DynamicRenderer;
import view.shellRenderer.ShellRenderer;

public class TankRenderer 
extends DynamicRenderer 
{
    protected ShellRenderer   shellRenderer;
    
    protected Animation       movingTank;
    protected Image           cannon; 
    
    protected int             movingTankSpriteCount;
    private int               selectTankSprite;

    public TankRenderer()
    {
        renderingObject = new Tank();
    }
    
    public TankRenderer(DynamicGameObject rObject)
    {
        renderingObject = (Tank) rObject;
    }
    
    @Override
    public void setRenderingObject(DynamicGameObject object)
    {
        renderingObject = (Tank) object;
    }
    
    public void setShellRenderer(ShellRenderer sRenderer)
    {
        shellRenderer = sRenderer;
    }
    
    public void setSpriteSheet(Image sheet, int spriteCount) throws SlickException
    {
        movingTank = new Animation(new SpriteSheet(sheet, sheet.getWidth() / spriteCount, (int) renderingObject.getHeight()), 1);
        movingTankSpriteCount = spriteCount;
        selectTankSprite = spriteCount / 2;
        sprite = movingTank.getImage(selectTankSprite);
    }

    public void setCannonSprite(Image sprite)
    {
        cannon = sprite; 
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {        
        for (int i = 0; i < ((Tank) renderingObject).getShellCount(); i++)
        {
            shellRenderer.setRenderingObject(((Tank) renderingObject).getShell(i));
            shellRenderer.render(gc, g);
        }
        
        drawBase(g, renderingObject.getBase());
        drawBase(g, ((Tank) renderingObject).getCannonBase());
        drawAnimation(g);
    }

    public void drawAnimation(Graphics g)
    {    
        Image newCannon;
                  
        if (renderingObject.getMoving() == Direction.STOP)
        {
            sprite = movingTank.getImage(0);
        }
        else
        {
            if (--selectTankSprite <= 0)
                selectTankSprite = movingTankSpriteCount - 1;
            
            sprite = movingTank.getImage(selectTankSprite); 
        }
                
        if (((Tank) renderingObject).getDirection() == Direction.BACK)
        {
            sprite = sprite.getFlippedCopy(true, false);
            newCannon = cannon.getFlippedCopy(true, false);
        } 
        else
        {
            sprite = movingTank.getImage(selectTankSprite);
            newCannon = cannon;
        }

        if (renderingObject.getScale() != 1)
        {  
            sprite = sprite.getScaledCopy(renderingObject.getScale());
            newCannon = newCannon.getScaledCopy(renderingObject.getScale());
        }
        
        newCannon.setRotation(((Tank) renderingObject).getCannonRotateAngle());
        newCannon.drawCentered(((Tank) renderingObject).getCannonCenterX(), 
                            ((Tank) renderingObject).getCannonCenterY());

        sprite.setRotation(renderingObject.getRotateAngle());
        sprite.drawCentered(renderingObject.getSimpleCenterX(), renderingObject.getSimpleCenterY());  
    }
}
