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
     * @param args  Arguments for query
     */
    public void nonReturnQuery(String query, DBVar... args) {
        Thread thread = new Thread(new NonReturnQuery(handler,query,false,args));
        thread.start();
    }

    /**
     * Method to run database queries that does not return output
     *
     * For every argument, use ? as a placeholder
     *
     * @param query query string
     * @param args  Arguments for query
     * @param isByteStream If inserted InputStream is either bytestream or asciistream
     */
    public void nonReturnQuery(String query, boolean isByteStream, DBVar... args) {
        Thread thread = new Thread(new NonReturnQuery(handler,query,isByteStream,args));
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
        ReturnQuery returnQuery = new ReturnQuery(handler,query,false,args);
        Thread thread = new Thread(returnQuery);
        thread.start();
        thread.join();
        while (returnQuery.getResult() != null) {
            return returnQuery.getResult();
        }
        return null;
    }

    /**
     * Method to run database quaries that returns a value
     * After executing, remember to close connection
     *
     * For every argument, use ? as a placeholder
     *
     * @param query         query string
     * @param args          arguments for query
     * @param isByteStream If inserted InputStream is either bytestream or asciistream
     * @return ResultSet
     * @throws InterruptedException Database query thread
     */
    public ResultSet returnQuery(String query, boolean isByteStream, DBVar... args) throws InterruptedException {
        ReturnQuery returnQuery = new ReturnQuery(handler,query,isByteStream,args);
        Thread thread = new Thread(returnQuery);
        thread.start();
        thread.join();
        while (returnQuery.getResult() != null) {
            return returnQuery.getResult();
        }
        return null;
    }

}
