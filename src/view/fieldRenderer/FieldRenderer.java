package view.fieldRenderer;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.field.Field;
import model.staticGameObject.StaticGameObject;
import view.staticRenderer.StaticRenderer;

public class FieldRenderer 
extends StaticRenderer 
{
    private Field           renderingObject;

    private Image           background;
    
    public FieldRenderer()
    {
        renderingObject = new Field();
    }
    
    public FieldRenderer(Field rObject)
    {
        renderingObject = rObject;
    }

    public void setSprite(Image sprite)
    {
        background = sprite;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.setBackground(new Color(246, 233, 217));
        background.draw(0, 0);
        g.setColor(new Color(104, 82, 47));
        g.draw(renderingObject.getBase());
        drawObjects(g);
    }
    
    public void drawObjects(Graphics g)
    {
        for (StaticGameObject object: renderingObject.getObjects())
        {            
            drawBase(g, object.getBase());
        }
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
