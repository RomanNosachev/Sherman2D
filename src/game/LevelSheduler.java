package game;

import model.Level;
import model.StaticLevel;
import model.Tank;

public class LevelSheduler {
	public Level createLevel(LevelBuilder builder, StaticLevel field, Tank actor)
	{
		builder.buildObject();
		builder.buildStaticLevel(field);
		builder.buildTank(actor);
		
		return builder.getObject();
	}
}
