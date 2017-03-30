package game;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import controller.GameController;
import model.Level;
import model.Shell;
import model.StaticLevel;
import model.Tank;
import view.ShellRenderer;
import view.StaticLevelRenderer;
import view.TankRenderer;

public class Game extends BasicGame{
	private Level 		level;
	private StaticLevel field;
	private Tank 		actor;
	private Shell		shell;
	
	private GameController	controller;
	
	private StaticLevelRenderer	fieldRenderer;
	private TankRenderer		actorRenderer;
	private ShellRenderer 		shellRenderer;

	public Game(String title) 
	{
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		fieldRenderer.render(gc, g);
		shellRenderer.render(gc, g);		
		actorRenderer.render(gc, g);
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{ 		
		try
		{
			Properties config = new Properties();
			Reader reader = new FileReader("config.ini");
			config.load(reader);
			
			Image background = new Image(config.getProperty("BackgroundTexture"));
			
			Image actorImg = new Image(config.getProperty("TankSpriteSheet"));
			int sheetCount = Integer.parseInt(config.getProperty("TankSpriteSheetCount"));
			
			Image shellImg = new Image(config.getProperty("ShellTexture"));
			
			float floorHeight = Float.parseFloat(config.getProperty("FloorHeight"));
						
			String[] levelPolygonPointStrArray = config.getProperty("LevelPolygonPoint").split(", ");
			float[] levelPolygonPoint = new float[levelPolygonPointStrArray.length];
			
			for (int i = 0; i < levelPolygonPointStrArray.length; i++)
				levelPolygonPoint[i] = Float.parseFloat(levelPolygonPointStrArray[i]);
					
			String[] tankPolygonPointStrArray = config.getProperty("TankPolygonPoint").split(", ");
			float[] tankPolygonPoint = new float[tankPolygonPointStrArray.length];
			
			for (int i = 0; i < tankPolygonPointStrArray.length; i++)
				tankPolygonPoint[i] = Float.parseFloat(tankPolygonPointStrArray[i]);
			
			field = new StaticLevel(floorHeight, new Polygon(levelPolygonPoint));
			
			actor = new Tank(new Polygon(tankPolygonPoint));
			
			String[] tankStartPositionStrArray = config.getProperty("TankStartPosition").split(", ");
			float[] tankStartPosition = new float[tankStartPositionStrArray.length];
			
			for (int i = 0; i < tankStartPositionStrArray.length; i++)
				tankStartPosition[i] = Float.parseFloat(tankStartPositionStrArray[i]);
			
			actor.setPosition(new Vector2f(tankStartPosition));
			
			shell = new Shell(actor.getBase().getCenterX(), 
					actor.getBase().getY() + shellImg.getHeight(), 
					shellImg);
					
			shell.setStartSpeed(Float.parseFloat(config.getProperty("ShellStartSpeed")));
			shell.setStartAngle(Float.parseFloat(config.getProperty("ShellStartAngle")));
			
			actor.setShell(shell);
			actor.setSpeed(Float.parseFloat(config.getProperty("TankSpeed")));
			actor.setMinAimingAngle(Float.parseFloat(config.getProperty("TankMinAimingAngle")));
			actor.setMaxAimingAngle(Float.parseFloat(config.getProperty("TankMaxAimingAngle")));
			
			level = new Level(actor, field);
			
			controller = new GameController(level);
					
			actorRenderer = new TankRenderer(actor);
			actorRenderer.setSprite(actorImg, sheetCount);
			actorRenderer.init(gc);
			
			shellRenderer = new ShellRenderer(shell);
			shellRenderer.setTexture(shellImg);
			shellRenderer.init(gc);
			
			fieldRenderer = new StaticLevelRenderer(field);
			fieldRenderer.setTexture(background);			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		controller.update(gc, delta);
		
		if (controller.isGameOver())
			gc.exit();
	};
	
	public static void main(String args[]) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new Game("Sherman2D"));
		app.setDisplayMode(800, 600, false);
		app.setAlwaysRender(true);
		app.setVSync(true);
		app.start();
	}
}
