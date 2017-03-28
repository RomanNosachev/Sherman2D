package game;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;

import controller.GameController;
import model.Level;
import model.Shell;
import model.StaticLevel;
import model.Tank;
import view.ShellRenderer;
import view.StaticLevelRenderer;
import view.TankRenderer;

/*
 * - Добавить звуковое сопровождение
 * - Исправить проблему с плохой отрисовкой
 * - Решить проблему с потерей FPS
 * - Сделать импорт настроек
 * - Реализовать создание игровых объектов посредством абстрактной фабрики
 * - Использовать Builder для создания классов 
 */

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
			Writer writer = new FileWriter("config.ini");
			
			config.setProperty("BackgroundTexture", "res/sprites/forest.jpg");
			config.setProperty("TankSpriteSheet", "res/sprites/tanksprite.png");
			config.setProperty("TankSpriteSheetCount", "8");
			config.setProperty("ShellTexture", "res/sprites/shell.png");
			config.setProperty("FloorHeight", "70");
			config.setProperty("LevelPolygonPoint", "0.0, 0.0, 800.0, 0.0, 800.0, 530.0, 730.0, 530.0, "
												  + "730.0, 460.0, 660.0, 460.0, 400.0, 530.0, 0.0, 530.0");
			
			config.setProperty("ShellStartSpeed", "750");
			config.setProperty("ShellStartAngle", "0");
			config.setProperty("TankSpeed", "150");
			config.setProperty("TankMinAimingAngle", "-5");
			config.setProperty("TankMaxAimingAngle", "90");
			
			config.store(writer, null);
		}
		catch (IOException e) {
			
		}
		
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
						
			//String polygonPointStr = config.getProperty("LevelPilygonPoint");
			//double[] polygonPoint = Arrays.stream(polygonPointStr.split(", ")).mapToDouble(Float::parseFloat).toArray();
			
			float [] polygonPoint = new float[]
			{
				0, 0,
				Display.getWidth(), 0,
				Display.getWidth(), Display.getHeight() - floorHeight,
				Display.getWidth() - floorHeight, Display.getHeight() - floorHeight,
				Display.getWidth() - floorHeight, Display.getHeight() - floorHeight * 2,
				Display.getWidth() - floorHeight * 2, Display.getHeight() - floorHeight * 2,
				Display.getWidth() / 2, Display.getHeight() - floorHeight,
				0, Display.getHeight() - floorHeight
			};
					
			field = new StaticLevel(floorHeight, new Polygon(polygonPoint));
			
			actor = new Tank(field.getFloorHeight(), 
					  Display.getHeight() - (actorImg.getHeight() + field.getFloorHeight() + 1F),
					  actorImg.getWidth() / sheetCount,
					  actorImg.getHeight());
			
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
