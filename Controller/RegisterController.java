package Controller;

import Connection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {
    private DBConnection dbConnection;


    public RegisterController() {
        dbConnection = DBConnection.getInstance();
    }









    public boolean registerUser(String name, String password, String phone, String alamat) {
        Connection connection = dbConnection.getConnection();

        if (connection != null) {
            try {
                if (isNameOrPhoneExists(name, phone)) {
                    System.out.println("Name or phone num Exists");
                    return false;
                }

                String query = "INSERT INTO users (name, phone_number, password, address) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, name);
                statement.setString(2, phone);
                statement.setString(3, password);
                statement.setString(4, alamat);


                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("User registered !! ");
                    return true;
                } else {
                    System.out.println("Failed to insert ke database");
                }
            } catch (SQLException e) {
                System.out.println("Error register");
                e.printStackTrace();
            }
        } else {
            System.out.println("Database connection failed");
        }
        return false;
    }
    











    private boolean isNameOrPhoneExists(String name, String phone) {
        Connection connection = dbConnection.getConnection();
        String query = "SELECT COUNT(*) FROM users WHERE name = ? OR phone_number = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, phone);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error cek duplikasi name/phone ");
            e.printStackTrace();
        }
        return false;
    }
}
