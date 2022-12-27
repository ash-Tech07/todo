package net.todoapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.todoapp.model.LoginBean;
import net.todoapp.utils.JDBCUtils;

public class LoginDao {

    public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
        boolean status = false;

        Connection connection;
        try {
            connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ? and password = ? ");
            preparedStatement.setString(1, loginBean.getUsername());
            preparedStatement.setString(2, loginBean.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return status;
    }
}
