package view.shellRenderer;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Line;

import model.shell.Shell;
import view.dynamicRenderer.DynamicRenderer;

public class ShellRenderer extends DynamicRenderer {
    private Shell       renderingObject;
    
    private Image       shellSprite;
    private Animation   explosion;
    
    public ShellRenderer()
    {
        renderingObject = new Shell();
    }
    
    public ShellRenderer(Shell rObject)
    {
        renderingObject = rObject;
        boundingRadius = rObject.getBoundingCircleRadius();
    }
    
    public void setShell(Shell shell)
    {
        renderingObject = shell;
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
        if (renderingObject.isFlying())
        {
            //drawBase(g, renderingObject.getBase());
            //drawBoundingSphere(g, renderingObject);
            //drawPath(g);
            drawSprite(g);
        }
        
        if (renderingObject.isCollides())
            drawExplosionAnimation();
    }
    
    public void drawExplosionAnimation()
    {        
        explosion.draw(renderingObject.getCollisionPoint().x - explosion.getWidth() / 2,
                renderingObject.getCollisionPoint().y - explosion.getHeight());
    }
    
    public void drawSprite(Graphics g)
    {
        shellSprite.setRotation(renderingObject.getRotateAngle());
        shellSprite.drawCentered(renderingObject.getSimpleCenterX(), renderingObject.getSimpleCenterY());
    }

    public void drawPath(Graphics g)
    {
        g.setColor(new Color(170, 170, 170));
        
        for (int i = 0; i < renderingObject.getPathSize(); i++)
        {
            g.draw(new Line(renderingObject.getPathPoint(i).getX(), renderingObject.getPathPoint(i).getY(),
                    renderingObject.getPathPoint(i + 1).getX(), renderingObject.getPathPoint(i + 1).getY()));
        }
    }
}
