package game;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

import model.DynamicGameObject;
import model.Shell;
import model.Tank;

public class TankBuilder implements DynamicGameObjectBuilder {
	Tank tank;
	
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
