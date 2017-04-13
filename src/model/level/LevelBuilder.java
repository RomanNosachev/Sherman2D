package model.level;

import model.field.Field;
import model.gameObject.Builder;
import model.tank.Tank;

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
    
    public void buildField(Field field)
    {
        level.setField(field);
    }
    
    public Level getObject()
    {
        return level;
    }
}
