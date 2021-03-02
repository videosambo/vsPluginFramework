package fi.videosambo.pluginFramework.proxy;

import fi.videosambo.pluginFramework.core.Handler;
import fi.videosambo.pluginFramework.core.database.DatabaseHandler;
import fi.videosambo.pluginFramework.core.stats.StatHandler;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.SQLException;

public class PluginFramework extends Plugin {

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
