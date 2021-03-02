package fi.videosambo.pluginFramework.core.stats;

import fi.videosambo.pluginFramework.core.stats.playerStats.PlayerStats;
import fi.videosambo.pluginFramework.core.stats.serverStats.ServerStats;

public class StatHandler {

    private static ServerStats serverStats;
    private static PlayerStats playerStats;

    public StatHandler() {
        serverStats = new ServerStats();
        playerStats = new PlayerStats();
    }

    public ServerStats getServerStats() {
        return serverStats;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }
}
