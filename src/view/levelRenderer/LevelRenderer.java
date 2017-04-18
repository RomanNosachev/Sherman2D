package view.levelRenderer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.camera.Camera;
import model.level.Level;
import view.fieldRenderer.FieldRenderer;
import view.shellRenderer.ShellRenderer;
import view.tankRenderer.TankRenderer;

public class LevelRenderer {    
    private Level level;
    
    private FieldRenderer   fieldRenderer;
    private TankRenderer    tankRenderer;
    private ShellRenderer   shellRenderer;
    
    public LevelRenderer()
    {
        level = new Level();
        fieldRenderer = new FieldRenderer();
        tankRenderer = new TankRenderer();
        shellRenderer = new ShellRenderer();
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        fieldRenderer.render(gc, g);
        tankRenderer.render(gc, g);
        shellRenderer.render(gc, g);
    }

    public LevelRenderer(Level level)
    {
        this.level = level;
    }
    
    public void setLevel(Level level)
    {
        this.level = level;
    }
    
    public void setCamera(Camera cam)
    {
        fieldRenderer.setCamera(cam);
        tankRenderer.setCamera(cam);
        shellRenderer.setCamera(cam);
    }
    
    public void setFieldRenderer(FieldRenderer fr)
    {
        fieldRenderer = fr;
    }
    
    public void setTankRenderer(TankRenderer tr)
    {
        tankRenderer = tr;
    }
    
    public void setShellRenderer(ShellRenderer sr)
    {
        shellRenderer = sr;
    }
}
