package game;

import java.io.IOException;

import org.lwjgl.Sys;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import controller.GameController;
import controller.KeyController;
import model.dynamicGameObject.Barrel;
import model.dynamicGameObject.Box;
import model.dynamicGameObject.DynamicGameObject;
import model.enemy.EnemyTank;
import model.enemy.EnemyTankBuilder;
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
import view.dynamicRenderer.BarrelRenderer;
import view.dynamicRenderer.BoxRenderer;
import view.fieldRenderer.FieldRenderer;
import view.levelRenderer.LevelRenderer;
import view.shellRenderer.ShellRenderer;
import view.tankRenderer.EnemyTankRenderer;
import view.tankRenderer.TankPlayer;
import view.tankRenderer.TankRenderer;

public class Game 
extends BasicGame 
{
    private Level               level;
    private Field               field;
    private Tank                actor;
    private Shell               shell;
    
    private KeyController       input;
    private GameController      controller;
    
    private LevelRenderer       levelRenderer;
    private FieldRenderer       fieldRenderer;
    private TankRenderer        actorRenderer;
    private ShellRenderer       shellRenderer;
    private BoxRenderer         boxRenderer;
    private BarrelRenderer      barrelRenderer;
    private EnemyTankRenderer   enemyTankRenderer;
    
    private TankPlayer          actorPlayer;
    
    public Game(String title)
    { 
        super(title);
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        levelRenderer.render(gc, g);
        actorPlayer.play(gc);
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException
    {                
        try
        {        
            ConfigManager configManager = new ConfigManager("physic.ini");
            
            FieldSheduler fieldSheduler = new FieldSheduler();
            field = fieldSheduler.createField(new FieldBuilder(), configManager);
            
            ShellSheduler shellSheduler = new ShellSheduler();
            shell = shellSheduler.createShell(new ShellBuilder(), configManager);
            
            TankSheduler tankSheduler = new TankSheduler();
            actor = tankSheduler.createTank(new TankBuilder(), shell, configManager);
            
            LevelSheduler levelSheduler = new LevelSheduler();
            level = levelSheduler.createLevel(new LevelBuilder(), field, actor);

            controller = new GameController(level);
            input = new KeyController(controller);
            
            shellRenderer = new ShellRenderer(shell);
            shellRenderer.setSprite(configManager.loadShellSprite());
            shellRenderer.setExplosionSpriteSheet(configManager.loadShellExplosionSpriteSheet(), 
                    configManager.loadShellExplosionSpriteSheetCount());
            
            actorRenderer = new TankRenderer(actor);
            actorRenderer.setSpriteSheet(configManager.loadTankSpriteSheet(), configManager.loadTankSpriteSheetCount());
            actorRenderer.setCannonSprite(configManager.loadTankCannonSprite());
            actorRenderer.setShellRenderer(shellRenderer);
   
            fieldRenderer = new FieldRenderer(field);
            fieldRenderer.setSprite(configManager.loadBackground());
            
            actorPlayer = new TankPlayer(actor);
            actorPlayer.setMove(configManager.loadTankMovingSound());
            actorPlayer.setHit(configManager.loadTankHitSound());
        
            ////
            DynamicGameObject a = new Barrel();
            a.setBase(new Rectangle(0, 0, 90, 125));
            a.setPosition(1300, 0);

            DynamicGameObject a1 = new Barrel();
            a1.setBase(new Rectangle(0, 0, 90, 125));
            a1.setPosition(1400, 0);
            a1.setScale(0.5F);
            
            DynamicGameObject a2 = new Barrel();
            a2.setBase(new Rectangle(0, 0, 90, 125));
            a2.setPosition(1400, -100);
            a2.setScale(0.25F);
            
            DynamicGameObject b = new Box();
            b.setBase(new Rectangle(0, 0, 100, 100));
            b.setPosition(800, 50);
            b.setScale(0.15F);
            
            DynamicGameObject b1 = new Box();
            b1.setBase(new Rectangle(0, 0, 100, 100));
            b1.setPosition(901, 50);
            b1.setScale(0.5F);
            
            DynamicGameObject b2 = new Box();
            b2.setBase(new Rectangle(0, 0, 100, 100));
            b2.setPosition(800, 150);
            b2.setScale(0.8F);
            
            DynamicGameObject b3 = new Box();
            b3.setBase(new Rectangle(0, 0, 100, 100));
            b3.setPosition(901, 150);
            
            level.addObject(a);
            level.addObject(a1);
            level.addObject(a2);
            level.addObject(b);
            level.addObject(b1);
            level.addObject(b2);
            level.addObject(b3);
            
            EnemyTank enemy = tankSheduler.createEnemyTank(new EnemyTankBuilder(), shell.clone(), configManager);
            enemy.setPosition(1000, 0);
            
            EnemyTank enemy2 = tankSheduler.createEnemyTank(new EnemyTankBuilder(), shell.clone(), configManager);
            enemy2.setPosition(1200, 0);
            enemy2.setPatrolRadius(100);
            enemy2.setPatrol(true);
            
            EnemyTank enemy3 = tankSheduler.createEnemyTank(new EnemyTankBuilder(), shell.clone(), configManager);
            enemy3.setPosition(2000, 0);
            
            EnemyTank enemy4 = tankSheduler.createEnemyTank(new EnemyTankBuilder(), shell.clone(), configManager);
            enemy4.setPosition(2300, 0);
            
            EnemyTank enemy5 = tankSheduler.createEnemyTank(new EnemyTankBuilder(), shell.clone(), configManager);
            enemy5.setPosition(2800, 0);
            
            level.addEnemies(enemy);
            level.addEnemies(enemy2);
            level.addEnemies(enemy3);
            level.addEnemies(enemy4);
            level.addEnemies(enemy5);
            //////
            
            barrelRenderer = new BarrelRenderer(new Barrel());
            barrelRenderer.setSprite(configManager.loadBarrelSprite());
            boxRenderer = new BoxRenderer(new Box());
            boxRenderer.setSprite(configManager.loadBoxSprite());
            
            enemyTankRenderer = new EnemyTankRenderer(enemy);
            enemyTankRenderer.setSpriteSheet(configManager.loadEnemyTankSpriteSheet(), configManager.loadEnemyTankSpriteSheetCount());
            enemyTankRenderer.setCannonSprite(configManager.loadEnemyTankCannonSprite());
            enemyTankRenderer.setShellRenderer(shellRenderer);
            
            levelRenderer = new LevelRenderer(level);
            levelRenderer.setInfoStringHeight(configManager.loadFloorHeight() * 2);
            levelRenderer.setFieldRenderer(fieldRenderer);
            levelRenderer.setTankRenderer(actorRenderer);
            levelRenderer.addObjectRenderer(barrelRenderer);
            levelRenderer.addObjectRenderer(boxRenderer);
            levelRenderer.addEnemyRenederer(enemyTankRenderer);
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
            app.setShowFPS(false);
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
