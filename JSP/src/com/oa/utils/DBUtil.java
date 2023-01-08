package com.oa.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * JDBC Util class
 */
public class DBUtil {
    //Create static resources bundle
    private static final String DB_BUNDLE = "resources/db";
    private static final ResourceBundle DB = ResourceBundle.getBundle(DB_BUNDLE);

    //Getting info from bundle
    private static final String DRIVER = DB.getString("driver");
    private static final String URL = DB.getString("url");
    private static final String USER = DB.getString("user");
    private static final String PASSWORD = DB.getString("password");

    static {
        try {
//            System.out.println(DRIVER);
//            System.out.println(URL);
//            System.out.println(USER);
//            System.out.println(PASSWORD);
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return connection Database connection object
     * @throws SQLException SQLException
     */
    public static Connection getConnection() throws SQLException {
        //Getting connection obj
        Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
        return connection;
    }


    // Release 'ResultSet', 'Statement' and 'Connection' obj resources
    /**
     *
     * @param connection connection obj
     * @param statement database operation obj
     * @param resultSet result set obj
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
