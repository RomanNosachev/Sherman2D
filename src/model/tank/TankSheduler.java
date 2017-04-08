package model.tank;

import game.ConfigManager;
import model.shell.Shell;

public class TankSheduler {
    public Tank createTank(TankBuilder builder, Shell shell, ConfigManager configManager)
    {
        builder.buildObject();
        builder.buildBase(configManager.loadTankPolygonPoints());
        builder.buildStartPosition(configManager.loadTankStartPosition());
        builder.buildTankSpeed(configManager.loadTankSpeed());
        builder.buildTankMaxAimingAngle(configManager.loadTankMaxAimingAngle());
        builder.buildTankMinAimingAngle(configManager.loadTankMinAimingAngle());
        builder.buildShell(shell);
        
        return (Tank) builder.getObject();
    }
}
