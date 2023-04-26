package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRepository {
    private static final Logger logger = LoggerFactory.getLogger(LoginRepository.class);

    public LoginView getLoginView(String username) {
        try (Connection connection = databaseConn.getConnection();
             PreparedStatement p = connection.prepareStatement(
                     "SELECT email, pwd FROM bds.login WHERE email = ?;"))
        {
            p.setString(1, username);
            try (ResultSet rs = p.executeQuery())
            {
                if (rs.next())
                {
                    return mapToLoginView(rs);
                }
            }
        } catch (SQLException e)
        {
            logger.error(String.format("Couldn't open Login view\nMeassage: %s", e.getMessage()));
        }
        return null;
    }

    private LoginView mapToLoginView(ResultSet rs) throws SQLException {
        System.out.println("mapToLoginView initialized");
        LoginView loginView = new LoginView();
        loginView.setHashed_password(rs.getString("pwd"));
        loginView.setUsername(rs.getString("email"));
        System.out.println("mapToLoginView success");
        return loginView;
    }


}
