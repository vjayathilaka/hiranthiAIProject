package courseSelection.jdbc.connection;

import com.mysql.jdbc.Connection;
import courseSelection.Constants;
import java.sql.*;
import java.sql.DriverManager;
/**
 * @desc A singleton database access class for MySQL
 * @author Ramindu
 */
public final class JDBCConnection1 {
    public Connection conn;
    private Statement statement;
    public static JDBCConnection1 db;
    private JDBCConnection1() {
        String url= Constants.URL;
        String dbName = Constants.DB_NAME;
        String driver = Constants.DRIVER;
        String userName = Constants.USERNAME;
        String password = Constants.PASSWORD;
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized JDBCConnection1 getDbCon() {
        if ( db == null ) {
            db = new JDBCConnection1();
        }
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
}