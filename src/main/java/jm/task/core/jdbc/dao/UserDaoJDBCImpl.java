package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDaoJDBCImpl implements UserDao {

    private static final String INSERT_NEW = "INSERT INTO users (name, lastname, age) Values (?, ?, ?);";
    private static final String GET_ALL = "SELECT * FROM users;";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS users;";
    private static final String REMOVE_USER = "DELETE FROM users WHERE id = ?;";
    private static final String TRUNCATE_TABLE = "TRUNCATE users;";

    private static final String CREATE = "CREATE TABLE IF NOT EXISTS users "+
            "(id BIGINT(19) NOT NULL AUTO_INCREMENT, " +
            "name VARCHAR(45) NOT NULL," +
            "lastname VARCHAR(45) NOT NULL, " +
            "age TINYINT(3) NOT NULL, " +
            "PRIMARY KEY (id));";

    private Util utilConnection = new Util();





    public UserDaoJDBCImpl() {


    }
    public void createUsersTable() {

        try(Connection connection = utilConnection.getUtilConnection();
            Statement statement= connection.createStatement();)
        {
            statement.executeUpdate(CREATE);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try(Connection connection = utilConnection.getUtilConnection();

            Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_TABLE);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try(Connection connection = utilConnection.getUtilConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW)) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try(Connection connection = utilConnection.getUtilConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<User> getAllUsers() {

        List<User> arrayUsers = new ArrayList<>();
        try(Connection connection = utilConnection.getUtilConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {



            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                String name= resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                Byte age = resultSet.getByte("age");

                User user = new User(name, lastname, age);

                arrayUsers.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return arrayUsers;
    }

    public void cleanUsersTable() {

        try(Connection connection = utilConnection.getUtilConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(TRUNCATE_TABLE)) {

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
