package model.tank;

import game.ConfigManager;
import model.enemy.EnemyTank;
import model.enemy.EnemyTankBuilder;
import model.shell.Shell;

public class TankSheduler 
{
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
        builder.buildTankMaxHitPoints(configManager.loadTankMaxHitPoints());
        builder.buildTankMeleeDamage(configManager.loadTankMeleeDamage());
                
        return (Tank) builder.getObject();
    }
    
    public EnemyTank createEnemyTank(EnemyTankBuilder builder, Shell shell, ConfigManager configManager)
    {
        builder.buildObject();
        
        builder.buildShell(shell);
        
        builder.buildBase(configManager.loadTankPolygonPoints());
        builder.buildCannon();
        builder.buildCannonBase(configManager.loadTankCannonPolygonPoints(), configManager.loadShellStartAngle());
        builder.buildCannonStartPosition(configManager.loadTankCannonStartPosition());
        builder.buildCannonRotationPoint(configManager.loadTankCannonRotationPoint());
                
        builder.buildStartPosition(configManager.loadTankStartPosition());
        
        builder.buildTankSpeed(configManager.loadEnemyTankSpeed());
        builder.buildTankMaxAimingAngle(configManager.loadTankMaxAimingAngle());
        builder.buildTankMinAimingAngle(configManager.loadTankMinAimingAngle());
        builder.buildTankMaxHitPoints(configManager.loadTankMaxHitPoints());
        builder.buildVisibility(configManager.loadEnemyTankVisibility());
        builder.buildTankMeleeDamage(configManager.loadEnemyTankMeleeDamage());
                
        return (EnemyTank) builder.getObject();
    }
}
