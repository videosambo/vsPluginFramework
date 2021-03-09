package fi.videosambo.pluginFramework.spigot;

import fi.videosambo.pluginFramework.core.database.DatabaseHandler;
import fi.videosambo.pluginFramework.core.stats.StatHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class PluginFramework extends JavaPlugin {

    private PluginFramework instance;
    private static StatHandler stats;

    @Override
    public void onEnable() {
        instance = this;
        stats = new StatHandler();
    }

    @Override
    public void onDisable() {

    }

    public PluginFramework getInstance() {
        return instance;
    }

    public static StatHandler getStats() {
        return stats;
    }
}
