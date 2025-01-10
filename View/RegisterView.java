package View;

import Controller.RegisterController;
import javax.swing.*;
import java.awt.*;

public class RegisterView {
    public RegisterView() {
        showRegister();
    }

    public void showRegister() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        JFrame registerFrame = new JFrame("Register");
        registerFrame.setLayout(null);
        registerFrame.setSize(400, 350);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = (screenSize.width - registerFrame.getWidth()) / 2;
        int y = (screenSize.height - registerFrame.getHeight()) / 2;
        registerFrame.setLocation(x, y);
        
        JLabel title = new JLabel("Register");
        title.setBounds(150, 20, 100, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        registerFrame.add(title);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 100, 25);
        registerFrame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 80, 200, 25);
        registerFrame.add(nameField);

        // Phone
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(50, 120, 100, 25);
        registerFrame.add(phoneLabel);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(150, 120, 200, 25);
        registerFrame.add(phoneField);

        // Alamat
        JLabel alamatLabel = new JLabel("Alamat:");
        alamatLabel.setBounds(50, 160, 100, 25);
        registerFrame.add(alamatLabel);

        JTextField alamatField = new JTextField();
        alamatField.setBounds(150, 160, 200, 25);
        registerFrame.add(alamatField);

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 200, 100, 25);
        registerFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 200, 25);
        registerFrame.add(passwordField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 250, 90, 30);
        registerFrame.add(registerButton);
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String alamat = alamatField.getText();
            String password = new String(passwordField.getPassword());

            // Validasi input
            if (name.isEmpty() || phone.isEmpty() || password.isEmpty() || alamat.isEmpty()) {
                JOptionPane.showMessageDialog(registerFrame, "Nama, no telp , alamat, Password gaboleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!phone.matches("\\d{10,12}")) {
                JOptionPane.showMessageDialog(registerFrame, "Invalid phone format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                RegisterController controller = new RegisterController();
                boolean success = controller.registerUser(name, password, phone, alamat);

                if (success) {
                    JOptionPane.showMessageDialog(registerFrame, "register succes!", "success", JOptionPane.INFORMATION_MESSAGE);
                    registerFrame.dispose();
                    new MainMenu(); // Kembali ke MainMenu setelah registrasi berhasil
                } else {
                    JOptionPane.showMessageDialog(registerFrame, "name/phone exist already", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(260, 250, 90, 30);
        registerFrame.add(backButton);

        backButton.addActionListener(e -> {
            registerFrame.dispose();
            new MainMenu(); // Kembali ke MainMenu
        });

        registerFrame.setVisible(true);
    }
}
