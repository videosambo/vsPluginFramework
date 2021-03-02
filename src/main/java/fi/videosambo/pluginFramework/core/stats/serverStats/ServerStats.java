package fi.videosambo.pluginFramework.core.stats.serverStats;

import java.util.ArrayList;

public class ServerStats {

    private ArrayList<ServerStatField> fields;

    public ServerStats() {
        fields = new ArrayList<>();
    }

    public void addNewField(ServerStatField field) {
        fields.add(field);
    }

    public void removeField(ServerStatField field) throws NoSuchFieldException {
        try {
            fields.remove(field);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchFieldException("Tried to remove field that does not exist");
        }
    }

    public Object getValue(String tag) throws NoSuchFieldException {
        for (ServerStatField field : fields) {
            if (field.getFieldTag().equals(tag))
                return field.getValue();
        }
        throw new NoSuchFieldException("Tried to get field that does not exist");
    }
}
