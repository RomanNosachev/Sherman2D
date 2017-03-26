package game;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import controller.GameController;
import model.Level;
import model.Shell;
import model.StaticLevel;
import model.Tank;
import view.ShellRenderer;
import view.StaticLevelRenderer;
import view.TankRenderer;

/*
 * - Исправить баг с коллизией снаряда и танка
 * - Добавить звуковое сопровождение
 * - Исправить проблему с плохой отрисовкой
 * - Решить проблему с потерей FPS
 * - Избавиться от магических констант
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
		Image background = new Image("res/sprites/forest.jpg");
		
		Image actorImg = new Image("res/sprites/tanksprite_green.png");
		int sheetCount = 8;
		
		Image shellImg = new Image("res/sprites/shell.png");
		
		field = new StaticLevel(70);
		
		actor = new Tank(field.getFloorHeight(), 
				  Display.getHeight() - (actorImg.getHeight() + field.getFloorHeight() + 1F),
				  actorImg.getWidth() / sheetCount,
				  actorImg.getHeight());
		
		shell = new Shell(actor.getBase().getCenterX(), 
				actor.getBase().getY() + shellImg.getHeight(), 
				shellImg);
				
		shell.setStartSpeed(750);
		shell.setStartAngle(0);
		
		actor.setShell(shell);
		actor.setSpeed(150F);
		actor.setMinAimingAngle(-5);
		actor.setMaxAimingAngle(90);
		
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
