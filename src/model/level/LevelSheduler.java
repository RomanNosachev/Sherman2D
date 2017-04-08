package model.level;

import model.field.Field;
import model.tank.Tank;

public class LevelSheduler {
    public Level createLevel(LevelBuilder builder, Field field, Tank actor)
    {
        builder.buildObject();
        builder.buildStaticLevel(field);
        builder.buildTank(actor);
        
        return builder.getObject();
    }
}
