package staffgui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifyShowtimeSeatDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton saveMovieButton;
	private JPanel theaterPanel;
	private JLabel theaterLabel;
	private JLabel showtimesLabel;
	private JRadioButton showtime1RadButton;
	private JRadioButton showtime2RadButton;
	private JRadioButton showtime3RadButton;
	private JLabel seatsAvailableLabel;
	private JPanel seatsAvailableMainPanel;
	private JPanel seatsPanel;

	private JPanel colorTaken;
	private JPanel screenPanel;
	private JLabel screenLabel;
	private JLabel takenLabel;

	private JLabel availableLabel;
	private JPanel colorAvailable;
	private JLabel selectedLabel;
	private JPanel colorSelected;
	
	private JButton saveChoiceButton;


	public static void main(String[] args) {
		try {
			ModifyShowtimeSeatDialog dialog = new ModifyShowtimeSeatDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModifyShowtimeSeatDialog() {
		getContentPane().setBackground(new Color(55, 65, 81));
		setTitle("Showtime and Seat");
		setBounds(100, 100, 1300, 720);
		getContentPane().setLayout(null);

		saveMovieButton = new JButton("Save Choice");
		saveMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveMovieButton.setForeground(new Color(255, 255, 255));
		saveMovieButton.setBackground(new Color(255, 81, 90));
		saveMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveMovieButton.setBounds(1000, -530, 150, 35);
		
		theaterPanel = new JPanel();
		theaterPanel.setForeground(new Color(255, 255, 255));
		theaterPanel.setBackground(new Color(249, 115, 22));
		theaterPanel.setBounds(0, 0, 1284, 30);
		theaterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		theaterLabel = new JLabel("THEATER 1");
		theaterLabel.setForeground(new Color(255, 255, 255));
		theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		theaterPanel.add(theaterLabel);
		
		showtimesLabel = new JLabel("Showtimes");
		showtimesLabel.setForeground(Color.WHITE);
		showtimesLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		showtimesLabel.setBounds(10, 40, 175, 20);
		
		showtime1RadButton = new JRadioButton("November 24, 2024 09:25 PM");
		showtime1RadButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		showtime1RadButton.setBounds(15, 80, 215, 30);
		
		showtime2RadButton = new JRadioButton("November 24, 2024 09:25 PM");
		showtime2RadButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		showtime2RadButton.setBounds(15, 120, 215, 30);;
		
		showtime3RadButton = new JRadioButton("November 24, 2024 09:25 PM");
		showtime3RadButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		showtime3RadButton.setBounds(15, 160, 215, 30);
		
		seatsAvailableLabel = new JLabel("Seats Available");
		seatsAvailableLabel.setForeground(Color.WHITE);
		seatsAvailableLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		seatsAvailableLabel.setBounds(300, 40, 130, 20);
		
		seatsAvailableMainPanel = new JPanel();
		seatsAvailableMainPanel.setBounds(297, 70, 977, 560);
		seatsAvailableMainPanel.setLayout(null);
		
		seatsPanel = new JPanel();
		seatsPanel.setBounds(10, 35, 955, 514);
		seatsAvailableMainPanel.add(seatsPanel);
		seatsPanel.setLayout(new GridLayout(8, 16, 2, 10));
		
		for (int row = 1; row <= 8; row++) {
			for (int col = 1; col <= 16; col++) {
				
				String seatLabel = " ";
				
				if (col == 5 || col == 12) {
					seatLabel = "  ";
				} else if (col <= 4) {
					seatLabel = (char) ('A' + row - 1) + String.valueOf(col);
				} else if (col <= 11) {
					seatLabel = (char) ('A' + row - 1) + String.valueOf(col - 1);
				} else if (col <= 16) {
					seatLabel = (char) ('A' + row - 1) + String.valueOf(col - 2);
				}
				

				JButton seatButton = new JButton(seatLabel);
				seatButton.setFocusable(false);
				if (col == 5 || col == 12) {
					seatButton.setVisible(false);
				}
				seatsPanel.add(seatButton);
				
			}
		}
		
		screenPanel = new JPanel();
		screenPanel.setBackground(new Color(0, 255, 128));
		screenPanel.setBounds(306, 0, 400, 21);
		seatsAvailableMainPanel.add(screenPanel);
		
		screenLabel = new JLabel("SCREEN");
		screenLabel.setVerticalAlignment(SwingConstants.TOP);
		screenLabel.setForeground(new Color(0, 0, 0));
		screenLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		screenPanel.add(screenLabel);
		
		colorTaken = new JPanel();
		colorTaken.setBounds(435, 45, 15, 15);
		
		takenLabel = new JLabel("Taken");
		takenLabel.setForeground(Color.WHITE);
		takenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		takenLabel.setBounds(455, 45, 53, 15);
		
		availableLabel = new JLabel("Available");
		availableLabel.setForeground(Color.WHITE);
		availableLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		availableLabel.setBounds(540, 45, 55, 15);
		
		colorAvailable = new JPanel();
		colorAvailable.setBounds(515, 45, 15, 15);
		
		selectedLabel = new JLabel("Selected");
		selectedLabel.setForeground(Color.WHITE);
		selectedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		selectedLabel.setBounds(630, 45, 55, 15);
		
		colorSelected = new JPanel();
		colorSelected.setBounds(610, 45, 15, 15);
		
		saveChoiceButton = new JButton("Save Choice");
		saveChoiceButton.setForeground(Color.WHITE);
		saveChoiceButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveChoiceButton.setBackground(new Color(255, 81, 90));
		saveChoiceButton.setBounds(1045, 640, 230, 35);
		
		add(saveMovieButton);
		add(theaterPanel);
		add(showtimesLabel);
		add(showtime1RadButton);
		add(showtime2RadButton);
		add(showtime3RadButton);
		add(seatsAvailableLabel);
		add(seatsAvailableMainPanel);
		add(colorTaken);
		add(takenLabel);
		add(availableLabel);
		add(colorAvailable);
		add(selectedLabel);
		add(colorSelected);
		add(saveChoiceButton);
	}
}
