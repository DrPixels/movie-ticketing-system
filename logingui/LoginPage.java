package logingui;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

    public LoginPage() {
        setSize(700, 400);
        setResizable(false);
        setTitle("Login");
        setLayout(null);
        
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(214, 217, 223));
        leftPanel.setBounds(334, 0, 350, 361);
        
        leftPanel.setLayout(null);
//        leftPanel.setBackground(Color.red);
        
        JLabel welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 25, 350, 30);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usernameLabel.setBounds(40, 120, 90, 20);
        
        JTextField usernameTF = new JTextField();
        usernameTF.setBounds(40, 145, 270, 30);
        
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setBounds(40, 200, 90, 20);
        JPasswordField passwordTF = new JPasswordField();
        passwordTF.setBounds(40, 225, 270, 30);
        
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loginButton.setBounds(130, 280, 80, 30 );
        
        leftPanel.add(welcomeLabel);
        leftPanel.add(usernameLabel);
        leftPanel.add(usernameTF);
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordTF);
        leftPanel.add(loginButton);
   
        JButton registerAsAdminButton = new JButton("Register as Admin?");
        registerAsAdminButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		dispose();
        		
        		JFrame registerAdmin = new RegisterAdmin();
        		registerAdmin.setVisible(true);
        	}
        });
        registerAsAdminButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
        registerAsAdminButton.setBackground(new Color(214, 217, 223));
        registerAsAdminButton.setBounds(100, 320, 150, 30);
        leftPanel.add(registerAsAdminButton);
        
        JPanel rightLabel = new JPanel();
        rightLabel.setBounds(0, 0, 334, 361);
        rightLabel.setBackground(Color.red);
        
        add(leftPanel);
        add(rightLabel);
        
        
    }
        

    public static void main(String[] args) {

//        try {
//            // Set Nimbus Look and Feel
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();  // Print any errors if the Nimbus look and feel can't be set
//        }

        // Create and show the login page
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
}
