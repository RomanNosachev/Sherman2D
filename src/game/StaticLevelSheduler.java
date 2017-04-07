package game;

import model.StaticLevel;

public class StaticLevelSheduler {
	public StaticLevel createStaticLevel(StaticLevelBuilder builder, ConfigManager configManager)
	{
		builder.buildObject();
		builder.buildBase(configManager.loadLevelPolygonPoints());
		builder.buildFloorHeight(configManager.loadFloorHeight());
		
		return (StaticLevel) builder.getObject();
	}
}
