package fi.videosambo.pluginFramework.core.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fi.videosambo.pluginFramework.core.Handler;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseHandler {

    private String dbHost;
    private int dbPort;
    private String dbDatabase;
    private String dbUser;
    private String dbPassword;

    private HikariConfig config;
    private HikariDataSource dataSource;

    /**
     * Database handler for adding database and handling it. Create new handler for every database that you use.
     * @param dbHost        Host for database
     * @param dbPort        Port for database
     * @param dbDatabase    Database to use
     * @param dbUser        Database user
     * @param dbPassword    Database password
     */
    public DatabaseHandler(String dbHost, int dbPort, String dbDatabase, String dbUser, String dbPassword) {
        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.dbDatabase = dbDatabase;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        config = new HikariConfig();

        config.setJdbcUrl("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbDatabase);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        dataSource = new HikariDataSource(config);

        Handler.getDbHandlers().add(this);
    }

    /**
     * Method to add additional properties
     * @param property  Propertyfield name
     * @param value     Value for property
     */
    public void setDataSourceProperty(String property, String value) {
        config.addDataSourceProperty(property, value);
        dataSource = new HikariDataSource(config);
    }

    /**
     * @return Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public HikariConfig getConfig() {
        return config;
    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public int getDbPort() {
        return dbPort;
    }

    public void setDbPort(int dbPort) {
        this.dbPort = dbPort;
    }

    public String getDbDatabase() {
        return dbDatabase;
    }

    public void setDbDatabase(String dbDatabase) {
        this.dbDatabase = dbDatabase;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }
}
