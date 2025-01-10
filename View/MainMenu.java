package View;

import javax.swing.*;
import Connection.DBConnection;
import Controller.SessionManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainMenu {

    private DBConnection dbConnection;

    public MainMenu() {
        showMenu();
    }

    public void showMenu() {
        // Kode showMenu()
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        JFrame mainMenu = new JFrame("Main Menu");
        mainMenu.setLayout(null);
        mainMenu.setSize(500, 400);
        mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = (screenSize.width - mainMenu.getWidth()) / 2;
        int y = (screenSize.height - mainMenu.getHeight()) / 2;
        mainMenu.setLocation(x, y);

        JLabel label = new JLabel("MAIN MENU");
        label.setBounds(190, 60, 200, 30);
        label.setFont(new Font("SansSerif", Font.BOLD, 20));
        mainMenu.add(label);

        JLabel title2 = new JLabel("Pratama Delivery");
        title2.setBounds(110, 15, 1000, 50);
        title2.setFont(new Font("SansSerif", Font.BOLD, 36));
        mainMenu.add(title2);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 200, 40);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setBackground(new Color(208, 44, 49));
        loginButton.setForeground(Color.WHITE);
        mainMenu.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(150, 200, 200, 40);
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        registerButton.setBackground(new Color(19, 149, 250));
        registerButton.setForeground(Color.WHITE);
        mainMenu.add(registerButton);

        JButton tambahTransaksiButton = new JButton("Add Transaction");
        tambahTransaksiButton.setBounds(150, 250, 200, 40);
        tambahTransaksiButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        tambahTransaksiButton.setBackground(new Color(49, 79, 50));
        tambahTransaksiButton.setForeground(Color.WHITE);
        mainMenu.add(tambahTransaksiButton);

        JButton historyButton = new JButton("History");
        historyButton.setBounds(150, 300, 200, 40);
        historyButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        historyButton.setBackground(new Color(0, 149, 50));
        historyButton.setForeground(Color.WHITE);
        mainMenu.add(historyButton);

        
        loginButton.addActionListener(e -> {
            mainMenu.dispose();
            new LoginView();
        });

        
        registerButton.addActionListener(e -> {
            mainMenu.dispose();
            new RegisterView();
        });

        
        tambahTransaksiButton.addActionListener(e -> {
            if (SessionManager.isLoggedIn()) {
                mainMenu.dispose();
                new TambahTransaksiView();
            } else {
                JOptionPane.showMessageDialog(mainMenu, "Login / register dulu bos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        historyButton.addActionListener(e -> {
            if (SessionManager.isLoggedIn()) {
                mainMenu.dispose();
                new HistoryView();
            } else {
                JOptionPane.showMessageDialog(mainMenu, "Login / register dulu bos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainMenu.setVisible(true);
    }
}
