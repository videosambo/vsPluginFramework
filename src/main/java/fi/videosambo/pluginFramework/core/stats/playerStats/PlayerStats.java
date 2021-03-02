package fi.videosambo.pluginFramework.core.stats.playerStats;

import java.util.ArrayList;

public class PlayerStats {

    private ArrayList<PlayerStatField> fields;

    public PlayerStats() {
        fields = new ArrayList<>();
    }

    public void addNewField(PlayerStatField field) {
        fields.add(field);
    }

    public void removeField(PlayerStatField field) throws NoSuchFieldException {
        try {
            fields.remove(field);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchFieldException("Tried to remove field that does not exist");
        }
    }

    public PlayerStatField getFieldByTag(String tag) throws NoSuchFieldException {
        for (PlayerStatField field : fields) {
            if (field.getFieldTag().equals(tag))
                return field;
        }
        throw new NoSuchFieldException("Tried to get field that does not exist");
    }
}
