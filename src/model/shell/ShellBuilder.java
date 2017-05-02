package model.shell;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.dynamicGameObject.DynamicGameObjectBuilder;

public class ShellBuilder implements DynamicGameObjectBuilder {
    private Shell shell;
    
    @Override
    public void buildObject()
    {
        shell = new Shell();
    }
    
    @Override
    public void buildBase(float[] polygonPoints)
    {
        shell.setBase(new Polygon(polygonPoints));
    }
    
    public void buildStartSpeed(float speed)
    {
        shell.setStartSpeed(speed);
    }
    
    public void buildStartAngle(float angle)
    {
        shell.setStartAngle(angle);
        shell.rotate(90 - angle);
    }
    
    public void buildStartCenterPosition(Vector2f pos)
    {
        shell.setStartPosition(new Vector2f(pos.x + shell.getStartWidth() / 2, pos.y + shell.getStartHeight() / 2));
    }
    
    public void buildStartPosition(Vector2f pos)
    {
        shell.setStartPosition(pos);
    }
    
    public void buildDamage(float damage)
    {
        shell.setPower(damage);
    }
    
    @Override
    public DynamicGameObject getObject()
    {
        return shell;
    }
}
