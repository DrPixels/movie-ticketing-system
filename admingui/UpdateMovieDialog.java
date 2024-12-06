package admingui;

import Database.AdminDatabaseManager;
import Model.Movie;
import Model.Showtime;
import helper.Helper;
import static helper.Helper.convertStringsToLocalDateTimes;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

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
	private JComboBox<String> genre1ComboBox;
	private JLabel genre2Label;
	private JComboBox<String> genre2ComboBox;
	
	private JLabel durationLabel;
	private JLabel hrsLabel;
	private JSpinner durationSpinner;
	private JLabel minsLabel;
	private JComboBox durationMinsComboBox;
	
	private JLabel contentRatingLabel;
	private JComboBox<String> contentRatingComboBox;
	
	private JLabel showtime1Label;
	private JComboBox<String> date1MonthComboBox;
	private JComboBox<Integer> date1DayComboBox;
	private JComboBox<Integer> date1YearComboBox;
        
	private JLabel time1Label;
	private JComboBox<String> time1HoursComboBox;
	private JComboBox<String> time1MinsComboBox;
	private JComboBox<String> time1AMPMComboBox;

	private JLabel showtime2Label;
	private JLabel time2Label;
	private JComboBox<String> time2HoursComboBox;
	private JComboBox<String> time2MinsComboBox;
	private JComboBox<String> time2AMPMComboBox;
        private JToggleButton showtime2ToggleButton;
	
	private JLabel showtime3Label;
	private JLabel time3Label;
	private JComboBox<String> time3HoursComboBox;
	private JComboBox<String> time3MinutesComboBox;
	private JComboBox<String> time3AMPMComboBox;
        private JToggleButton showtime3ToggleButton;
	
	private JButton saveMovieButton;
        
        //More fields
        public static String movieIdShowing;
        private Movie movieToUpdateData = AdminDatabaseManager.retrieveMovieById(movieIdShowing);
        private String moviePosterPath = Helper.DEFAULT_MOVIE_POSTER_PATH;
        public static int theaterId;


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
            setResizable(false);
		getContentPane().setBackground(new Color(55, 65, 81));
		setTitle("Update Movie");
		setBounds(100, 100, 950, 600);
		getContentPane().setLayout(null);
		
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
                
                ImageIcon iconImage = new ImageIcon(movieToUpdateData.getMoviePosterPicturePath());
                Image originalImage = iconImage.getImage();
                Image scaledImage = originalImage.getScaledInstance(moviePosterLabel.getWidth(), moviePosterLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                moviePosterLabel.setIcon(scaledImageIcon);
		
		movieNameLabel = new JLabel("Movie Name:");
		movieNameLabel.setForeground(new Color(255, 255, 255));
		movieNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		movieNameLabel.setBounds(200, 50, 100, 20);	
		
		movieNameTF = new JTextField();
		movieNameTF.setColumns(10);
		movieNameTF.setBounds(200, 75, 230, 30);
                movieNameTF.setText(movieToUpdateData.getMovieName());
                movieNameTF.setFont(Helper.fontForTF);
		
		moviePriceLabel = new JLabel("Price:");
		moviePriceLabel.setForeground(new Color(255, 255, 255));
		moviePriceLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		moviePriceLabel.setBounds(200, 120, 100, 20);
		
		moviePriceTF = new JTextField();
		moviePriceTF.setColumns(10);
		moviePriceTF.setBounds(200, 145, 115, 25);
                moviePriceTF.setText(String.valueOf(movieToUpdateData.getMoviePrice()));
                moviePriceTF.setFont(Helper.fontForTF);
		
		genre1Label = new JLabel("Genre 1:");
		genre1Label.setForeground(new Color(255, 255, 255));
		genre1Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		genre1Label.setBounds(200, 190, 100, 20);
		
		genre1ComboBox = new JComboBox<>(Helper.GENRES);
		genre1ComboBox.setBounds(200, 215, 115, 25);
                genre1ComboBox.setSelectedItem(movieToUpdateData.getMovieGenre1());
                
                                genre1ComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Get the selected item
                        String selectedOption = (String) genre1ComboBox.getSelectedItem();

                        if(selectedOption.equals("Select Genre")) {
                            genre2ComboBox.setEnabled(false);
                        } else {
                            genre2ComboBox.setEnabled(true);
                        }
                    }
                });
		
		genre2Label = new JLabel("Genre 2 (optional):");
		genre2Label.setForeground(new Color(255, 255, 255));
		genre2Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		genre2Label.setBounds(340, 190, 150, 20);	
		
		genre2ComboBox = new JComboBox<>(Helper.GENRES);
		genre2ComboBox.setBounds(340, 215, 115, 25);	
                if(movieToUpdateData.getMovieGenre2() != null) {
                    genre2ComboBox.setSelectedItem(movieToUpdateData.getMovieGenre2());
                }
		
		durationLabel = new JLabel("Duration (minutes):");
		durationLabel.setForeground(new Color(255, 255, 255));
		durationLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		durationLabel.setBounds(520, 55, 150, 20);
		
                // Create a SpinnerNumberModel with minimum 0, initial value 0, and step size 1
                SpinnerNumberModel durationSpinnermodel = new SpinnerNumberModel(0, 0, 999, 1);
        
		durationSpinner = new JSpinner(durationSpinnermodel);
		durationSpinner.setBounds(520, 80, 100, 25);
                durationSpinner.setValue(movieToUpdateData.getDuration());
                
                durationSpinner.addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        int value = (int) durationSpinner.getValue();
                        
                        if (value <= 0) {
 	
                            date1MonthComboBox.setEnabled(false);
                            date1DayComboBox.setEnabled(false);
                            date1YearComboBox.setEnabled(false);

                            time1HoursComboBox.setEnabled(false);
                            time1MinsComboBox.setEnabled(false);
                            time1AMPMComboBox.setEnabled(false);
                            
                            showtime2ToggleButton.setEnabled(false);
                        } else {
                            date1MonthComboBox.setEnabled(true);
                            date1DayComboBox.setEnabled(true);
                            date1YearComboBox.setEnabled(true);

                            time1HoursComboBox.setEnabled(true);
                            time1MinsComboBox.setEnabled(true);
                            time1AMPMComboBox.setEnabled(true);
                            
                            showtime2ToggleButton.setSelected(false);
                            showtime2ToggleButton.setEnabled(true);
                        }
                        
                        revalidate();
                        repaint();
                    }
                });
		
//		hrsLabel = new JLabel("hrs");
//		hrsLabel.setForeground(new Color(255, 255, 255));
//		hrsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
//		hrsLabel.setBounds(585, 80, 50, 20);	
//		
//		durationMinsComboBox = new JComboBox();
//		durationMinsComboBox.setBounds(615, 80, 60, 25);	
//		
//		minsLabel = new JLabel("mins");
//		minsLabel.setForeground(new Color(255, 255, 255));
//		minsLabel.setFont(new Font("Segoe UI", Font.ITALIC, 13));
//		minsLabel.setBounds(680, 80, 50, 20);	
		
		contentRatingLabel = new JLabel("Content Rating:");
		contentRatingLabel.setForeground(new Color(255, 255, 255));
		contentRatingLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		contentRatingLabel.setBounds(200, 269, 135, 20);	
		
		contentRatingComboBox = new JComboBox<>(Helper.RATINGS);
		contentRatingComboBox.setBounds(200, 294, 150, 25);
                contentRatingComboBox.setSelectedItem(movieToUpdateData.getContentRating());
		
		uploadPosterButton = new JButton("Upload Poster");
		uploadPosterButton.setBorder(UIManager.getBorder("Button.border"));
		uploadPosterButton.setForeground(new Color(255, 255, 255));
		uploadPosterButton.setBackground(new Color(55, 65, 81));
		uploadPosterButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		uploadPosterButton.setBounds(10, 300, 150, 35);
                
                            // Add action listener to the button
                uploadPosterButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //For scaling the file
                    int profPicLabelWidth = moviePosterLabel.getWidth();
                    int profPicLabelHeight = moviePosterLabel.getHeight();

                    //Open as model dialog
                    JFileChooser fileChooser = new JFileChooser();

                    //Filtering the file based on their file extensions
                    fileChooser.setFileFilter(new FileNameExtensionFilter("jpg/png/gif", "jpg", "png", "gif"));

                    int result = fileChooser.showOpenDialog(Helper.getCurrentFrame());
                    if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            moviePosterPath = selectedFile.getAbsolutePath();

                            ImageIcon imageIcon = new ImageIcon(moviePosterPath);

                            //Scaling the image
                            Image originalImage = imageIcon.getImage();
                            Image scaledImage = originalImage.getScaledInstance(profPicLabelWidth, profPicLabelHeight, Image.SCALE_SMOOTH);
                            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                            moviePosterLabel.setIcon(scaledImageIcon);
                            moviePosterLabel.putClientProperty("imagePath", selectedFile.getAbsolutePath());
                    }
                    }
                });
		
		showtime1Label = new JLabel("Showtime 1");
		showtime1Label.setForeground(new Color(255, 255, 255));
		showtime1Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		showtime1Label.setBounds(535, 200, 100, 20);
		
                //Showtime Date
                int showtimeSize = movieToUpdateData.getShowtimes().size();
                LocalDateTime showtimeLocalDateTime = movieToUpdateData.getShowtimes().get(0).getShowDateTime();
                LocalDate showtimeLocalDate = showtimeLocalDateTime.toLocalDate();
                
		date1MonthComboBox = new JComboBox<>(Helper.MONTHS);
		date1MonthComboBox.setBounds(520, 150, 100, 25);
		
		date1DayComboBox = new JComboBox<>();
		date1DayComboBox.setBounds(630, 150, 50, 25);
				
		date1YearComboBox = new JComboBox<>();
		date1YearComboBox.setBounds(690, 150, 70, 25);
                
                Helper.setupDateComboBoxes(date1MonthComboBox, date1DayComboBox, date1YearComboBox);
                
                date1MonthComboBox.setSelectedIndex(showtimeLocalDate.getMonthValue() - 1);
                date1YearComboBox.setSelectedItem(showtimeLocalDate.getYear());
                date1DayComboBox.setSelectedItem(showtimeLocalDate.getDayOfMonth());
		
		time1Label = new JLabel("Time:");
		time1Label.setForeground(new Color(255, 255, 255));
		time1Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		time1Label.setBounds(545, 230, 40, 20);	
		
		time1HoursComboBox = new JComboBox<>();
		time1HoursComboBox.setBounds(580, 230, 50, 25);	
		
		time1MinsComboBox = new JComboBox<>();
		time1MinsComboBox.setBounds(640, 230, 50, 25);
		
		time1AMPMComboBox = new JComboBox<>();
		time1AMPMComboBox.setBounds(700, 230, 50, 25);
                
                Helper.setUpTimeComboBoxes(time1HoursComboBox, time1MinsComboBox, time1AMPMComboBox);
                
                int showtimeHour1 = showtimeLocalDateTime.getHour();
                int showtimeMinute1 = showtimeLocalDateTime.getMinute();
                String showtimeAmPm1 = (showtimeHour1 >= 12) ? "PM" : "AM";
                
                // Convert hour to 12-hour format
                int showtime1Hour12 = (showtimeHour1 % 12 == 0) ? 12 : showtimeHour1 % 12;
                
                time1HoursComboBox.setSelectedIndex(showtime1Hour12 - 1);
                time1MinsComboBox.setSelectedIndex(showtimeMinute1);
                time1AMPMComboBox.setSelectedItem(showtimeAmPm1);
		
		showtime2Label = new JLabel("Showtime 2");
		showtime2Label.setForeground(new Color(255, 255, 255));
		showtime2Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		showtime2Label.setBounds(535, 280, 100, 20);
              
		
		time2Label = new JLabel("Time:");
		time2Label.setForeground(new Color(255, 255, 255));
		time2Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		time2Label.setBounds(545, 310, 40, 20);
		
		time2HoursComboBox = new JComboBox<>();
		time2HoursComboBox.setBounds(580, 310, 50, 25);	
		
		time2MinsComboBox = new JComboBox<>();
		time2MinsComboBox.setBounds(640, 310, 50, 25);	
		
		time2AMPMComboBox = new JComboBox<>();
		time2AMPMComboBox.setBounds(700, 310, 50, 25);
                
                showtime2ToggleButton = new JToggleButton("Add");
                showtime2ToggleButton.setBounds(650, 280, 70, 20);
                showtime2ToggleButton.setFocusable(false);
                showtime2ToggleButton.setEnabled(false);
                add(showtime2ToggleButton);
                
                showtime2ToggleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Check if the toggle button is selected or not
                        boolean isSelected = showtime2ToggleButton.isSelected();
                        System.out.println(isSelected);
                        int durationSpinnerValue = (int) durationSpinner.getValue();
                        if (isSelected &&  durationSpinnerValue != 0) {       
                            time2HoursComboBox.setEnabled(true);
                            time2MinsComboBox.setEnabled(true);
                            time2AMPMComboBox.setEnabled(true);
                            showtime3ToggleButton.setSelected(false);
                            showtime3ToggleButton.setEnabled(true);

                        } else {
                            time2AMPMComboBox.setEnabled(false);
                            time2HoursComboBox.setEnabled(false);
                            time2MinsComboBox.setEnabled(false);
                            showtime3ToggleButton.setSelected(false);
                            showtime3ToggleButton.setEnabled(false);
                            time3HoursComboBox.setEnabled(false);
                            time3MinutesComboBox.setEnabled(false);
                            time3AMPMComboBox.setEnabled(false);
                            
                        }
                    } 
                });
                
                Helper.setUpTimeComboBoxes(time2HoursComboBox, time2MinsComboBox, time2AMPMComboBox);   
		
		showtime3Label = new JLabel("Showtime 3");
		showtime3Label.setForeground(new Color(255, 255, 255));
		showtime3Label.setFont(new Font("Segoe UI", Font.BOLD, 13));
		showtime3Label.setBounds(535, 360, 100, 20);
                
                showtime3ToggleButton = new JToggleButton("Add");
                showtime3ToggleButton.setBounds(650, 360, 70, 20);
                showtime3ToggleButton.setFocusable(false);
                showtime3ToggleButton.setEnabled(false);
                add(showtime3ToggleButton);
		
		time3Label = new JLabel("Time:");
		time3Label.setForeground(new Color(255, 255, 255));
		time3Label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		time3Label.setBounds(545, 400, 40, 20);

		
		time3HoursComboBox = new JComboBox<>();
		time3HoursComboBox.setBounds(580, 400, 50, 25);
		
		time3MinutesComboBox = new JComboBox<>();
		time3MinutesComboBox.setBounds(640, 400, 50, 25);
		
		time3AMPMComboBox = new JComboBox<>();
		time3AMPMComboBox.setBounds(700, 400, 50, 25);
                
                showtime3ToggleButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Check if the toggle button is selected or not
                        boolean isSelected = showtime3ToggleButton.isSelected();
                        int durationSpinnerValue = (int) durationSpinner.getValue();
                        if (isSelected &&  durationSpinnerValue != 0 && showtime2ToggleButton.isSelected()) {
                            time3HoursComboBox.setEnabled(true);
                            time3MinutesComboBox.setEnabled(true);
                            time3AMPMComboBox.setEnabled(true);
                           
                        } else {
                            time3HoursComboBox.setEnabled(false);
                            time3MinutesComboBox.setEnabled(false);
                            time3AMPMComboBox.setEnabled(false);
                        }
                    } 
                });
		
                Helper.setUpTimeComboBoxes(time3HoursComboBox, time3MinutesComboBox, time3AMPMComboBox);
                
                //Initial Render
                if (showtimeSize == 1) {
                    time2HoursComboBox.setEnabled(false);
                    time2MinsComboBox.setEnabled(false);
                    time2AMPMComboBox.setEnabled(false);

                    time3HoursComboBox.setEnabled(false);
                    time3MinutesComboBox.setEnabled(false);
                    time3AMPMComboBox.setEnabled(false);
                } else if (showtimeSize == 2) {
                    showtime2ToggleButton.setSelected(true);
                    time3HoursComboBox.setEnabled(false);
                    time3MinutesComboBox.setEnabled(false);
                    time3AMPMComboBox.setEnabled(false);
                } else {
                    showtime2ToggleButton.setSelected(true);
                    showtime3ToggleButton.setSelected(true);
                }
                
                //Update values of showtime 2 and 3
                if(showtimeSize == 2) {
                    LocalDateTime showtimeLocalDateTime2 = movieToUpdateData.getShowtimes().get(1).getShowDateTime();
                    
                    int showtimeHour2 = showtimeLocalDateTime2.getHour();
                    int showtimeMinute2 = showtimeLocalDateTime2.getMinute();
                    String showtimeAmPm2 = (showtimeHour2 >= 12) ? "PM" : "AM";

                    // Convert hour to 12-hour format
                    int showtime2Hour12 = (showtimeHour2 % 12 == 0) ? 12 : showtimeHour2 % 12;

                    time2HoursComboBox.setSelectedIndex(showtime2Hour12 - 1);
                    time2MinsComboBox.setSelectedIndex(showtimeMinute2);
                    time2AMPMComboBox.setSelectedItem(showtimeAmPm2);
                }   
                
                if(showtimeSize == 3) {
                    LocalDateTime showtimeLocalDateTime2 = movieToUpdateData.getShowtimes().get(1).getShowDateTime();
                    
                    int showtimeHour2 = showtimeLocalDateTime2.getHour();
                    int showtimeMinute2 = showtimeLocalDateTime2.getMinute();
                    String showtimeAmPm2 = (showtimeHour2 >= 12) ? "PM" : "AM";

                    // Convert hour to 12-hour format
                    int showtime2Hour12 = (showtimeHour2 % 12 == 0) ? 12 : showtimeHour2 % 12;

                    time2HoursComboBox.setSelectedIndex(showtime2Hour12 - 1);
                    time2MinsComboBox.setSelectedIndex(showtimeMinute2);
                    time2AMPMComboBox.setSelectedItem(showtimeAmPm2);
                    
                    LocalDateTime showtimeLocalDateTime3 = movieToUpdateData.getShowtimes().get(2).getShowDateTime();
                    
                    int showtimeHour3 = showtimeLocalDateTime3.getHour();
                    int showtimeMinute3 = showtimeLocalDateTime3.getMinute();
                    String showtimeAmPm3 = (showtimeHour2 >= 12) ? "PM" : "AM";

                    // Convert hour to 12-hour format
                    int showtime3Hour12 = (showtimeHour3 % 12 == 0) ? 12 : showtimeHour3 % 12;

                    time3HoursComboBox.setSelectedIndex(showtime3Hour12 - 1);
                    time3MinutesComboBox.setSelectedIndex(showtimeMinute3);
                    time3AMPMComboBox.setSelectedItem(showtimeAmPm3);
                    
                    
                    
                }
                    
         
                
		saveMovieButton = new JButton("Save Movie");
		saveMovieButton.setForeground(new Color(255, 255, 255));
		saveMovieButton.setBackground(new Color(255, 81, 90));
		saveMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveMovieButton.setBounds(770, 510, 150, 35);
                saveMovieButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                   if(!isFieldsValid()) {
                       JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Missing fields. Please try again.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                       return;
                   } else if (isShowtimeConflict()) {
                       JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "There is a conflict between the showtimes. Please try again.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                       return;
                   } else if (Float.parseFloat(moviePriceTF.getText()) <= 0){
                       JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Movie price must be greater than 0. Please try again.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                       return;
                   }
                   
                   String posterPath = moviePosterPath;
                   String movieName = movieNameTF.getText();
                   float moviePrice = Float.parseFloat(moviePriceTF.getText());
                   String genre1 = genre1ComboBox.getSelectedItem().toString();
                   String genre2 = (genre2ComboBox.getSelectedItem() == "Select Genre") ? null : genre2ComboBox.getSelectedItem().toString();
                   int duration = (int) durationSpinner.getValue();
                   String contentRating = contentRatingComboBox.getSelectedItem().toString();
                   
                    ArrayList<String> startTimesStr = new ArrayList<>();                    //For Showtime 1
                    String showDate = date1MonthComboBox.getSelectedItem() + " " + date1DayComboBox.getSelectedItem() + ", " + date1YearComboBox.getSelectedItem();
                    String showTime1 = time1HoursComboBox.getSelectedItem() + ":" + time1MinsComboBox.getSelectedItem() + " " + time1AMPMComboBox.getSelectedItem();
                    String showDateTime1 = showDate + " " + showTime1;
                    startTimesStr.add(showDateTime1);

                    if(showtime2ToggleButton.isSelected()) { 
                        String showTime2 = time2HoursComboBox.getSelectedItem() + ":" + time2MinsComboBox.getSelectedItem() + " " + time2AMPMComboBox.getSelectedItem();
                        String showDateTime2 = showDate + " " + showTime2;
                        startTimesStr.add(showDateTime2);
                    }

                    if(showtime3ToggleButton.isSelected()) {
                        String showTime3 = time3HoursComboBox.getSelectedItem() + ":" + time3MinutesComboBox.getSelectedItem() + " " + time3AMPMComboBox.getSelectedItem();
                        String showDateTime3 = showDate + " " + showTime3;
                        startTimesStr.add(showDateTime3);
                    }
                    
                        ArrayList<LocalDateTime> startTimes = convertStringsToLocalDateTimes(startTimesStr);
                        ArrayList<Showtime> modifiedShowtimes = Helper.convertLocalDateTimesToShowtimes(startTimesStr);
        
                        ArrayList<Showtime> showtimes = new ArrayList<>();                
                    
                    if(movieToUpdateData.getShowtimes().size() == startTimes.size()) {
                        
                        for (int i = 0; i < movieToUpdateData.getShowtimes().size(); i++) {
                            String showtimeId = movieToUpdateData.getShowtimes().get(i).getShowtimeId();
                            showtimes.add(new Showtime(showtimeId ,startTimes.get(i)));
                        }
                        
                    Movie movieData = new Movie(movieToUpdateData.getMovieId(), posterPath, movieName, moviePrice, genre1, genre2, duration, contentRating, showtimes);
                    
                    if(!AdminDatabaseManager.updateMovies(movieData)) {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Invalid action. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                            System.out.println("same");
                            return;
                        }
                    } else if(movieToUpdateData.getShowtimes().size() > startTimes.size()) {
                        System.out.println("deleting");
                        ArrayList<String> removedShowtimeIds = new ArrayList<>();
                        
                        int difference = movieToUpdateData.getShowtimes().size() - startTimes.size();
                        int movieToUpdateDataShowtimeSize = movieToUpdateData.getShowtimes().size();
                        for (int i = movieToUpdateDataShowtimeSize - 1; i > movieToUpdateDataShowtimeSize - difference - 1; i--) {
                            removedShowtimeIds.add(movieToUpdateData.getShowtimes().get(i).getShowtimeId());
                        }
                        
                        AdminDatabaseManager.deleteShowtimeInShowtime(removedShowtimeIds);  
                        
                    } else if(movieToUpdateData.getShowtimes().size() < startTimes.size()) {
                        System.out.println("adding");
                        
                        int difference = startTimes.size() - movieToUpdateData.getShowtimes().size();
                        
                        ArrayList<Showtime> newShowtimes = new ArrayList<>();
                        
                        for (int i = 1; i <= difference; i++) {
                            newShowtimes.add(modifiedShowtimes.get(i));
                        }

                        AdminDatabaseManager.addMovieShowtimeToShowtime(theaterId, movieToUpdateData.getMovieId(), newShowtimes, duration);
                        
                    }
                    JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "The movie was updated successfully.", "Successful Action", JOptionPane.INFORMATION_MESSAGE);
                    dispose();


        	}
                });  
		
		getContentPane().add(moviePosterLabel);
		getContentPane().add(movieNameLabel);
		getContentPane().add(movieNameTF);
		getContentPane().add(moviePriceLabel);
		getContentPane().add(moviePriceTF);
		getContentPane().add(genre1Label);
		getContentPane().add(genre1ComboBox);
		getContentPane().add(genre2Label);
		getContentPane().add(genre2ComboBox);
		getContentPane().add(durationLabel);
		getContentPane().add(durationSpinner);
//		getContentPane().add(hrsLabel);
//		getContentPane().add(durationMinsComboBox);
//		getContentPane().add(minsLabel);
		getContentPane().add(contentRatingLabel);
		getContentPane().add(contentRatingComboBox);
		getContentPane().add(uploadPosterButton);
		getContentPane().add(showtime1Label);
		getContentPane().add(date1MonthComboBox);
		getContentPane().add(date1DayComboBox);
		getContentPane().add(date1YearComboBox);
		getContentPane().add(time1Label);
		getContentPane().add(time1HoursComboBox);
		getContentPane().add(time1MinsComboBox);
		getContentPane().add(time1AMPMComboBox);
		getContentPane().add(showtime2Label);
		getContentPane().add(time2Label);
		getContentPane().add(time2HoursComboBox);
		getContentPane().add(time2MinsComboBox);
		getContentPane().add(time2AMPMComboBox);
		getContentPane().add(showtime3Label);
		getContentPane().add(time3Label);
		getContentPane().add(time3HoursComboBox);
		getContentPane().add(time3MinutesComboBox);
		getContentPane().add(time3AMPMComboBox);
		getContentPane().add(saveMovieButton);
		getContentPane().add(theaterPanel);
		
		JLabel lblDate = new JLabel("Showing Date:");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblDate.setBounds(520, 125, 120, 20);
		getContentPane().add(lblDate);
	}
    
    private boolean isFieldsValid() {
        
        boolean isGenre1Valid = genre1ComboBox.getSelectedItem() != "Select Genre";
        int durationSpinnerValue = (int) durationSpinner.getValue();   
        
        return !(movieNameTF.getText().isEmpty() || moviePriceTF.getText().isEmpty()) && isGenre1Valid && durationSpinnerValue != 0;
    }
    
        private boolean isShowtimeConflict() {
        int durationSpinnerValue = (int) durationSpinner.getValue();
        ArrayList<String> startTimesStr = new ArrayList<>();

        //For Showtime 1
        String showDate = date1MonthComboBox.getSelectedItem() + " " + date1DayComboBox.getSelectedItem() + ", " + date1YearComboBox.getSelectedItem();
        String showTime1 = time1HoursComboBox.getSelectedItem() + ":" + time1MinsComboBox.getSelectedItem() + " " + time1AMPMComboBox.getSelectedItem();
        String showDateTime1 = showDate + " " + showTime1;
        startTimesStr.add(showDateTime1);
        
        if(showtime2ToggleButton.isSelected()) { 
            String showTime2 = time2HoursComboBox.getSelectedItem() + ":" + time2MinsComboBox.getSelectedItem() + " " + time2AMPMComboBox.getSelectedItem();
            String showDateTime2 = showDate + " " + showTime2;
            startTimesStr.add(showDateTime2);
        }
        
        if(showtime3ToggleButton.isSelected()) {
            String showTime3 = time3HoursComboBox.getSelectedItem() + ":" + time3MinutesComboBox.getSelectedItem() + " " + time3AMPMComboBox.getSelectedItem();
            String showDateTime3 = showDate + " " + showTime3;
            startTimesStr.add(showDateTime3);
        }
        
        // Convert string times to LocalDateTime
        ArrayList<LocalDateTime> startTimes = Helper.convertStringsToLocalDateTimes(startTimesStr);
        
        boolean isConflict = Helper.checkConflicts(startTimes, durationSpinnerValue);
                // Check for conflicts
        if (isConflict) {
            System.out.println("There is a conflict between the showtimes.");
        } else {
            System.out.println("No conflict between the showtimes.");
        }
        
        return isConflict;
    }
}
