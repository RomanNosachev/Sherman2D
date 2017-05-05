package view.shellRenderer;

import java.util.Timer;
import java.util.TimerTask;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.shell.Shell;
import view.dynamicRenderer.DynamicRenderer;

public class ShellRenderer 
extends DynamicRenderer 
{    
    private Image       shellSprite;
    private Animation   explosion;
    private Vector2f    explosionPosition;
    private boolean     explodes = false;
    
    public ShellRenderer()
    {
        renderingObject = new Shell();
    }
    
    public ShellRenderer(DynamicGameObject rObject)
    {
        renderingObject = (Shell) rObject;
    }
    
    @Override
    public void setRenderingObject(DynamicGameObject object)
    {
        renderingObject = (Shell) object;
    }
    
    public void setExplosionSpriteSheet(Image sheet, int spriteCount)
    {
        explosion = new Animation(new SpriteSheet(sheet, sheet.getWidth() / spriteCount, sheet.getHeight()), 30);
        explosion.setCurrentFrame(0);
    }
    
    public void setSprite(Image sprite)
    {
        shellSprite = sprite;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {                
        if (((Shell) renderingObject).isFlying())
        {
            drawSprite(g);
        }
        else
        {
            if (((Shell) renderingObject).isCollides())
            {                
                explosion.restart();
                explosionPosition = ((Shell) renderingObject).getCollisionPoint();
                explodes = true;
                
                Timer explosionTimer = new Timer(true);
                explosionTimer.schedule(new TimerTask() {
                    @Override
                    public void run()
                    {
                        explodes = false;
                    }
                }, explosion.getDuration(explosion.getFrame()) * explosion.getFrameCount());
            }
        }
        
        if (explodes)
        {
            drawExplosionAnimation();
        }
    }
    
    public void drawExplosionAnimation()
    {        
        explosion.draw(explosionPosition.x - explosion.getWidth() / 2,
                explosionPosition.y - explosion.getHeight());
    }
    
    public void drawSprite(Graphics g)
    {
        shellSprite.setRotation(renderingObject.getRotateAngle());
        shellSprite.drawCentered(renderingObject.getSimpleCenterX(), renderingObject.getSimpleCenterY());
    }

    public void drawPath(Graphics g)
    {
        g.setColor(new Color(170, 170, 170));
        
        for (int i = 0; i < ((Shell) renderingObject).getPathSize(); i++)
        {
            g.draw(new Line(((Shell) renderingObject).getPathPoint(i).getX(), ((Shell) renderingObject).getPathPoint(i).getY(),
                    ((Shell) renderingObject).getPathPoint(i + 1).getX(), ((Shell) renderingObject).getPathPoint(i + 1).getY()));
        }
    }
}
