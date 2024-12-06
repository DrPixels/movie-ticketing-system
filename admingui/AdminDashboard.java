package admingui;
import Database.AdminDatabaseManager;
import Model.AdminEmployee;
import Model.Authentication;
import helper.Helper;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

import logingui.LoginPage;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;


public class AdminDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout rightCardLayout;
	
	private JPanel leftDashboardPanel;
	private JPanel rightDashboardPanel;
	
        private AdminEmployee adminData = AdminDatabaseManager.retrieveAdminDataById(Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID);
        
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminDashboard() {
		setResizable(false);
		setTitle("Admin's Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 730);
		getContentPane().setLayout(null);
		
		leftDashboardPanel = new JPanel();
		leftDashboardPanel.setBackground(new Color(17, 24, 39));
		leftDashboardPanel.setBounds(0, 0, 200, 711);
		leftDashboardPanel.setLayout(null);
		
		JLabel profilePictureLabel = new JLabel();
		profilePictureLabel.setBounds(45, 20, 110, 110);
                profilePictureLabel.setPreferredSize(new Dimension(110, 110));
		leftDashboardPanel.add(profilePictureLabel);
                
                Helper.setImageToLabel(profilePictureLabel, adminData.getPicturePath());
		
		JLabel helloLabel = new JLabel("Hello, " + adminData.getFirstName());
		helloLabel.setForeground(new Color(255, 255, 255));
		helloLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setBounds(0, 134, 200, 16);
		leftDashboardPanel.add(helloLabel);
		
		JButton manageMoviesButton = new JButton("Manage Movies");
		manageMoviesButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		manageMoviesButton.setBorder(UIManager.getBorder("Button.border"));
		manageMoviesButton.setForeground(new Color(255, 255, 255));
		manageMoviesButton.setBackground(new Color(17, 24, 39));
		manageMoviesButton.setBounds(20, 220, 160, 25);
                manageMoviesButton.setFocusable(false);
		leftDashboardPanel.add(manageMoviesButton);
		
		JButton reportsButton = new JButton("Reports");
		reportsButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		reportsButton.setForeground(new Color(255, 255, 255));
		reportsButton.setBackground(new Color(17, 24, 39));
		reportsButton.setBounds(20, 260, 160, 25);
                reportsButton.setFocusable(false);
		leftDashboardPanel.add(reportsButton);
		
		JButton manageStaffButton = new JButton("Manage Staff");
		manageStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		manageStaffButton.setForeground(new Color(255, 255, 255));
		manageStaffButton.setBackground(new Color(17, 24, 39));
		manageStaffButton.setBounds(20, 300, 160, 25);
                manageStaffButton.setFocusable(false);
		leftDashboardPanel.add(manageStaffButton);
                
                JButton editProfileButton = new JButton("Edit Profile");
		editProfileButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		editProfileButton.setForeground(new Color(255, 255, 255));
		editProfileButton.setBackground(new Color(17, 24, 39));
		editProfileButton.setBounds(20, 340, 160, 25);
                editProfileButton.setFocusable(false);
		leftDashboardPanel.add(editProfileButton);
                
                editProfileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create a custom dialog
                        EditAdminProfileDialog editAdminProfileDialog = new EditAdminProfileDialog();

                        editAdminProfileDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
                        editAdminProfileDialog.setVisible(true);
                        
                        // Add a WindowListener to monitor disposal events
                        editAdminProfileDialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                System.out.println("hey");
                                adminData = AdminDatabaseManager.retrieveAdminDataById(Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID);
                                
                                ImageIcon iconImage = new ImageIcon(adminData.getPicturePath());
                                Image originalImage = iconImage.getImage();
                                Image scaledImage = originalImage.getScaledInstance(profilePictureLabel.getWidth(), profilePictureLabel.getHeight(), Image.SCALE_SMOOTH);
                                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                                profilePictureLabel.setIcon(scaledImageIcon);
                                
                                helloLabel.setText("Hello, " + adminData.getFirstName());
                                
                                leftDashboardPanel.revalidate();
                                leftDashboardPanel.repaint();
                            }
                        });
                    }
                });
		
		JButton aboutButton = new JButton("About");
		aboutButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		aboutButton.setForeground(new Color(255, 255, 255));
		aboutButton.setBackground(new Color(17, 24, 39));
		aboutButton.setBounds(20, 380, 160, 25);
                aboutButton.setFocusable(false);
		leftDashboardPanel.add(aboutButton);
                
		JButton logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
        		
        		JFrame loginPage = new LoginPage();
        		loginPage.setVisible(true);
			}
		});
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setBackground(new Color(17, 24, 39));
		logoutButton.setBounds(20, 440, 160, 25);
                logoutButton.setFocusable(false);
		leftDashboardPanel.add(logoutButton);
		
		JLabel logoLabel = new JLabel();
		logoLabel.setBounds(75, 630, 50, 50);
                logoLabel.setPreferredSize(new Dimension(50, 50));
                Helper.setImageToLabel(logoLabel, Helper.DEFAULT_MOVIE_LOGO_W_O_NAME_PATH);
		leftDashboardPanel.add(logoLabel);         
		
		rightDashboardPanel = new JPanel();
		rightDashboardPanel.setBackground(new Color(211, 211, 211));
		rightDashboardPanel.setBounds(199, 0, 1011, 691);
		
		rightCardLayout = new CardLayout();
		rightDashboardPanel.setLayout(rightCardLayout);
		
		JPanel manageMovies = new ManageMovies();
		manageMoviesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Manage Movies"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		JPanel manageStaff = new ManageStaff();
		manageStaffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Manage Staff"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		JPanel reports = new Reports();
		reportsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Reports"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		JPanel about = new About();
		aboutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "About"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		rightDashboardPanel.add(manageMovies, "Manage Movies");
		rightDashboardPanel.add(manageStaff, "Manage Staff");
		rightDashboardPanel.add(reports, "Reports");
		rightDashboardPanel.add(about, "About");	
		
		add(leftDashboardPanel);
		add(rightDashboardPanel);
	}
}
