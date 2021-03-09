package fi.videosambo.pluginFramework.proxy;

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
