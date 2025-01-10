package View;


import Connection.DBConnection;
import Controller.TTController;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class TambahTransaksiView {

    private JComboBox<String> kategoriComboBox;
    private JTextField namaField, alamatField, noTelpField, beratField;

    public TambahTransaksiView() {
        TambahTransaksi();
    }

    public void TambahTransaksi() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        JFrame tambahMenu = new JFrame("Add Transaction");
        tambahMenu.setLayout(null);
        tambahMenu.setSize(500, 500);
        tambahMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = (screenSize.width - tambahMenu.getWidth()) / 2;
        int y = (screenSize.height - tambahMenu.getHeight()) / 2;
        tambahMenu.setLocation(x, y);

        JLabel label = new JLabel("Add Transaction");
        label.setBounds(190, 60, 200, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        tambahMenu.add(label);

        
        JLabel namaLabel = new JLabel("Nama Penerima:");
        namaLabel.setBounds(50, 100, 150, 25);
        tambahMenu.add(namaLabel);

        namaField = new JTextField();
        namaField.setBounds(200, 100, 200, 25);
        tambahMenu.add(namaField);

        JLabel alamatLabel = new JLabel("Alamat:");
        alamatLabel.setBounds(50, 140, 150, 25);
        tambahMenu.add(alamatLabel);

        alamatField = new JTextField();
        alamatField.setBounds(200, 140, 200, 25);
        tambahMenu.add(alamatField);

        JLabel noTelpLabel = new JLabel("No Telp Penerima:");
        noTelpLabel.setBounds(50, 180, 150, 25);
        tambahMenu.add(noTelpLabel);

        noTelpField = new JTextField();
        noTelpField.setBounds(200, 180, 200, 25);
        tambahMenu.add(noTelpField);

        JLabel beratLabel = new JLabel("Berat Pengiriman (kg):");
        beratLabel.setBounds(50, 220, 150, 25);
        tambahMenu.add(beratLabel);

        beratField = new JTextField();
        beratField.setBounds(200, 220, 200, 25);
        tambahMenu.add(beratField);

        JLabel kategoriLabel = new JLabel("Kategori Pengiriman:");
        kategoriLabel.setBounds(50, 260, 150, 25);
        tambahMenu.add(kategoriLabel);


        kategoriComboBox = new JComboBox<>();
        kategoriComboBox.setBounds(200, 260, 200, 25);
        tambahMenu.add(kategoriComboBox);

        
        loadCategories();

        
        JButton simpanButton = new JButton("Simpan");
        simpanButton.setBounds(100, 300, 150, 30);
        simpanButton.setBackground(new Color(49, 79, 50));
        simpanButton.setForeground(Color.WHITE);
        tambahMenu.add(simpanButton);

        simpanButton.addActionListener(e -> {
            
            String nama = namaField.getText();
            String alamat = alamatField.getText();
            String noTelp = noTelpField.getText();
            String beratStr = beratField.getText();
            String kategori = (String) kategoriComboBox.getSelectedItem();


            if (nama.isEmpty() || alamat.isEmpty() || noTelp.isEmpty() || beratStr.isEmpty() || kategori == null) {
                JOptionPane.showMessageDialog(tambahMenu, "Semua field harus diisi", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    double berat = Double.parseDouble(beratStr);
                    if (berat <= 0) {
                        JOptionPane.showMessageDialog(tambahMenu, "berat  tidak boleh 0 ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        
                        double fee = getCategoryFee(kategori);
                        double cost = berat * fee;


                        TTController controller = new TTController();
                        boolean success = controller.saveTransaction(nama, alamat, noTelp, berat, kategori, cost);

                        if (success) {
                            JOptionPane.showMessageDialog(tambahMenu, "Transaksi tersimpan", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(tambahMenu, "transaksi gagal disimpan", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(tambahMenu, "berat haruSs angka", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        
        JButton backButton = new JButton("Back");
        backButton.setBounds(260, 300, 150, 30);
        backButton.setBackground(new Color(200, 4, 49));
        backButton.setForeground(Color.WHITE);
        tambahMenu.add(backButton);

        backButton.addActionListener(e -> {
            tambahMenu.dispose();
            new MainMenu(); 
        });

        tambahMenu.setVisible(true);
    }

    private void loadCategories() {
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "SELECT kategori FROM kategori_pengiriman";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            Vector<String> categories = new Vector<>();
            while (resultSet.next()) {
                categories.add(resultSet.getString("kategori"));
            }
            kategoriComboBox.setModel(new DefaultComboBoxModel<>(categories));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private double getCategoryFee(String category) {
        double fee = 0;
        try (Connection connection = DBConnection.getInstance().getConnection()) {
            String query = "SELECT fee FROM kategori_pengiriman WHERE kategori = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                fee = resultSet.getDouble("fee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fee;
    }

    public static void main(String[] args) {
        new TambahTransaksiView();

    }
}
