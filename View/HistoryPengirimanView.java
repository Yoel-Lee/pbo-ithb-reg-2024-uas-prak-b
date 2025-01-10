package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Connection.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HistoryPengirimanView {


  public HistoryPengirimanView() {
    showMenu();
  }


  public void showMenu() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    JFrame historyMenu = new JFrame("History Pengiriman");
    historyMenu.setLayout(null);
    historyMenu.setSize(600, 500);
    historyMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    int x = (screenSize.width - historyMenu.getWidth()) / 2;
    int y = (screenSize.height - historyMenu.getHeight()) / 2;
    historyMenu.setLocation(x, y);

    JLabel label = new JLabel("History Pengiriman");
    label.setBounds(200, 30, 200, 30);
    label.setFont(new Font("SansSerif", Font.BOLD, 20));
    historyMenu.add(label);

    JTable table = new JTable();
    DefaultTableModel tableModel = new DefaultTableModel();
    tableModel.addColumn("Transaction ID");
    tableModel.addColumn("Status");
    tableModel.addColumn("Current Position");
    tableModel.addColumn("Detail");













    try {
      Connection conn = DBConnection.getInstance().getConnection();
      String query = "SELECT * FROM delivery_status";
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);


      while (rs.next()) {
        int transactionId = rs.getInt("transaction_id");
        String status = rs.getString("status");
        String currentPosition = rs.getString("current_position");



        tableModel.addRow(new Object[] {transactionId, status, currentPosition, "View Details"});
      }

      stmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    table.setModel(tableModel);
    table.setBounds(30, 80, 500, 300);
    historyMenu.add(table);

    table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
    table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JButton(), table));


    JButton backButton = new JButton("back");
    backButton.setBounds(260, 400, 90, 30);
    historyMenu.add(backButton);

    backButton.addActionListener(e -> {
      historyMenu.dispose();
      new MainMenu();
    });

    historyMenu.setVisible(true);
  }

  private static class ButtonRenderer extends javax.swing.JButton implements javax.swing.table.TableCellRenderer {
    public ButtonRenderer() {
      setOpaque(true);
    }

    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      if (isSelected) {
        setBackground(table.getSelectionBackground());
      } else {
        setBackground(table.getBackground());
      }
      setText("pengiriman");
      return this;
    }
  }









  //button editor
  private static class ButtonEditor extends javax.swing.DefaultCellEditor {
    protected JButton button;
    private JTable table;



    public ButtonEditor(JButton button, JTable table) {
      super(new javax.swing.JTextField());
      this.table = table;
      this.button = button;
      this.button.setOpaque(true);
      this.button.addActionListener(e -> {
        int row = table.getSelectedRow();
        int transactionId = (Integer) table.getValueAt(row, 0);
        showDeliveryDetails(transactionId); 
      });
    }




    public java.awt.Component getTableCellEditorComponent(javax.swing.JTable table, Object value, boolean isSelected, int row, int column) {
      if (isSelected) {

        button.setBackground(table.getSelectionBackground());
      } else {

        button.setBackground(table.getBackground());
      }
      return button;
    }

    public void cancelCellEditing() {
      super.cancelCellEditing();
    }


  }







  public static void showDeliveryDetails(int  transactionId) {
    //FRAME

    JFrame detailFrame = new JFrame("Detil vdelivery ");
    detailFrame.setSize(600, 400) ;
    detailFrame.setLayout(null );
    detailFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    int x = (Toolkit.getDefaultToolkit().getScreenSize().width - detailFrame.getWidth()) / 2;
    int y = (Toolkit.getDefaultToolkit().getScreenSize().height - detailFrame.getHeight()) / 2;
    detailFrame.setLocation(x, y);

    JLabel titleLabel = new JLabel("Detail Pengiriman");
    titleLabel.setBounds(200, 30, 200, 30);
    titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
    detailFrame.add(titleLabel);




















    try {
      Connection conn = DBConnection.getInstance().getConnection();
      String query = "SELECT * FROM delivery_status WHERE transaction_id = " + transactionId;
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(query);

      if (rs.next()) {
        String status = rs.getString("status");
        String currentPosition = rs.getString("current_position ");
        String evidence = rs.getString("evidence");
        String updatedBy = rs.getString("updated_by");

        JLabel statusLabel = new JLabel("Status : " + status);
        statusLabel.setBounds(30, 80, 500, 30);
        detailFrame.add(statusLabel);

        JLabel currentPositionLabel = new JLabel("Current Position : " + currentPosition);
        currentPositionLabel.setBounds(30, 120, 500, 30);
        detailFrame.add(currentPositionLabel);

        JLabel evidenceLabel = new JLabel("Evidenc : " + evidence);
        evidenceLabel.setBounds(30, 160, 500, 30);
        detailFrame.add(evidenceLabel);

        JLabel updatedByLabel = new JLabel("Updated By:" + updatedBy);
        updatedByLabel.setBounds(30, 200, 500, 30);
        detailFrame.add(updatedByLabel);
      }

      stmt.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    detailFrame.setVisible(true);
  }
}
