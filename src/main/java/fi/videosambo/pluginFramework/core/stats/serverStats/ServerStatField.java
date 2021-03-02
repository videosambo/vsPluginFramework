package fi.videosambo.pluginFramework.core.stats.serverStats;

import fi.videosambo.pluginFramework.core.stats.playerStats.PlayerStat;

import java.util.ArrayList;
import java.util.UUID;

public class ServerStatField<E> {

    private String fieldTag;
    private E value;

    /**
     * @param fieldTag  Field name that can be used for identification
     */
    public ServerStatField(String fieldTag) {
        this.fieldTag = fieldTag;
    }

    /**
     * @param fieldTag  Field name that can be used for identification
     * @param value    Value for field
     */
    public ServerStatField(String fieldTag, E value) {
        this.fieldTag = fieldTag;
        this.value = value;
    }

    public void setFieldTag(String fieldTag) {
        this.fieldTag = fieldTag;
    }

    public String getFieldTag() {
        return fieldTag;
    }

    public E getValue() {
        return value;
    }
}
