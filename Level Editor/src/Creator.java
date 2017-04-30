import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Properties;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

public class Creator extends BasicGame{
    float scale = 1;
    float cameraX;
    float cameraY;
    
	float floorHeight = 70;
	
	Shape tank;
	Shape shell;
	
	float tankPos[];
	float shellPos[];

	ArrayList<Vector2f> newLevelPolygonPoints;
	
	float[] levelPolygonPoints;
	float[] tankPolygonPoints;
	float[] shellPolygonPoints;
	
	public Creator(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
	    scale(g);
	    scroll(gc, g);
	    
		g.setBackground(Color.white);
	
		g.setColor(Color.magenta);
	    g.draw(new Circle(0, 0, 10));
	    g.draw(new Circle(0, Display.getHeight() - floorHeight, 10));
		
		g.setColor(Color.yellow);
	    g.draw(new Polygon(levelPolygonPoints));
	    
	    g.setColor(Color.red);
	    
	    Polygon newLevel = new Polygon();
	    
	    for (Vector2f p: newLevelPolygonPoints)
	    {
	        newLevel.addPoint(p.x, p.y);
	    }
	    
	    if (newLevelPolygonPoints.size() > 0)
	        g.draw(newLevel);
	    
		g.draw(tank);
		g.draw(shell);
	}

	public void scroll(GameContainer gc, Graphics g)
	{	    
	    if (gc.getInput().isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))
	    {
	        cameraX = gc.getInput().getAbsoluteMouseX();
	        cameraY = gc.getInput().getAbsoluteMouseY();
	    }	  
	    
        g.translate(cameraX, cameraY);
	}
	
	public void scale(Graphics g)
	{
	    float dWhell = Mouse.getDWheel();
        if (dWhell > 0)
            scale += 0.1;
        else if (dWhell < 0)
            scale -= 0.1;
        
        g.scale(scale, scale);
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException 
	{		
		newLevelPolygonPoints = new ArrayList<>();
		
		levelPolygonPoints = new float[]
		{
		        0, -2000, 3000, -2000, 3000, 530, 2730, 530, 2730, 460, 2660, 460, 2400, 530, 800, 530, 770, 500, 740, 530, 700, 530, 500, 450, 400, 450, 200, 530, 0, 530
		};
		
		shellPolygonPoints = new float[]
		{
			11, 30,
			11, 4,
			16, 4,
			16, 30
		};
		
		tankPolygonPoints = new float[]
		{
			22, 80,
			1, 60,
			0, 45,
			20, 31,
			43, 29,
			43, 7,
			64, 6,
			67, 1,
			89, 0,
			101, 7,
			106, 22,
			129, 29,
			130, 37,
			142, 47,
			142, 59,
			119, 80
		};
			
		tank = new Polygon(tankPolygonPoints);
		
		tankPos = new float[2];
		tankPos[0] = floorHeight;
		tankPos[1] = Display.getHeight() - (80 + floorHeight + 1F);
		
		tank.setLocation(new Vector2f(tankPos));
		
		cameraX = tank.getCenterX();
		cameraY = tank.getCenterY();
		
		shell = new Polygon(shellPolygonPoints);
		
		shellPos = new float[2];
		shellPos[0] = tank.getCenterX() + shell.getWidth() * 4;
		shellPos[1] = tank.getCenterY() - shell.getHeight();
		
		shell.setLocation(new Vector2f(shellPos));
	}

	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
		{
		    newLevelPolygonPoints.add(new Vector2f(gc.getInput().getMouseX(),
		            gc.getInput().getMouseY()));
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
		    newLevelPolygonPoints.clear();
		
		if (gc.getInput().isKeyDown(Input.KEY_S) && gc.getInput().isKeyDown(Input.KEY_LCONTROL))
		{
		    save();
		    System.out.println("Настройки успешно сохранены");
		}
	}
	
	public void save()
	{
	    try
        {
            Properties config = new Properties();
            FileReader reader = new FileReader("../physicSC.ini");
            config.load(reader);
            
            Writer writer = new FileWriter("../physicSC.ini");
                
            float[] newLevelPolygonPointsArr = new float[newLevelPolygonPoints.size() * 2];
            String newLevelPolygonPointsStr = new String();
            
            for (int i = 0; i < newLevelPolygonPoints.size(); i += 2)
            {
                newLevelPolygonPointsArr[i] = newLevelPolygonPoints.get(i).x;
                newLevelPolygonPointsArr[i + 1] = newLevelPolygonPoints.get(i).y;
                newLevelPolygonPointsStr += newLevelPolygonPointsArr[i] + ", " +  
                                            newLevelPolygonPointsArr[i + 1] + ", ";
            }
            
            config.setProperty("LevelPolygonPoints", newLevelPolygonPointsStr);
            
            config.store(writer, null);
            
            StringSelection ss = new StringSelection(newLevelPolygonPointsStr);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Creator("Settings Creator"));
		app.setDisplayMode(800, 600, false);
		app.setVSync(true);
		app.start();
	}
}
