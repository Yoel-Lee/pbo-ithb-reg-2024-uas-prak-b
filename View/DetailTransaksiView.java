package View;

import javax.swing.*;
import java.awt.*;

import Controller.DTController;
import Controller.DTController;

public class DetailTransaksiView{
    private JTextField transactionIdField;
    private JTextField currentPositionField;
    private JTextField evidenceField;
    private JComboBox<String> statusComboBox;
    private JTextField updatedByField;


    public DetailTransaksiView() {
        TambahTransaksi();
    }

    public void TambahTransaksi() {
        
        JFrame tambahMenu = new JFrame("Add Delivery Status");
        tambahMenu.setLayout(null);
        tambahMenu.setSize(500, 400);
        tambahMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - tambahMenu.getWidth()) / 2;
        int y = (screenSize.height - tambahMenu.getHeight()) / 2;
        tambahMenu.setLocation(x, y);

        
        JLabel label = new JLabel("Add Delivery Status");
        label.setBounds(190, 30, 200, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        tambahMenu.add(label);


        JLabel transactionIdLabel = new JLabel("transaction ID:");
        transactionIdLabel.setBounds(30, 70, 150, 25);
        tambahMenu.add(transactionIdLabel);
         transactionIdField = new JTextField();
        transactionIdField.setBounds(150, 70, 200, 25);
        tambahMenu.add(transactionIdField);

        
        JLabel statusLabel = new JLabel("status:");
         statusLabel.setBounds(30, 110, 150, 25);
        tambahMenu.add(statusLabel);
        statusComboBox = new JComboBox<>(new String[]{"PENDING", "IN_PROGRESS", "ON_DELIVERY", "ARRIVED"});
        statusComboBox.setBounds(150, 110, 200, 25);
        tambahMenu.add(statusComboBox);

        
        JLabel currentPositionLabel = new JLabel("current posisi:");
        currentPositionLabel.setBounds(30, 150, 150, 25);
        tambahMenu.add(currentPositionLabel);
        currentPositionField = new JTextField();
        currentPositionField.setBounds(150, 150, 200, 25);
         tambahMenu.add(currentPositionField);



        
        JLabel evidenceLabel = new JLabel("evidence :");
        evidenceLabel.setBounds(30, 190, 150, 25);
        tambahMenu.add(evidenceLabel);
          evidenceField = new JTextField();
        evidenceField.setBounds(150, 190, 200, 25);
        tambahMenu.add(evidenceField);




        JLabel updatedByLabel = new JLabel("uupdated By:");
        updatedByLabel.setBounds(30, 230, 150, 25);
        tambahMenu.add(updatedByLabel);
         updatedByField = new JTextField();
        updatedByField.setBounds(150, 230, 200, 25);
        tambahMenu.add(updatedByField);




        
        JButton saveButton = new JButton("save");
        saveButton.setBounds(150, 270, 100, 30);
        tambahMenu.add(saveButton);
        saveButton.addActionListener(e -> saveDeliveryStatus());

        JButton backButton = new JButton("Back");
        backButton.setBounds(270, 270, 100, 30);
        tambahMenu.add(backButton);
        
        backButton.addActionListener(e -> {
            tambahMenu.dispose();
            new MainMenu(); 
        });
        tambahMenu.setVisible(true);
    }





    
    private void saveDeliveryStatus() {
        
        String transactionId = transactionIdField.getText();
        String status = (String) statusComboBox.getSelectedItem();
        String currentPosition = currentPositionField.getText();
        String evidence = evidenceField.getText();
        String updatedBy = updatedByField.getText();




        if (transactionId.isEmpty() || currentPosition.isEmpty() || evidence.isEmpty() || updatedBy.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fields ada yang kosong");
            return;
        }

        
        DTController controller = new DTController();
        boolean success = controller.saveDeliveryStatus(
                Integer.parseInt(transactionId), 
                status, 
                currentPosition, 
                evidence,
                updatedBy);

        if (success) {
            JOptionPane.showMessageDialog(null, "Data berhasil di insert");
        } else {
            JOptionPane.showMessageDialog(null, "Data gagal diinsert");
        }
    }

    public static void main(String[] args) {
        new DetailTransaksiView();
    }
}
