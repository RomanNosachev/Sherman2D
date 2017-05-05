package model.tank;

import game.ConfigManager;
import model.enemy.EnemyTank;
import model.shell.Shell;

public class TankSheduler {
    public Tank createTank(TankBuilder builder, Shell shell, ConfigManager configManager)
    {
        builder.buildObject();
        
        builder.buildShell(shell);
        
        builder.buildBase(configManager.loadTankPolygonPoints());
        builder.buildCannon();
        builder.buildCannonBase(configManager.loadTankCannonPolygonPoints(), configManager.loadShellStartAngle());
        builder.buildCannonStartPosition(configManager.loadTankCannonStartPosition());
        builder.buildCannonRotationPoint(configManager.loadTankCannonRotationPoint());
                
        builder.buildStartPosition(configManager.loadTankStartPosition());
        
        builder.buildTankSpeed(configManager.loadTankSpeed());
        builder.buildTankMaxAimingAngle(configManager.loadTankMaxAimingAngle());
        builder.buildTankMinAimingAngle(configManager.loadTankMinAimingAngle());
        builder.buildTankMaxHitPoint(configManager.loadTankMaxHitPoint());
                
        return (Tank) builder.getObject();
    }
    
    public EnemyTank createEnemyTank(TankBuilder builder, Shell shell, ConfigManager configManager)
    {
        builder.buildObject();
        
        builder.buildShell(shell);
        
        builder.buildBase(configManager.loadTankPolygonPoints());
        builder.buildCannon();
        builder.buildCannonBase(configManager.loadTankCannonPolygonPoints(), configManager.loadShellStartAngle());
        builder.buildCannonStartPosition(configManager.loadTankCannonStartPosition());
        builder.buildCannonRotationPoint(configManager.loadTankCannonRotationPoint());
                
        builder.buildStartPosition(configManager.loadTankStartPosition());
        
        builder.buildTankSpeed(configManager.loadTankSpeed());
        builder.buildTankMaxAimingAngle(configManager.loadTankMaxAimingAngle());
        builder.buildTankMinAimingAngle(configManager.loadTankMinAimingAngle());
        builder.buildTankMaxHitPoint(configManager.loadTankMaxHitPoint());
                
        return (EnemyTank) builder.getObject();
    }
}
