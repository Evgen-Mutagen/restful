package ru.astondevs.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

import static ru.astondevs.data.Properties.*;

public class BaseConnection {
    private static DataSource dataSource;
    private static HikariConfig config = new HikariConfig();

    private BaseConnection() {
    }

    public static DataSource getInstance() {
        if (dataSource == null) {
            synchronized (BaseConnection.class) {
                if (dataSource == null) {
                    dataSource = getDataSource();
                }

            }
        }
        return dataSource;
    }

    private static DataSource getDataSource() {
        try {
            Class.forName(DRIVER);
            config.setJdbcUrl(URL);
            config.setUsername(USER);
            config.setPassword(PASSWORD);
            config.setMaximumPoolSize(10);
            dataSource = new HikariDataSource(config);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
