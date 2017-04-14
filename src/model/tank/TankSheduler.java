package model.tank;

import game.ConfigManager;
import model.shell.Shell;

public class TankSheduler {
    public Tank createTank(TankBuilder builder, Shell shell, ConfigManager configManager)
    {
        builder.buildObject();
        
        builder.buildBase(configManager.loadTankPolygonPoints());
        builder.buildCannonBase(configManager.loadTankCannonPolygonPoints(), configManager.loadShellStartAngle());
        builder.buildCannonStartPosition(configManager.loadTankCannonStartPosition());
        builder.buildCannonRotationPoint(configManager.loadTankCannonRotationPoint());
        
        builder.buildShell(shell);
        
        builder.buildStartPosition(configManager.loadTankStartPosition());
        
        builder.buildTankSpeed(configManager.loadTankSpeed());
        builder.buildTankMaxAimingAngle(configManager.loadTankMaxAimingAngle());
        builder.buildTankMinAimingAngle(configManager.loadTankMinAimingAngle());
                
        return (Tank) builder.getObject();
    }
}
