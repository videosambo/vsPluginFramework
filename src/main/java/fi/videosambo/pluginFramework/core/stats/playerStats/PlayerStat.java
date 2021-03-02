package fi.videosambo.pluginFramework.core.stats.playerStats;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * This class is for storing value to every player that is instance of ProxiedPlayer or Player
 * @param <E>   Type of value
 */

public class PlayerStat<E> {

    private Player player;
    private ProxiedPlayer proxiedPlayer;

    private UUID uuid;

    private Object playerReference;

    private E value;

    /**
     * This constructor is used for only protected data storing. For example data that is stored using spigot Player cannot be used in proxy.
     * @param player    This parameter is either spigot Player or bungeecord ProxiedPlayer.
     * @param value     Object that is stored for player
     */
    public PlayerStat(Object player,E value) {
        playerReference = player;
        if (player instanceof Player)
            this.player = (Player) player;
        else if (player instanceof ProxiedPlayer)
            this.proxiedPlayer = (ProxiedPlayer) player;
        else
            throw new ClassCastException("Invalid parameter");
        this.value = value;
    }

    /**
     * This constructor is used for shared data storing that can be used by normal servers along with proxy.
     * @param uuid      Player or ProxiedPlayer UUID
     * @param value     Object that is stored for player
     */
    public PlayerStat(UUID uuid,E value) {
        this.uuid = uuid;
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Object getPlayer() {
        if (playerReference instanceof Player)
            return player;
        if (playerReference instanceof ProxiedPlayer)
            return proxiedPlayer;
        return null;
    }

    public UUID getUuid() {
        if (playerReference instanceof Player)
            return player.getUniqueId();
        if (playerReference instanceof ProxiedPlayer)
            return proxiedPlayer.getUniqueId();
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
