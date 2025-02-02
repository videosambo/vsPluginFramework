package fi.videosambo.pluginFramework.core.database.query;

import fi.videosambo.pluginFramework.core.Utils;
import fi.videosambo.pluginFramework.core.database.DBVar;
import fi.videosambo.pluginFramework.core.database.DatabaseHandler;
import fi.videosambo.pluginFramework.core.exceptions.DBUndentifiedValueException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class NonReturnQuery implements Runnable{

    private DatabaseHandler handler;
    private String query;
    private DBVar[] args;
    private boolean isByteStream;

    public NonReturnQuery(DatabaseHandler handler, String query, boolean isByteStream, DBVar... args) {
        this.handler = handler;
        this.query = query;
        this.args = args;
        this.isByteStream = isByteStream;
    }

    @Override
    public void run() {
        try {
            if (args == null) {
                Statement stmt = handler.getConnection().createStatement();
                stmt.execute(query);
                stmt.close();
            } else {
                PreparedStatement statement = handler.getConnection().prepareStatement(query);
                Utils.prepareStatement(statement,isByteStream, args);
                statement.executeQuery();
                statement.close();
            }
        }catch (SQLException | DBUndentifiedValueException e) {
            e.printStackTrace();
        }
    }
}
