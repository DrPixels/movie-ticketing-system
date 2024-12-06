package logingui;


import Database.AuthenticateDatabaseManager;
import Database.DatabaseManager;
import Model.Authentication;
import Model.AuthenticationStatus;
import Model.Credential;
import admingui.AdminDashboard;
import helper.Helper;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import staffgui.StaffDashboard;

public class LoginPage extends JFrame {
    
    private JPanel leftPanel;
    private JLabel welcomeLabel;
    private JLabel usernameLabel;
    private JTextField usernameTF;
    private JPasswordField passwordTF;
    private JLabel passwordLabel;
    private JButton loginButton;
    private JButton registerAsAdminButton;
    private JPanel rightPanel;
    

    public LoginPage() {
        setSize(700, 400);
        setResizable(false);
        setTitle("Login");
        setLayout(null);
        
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(214, 217, 223));
        leftPanel.setBounds(334, 0, 350, 361);
        
        leftPanel.setLayout(null);
//        leftPanel.setBackground(Color.red);
        
        welcomeLabel = new JLabel("Welcome Back!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 25, 350, 30);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        
        usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        usernameLabel.setBounds(40, 120, 90, 20);
        
        usernameTF = new JTextField();
        usernameTF.setBounds(40, 145, 270, 30);
        usernameTF.setFont(Helper.fontForTF);
        
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        passwordLabel.setBounds(40, 200, 90, 20);
        
        passwordTF = new JPasswordField();
        passwordTF.setBounds(40, 225, 270, 30);
        passwordTF.setFont(Helper.fontForTF);
        
        usernameTF.setText("Staff");
        passwordTF.setText("Staff");
        
        JToggleButton showPasswordButton = new JToggleButton("Show");
        showPasswordButton.setFocusable(false);
        showPasswordButton.setBounds(115, 201, 60, 20);
        
        // Add action listener to toggle password visibility
        showPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordButton.isSelected()) {
                    passwordTF.setEchoChar((char) 0); // Show password
                    showPasswordButton.setText("Hide");
                } else {
                    passwordTF.setEchoChar('*'); // Hide password
                    showPasswordButton.setText("Show");
                }
            }
        });
        
        
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        loginButton.setBounds(130, 280, 80, 30 );
        loginButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		if(!isFieldsValid()) {
                            JOptionPane.showMessageDialog(LoginPage.this, "Missing fields. Please try again.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        
                        String username = usernameTF.getText();
                        String password = passwordTF.getText();
                        Credential credential = new Credential(username, password);
                        AuthenticationStatus authentication = AuthenticateDatabaseManager.verifyAuthentication(credential);
                        
                        if(!authentication.isStatus()) {
                            JOptionPane.showMessageDialog(LoginPage.this, "Invalid Credentials. Please try again.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                        
                        Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID = authentication.getEmployeeId();
                        
                        if(authentication.getRole().equals("ADMIN")) {
                            dispose();
                            
                            AdminDashboard adminDashboard = new AdminDashboard();
                            adminDashboard.setVisible(true);
                        } else {
                            dispose();
                            
                            StaffDashboard staffDashboard = new StaffDashboard();
                            staffDashboard.setVisible(true);
                        }
                        
                        
                        
        	}
        });
        
        leftPanel.add(welcomeLabel);
        leftPanel.add(usernameLabel);
        leftPanel.add(usernameTF);
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordLabel);
        leftPanel.add(passwordTF);
        leftPanel.add(loginButton);
        leftPanel.add(showPasswordButton);
   
        registerAsAdminButton = new JButton("Register as Admin?");
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
        
        JLabel rightLabel = new JLabel();
        rightLabel.setBounds(0, 0, 334, 361);
        rightLabel.setBackground(Color.red);
        
        ImageIcon iconImage = new ImageIcon(Helper.DEFAULT_MOVIE_LOGO_W_NAME_PATH);
        Image originalImage = iconImage.getImage();
        Image scaledImage = originalImage.getScaledInstance(rightLabel.getWidth(), rightLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        rightLabel.setIcon(scaledImageIcon);
        
        add(leftPanel);
        add(rightLabel);
        
    }
        

    public static void main(String[] args) {

        try {
            // Set Nimbus Look and Feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();  // Print any errors if the Nimbus look and feel can't be set
        }

        // Create and show the login page
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }
    
    private boolean isFieldsValid() {
        return !(usernameTF.getText().isEmpty() || passwordTF.getText().isEmpty());
    }
}
