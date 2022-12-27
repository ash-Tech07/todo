package net.todoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.todoapp.model.User;
import net.todoapp.utils.JDBCUtils;

public class UserDao {

    public int registerEmployee(User employee) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO users"
                + "  (first_name, last_name, username, password) VALUES "
                + " (?, ?, ?, ?);";

        int result = 0;
        try (
            Connection connection = JDBCUtils.getConnection(); 
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getUsername());
            preparedStatement.setString(4, employee.getPassword());

            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

}
