package com.example.base;

import com.example.helpers.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class BaseDbTest {

    public static final Logger logger = LoggerFactory.getLogger(BaseDbTest.class);
    String jdbcUrl = ConfigLoader.getProperty("db.url");
    protected Connection connection;

    public void setUp(){
        try {
            connection = DriverManager.getConnection(jdbcUrl);
            logger.info("Db connection established!");
        } catch (SQLException e) {
          throw new RuntimeException("Failed to connect to db: " +e.getMessage());
        }
    }

    public void tearDown(){
        try{
            if (connection != null && !connection.isClosed()) {
                connection.close();
                logger.info("Database connection closed");
            }
        } catch (SQLException e){
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    public boolean isConnectionValid(){
        try {
            if (connection != null && !connection.isClosed()) {
                return connection.isValid(2);
            }
        } catch (SQLException e){
            System.err.println("Error checkin connection validity: " + e.getMessage());
        }
        return  false;
    }

}