package view.field;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.field.Field;
import view.renderer.Renderer;

public class FieldRenderer implements Renderer {
    
    private Field renderingObject;
    
    private Image       background;
    
    public FieldRenderer(Field rObject)
    {
        renderingObject = rObject;
    }

    public void setSprite(Image sprite)
    {
        background = sprite;
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException
    {
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        background.draw(0, 0);
        // drawDebugLines(g, 50);
        g.setColor(Color.green);
        g.draw(renderingObject.getBase());
    }
    
    public void drawDebugLines(Graphics g, int size)
    {
        int resolution = Display.getWidth();
        g.setColor(Color.black);
        
        for (int i = 0; i < resolution; i += size)
        {
            g.drawLine(i, 0, i, resolution);
            g.drawLine(0, i, resolution, i);
        }
    }
}
