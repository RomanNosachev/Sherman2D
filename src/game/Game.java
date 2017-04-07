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
			
			Image shellImg = new Image(config.getProperty("ShellSprite"));
		
			ConfigManager configManager = new ConfigManager("config.ini");
			
			StaticLevelSheduler staticLevelSheduler = new StaticLevelSheduler();
			field = staticLevelSheduler.createStaticLevel(new StaticLevelBuilder(), configManager);
				
			ShellSheduler shellSheduler = new ShellSheduler();
			shell = shellSheduler.createShell(new ShellBuilder(), configManager);
			
			TankSheduler tankSheduler = new TankSheduler();
			actor = tankSheduler.createTank(new TankBuilder(), shell, configManager);
				
			LevelSheduler levelSheduler = new LevelSheduler();
			level = levelSheduler.createLevel(new LevelBuilder(), field, actor);
			
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
		app.setTargetFrameRate(100);
		app.start();
	}
}
