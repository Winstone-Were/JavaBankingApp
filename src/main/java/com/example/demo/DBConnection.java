package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/bar";
    static final String USER = "root";
    static final String PASS = "123pass";
    private static DBConnection ConnectorClass = null;
    public static Connection Conn = null;

    static {
        try{
            Conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    static {
        try {
            // Load the MariaDB JDBC driver explicitly
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection GetConnection() {
        try{
            if(Conn == null){
                Conn = DriverManager.getConnection(DB_URL,USER,PASS);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return Conn;
    }

}
