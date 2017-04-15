package model.shell;

import game.ConfigManager;

public class ShellSheduler {
    public Shell createShell(ShellBuilder builder, ConfigManager configManager)
    {
        builder.buildObject();
        builder.buildBase(configManager.loadShellPolygonPoints());
        builder.buildStartAngle(configManager.loadShellStartAngle());
        builder.buildStartSpeed(configManager.loadShellStartSpeed());
        builder.buildDamage(configManager.loadShellDamage());
        
        return (Shell) builder.getObject();
    }
}
