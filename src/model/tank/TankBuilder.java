package model.tank;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import model.dynamicGameObject.DynamicGameObject;
import model.dynamicGameObject.DynamicGameObjectBuilder;
import model.shell.Shell;

public class TankBuilder implements DynamicGameObjectBuilder {
    private Tank tank;
    
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
        tank.setCannonRotationPoint(new Vector2f(tank.getCannonRotationX() + pos.x, tank.getCannonRotationY() + pos.y));
        tank.setShellPosition(tank.getShellStartPositionX() + pos.x, tank.getShellStartPositionY() + pos.y);
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
    
    public void buildCannonStartPosition(Vector2f cannonPos)
    {
        tank.setCannonStartPosition(cannonPos);
    }
    
    public void buildShell(Shell ammo)
    {
        tank.setShell(ammo);
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
    
    @Override
    public DynamicGameObject getObject()
    {
        return tank;
    }
}
