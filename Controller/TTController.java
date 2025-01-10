package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Connection.DBConnection;

public class TTController {
    
    public boolean saveTransaction(String nama, String alamat, String noTelp, double berat, String kategori, double cost) {
        
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "INSERT INTO transaksi (nama, alamat, no_telp, berat, kategori, cost, created_at) VALUES (?, ?, ?, ?, ?, ?, NOW())";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, nama);
            statement.setString(2, alamat);
            statement.setString(3, noTelp);
            statement.setDouble(4, berat);
            statement.setString(5, kategori);
            statement.setDouble(6, cost);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  
    }
}
