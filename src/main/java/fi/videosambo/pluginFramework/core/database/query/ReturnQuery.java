package fi.videosambo.pluginFramework.core.database.query;

import fi.videosambo.pluginFramework.core.Utils;
import fi.videosambo.pluginFramework.core.database.DBVar;
import fi.videosambo.pluginFramework.core.database.DatabaseHandler;
import fi.videosambo.pluginFramework.core.database.QueryType;
import fi.videosambo.pluginFramework.core.exceptions.DBNullResultException;
import fi.videosambo.pluginFramework.core.exceptions.DBUndentifiedValueException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReturnQuery implements Runnable {

    private ResultSet result;
    private DatabaseHandler handler;
    private QueryType type;
    private String query;
    private DBVar[] args;
    private boolean isByteStream;

    public ReturnQuery(QueryType type,DatabaseHandler handler, String query, boolean isByteStream, DBVar... args) {
        this.handler = handler;
        this.type = type;
        this.query = query;
        this.args = args;
        this.isByteStream = isByteStream;
    }

    @Override
    public void run() {
        try {
            if (args == null) {
                Statement stmt = handler.getConnection().createStatement();
                if (type.equals(QueryType.UPDATE))
                    stmt.executeUpdate(query);
                else
                    stmt.executeQuery(query);
                if (result == null)
                    throw new DBNullResultException("Expected result but got nothing while executing " + stmt.toString());
            } else {
                PreparedStatement statement = handler.getConnection().prepareStatement(query);
                Utils.prepareStatement(statement, isByteStream, args);
                if (type.equals(QueryType.UPDATE))
                    statement.executeUpdate(query);
                else
                    statement.executeQuery(query);
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
