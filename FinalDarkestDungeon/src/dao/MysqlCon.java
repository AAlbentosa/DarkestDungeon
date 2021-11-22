package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlCon {

    public String driver;
    public String database;
    public String hostname;
    public String port;
    public String url;
    public String username;
    public String password;
    private Connection conn;
    
    public MysqlCon() {
    	conn = null;
    	driver = "com.mysql.cj.jdbc.Driver";
        database = "DarkestDungeon";
        hostname = "localhost";
        port = "3306";
        url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";
        username = "user";
        password = "";
    }
    
    public Connection getConnection() {
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Can't stablish connection to the database, check your internet connection!");
        }

        return conn;
    }
}