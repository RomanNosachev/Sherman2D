package view.fieldRenderer;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import model.camera.Camera;
import model.field.Field;
import view.staticRenderer.StaticRenderer;

public class FieldRenderer extends StaticRenderer {
    private Field renderingObject;
    
    private Image       background;
    
    public FieldRenderer()
    {
        renderingObject = new Field();
    }
    
    public FieldRenderer(Field rObject)
    {
        renderingObject = rObject;
    }

    public void setCamera(Camera cam)
    {
        camera = cam;
    }
    
    public void setSprite(Image sprite)
    {
        background = sprite;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.translate(-camera.getX(), -camera.getY());
        background.draw(0, 0);
        //drawDebugLines(g, 50);
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
