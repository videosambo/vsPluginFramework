package fi.videosambo.pluginFramework.core.database;

import fi.videosambo.pluginFramework.core.database.query.NonReturnQuery;
import fi.videosambo.pluginFramework.core.database.query.ReturnQuery;

import java.sql.*;

public class DatabaseQuery {

    private DatabaseHandler handler;

    public DatabaseQuery(DatabaseHandler handler) {
        this.handler = handler;
    }

    public void nonReturnQuery(String query, DBVar... args) {
        Thread thread = new Thread(new NonReturnQuery(handler,query,args));
        thread.start();
    }

    public ResultSet returnQuery(String query, DBVar... args) throws InterruptedException {
        ReturnQuery returnQuery = new ReturnQuery(handler,query,args);
        Thread thread = new Thread(returnQuery);
        thread.start();
        thread.join();
        return returnQuery.getResult();
    }

}
