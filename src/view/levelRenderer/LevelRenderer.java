package view.levelRenderer;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import model.level.Level;
import view.fieldRenderer.FieldRenderer;
import view.shellRenderer.ShellRenderer;
import view.tankRenderer.TankRenderer;

public class LevelRenderer {    
    private Level level;
    
    private float infoStringHeight = 0;
    
    private FieldRenderer   fieldRenderer;
    private TankRenderer    tankRenderer;
    
    public LevelRenderer()
    {
        level = new Level();
        fieldRenderer = new FieldRenderer();
        tankRenderer = new TankRenderer();
        tankRenderer.setShellRenderer(new ShellRenderer());
        //shellRenderer = new ShellRenderer();
    }
    
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.translate(-level.getCameraX(), -level.getCameraY());
        fieldRenderer.render(gc, g);
        tankRenderer.render(gc, g);
        //shellRenderer.render(gc, g);
        drawInfo(g);
    }

    public void drawInfo(Graphics g)
    {
        drawShotInfo(g);
        drawHP(g);
    }
    
    public void drawShotInfo(Graphics g)
    {
        g.setColor(Color.white);
        g.drawString("Speed: " + Float.toString(level.getShotStartSpeed(level.getShellCount() - 1)), 
                20 + level.getCameraX(), Display.getHeight() - infoStringHeight + level.getCameraY());    
        g.drawString("Angle: " + Float.toString(level.getShotStartAngle(level.getShellCount() - 1) % 180), 
                190 + level.getCameraX(), Display.getHeight() - infoStringHeight + level.getCameraY());
    }
    
    public void drawHP(Graphics g)
    {
        if (level.isTankDamaged())
            g.setColor(Color.red);
        else
            g.setColor(Color.white);
        
        g.drawString("HP: " + Integer.toString(level.getTankHitPoint()), 
                Display.getWidth() - 100 + level.getCameraX(), Display.getHeight() - infoStringHeight + level.getCameraY());
    }
    
    public LevelRenderer(Level level)
    {
        this.level = level;
    }
    
    public void setInfoStringHeight(float height)
    {
        infoStringHeight = height;
    }
    
    public void setLevel(Level level)
    {
        this.level = level;
    }
   
    public void setFieldRenderer(FieldRenderer fr)
    {
        fieldRenderer = fr;
        fr.setCamera(level.getCamera());
    }
    
    public void setTankRenderer(TankRenderer tr)
    {
        tankRenderer = tr;
        //tr.setCamera(level.getCamera());
    }
    
    public void setShellRenderer(ShellRenderer sr)
    {
        tankRenderer.setShellRenderer(sr);
        //shellRenderer = sr;
        //sr.setCamera(level.getCamera());
    }
}
