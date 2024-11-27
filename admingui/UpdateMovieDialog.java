package admingui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class UpdateMovieDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel theaterPanel;
	private JLabel theaterLabel;
	
	private JLabel moviePosterLabel;
	private JButton uploadPosterButton;
	
	private JLabel movieNameLabel;
	private JTextField movieNameTF;
	
	private JLabel moviePriceLabel;
	private JTextField moviePriceTF;
	
	private JLabel genre1Label;
	private JComboBox genre1ComboBox;
	private JLabel genre2Label;
	private JComboBox genre2ComboBox;
	
	private JLabel durationLabel;
	private JLabel hrsLabel;
	private JComboBox durationHrsComboBox;
	private JLabel minsLabel;
	private JComboBox durationMinsComboBox;
	
	private JLabel contentRatingLabel;
	private JComboBox contentRatingComboBox;
	
	private JLabel showtimesLabel;
	
	private JLabel showtime1Label;
	private JLabel date1Label;
	private JComboBox date1MonthComboBox;
	private JComboBox date1DayComboBox;
	private JComboBox date1YearComboBox;
	private JLabel time1Label;
	private JComboBox time1HoursComboBox;
	private JComboBox time1MinsComboBox;
	private JComboBox time1AMPMComboBox;

	private JLabel showtime2Label;
	private JLabel date2Label;
	private JComboBox date2MonthComboBox;
	private JComboBox date2DayComboBox;
	private JComboBox date2YearComboBox;
	private JLabel time2Label;
	private JComboBox time2HoursComboBox;
	private JComboBox time2MinsComboBox;
	private JComboBox time2AMPMComboBox;
	
	private JLabel showtime3Label;
	private JLabel date3Label;
	private JComboBox date3MonthComboBox;
	private JComboBox date3DayComboBox;
	private JComboBox date3YearComboBox;
	private JLabel time3Label;
	private JComboBox time3HoursComboBox;
	private JComboBox time3MinutesComboBox;
	private JComboBox time3AMPMComboBox;
	
	private JButton saveMovieButton;


	public static void main(String[] args) {
		try {
			UpdateMovieDialog dialog = new UpdateMovieDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UpdateMovieDialog() {
		getContentPane().setBackground(new Color(55, 65, 81));
		setTitle("Add Movie");
		setBounds(100, 100, 950, 600);
		setLayout(null);
		
		theaterPanel = new JPanel();
		theaterPanel.setForeground(new Color(255, 255, 255));
		theaterPanel.setBackground(new Color(249, 115, 22));
		theaterPanel.setBounds(0, 0, 935, 30);

		theaterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		theaterLabel = new JLabel("THEATER 1");
		theaterLabel.setForeground(new Color(255, 255, 255));
		theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		theaterPanel.add(theaterLabel);
		
		moviePosterLabel = new JLabel();
		moviePosterLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		moviePosterLabel.setBounds(10, 55, 150, 240);
		
		movieNameLabel = new JLabel("Movie Name:");
		movieNameLabel.setForeground(new Color(255, 255, 255));
		movieNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		movieNameLabel.setBounds(200, 50, 100, 20);	
		
		movieNameTF = new JTextField();
		movieNameTF.setColumns(10);
		movieNameTF.setBounds(200, 75, 230, 25);
		
		moviePriceLabel = new JLabel("Price:");
		moviePriceLabel.setForeground(new Color(255, 255, 255));
		moviePriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		moviePriceLabel.setBounds(200, 120, 100, 20);
		
		moviePriceTF = new JTextField();
		moviePriceTF.setColumns(10);
		moviePriceTF.setBounds(200, 145, 115, 25);
		
		genre1Label = new JLabel("Genre 1:");
		genre1Label.setForeground(new Color(255, 255, 255));
		genre1Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		genre1Label.setBounds(200, 190, 100, 20);
		
		genre1ComboBox = new JComboBox();
		genre1ComboBox.setBounds(200, 215, 115, 25);
		
		genre2Label = new JLabel("Genre 2:");
		genre2Label.setForeground(new Color(255, 255, 255));
		genre2Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		genre2Label.setBounds(340, 190, 100, 20);	
		
		genre2ComboBox = new JComboBox();
		genre2ComboBox.setBounds(340, 215, 115, 25);	
		
		durationLabel = new JLabel("Duration:");
		durationLabel.setForeground(new Color(255, 255, 255));
		durationLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		durationLabel.setBounds(200, 260, 100, 20);
		
		durationHrsComboBox = new JComboBox();
		durationHrsComboBox.setBounds(200, 285, 60, 25);
		
		hrsLabel = new JLabel("hrs");
		hrsLabel.setForeground(new Color(255, 255, 255));
		hrsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		hrsLabel.setBounds(265, 285, 50, 20);	
		
		durationMinsComboBox = new JComboBox();
		durationMinsComboBox.setBounds(295, 285, 60, 25);	
		
		minsLabel = new JLabel("mins");
		minsLabel.setForeground(new Color(255, 255, 255));
		minsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		minsLabel.setBounds(360, 285, 50, 20);	
		
		contentRatingLabel = new JLabel("Content Rating:");
		contentRatingLabel.setForeground(new Color(255, 255, 255));
		contentRatingLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		contentRatingLabel.setBounds(200, 330, 135, 20);	
		
		contentRatingComboBox = new JComboBox();
		contentRatingComboBox.setBounds(200, 355, 150, 25);	
		
		uploadPosterButton = new JButton("Upload Poster");
		uploadPosterButton.setBorder(UIManager.getBorder("Button.border"));
		uploadPosterButton.setForeground(new Color(255, 255, 255));
		uploadPosterButton.setBackground(new Color(55, 65, 81));
		uploadPosterButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		uploadPosterButton.setBounds(10, 300, 150, 35);
		
		showtimesLabel = new JLabel("SHOWTIMES");
		showtimesLabel.setForeground(new Color(255, 255, 255));
		showtimesLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		showtimesLabel.setBounds(520, 50, 100, 20);		
		
		showtime1Label = new JLabel("Showtime 1");
		showtime1Label.setForeground(new Color(255, 255, 255));
		showtime1Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		showtime1Label.setBounds(535, 95, 100, 20);
		
		date1Label = new JLabel("Date:");
		date1Label.setForeground(new Color(255, 255, 255));
		date1Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		date1Label.setBounds(545, 125, 35, 20);
		
		date1MonthComboBox = new JComboBox();
		date1MonthComboBox.setBounds(580, 120, 100, 25);
		
		date1DayComboBox = new JComboBox();
		date1DayComboBox.setBounds(690, 120, 50, 25);
				
		date1YearComboBox = new JComboBox();
		date1YearComboBox.setBounds(750, 120, 60, 25);
		
		time1Label = new JLabel("Time:");
		time1Label.setForeground(new Color(255, 255, 255));
		time1Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		time1Label.setBounds(545, 160, 40, 20);	
		
		time1HoursComboBox = new JComboBox();
		time1HoursComboBox.setBounds(580, 160, 75, 25);	
		
		time1MinsComboBox = new JComboBox();
		time1MinsComboBox.setBounds(665, 160, 75, 25);
		
		time1AMPMComboBox = new JComboBox();
		time1AMPMComboBox.setBounds(750, 160, 40, 25);	
		
		showtime2Label = new JLabel("Showtime 2");
		showtime2Label.setForeground(new Color(255, 255, 255));
		showtime2Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		showtime2Label.setBounds(535, 230, 100, 20);	
		
		date2Label = new JLabel("Date:");
		date2Label.setForeground(new Color(255, 255, 255));
		date2Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		date2Label.setBounds(545, 260, 40, 20);	
		
		date2MonthComboBox = new JComboBox();
		date2MonthComboBox.setBounds(580, 255, 100, 25);		
		
		date2DayComboBox = new JComboBox();
		date2DayComboBox.setBounds(690, 255, 50, 25);
		
		date2YearComboBox = new JComboBox();
		date2YearComboBox.setBounds(750, 255, 60, 25);
		
		time2Label = new JLabel("Time:");
		time2Label.setForeground(new Color(255, 255, 255));
		time2Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		time2Label.setBounds(545, 295, 40, 20);
		
		time2HoursComboBox = new JComboBox();
		time2HoursComboBox.setBounds(580, 295, 75, 25);	
		
		time2MinsComboBox = new JComboBox();
		time2MinsComboBox.setBounds(665, 295, 75, 25);	
		
		time2AMPMComboBox = new JComboBox();
		time2AMPMComboBox.setBounds(750, 295, 40, 25);
		
		showtime3Label = new JLabel("Showtime 3");
		showtime3Label.setForeground(new Color(255, 255, 255));
		showtime3Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		showtime3Label.setBounds(535, 365, 100, 20);
		
		date3Label = new JLabel("Date:");
		date3Label.setForeground(new Color(255, 255, 255));
		date3Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		date3Label.setBounds(545, 395, 40, 20);	
		
		date3MonthComboBox = new JComboBox();
		date3MonthComboBox.setBounds(580, 390, 100, 25);
		
		date3DayComboBox = new JComboBox();
		date3DayComboBox.setBounds(690, 390, 50, 25);
		
		
		date3YearComboBox = new JComboBox();
		date3YearComboBox.setBounds(750, 390, 60, 25);
		
		time3Label = new JLabel("Time:");
		time3Label.setForeground(new Color(255, 255, 255));
		time3Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		time3Label.setBounds(545, 430, 40, 20);
		
		time3HoursComboBox = new JComboBox();
		time3HoursComboBox.setBounds(580, 430, 75, 25);
		
		time3MinutesComboBox = new JComboBox();
		time3MinutesComboBox.setBounds(665, 430, 75, 25);
		
		time3AMPMComboBox = new JComboBox();
		time3AMPMComboBox.setBounds(750, 430, 40, 25);
		
		saveMovieButton = new JButton("Save Movie");
		saveMovieButton.setForeground(new Color(255, 255, 255));
		saveMovieButton.setBackground(new Color(255, 81, 90));
		saveMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveMovieButton.setBounds(770, 510, 150, 35);
		
		add(moviePosterLabel);
		add(movieNameLabel);
		add(movieNameTF);
		add(moviePriceLabel);
		add(moviePriceTF);
		add(genre1Label);
		add(genre1ComboBox);
		add(genre2Label);
		add(genre2ComboBox);
		add(durationLabel);
		add(durationHrsComboBox);
		add(hrsLabel);
		add(durationMinsComboBox);
		add(minsLabel);
		add(contentRatingLabel);
		add(contentRatingComboBox);
		add(uploadPosterButton);
		add(showtimesLabel);
		add(showtime1Label);
		add(date1Label);
		add(date1MonthComboBox);
		add(date1DayComboBox);
		add(date1YearComboBox);
		add(time1Label);
		add(time1HoursComboBox);
		add(time1MinsComboBox);
		add(time1AMPMComboBox);
		add(showtime2Label);
		add(date2Label);
		add(date2MonthComboBox);
		add(date2DayComboBox);
		add(date2YearComboBox);
		add(time2Label);
		add(time2HoursComboBox);
		add(time2MinsComboBox);
		add(time2AMPMComboBox);
		add(showtime3Label);
		add(date3Label);
		add(date3MonthComboBox);
		add(date3DayComboBox);
		add(date3YearComboBox);
		add(time3Label);
		add(time3HoursComboBox);
		add(time3MinutesComboBox);
		add(time3AMPMComboBox);
		add(saveMovieButton);
		add(theaterPanel);







	}
}
