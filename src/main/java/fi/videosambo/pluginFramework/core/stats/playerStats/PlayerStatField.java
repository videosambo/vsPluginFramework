package fi.videosambo.pluginFramework.core.stats.playerStats;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class is for storing values that can be used by other plugins that are using this plugin framework.
 * PlayerStatField is a value that every player has.
 * @param <E>   Type of value
 */

public class PlayerStatField<E> {

    private String fieldTag;
    private ArrayList<PlayerStat<E>> valueList;

    /**
     * @param fieldTag  Field name that can be used for identification
     */
    public PlayerStatField(String fieldTag) {
        this.fieldTag = fieldTag;
    }

    /**
     * @param fieldTag  Field name that can be used for identification
     * @param object    Object that field is going to store for use
     */
    public PlayerStatField(String fieldTag, E object) {
        this.fieldTag = fieldTag;
        this.valueList = new ArrayList<PlayerStat<E>>();
    }

    public void setFieldTag(String fieldTag) {
        this.fieldTag = fieldTag;
    }

    public void setValueList(ArrayList<PlayerStat<E>> valueList) {
        this.valueList = valueList;
    }

    public String getFieldTag() {
        return fieldTag;
    }

    public E getValueFromPlayer(Object player) {
        for (PlayerStat<E> stat : valueList) {
            if (stat.getPlayer().equals(player)) {
                return stat.getValue();
            }
        }
        return null;
    }

    public E getValueFromUUID(UUID uuid) {
        for (PlayerStat<E> stat : valueList) {
            if (stat.getUuid().equals(uuid)) {
                return stat.getValue();
            }
        }
        return null;
    }
}
