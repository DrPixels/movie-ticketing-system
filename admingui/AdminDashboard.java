package admingui;
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


public class AdminDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private CardLayout rightCardLayout;
	
	private JPanel leftDashboardPanel;
	private JPanel rightDashboardPanel;
	

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
		
		JPanel profilePicturePanel = new JPanel();
		profilePicturePanel.setBounds(45, 20, 110, 110);
		leftDashboardPanel.add(profilePicturePanel);
		
		JLabel helloLabel = new JLabel("Hello, Name!");
		helloLabel.setForeground(new Color(255, 255, 255));
		helloLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setBounds(0, 134, 200, 14);
		leftDashboardPanel.add(helloLabel);
		
		JButton manageMoviesButton = new JButton("Manage Movies");
		manageMoviesButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		manageMoviesButton.setBorder(UIManager.getBorder("Button.border"));
		manageMoviesButton.setForeground(new Color(255, 255, 255));
		manageMoviesButton.setBackground(new Color(17, 24, 39));
		manageMoviesButton.setBounds(20, 220, 160, 25);
		leftDashboardPanel.add(manageMoviesButton);
		
		JButton reportsButton = new JButton("Reports");
		reportsButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		reportsButton.setForeground(new Color(255, 255, 255));
		reportsButton.setBackground(new Color(17, 24, 39));
		reportsButton.setBounds(20, 260, 160, 25);
		leftDashboardPanel.add(reportsButton);
		
		JButton manageStaffButton = new JButton("Manage Staff");
		manageStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		manageStaffButton.setForeground(new Color(255, 255, 255));
		manageStaffButton.setBackground(new Color(17, 24, 39));
		manageStaffButton.setBounds(20, 300, 160, 25);
		leftDashboardPanel.add(manageStaffButton);
		
		JButton aboutButton = new JButton("About");
		aboutButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		aboutButton.setForeground(new Color(255, 255, 255));
		aboutButton.setBackground(new Color(17, 24, 39));
		aboutButton.setBounds(20, 340, 160, 25);
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
		logoutButton.setBounds(20, 400, 160, 25);
		leftDashboardPanel.add(logoutButton);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(75, 630, 50, 50);
		leftDashboardPanel.add(logoPanel);
		
		rightDashboardPanel = new JPanel();
		rightDashboardPanel.setBackground(new Color(211, 211, 211));
		rightDashboardPanel.setBounds(199, 0, 1011, 691);
		
		rightCardLayout = new CardLayout();
		rightDashboardPanel.setLayout(rightCardLayout);
		
		JPanel manageMovies = new ManageMovies(this);
		manageMoviesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Manage Movies"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		JPanel manageStaff = new ManageStaff(this);
		manageStaffButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Manage Staff"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		JPanel reports = new Reports(this);
		reportsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightCardLayout.show(rightDashboardPanel, "Reports"); // Show Movies panel
				rightDashboardPanel.revalidate();
				rightDashboardPanel.repaint();
			}
		});
		
		JPanel about = new About(this);
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
