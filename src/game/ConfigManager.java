package game;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Vector2f;

public class ConfigManager {
    private String     filePath;
    private Properties config;
    
    public ConfigManager(String path) throws IOException
    {
        filePath = path;
        config = new Properties();
        Reader reader = new FileReader(filePath);
        config.load(reader);
    }
    
    public Image loadBackground() throws SlickException
    {
        return new Image(config.getProperty("BackgroundTexture"));
    }
    
    public Image loadTankSpriteSheet() throws SlickException
    {
        return new Image(config.getProperty("TankSpriteSheet"));
    }
    
    public Image loadTankCannonSprite() throws SlickException
    {
        return new Image(config.getProperty("TankCannonSprite"));
    }
    
    public int loadTankSpriteSheetCount()
    {
        return Integer.parseInt(config.getProperty("TankSpriteSheetCount"));
    }
    
    public Image loadShellSprite() throws SlickException
    {
        return new Image(config.getProperty("ShellSprite"));
    }
    
    public float loadFloorHeight()
    {
        return Float.parseFloat(config.getProperty("FloorHeight"));
    }
    
    public float[] loadLevelPolygonPoints()
    {
        String[] levelPolygonPointsStrArray = config.getProperty("LevelPolygonPoints").split(", ");
        float[] levelPolygonPoints = new float[levelPolygonPointsStrArray.length];
        
        for (int i = 0; i < levelPolygonPointsStrArray.length; i++)
            levelPolygonPoints[i] = Float.parseFloat(levelPolygonPointsStrArray[i]);
        
        return levelPolygonPoints;
    }
    
    public float[] loadTankPolygonPoints()
    {
        String[] tankPolygonPointsStrArray = config.getProperty("TankPolygonPoints").split(", ");
        float[] tankPolygonPoints = new float[tankPolygonPointsStrArray.length];
        
        for (int i = 0; i < tankPolygonPointsStrArray.length; i++)
            tankPolygonPoints[i] = Float.parseFloat(tankPolygonPointsStrArray[i]);
        
        return tankPolygonPoints;
    }
    
    public float[] loadTankCannonPolygonPoints()
    {
        String[] tankCannonPolygonPointsStrArray = config.getProperty("TankCannonPolygonPoints").split(", ");
        float[] tankCannonPolygonPoints = new float[tankCannonPolygonPointsStrArray.length];
        
        for (int i = 0; i < tankCannonPolygonPointsStrArray.length; i++)
            tankCannonPolygonPoints[i] = Float.parseFloat(tankCannonPolygonPointsStrArray[i]);
        
        return tankCannonPolygonPoints;
    }
    
    public Vector2f loadTankStartPosition()
    {
        String[] tankStartPositionStrArray = config.getProperty("TankStartPosition").split(", ");
        
        return new Vector2f(Float.parseFloat(tankStartPositionStrArray[0]),
                Float.parseFloat(tankStartPositionStrArray[1]));
    }
    
    public Vector2f loadTankCannonStartPosition()
    {
        String[] tankCannonPositionStrArray = config.getProperty("TankCannonStartPosition").split(", ");
        
        return new Vector2f(Float.parseFloat(tankCannonPositionStrArray[0]),
                Float.parseFloat(tankCannonPositionStrArray[1]));
    }
    
    public Vector2f loadTankCannonRotationPoint()
    {
        String[] tankCannonRotationPointStrArray = config.getProperty("TankCannonRotationPoint").split(", ");
        
        return new Vector2f(Float.parseFloat(tankCannonRotationPointStrArray[0]),
                Float.parseFloat(tankCannonRotationPointStrArray[1]));
    }
    
    public float[] loadShellPolygonPoints()
    {
        String[] shellPolygonPointsStrArray = config.getProperty("ShellPolygonPoints").split(", ");
        float[] shellPolygonPoints = new float[shellPolygonPointsStrArray.length];
        
        for (int i = 0; i < shellPolygonPointsStrArray.length; i++)
            shellPolygonPoints[i] = Float.parseFloat(shellPolygonPointsStrArray[i]);
        
        return shellPolygonPoints;
    }
    
    public float loadShellStartSpeed()
    {
        return Float.parseFloat(config.getProperty("ShellStartSpeed"));
    }
    
    public float loadShellStartAngle()
    {
        return Float.parseFloat(config.getProperty("ShellStartAngle"));
    }
    
    public float loadTankSpeed()
    {
        return Float.parseFloat(config.getProperty("TankSpeed"));
    }
    
    public float loadTankMinAimingAngle()
    {
        return Float.parseFloat(config.getProperty("TankMinAimingAngle"));
    }
    
    public float loadTankMaxAimingAngle()
    {
        return Float.parseFloat(config.getProperty("TankMaxAimingAngle"));
    }
    
    public int loadShellDamage()
    {
        return Integer.parseInt(config.getProperty("ShellDamage"));
    }
    
    public int loadTankMaxHitPoints()
    {
        return Integer.parseInt(config.getProperty("TankMaxHitPoints"));
    }
    
    public Sound loadTankMovingSound() throws SlickException
    {
        return new Sound(config.getProperty("TankMovingSound"));
    }
    
    public ArrayList<Sound> loadTankHitSound() throws SlickException
    {
        ArrayList<Sound> soundSheet = new ArrayList<>();
        soundSheet.add(new Sound(config.getProperty("TankHitSound")));
        soundSheet.add(new Sound(config.getProperty("TankHitSound1")));

        return soundSheet;
    }
    
    public Image loadShellExplosionSpriteSheet() throws SlickException
    {
        return new Image(config.getProperty("ShellExplosionSpriteSheet"));
    }
    
    public Image loadEnemyTankSpriteSheet() throws SlickException
    {
        return new Image(config.getProperty("EnemyTankSpriteSheet"));
    }
    
    public int loadEnemyTankSpriteSheetCount()
    {
        return Integer.parseInt(config.getProperty("EnemyTankSpriteSheetCount"));
    }
    
    public Image loadEnemyTankCannonSprite() throws SlickException
    {
        return new Image(config.getProperty("EnemyTankCannonSprite"));
    }
    
    public int loadShellExplosionSpriteSheetCount()
    {
        return Integer.parseInt(config.getProperty("ShellExplosionSpriteSheetCount"));
    }
    
    public Image loadBoxSprite() throws SlickException
    {
        return new Image(config.getProperty("BoxSprite"));
    }
    
    public Image loadBarrelSprite() throws SlickException
    {
        return new Image(config.getProperty("BarrelSprite"));
    }
    
    public float loadEnemyTankVisibility()
    {
        return Float.parseFloat(config.getProperty("EnemyTankVisibility"));
    }
    
    public float loadEnemyTankSpeed()
    {
        return Float.parseFloat(config.getProperty("EnemyTankSpeed"));
    }
    
    public float loadEnemyTankMeleeDamage()
    {
        return Float.parseFloat(config.getProperty("EnemyTankMeleeDamage"));
    }
    
    public float loadTankMeleeDamage()
    {
        return Float.parseFloat(config.getProperty("TankMeleeDamage"));
    }
}
