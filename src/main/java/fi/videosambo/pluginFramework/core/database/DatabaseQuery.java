package fi.videosambo.pluginFramework.core.database;

import fi.videosambo.pluginFramework.core.database.query.NonReturnQuery;
import fi.videosambo.pluginFramework.core.database.query.ReturnQuery;

import java.sql.*;

public class DatabaseQuery {

    private DatabaseHandler handler;

    /**
     * @param handler   Database handler for query.
     */
    public DatabaseQuery(DatabaseHandler handler) {
        this.handler = handler;
    }

    /**
     * Method to run database queries that does not return output
     *
     * For every argument, use ? as a placeholder
     *
     * @param query query string
     * @param args  Arguments for query,
     */
    public void nonReturnQuery(String query, DBVar... args) {
        Thread thread = new Thread(new NonReturnQuery(handler,query,args));
        thread.start();
    }

    /**
     * Method to run database quaries that returns a value
     * After executing, remember to close connection
     *
     * For every argument, use ? as a placeholder
     *
     * @param query         query string
     * @param args          arguments for query
     * @return ResultSet
     * @throws InterruptedException Database query thread
     */
    public ResultSet returnQuery(String query, DBVar... args) throws InterruptedException {
        ReturnQuery returnQuery = new ReturnQuery(handler,query,args);
        Thread thread = new Thread(returnQuery);
        thread.start();
        thread.join();
        return returnQuery.getResult();
    }

}
