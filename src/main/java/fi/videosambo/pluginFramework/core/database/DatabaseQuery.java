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
    public void nonReturnQuery(QueryType type, String query, DBVar... args) {
        Thread thread = new Thread(new NonReturnQuery(type,handler,query,false,args));
        thread.start();
    }

    /**
     * Static method to run database queries that does not return output
     *
     * For every argument, use ? as a placeholder
     *
     * @param dbHandler DatabaseHandler
     * @param query query string
     * @param args  Arguments for query
     */
    public static void nonReturnQuery(QueryType type, DatabaseHandler dbHandler, String query, DBVar... args) {
        Thread thread = new Thread(new NonReturnQuery(type,dbHandler,query,false,args));
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
    public void nonReturnQuery(QueryType type, String query, boolean isByteStream, DBVar... args) {
        Thread thread = new Thread(new NonReturnQuery(type,handler,query,isByteStream,args));
        thread.start();
    }

    /**
     * Static method to run database quaries that returns a value
     * After executing, remember to close connection
     *
     * For every argument, use ? as a placeholder
     *
     * @param dbHandler DatabaseHandler
     * @param query         query string
     * @param args          arguments for query
     * @return ResultSet
     * @throws InterruptedException Database query thread
     */
    public static ResultSet returnQuery(QueryType type, DatabaseHandler dbHandler, String query, DBVar... args) throws InterruptedException {
        ReturnQuery returnQuery = new ReturnQuery(type,dbHandler,query,false,args);
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
     * @return ResultSet
     * @throws InterruptedException Database query thread
     */
    public ResultSet returnQuery(QueryType type, String query, DBVar... args) throws InterruptedException {
        ReturnQuery returnQuery = new ReturnQuery(type, handler,query,false,args);
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
    public ResultSet returnQuery(QueryType type, String query, boolean isByteStream, DBVar... args) throws InterruptedException {
        ReturnQuery returnQuery = new ReturnQuery(type, handler,query,isByteStream,args);
        Thread thread = new Thread(returnQuery);
        thread.start();
        thread.join();
        while (returnQuery.getResult() != null) {
            return returnQuery.getResult();
        }
        return null;
    }

}
