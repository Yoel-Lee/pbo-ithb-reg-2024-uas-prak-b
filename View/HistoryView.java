package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;


public class HistoryView {

    public HistoryView(){
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


    JButton detailTransaksiButton = new JButton("Detail Transaksi Pengiriman");
    detailTransaksiButton.setBounds(150, 150, 200, 40);
    detailTransaksiButton.setFont(new Font("SansSerif", Font.BOLD, 16));
    detailTransaksiButton.setBackground(new Color(208, 44, 49));
    detailTransaksiButton.setForeground(Color.WHITE);
    mainMenu.add(detailTransaksiButton);

    
    JButton historyPengirimanButton = new JButton("History Pengiriman");
    historyPengirimanButton.setBounds(150, 200, 200, 40);
    historyPengirimanButton.setFont(new Font("SansSerif", Font.BOLD, 16));
    historyPengirimanButton.setBackground(new Color(19, 149, 250));
    historyPengirimanButton.setForeground(Color.WHITE);
    mainMenu.add(historyPengirimanButton);

    
    
    
    detailTransaksiButton.addActionListener(e -> {
        mainMenu.dispose();
        new DetailTransaksiView();
    });
    
        
    historyPengirimanButton.addActionListener(e -> {
        mainMenu.dispose();
        new HistoryPengirimanView();
    });
    
    JButton backButton = new JButton("Back");
        backButton.setBounds(260, 280, 90, 30);
        mainMenu.add(backButton);

        backButton.addActionListener(e -> {
            mainMenu.dispose();
            new MainMenu(); // Kembali ke Main Menu
        });

        mainMenu.setVisible(true);
}

}
