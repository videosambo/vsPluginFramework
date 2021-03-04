package fi.videosambo.pluginFramework.core.logger;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PluginLogger extends Logger {

    private String prefix, name;

    private String prefixColor, infoColor, warningColor, errorColor;

    protected PluginLogger(Object plugin) {
        super(plugin.getClass().getCanonicalName(), null);
        if (plugin instanceof JavaPlugin) {
            prefix = ((JavaPlugin) plugin).getDescription().getPrefix();
            name = prefix != null ? new StringBuilder().append("[").append(prefixColor).append(prefix).append(ConsoleColor.RESET + "] ").toString() : "[" + prefixColor + ((JavaPlugin) plugin).getDescription().getName() + ConsoleColor.RESET+ "] ";
        } else if (plugin instanceof Plugin) {
            prefix = ((Plugin) plugin).getDescription().getPrefix();
            name = prefix != null ? new StringBuilder().append("[").append(prefixColor).append(prefix).append(ConsoleColor.RESET + "] ").toString() : "[" + prefixColor+ ((Plugin) plugin).getDescription().getName() + ConsoleColor.RESET + "] ";
        } else
            throw  new ClassCastException("Not a minecraft plugin");
        setLevel(Level.ALL);
        prefixColor = ConsoleColor.WHITE;
        infoColor = ConsoleColor.WHITE;
        warningColor = ConsoleColor.RED_BRIGHT;
        errorColor = ConsoleColor.RED_BOLD;
    }

    public void logInfo(String msg) {
        super.log(Level.INFO, name + infoColor + msg);
    }

    public void logWarning(String msg) {
        super.log(Level.WARNING, name + warningColor + msg);
    }

    public void logError(String msg) {
        super.log(Level.SEVERE, name + errorColor + msg);
    }

    public void setPrefixColor(String prefixColor) {
        this.prefixColor = prefixColor;
    }

    public void setInfoColor(String infoColor) {
        this.infoColor = infoColor;
    }

    public void setWarningColor(String warningColor) {
        this.warningColor = warningColor;
    }

    public void setErrorColor(String errorColor) {
        this.errorColor = errorColor;
    }
}
