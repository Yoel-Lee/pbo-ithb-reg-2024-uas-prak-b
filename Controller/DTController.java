package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Connection.DBConnection;

public class DTController {
    private DBConnection dbConnection;

    public DTController() {
        dbConnection = DBConnection.getInstance();
    }

    public boolean saveDeliveryStatus(int transactionId, String status, String currentPosition, String evidence, String updatedBy) {
        
        if (transactionId <= 0 || status == null || currentPosition == null || evidence == null || updatedBy == null) {
            return false;
        }

        // Koneksi ke database
        Connection connection = dbConnection.getConnection();
        if (connection != null) {
            try {
                // Query untuk menyimpan detail status pengiriman
                String query = "INSERT INTO delivery_status (transaction_id, status, current_position, evidence,updated_by) VALUES (?, ?, ?, ?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, transactionId);
                statement.setString(2, status);
                statement.setString(3, currentPosition);
                statement.setString(4, evidence);
                statement.setString(5, updatedBy);

                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0;  
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;  
    }
}
