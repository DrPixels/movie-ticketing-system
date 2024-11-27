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

public class ShowtimeSeatDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel moviePosterPanel;
	private JLabel movieNameLabel;
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


	public static void main(String[] args) {
		try {
			ShowtimeSeatDialog dialog = new ShowtimeSeatDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ShowtimeSeatDialog() {
		getContentPane().setBackground(new Color(55, 65, 81));
		setTitle("Showtime and Seat");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		moviePosterPanel = new JPanel();
		moviePosterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		moviePosterPanel.setBounds(10, 75, 150, 240);
		
		movieNameLabel = new JLabel("Black to the Future");
		movieNameLabel.setForeground(new Color(255, 255, 255));
		movieNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 17));
		movieNameLabel.setBounds(10, 45, 180, 20);

		saveMovieButton = new JButton("Save Choice");
		saveMovieButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		saveMovieButton.setForeground(new Color(255, 255, 255));
		saveMovieButton.setBackground(new Color(255, 81, 90));
		saveMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveMovieButton.setBounds(825, 515, 150, 35);
		
		theaterPanel = new JPanel();
		theaterPanel.setForeground(new Color(255, 255, 255));
		theaterPanel.setBackground(new Color(249, 115, 22));
		theaterPanel.setBounds(0, 0, 984, 30);
		theaterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		theaterLabel = new JLabel("THEATER 1");
		theaterLabel.setForeground(new Color(255, 255, 255));
		theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		theaterPanel.add(theaterLabel);
		
		showtimesLabel = new JLabel("Showtimes");
		showtimesLabel.setForeground(Color.WHITE);
		showtimesLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		showtimesLabel.setBounds(190, 75, 175, 20);
		
		showtime1RadButton = new JRadioButton("November 24, 2024 09:25 PM");
		showtime1RadButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		showtime1RadButton.setBounds(200, 110, 215, 30);
		
		showtime2RadButton = new JRadioButton("November 24, 2024 09:25 PM");
		showtime2RadButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		showtime2RadButton.setBounds(200, 150, 215, 30);;
		
		showtime3RadButton = new JRadioButton("November 24, 2024 09:25 PM");
		showtime3RadButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		showtime3RadButton.setBounds(200, 190, 215, 30);
		
		seatsAvailableLabel = new JLabel("Seats Available");
		seatsAvailableLabel.setForeground(Color.WHITE);
		seatsAvailableLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		seatsAvailableLabel.setBounds(465, 75, 133, 20);
		
		seatsAvailableMainPanel = new JPanel();
		seatsAvailableMainPanel.setBounds(465, 110, 510, 370);
		seatsAvailableMainPanel.setLayout(null);
		
		seatsPanel = new JPanel();
		seatsPanel.setBounds(10, 35, 490, 327);
		seatsAvailableMainPanel.add(seatsPanel);
		seatsPanel.setLayout(new GridLayout(8, 8, 2, 10));
		
		for (int row = 1; row <= 8; row++) {
			for (int col = 1; col <= 8; col++) {
				String seatLabel = (char) ('A' + row - 1) + String.valueOf(col);

				JButton seatButton = new JButton(seatLabel);
				seatsPanel.add(seatButton);
				
			}
		}
		
		screenPanel = new JPanel();
		screenPanel.setBackground(new Color(0, 255, 128));
		screenPanel.setBounds(105, 0, 300, 21);
		seatsAvailableMainPanel.add(screenPanel);
		
		screenLabel = new JLabel("SCREEN");
		screenLabel.setVerticalAlignment(SwingConstants.TOP);
		screenLabel.setForeground(new Color(0, 0, 0));
		screenLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		screenPanel.add(screenLabel);
		
		colorTaken = new JPanel();
		colorTaken.setBounds(605, 80, 15, 15);
		
		takenLabel = new JLabel("Taken");
		takenLabel.setForeground(Color.WHITE);
		takenLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		takenLabel.setBounds(625, 80, 53, 15);
		
		availableLabel = new JLabel("Available");
		availableLabel.setForeground(Color.WHITE);
		availableLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		availableLabel.setBounds(707, 80, 53, 15);
		
		colorAvailable = new JPanel();
		colorAvailable.setBounds(687, 80, 15, 15);
		
		selectedLabel = new JLabel("Selected");
		selectedLabel.setForeground(Color.WHITE);
		selectedLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		selectedLabel.setBounds(800, 80, 53, 15);
		
		colorSelected = new JPanel();
		colorSelected.setBounds(780, 80, 15, 15);
		
		add(moviePosterPanel);
		add(movieNameLabel);
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
	}
}