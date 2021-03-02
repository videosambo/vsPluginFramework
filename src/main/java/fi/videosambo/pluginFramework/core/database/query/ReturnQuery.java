package fi.videosambo.pluginFramework.core.database.query;

import fi.videosambo.pluginFramework.core.Utils;
import fi.videosambo.pluginFramework.core.database.DBVar;
import fi.videosambo.pluginFramework.core.database.DatabaseHandler;
import fi.videosambo.pluginFramework.core.exceptions.DBNullResultException;
import fi.videosambo.pluginFramework.core.exceptions.DBUndentifiedValueException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReturnQuery implements Runnable {

    private ResultSet result;
    private DatabaseHandler handler;
    private String query;
    private DBVar[] args;

    public ReturnQuery(DatabaseHandler handler, String query, DBVar... args) {
        this.handler = handler;
        this.query = query;
        this.args = args;
    }

    @Override
    public void run() {
        try {
            if (args == null) {
                Statement stmt = handler.getConnection().createStatement();
                result = stmt.executeQuery(query);
                if (result == null)
                    throw new DBNullResultException("Expected result but got nothing while executing " + stmt.toString());
            } else {
                PreparedStatement statement = handler.getConnection().prepareStatement(query);
                Utils.prepareStatement(statement, args);
                result = statement.executeQuery();
                if (result == null)
                    throw new DBNullResultException("Expected result but got nothing while executing " + statement.toString());
            }
        } catch (SQLException | DBNullResultException | DBUndentifiedValueException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResult() {
        return result;
    }
}
