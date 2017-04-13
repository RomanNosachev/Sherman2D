package model.field;

import game.ConfigManager;

public class FieldSheduler {
    public Field createStaticLevel(FieldBuilder builder, ConfigManager configManager)
    {
        builder.buildObject();
        builder.buildBase(configManager.loadLevelPolygonPoints());
        builder.buildFloorHeight(configManager.loadFloorHeight());
        
        return (Field) builder.getObject();
    }
}
