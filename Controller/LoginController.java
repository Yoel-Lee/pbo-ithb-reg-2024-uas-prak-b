package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Connection.DBConnection;


public class LoginController {
    private DBConnection dbConnection;

    public LoginController() {
        dbConnection = DBConnection.getInstance();
    }

    public boolean loginUser(String noTelp, String password) {
        Connection connection = dbConnection.getConnection();
        if (connection != null) {
            try {
                
                String query = "SELECT id_user FROM users WHERE phone_number = ? AND password = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, noTelp);
                statement.setString(2, password);

                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    int userId = resultSet.getInt("id_user");
                    SessionManager.setLoggedIn(true);
                    SessionManager.setUserId(userId);  
                    return true;
                } else {
                    System.out.println("Login gagal, eror phone number / password");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Database connection failed");
        }
        return false;
    }
}
