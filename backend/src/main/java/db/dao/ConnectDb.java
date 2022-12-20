package db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectDb {

    String url = "jdbc:postgresql://localhost:5432/tp_transversal_s5";
    String database = "tp_transversal_s5";
    String port = "5432";
    String hostName = "localhost";
    String user = "postgres";
    String password = "0000";
    String base = "postgresql";
    Connection connection;

    public ConnectDb(String base, String user, String database, String password)
            throws SQLException {
        setBase(base);
        setDatabase(database);
        setUser(user);
        setPassword(password);
        try {
            connect();
        } catch (SQLException e) {
            throw e;
        }
    }

    public ConnectDb(String base, String user, String passwd)
            throws SQLException {
        setBase(base);
        setUser(user);
        setPassword(passwd);
        try {
            connect();
        } catch (SQLException e) {
            throw e;
        }
    }

    public ConnectDb() throws SQLException {
        try {
            connect();

        } catch (SQLException e) {
            throw e;
        }
    }

    // loading the url when some attributes are changed
    public void loadUrl() {
//		 System.out.println("SGBD: "+base);
        if (base.equals("oracle")) {

            port = "1521";
            database = "DBCOURS";
            url = "jdbc:oracle:thin:@" + hostName + ":" + port + ":" + database;
        } else if (base.equals("postgresql")) {

            port = "5432";
            url = "jdbc:postgresql://" + hostName + ":" + port + "/" + database;
        } else {
            throw new IllegalArgumentException("Unexpected value: " + base + "\n sgbd available: oracle and postgresql");
        }

    }

    public Connection getConnection() {
        return this.connection;
    }

    private void setConnection(Connection connection) {
        this.connection = connection;
    }

//	  protected String getPassword() {
//	    return this.password;
//	  }

    public String getUrl() {
        return this.url;
    }

    public String getUser() {
        return this.user;
    }

    private void setUser(String user) {
        this.user = user;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return this.database;
    }

    private void setDatabase(String database) {
        this.database = database;
        loadUrl();
    }

    public String getPort() {
        return this.port;
    }

    public void setPort(String port) {
        this.port = port;
        loadUrl();
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
        loadUrl();
    }

    public String getBase() {
        return this.base;
    }

    private void setBase(String base) {
        this.base = base;
        loadUrl();
    }

    @Override
    public String toString() {
        return "ConnectDb [url=" + url + ", database=" + database + ", port=" + port + ", hostName=" + hostName
                + ", user=" + user + ", base=" + base + ", connection=" + connection + "]";
    }

    //	commit the transaction
    public void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            throw e;
        }
    }

    //	cancel the transaction
    public void rollback() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw e;
        }
    }

    // Initializing the connection on the server
    public void connect() throws SQLException {
        Connection connect = null;
//		  System.out.println(url);
        try {
            connect = DriverManager.getConnection(
                    this.url,
                    this.user,
                    this.password);
            connect.setAutoCommit(false);


        } catch (SQLException e) {

            throw e;
        }
        setConnection(connect);
    }

    public PreparedStatement prepareStmt(String sql) throws SQLException {
        try {
            return connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw e;
        }
    }

    public void close() throws SQLException {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException e) {
                throw e;
            }
    }
}
