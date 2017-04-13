package game;

import java.io.IOException;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import controller.GameController;
import model.field.Field;
import model.field.FieldBuilder;
import model.field.FieldSheduler;
import model.level.Level;
import model.level.LevelBuilder;
import model.level.LevelSheduler;
import model.shell.Shell;
import model.shell.ShellBuilder;
import model.shell.ShellSheduler;
import model.tank.Tank;
import model.tank.TankBuilder;
import model.tank.TankSheduler;
import view.ShellRenderer;
import view.FieldRenderer;
import view.TankRenderer;

public class Game extends BasicGame {
    private Level               level;
    private Field               field;
    private Tank                actor;
    private Shell               shell;
    
    private GameController      controller;
    
    private FieldRenderer       fieldRenderer;
    private TankRenderer        actorRenderer;
    private ShellRenderer       shellRenderer;
    
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
            ConfigManager configManager = new ConfigManager("config.ini");
            
            FieldSheduler fieldSheduler = new FieldSheduler();
            field = fieldSheduler.createStaticLevel(new FieldBuilder(), configManager);
            
            ShellSheduler shellSheduler = new ShellSheduler();
            shell = shellSheduler.createShell(new ShellBuilder(), configManager);
            
            TankSheduler tankSheduler = new TankSheduler();
            actor = tankSheduler.createTank(new TankBuilder(), shell, configManager);
            
            LevelSheduler levelSheduler = new LevelSheduler();
            level = levelSheduler.createLevel(new LevelBuilder(), field, actor);
            
            controller = new GameController(level);
            
            actorRenderer = new TankRenderer(actor);
            actorRenderer.setSpriteSheet(configManager.loadTankSpriteSheet(), configManager.loadTankSpriteSheetCount());
            actorRenderer.setCannonSprite(new Image("res/sprites/cannon.png"));
            
            shellRenderer = new ShellRenderer(shell);
            shellRenderer.setSprite(configManager.loadShellSprite());
            
            fieldRenderer = new FieldRenderer(field);
            fieldRenderer.setSprite(configManager.loadBackground());
        } catch (IOException e)
        {
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
