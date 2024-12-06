package staffgui;
import Database.AdminDatabaseManager;
import Model.StaffEmployee;
import Model.Authentication;
import admingui.About;
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
import java.awt.CardLayout;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import logingui.LoginPage;


public class StaffDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel leftDashboardPanel;
	private CardLayout rightCardLayout;
	private JPanel rightDashboardPanel;
	
	private StaffEmployee staffData = AdminDatabaseManager.retrieveStaffDataById(Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffDashboard frame = new StaffDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public StaffDashboard() {
            
		setResizable(false);
		setTitle("Staff's Dashboard");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1225, 730);
		getContentPane().setLayout(null);
		
		leftDashboardPanel = new JPanel();
		leftDashboardPanel.setBackground(new Color(15, 23, 42));
		leftDashboardPanel.setBounds(0, 0, 200, 691);
		leftDashboardPanel.setLayout(null);
		
		JLabel profilePictureLabel = new JLabel();
		profilePictureLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		profilePictureLabel.setBounds(45, 20, 110, 110);
                profilePictureLabel.setPreferredSize(new Dimension(110, 110));
                
                ImageIcon iconImage = new ImageIcon(staffData.getPicturePath());
                Image originalImage = iconImage.getImage();
                Image scaledImage = originalImage.getScaledInstance(profilePictureLabel.getWidth(), profilePictureLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                profilePictureLabel.setIcon(scaledImageIcon);
		leftDashboardPanel.add(profilePictureLabel);
		
		JLabel helloLabel = new JLabel("Hello, " + staffData.getFirstName() + "!");
		helloLabel.setForeground(new Color(255, 255, 255));
		helloLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setBounds(0, 134, 200, 14);
		leftDashboardPanel.add(helloLabel);
		
		JButton bookingButton = new JButton("Booking");
                bookingButton.setFocusable(false);
		bookingButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		bookingButton.setBorder(UIManager.getBorder("Button.border"));
		bookingButton.setForeground(new Color(255, 255, 255));
		bookingButton.setBackground(new Color(15, 23, 42));
		bookingButton.setBounds(20, 220, 160, 25);
		leftDashboardPanel.add(bookingButton);
		
		JButton bookingHistoryButton = new JButton("Booking History");
                bookingHistoryButton.setFocusable(false);
		bookingHistoryButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		bookingHistoryButton.setForeground(new Color(255, 255, 255));
		bookingHistoryButton.setBackground(new Color(15, 23, 42));
		bookingHistoryButton.setBounds(20, 260, 160, 25);
		leftDashboardPanel.add(bookingHistoryButton);
                
                JButton aboutButton = new JButton("About");
		aboutButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		aboutButton.setForeground(new Color(255, 255, 255));
		aboutButton.setBackground(new Color(17, 24, 39));
		aboutButton.setBounds(20, 300, 160, 25);
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
		logoutButton.setBackground(new Color(15, 23, 42));
		logoutButton.setBounds(20, 400, 160, 25);
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
		
		JPanel booking = new Booking(this);
		bookingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Booking"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		JPanel bookingHistory = new BookingHistory(this);
		bookingHistoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Booking History"); // Show Movies panel
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
		

		rightDashboardPanel.add(booking, "Booking");
		rightDashboardPanel.add(bookingHistory, "Booking History");
                rightDashboardPanel.add(about, "About");
//		rightDashboardPanel.add(manageStaff, "Manage Staff");
		
		add(leftDashboardPanel);
		add(rightDashboardPanel);
	}
}
