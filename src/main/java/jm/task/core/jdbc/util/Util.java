package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql//127.0.0.1:3306/mydbtest";
    private final static String URLFIXED =
            "jdbc:mysql://127.0.0.1:3306/mydbtest?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USERNAME = "bestuser";
    private static final String PASSWORD = "bestuser";

    private Connection connection;

    public Util(){   }

    public Connection getUtilConnection() { // геттер приватного поля connection + создаем connection
        try {
            connection = DriverManager.getConnection(URLFIXED, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return connection;
    }
}
