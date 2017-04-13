package view;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import model.tank.Move;
import model.tank.Tank;

public class TankRenderer extends DynamicRenderer {
    private Tank      renderingObject;
    
    private Animation animation;
    private Image     frame;
    private Image     cannon; 
    private int       sheetCount;
    private int       selectSheet;
    
    public TankRenderer(Tank rObject)
    {
        renderingObject = rObject;
        boundingRadius = renderingObject.getBoundingCircleRadius();
    }
    
    public void setSpriteSheet(Image sprite, int count)
    {
        animation = new Animation(new SpriteSheet(sprite, sprite.getWidth() / count, (int) renderingObject.getHeight()), 1);
        sheetCount = count;
        selectSheet = count / 2;
        frame = animation.getImage(selectSheet);
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
    public void init(GameContainer gc) throws SlickException
    {
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        drawBase(g, renderingObject.getBase());
        drawBoundingSphere(g, renderingObject);
       // drawAnimation(g);
    }
    
    public void drawAnimation(Graphics g)
    {
        if (renderingObject.isMoving() == Move.BACK)
            if (++selectSheet >= sheetCount)
                selectSheet = 0;
            
        if (renderingObject.isMoving() == Move.FORTH)
            if (--selectSheet <= 0)
                selectSheet = sheetCount - 1;
            
        frame = animation.getImage(selectSheet);
        frame.setRotation(renderingObject.getRotateAngle());
        frame.drawCentered(renderingObject.getSimpleCenterX(), renderingObject.getSimpleCenterY());
        
        cannon.setRotation(90 - renderingObject.getShellStartAngle());
        cannon.drawCentered(renderingObject.getShellSimpleCenterX() + renderingObject.getShellStartWidth(), 
                            renderingObject.getShellSimpleCenterY() - renderingObject.getShellStartHeight());
    }
}
