package game;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class ConfigManager {
	private String 		filePath;
	private Properties 	config;
	
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
	
	public Vector2f loadTankStartPosition()
	{
		String[] tankStartPositionStrArray = config.getProperty("TankStartPosition").split(", ");
		
		return new Vector2f(Float.parseFloat(tankStartPositionStrArray[0]), 
							Float.parseFloat(tankStartPositionStrArray[1]));
	}
	
	public float[] loadShellPolygonPoints()
	{
		String[] shellPolygonPointsStrArray = config.getProperty("ShellPolygonPoints").split(", ");
		float[] shellPolygonPoints = new float[shellPolygonPointsStrArray.length];
		
		for (int i = 0; i < shellPolygonPointsStrArray.length; i++)
			shellPolygonPoints[i] = Float.parseFloat(shellPolygonPointsStrArray[i]);
		
		return shellPolygonPoints;
	}
	
	public Vector2f loadShellStartPosition()
	{
		String[] shellStartPositionStrArray = config.getProperty("ShellStartPosition").split(", ");
				
		return new Vector2f(Float.parseFloat(shellStartPositionStrArray[0]), 
						    Float.parseFloat(shellStartPositionStrArray[1]));
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
}
