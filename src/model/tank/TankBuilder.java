package model.tank;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.dynamicGameObject.DynamicGameObjectBuilder;
import model.shell.Shell;

public class TankBuilder 
implements DynamicGameObjectBuilder 
{
    protected Tank tank;
    
    @Override
    public void buildObject()
    {
        tank = new Tank();
    }
    
    @Override
    public void buildBase(float[] polygonPoints)
    {
        tank.setBase(new Polygon(polygonPoints));
    }
    
    @Override
    public void buildStartPosition(Vector2f pos)
    {
        tank.setStartPosition(pos);
        tank.setCannonStartPosition(new Vector2f(tank.getCannonX() + pos.x, tank.getCannonY() + pos.y));
        tank.setCannonRotationPoint(new Vector2f(tank.getCannonRotationPointX() + pos.x, tank.getCannonRotationPointY() + pos.y));
        tank.setShellStartPosition(0, tank.getCannonCenterX() - tank.getShellStartHeight(0) / 2, 
                              tank.getCannonCenterY() - tank.getShellStartWidth(0) / 2);        
    }
    
    public void buildCannon()
    {
        tank.setCannon(new Cannon());
    }
    
    public void buildCannonRotationPoint(Vector2f pos)
    {
        tank.setCannonRotationPoint(pos);
    }
    
    public void buildCannonBase(float[] polygonPoints, float angle)
    {
        tank.setCannonBase(new Polygon(polygonPoints));
        tank.cannonRotate(90 - angle, tank.getCannonCenterX(), tank.getCannonCenterY());
    }
    
    public void buildCannonStartPosition(Vector2f pos)
    {
        tank.setCannonStartPosition(pos);
    }
    
    public void buildShell(Shell ammo)
    {
        tank.addShell(ammo);
    }
    
    public void buildTankSpeed(float speed)
    {
        tank.setSpeed(speed);
    }
    
    public void buildTankMinAimingAngle(float angle)
    {
        tank.setMinAimingAngle(angle);
    }
    
    public void buildTankMaxAimingAngle(float angle)
    {
        tank.setMaxAimingAngle(angle);
    }
    
    public void buildTankMaxHitPoint(int maxHP)
    {
        tank.setMaxHitPoint(maxHP);
    }
    
    @Override
    public DynamicGameObject getObject()
    {
        return tank;
    }
}
