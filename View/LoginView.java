package View;

import javax.swing.*;
import Controller.LoginController;
import Controller.SessionManager;

import java.awt.*;
import Controller.SessionManager;

public class LoginView {
    public LoginView() {
        showLogin();
    }

    public void showLogin() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        JFrame loginFrame = new JFrame("Login");
        loginFrame.setLayout(null);
        loginFrame.setSize(400, 400);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int x = (screenSize.width - loginFrame.getWidth()) / 2;
        int y = (screenSize.height - loginFrame.getHeight()) / 2;
        loginFrame.setLocation(x, y);


        JLabel logoLabel = new JLabel(new ImageIcon("pratama.jpg")); 
        logoLabel.setBounds(140, 40, 120, 120); 
        loginFrame.add(logoLabel);







        JLabel title = new JLabel("Login Pratama Delivery");
        title.setBounds(47, 180, 1000, 30);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        loginFrame.add(title);



        JLabel noTelpLabel = new JLabel("noTelp:");
        noTelpLabel.setBounds(50, 220, 100, 25);
        loginFrame.add(noTelpLabel);

        JTextField noTelpField = new JTextField();
        noTelpField.setBounds(150, 220, 200, 25);
        loginFrame.add(noTelpField);




        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 260, 100, 25);
        loginFrame.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 260, 200, 25);
        loginFrame.add(passwordField);




        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 300, 90, 30);
        loginFrame.add(loginButton);



        loginButton.addActionListener(e -> {
            String noTelp = noTelpField.getText();
            String password = new String(passwordField.getPassword());

            if (noTelp.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginFrame, "Name atau Password tidak boleh kosong", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                LoginController controller = new LoginController();
                boolean success = controller.loginUser(noTelp, password);

                if (success) {
                    JOptionPane.showMessageDialog(loginFrame, "Welcome", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    
                    SessionManager.setLoggedIn(true);

                    
                    loginFrame.dispose();
                    new MainMenu(); 
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Notelp atau password salah", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(260, 300, 90, 30);
        loginFrame.add(backButton);

        backButton.addActionListener(e -> {
            loginFrame.dispose();
            new MainMenu(); 
        });

        loginFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginView();
    }
}
