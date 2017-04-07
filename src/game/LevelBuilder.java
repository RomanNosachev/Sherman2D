package game;

import model.Level;
import model.StaticLevel;
import model.Tank;

public class LevelBuilder implements Builder {
	Level level;
	
	@Override
	public void buildObject() 
	{
		level = new Level();
	}
	
	public void buildTank(Tank actor)
	{
		level.setTank(actor);
	}
	
	public void buildStaticLevel(StaticLevel field)
	{
		level.setStaticLevel(field);
	}
	
	public Level getObject()
	{
		return level;
	}
}
