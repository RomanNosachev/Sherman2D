package model.shell;

import game.ConfigManager;

public class ShellSheduler {
    public Shell createShell(ShellBuilder builder, ConfigManager configManager)
    {
        builder.buildObject();
        builder.buildBase(configManager.loadShellPolygonPoints());
        builder.buildStartPosition(configManager.loadTankCannonRotationPoint());
        builder.buildStartAngle(configManager.loadShellStartAngle());
        builder.buildStartSpeed(configManager.loadShellStartSpeed());
        
        return (Shell) builder.getObject();
    }
}
