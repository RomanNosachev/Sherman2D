package game;

import java.io.IOException;

import org.lwjgl.Sys;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import controller.GameController;
import controller.KeyController;
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
import view.tank.TankPlayer;
import view.tank.TankRenderer;
import view.field.FieldRenderer;
import view.shell.ShellRenderer;

public class Game extends BasicGame {
    private Level               level;
    private Field               field;
    private Tank                actor;
    private Shell               shell;
    
    private KeyController       input;
    private GameController      controller;
    
    private FieldRenderer       fieldRenderer;
    private TankRenderer        actorRenderer;
    private ShellRenderer       shellRenderer;
    
    private TankPlayer          actorPlayer;
    
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
        
        actorPlayer.play(gc);
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
            input = new KeyController(controller);
            
            actorRenderer = new TankRenderer(actor);
            actorRenderer.setSpriteSheet(configManager.loadTankSpriteSheet(), configManager.loadTankSpriteSheetCount());
            actorRenderer.setCannonSprite(configManager.loadTankCannonSprite());
            actorRenderer.setInfoStringHeight(configManager.loadFloorHeight() / 2);
            
            shellRenderer = new ShellRenderer(shell);
            shellRenderer.setSprite(configManager.loadShellSprite());
            shellRenderer.setInfoStringHeight(configManager.loadFloorHeight() / 2);
            shellRenderer.setExplosionSpriteSheet(configManager.loadShellExplosionSpriteSheet(), 
                    configManager.loadShellExplosionSpriteSheetCount());
            
            fieldRenderer = new FieldRenderer(field);
            fieldRenderer.setSprite(configManager.loadBackground());
            
            actorPlayer = new TankPlayer(actor);
            actorPlayer.setMove(configManager.loadTankMovingSound());
            actorPlayer.setHit(configManager.loadTankHitSound());
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        } 
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException
    {
        input.inputHandle(gc, delta);
        controller.mainLoop(gc, delta);
        
        if (controller.isGameOver())
        {
            gc.exit();
        }
    };
    
    public static void main(String args[]) throws SlickException
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new Game("Sherman2D"));
            app.setDisplayMode(800, 600, false);
            app.setAlwaysRender(true);
            app.setTargetFrameRate(100);
                
            app.start();
        } 
        catch (Exception e)
        {
            Sys.alert(e.getClass().getName() + ": ", e.getMessage());
        }
        
    }
}
