package staffgui;
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
import javax.swing.border.LineBorder;

import logingui.LoginPage;


public class StaffDashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel leftDashboardPanel;
	private CardLayout rightCardLayout;
	private JPanel rightDashboardPanel;
	
	

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
		
		JPanel profilePicturePanel = new JPanel();
		profilePicturePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		profilePicturePanel.setBounds(45, 20, 110, 110);
		leftDashboardPanel.add(profilePicturePanel);
		
		JLabel helloLabel = new JLabel("Hello, Name!");
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
		logoLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		logoLabel.setBounds(75, 630, 50, 50);
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
		

		rightDashboardPanel.add(booking, "Booking");
		rightDashboardPanel.add(bookingHistory, "Booking History");
//		rightDashboardPanel.add(manageStaff, "Manage Staff");
		
		add(leftDashboardPanel);
		add(rightDashboardPanel);
	}
}
