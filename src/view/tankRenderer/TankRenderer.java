package view.tankRenderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import model.tank.Move;
import model.tank.Tank;
import view.dynamicRenderer.DynamicRenderer;
import view.shellRenderer.ShellRenderer;

public class TankRenderer extends DynamicRenderer {
    private Tank            renderingObject;
    private ShellRenderer   shellRenderer;
    
    private Animation       movingTank;
    private Image           frame;
    private Image           cannon; 
    
    private int             movingTankSpriteCount;
    private int             selectTankSprite;

    public TankRenderer()
    {
        renderingObject = new Tank();
    }
    
    public TankRenderer(Tank rObject)
    {
        renderingObject = rObject;
        boundingRadius = renderingObject.getBoundingCircleRadius();
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
        frame = movingTank.getImage(selectTankSprite);
    }
    
    public void setSprite(Image sprite)
    {
        frame = sprite;
    }
    
    public void setCannonSprite(Image sprite)
    {
        cannon = sprite; 
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {        
        for (int i = 0; i < renderingObject.getShellCount() - 1; i++)
        {
            shellRenderer.setShell(renderingObject.getShell(i));
            shellRenderer.render(gc, g);
        }
        
        drawAnimation(g);
    }

    public void drawAnimation(Graphics g)
    {        
        if (renderingObject.isMoving() == Move.BACK)
            if (++selectTankSprite >= movingTankSpriteCount)
                selectTankSprite = 0;
            
        if (renderingObject.isMoving() == Move.FORTH)
            if (--selectTankSprite <= 0)
                selectTankSprite = movingTankSpriteCount - 1;
            
        cannon.setRotation(90 - renderingObject.getShellStartAngle(renderingObject.getShellCount() - 1));
        cannon.drawCentered(renderingObject.getCannonCenterX(), 
                            renderingObject.getCannonCenterY());
        
        frame = movingTank.getImage(selectTankSprite);
        frame.setRotation(renderingObject.getRotateAngle());
        frame.drawCentered(renderingObject.getSimpleCenterX(), renderingObject.getSimpleCenterY());  
    }
}
