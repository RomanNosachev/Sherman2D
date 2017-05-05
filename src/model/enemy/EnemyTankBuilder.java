package model.enemy;

import model.tank.TankBuilder;

public class EnemyTankBuilder 
extends TankBuilder
{
    @Override
    public void buildObject()
    {
        tank = new EnemyTank();
    }
}
