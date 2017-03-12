/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package courseSelection;

/**
 *
 * @author Hiranthi
 */
public class Constants {
    public static final String URL= "jdbc:mysql://localhost:3306/";
    public static final String MAHOUT_URL= "jdbc:mysql://localhost:3306/courseselection";
    public static final String DB_NAME = "courseselection";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public static final int RECOMONDATION_COUNT = 5;
    
    // JDBC driver name and database URL
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://localhost/courseselection";
    
    public static final int MAIL_READER_INTERVAL=1*3600*1000;
    public static final int MAIL_SENDER_INTERVAL=1*3600*1000;
    
    // mahout table
    public static final String MAHOUT_TABLE = "mahout_recomonder_data";
    
}
