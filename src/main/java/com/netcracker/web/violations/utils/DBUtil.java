package com.netcracker.web.violations.utils;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


public class DBUtil {
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";
    private static final String DB_URL = "db.url";
    private static final String DB_DRIVER_CLASS = "driver.class.name";

    private static Properties properties = null;
    private static HikariDataSource dataSource;
    static {
        try{
            properties = new Properties();

            properties.load(new FileReader("C:\\Users\\Антон\\IdeaProjects\\ViolationsWebApp\\src\\main\\resources\\database.properties"));
            dataSource = new HikariDataSource();

            dataSource.setDriverClassName(properties.getProperty(DB_DRIVER_CLASS));

            dataSource.setJdbcUrl(properties.getProperty(DB_URL));
            dataSource.setUsername(properties.getProperty(DB_USERNAME));
            dataSource.setPassword(properties.getProperty(DB_PASSWORD));

            dataSource.setMinimumIdle(2);
            dataSource.setMaximumPoolSize(10);
            dataSource.setAutoCommit(false);
            dataSource.setLoginTimeout(3);


        } catch (IOException |  SQLException e){
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }
}
