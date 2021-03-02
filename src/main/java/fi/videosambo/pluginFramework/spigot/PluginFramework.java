package fi.videosambo.pluginFramework.spigot;

import fi.videosambo.pluginFramework.core.Handler;
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
        Handler.setHandler(this);
    }

    @Override
    public void onDisable() {
        for (DatabaseHandler dbHandler : Handler.getDbHandlers()) {
            try {
                dbHandler.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public PluginFramework getInstance() {
        return instance;
    }

    public static StatHandler getStats() {
        return stats;
    }
}
