package admingui;

import Database.AdminDatabaseManager;
import Model.Authentication;
import Model.Movie;
import Model.Theater;
import helper.Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class ManageMovies extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    private JButton viewArchiveButton;
    
    private JPanel MoviePanel;
    
    private JPanel theaterPanel;
    private JLabel theaterLabel;
    
    private JLabel moviePosterLabel;
    private JLabel movieName;
    private JLabel moviePrice;
    
    private JLabel genreLabel;
    private JLabel genre;
    
    private JLabel durationLabel;
    private JLabel duration;
    
    private JLabel contentRatingLabel;
    private JLabel contentRating;
    
    private JPanel showDateTimePanel;
    private JPanel showDatePanel;
    private JLabel showDate;
    private JPanel showtimeTime1Panel;
    private JLabel showtimeTime1;
    private JPanel showtimeTime2Panel;
    private JLabel showtimeTime2;
    private JPanel showtimeTime3Panel;
    private JLabel showtimeTime3;
    
    private JButton updateMovieButton;
    private JButton archiveMovieButton;

    private JPanel emptyMoviePanel;
    private JLabel showtime1RemSeats;
    private JLabel showtime2RemSeats;
    private JLabel showtime3RemSeats;
    
    //Fields
    private ArrayList<Theater> theatersData = AdminDatabaseManager.retrieveTheaterData();

    public ManageMovies() {
    	
    	setLayout(null);
    	
        mainPanel = new JPanel();
//        mainPanel.setLayout(null);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
           
        addTheatersDataToPanel(theatersData);
     
        scrollPane = new JScrollPane(mainPanel);
    	scrollPane.setBounds(10, 49, 980, 630);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	
    	viewArchiveButton = new JButton("View Archived Movies");
    	viewArchiveButton.setForeground(Color.WHITE);
    	viewArchiveButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	viewArchiveButton.setBackground(new Color(255, 81, 90));
    	viewArchiveButton.setBounds(790, 10, 180, 35);
    	viewArchiveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
            	ArchivedMoviesDialog archivedMoviesDialog = new ArchivedMoviesDialog();
                archivedMoviesDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            	archivedMoviesDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
            	archivedMoviesDialog.setVisible(true);
                
                archivedMoviesDialog.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        mainPanel.removeAll();
                        ArrayList<Theater> newTheatersData  = AdminDatabaseManager.retrieveTheaterData();
                        for(Theater newTheaterData: newTheatersData) {
                            System.out.println(newTheaterData.getShowingMovie().getMovieName());
                        }
                        addTheatersDataToPanel(newTheatersData);

                        revalidate();
                        repaint();

                    }
                });
            }
        });
    	
    	add(scrollPane);
    	add(viewArchiveButton);
    }

    private JPanel createMoviePanel(Theater theaterData) {
        Movie theaterMovie = theaterData.getShowingMovie();
                MoviePanel = new JPanel();
		MoviePanel.setBackground(new Color(31, 41, 55));
		MoviePanel.setBounds(10, 11, 980, 300);
		MoviePanel.setPreferredSize(new Dimension(980, 300)); // Set preferred size
		MoviePanel.setLayout(null);
		
		theaterPanel = new JPanel();
		theaterPanel.setBackground(new Color(249, 115, 22));
		theaterPanel.setBounds(0, 0, 980, 35);	
		theaterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		theaterLabel = new JLabel(theaterData.getTheaterName().toUpperCase());
		theaterLabel.setForeground(new Color(255, 255, 255));
		theaterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		theaterPanel.add(theaterLabel);
		theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		
		moviePosterLabel = new JLabel();
		moviePosterLabel.setBounds(10, 46, 150, 243);
                moviePosterLabel.setPreferredSize(new Dimension(150, 243));
                
                ImageIcon iconImage = new ImageIcon(theaterMovie.getMoviePosterPicturePath());
                Image originalImage = iconImage.getImage();
                Image scaledImage = originalImage.getScaledInstance(moviePosterLabel.getWidth(), moviePosterLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                moviePosterLabel.setIcon(scaledImageIcon);
		
		movieName = new JLabel(theaterMovie.getMovieName());
		movieName.setForeground(new Color(255, 255, 255));
		movieName.setFont(new Font("Segoe UI", Font.BOLD, 20));
		movieName.setBounds(170, 46, 250, 30);
		
		moviePrice = new JLabel("₱ " + Helper.formatPrice(theaterMovie.getMoviePrice()));
		moviePrice.setForeground(new Color(255, 255, 255));
		moviePrice.setFont(new Font("Segoe UI", Font.BOLD, 17));
		moviePrice.setBounds(170, 80, 250, 20);
		
		genreLabel = new JLabel("Genre:");
		genreLabel.setForeground(new Color(255, 255, 255));
		genreLabel.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		genreLabel.setBounds(170, 120, 129, 20);
		
                String genres = (theaterMovie.getMovieGenre2() == null) ? theaterMovie.getMovieGenre1() : theaterMovie.getMovieGenre1() + ", " + theaterMovie.getMovieGenre2();
		genre = new JLabel(genres);
		genre.setForeground(new Color(255, 255, 255));
		genre.setFont(new Font("Segoe UI", Font.BOLD, 15));
		genre.setBounds(180, 145, 250, 20);
		
		durationLabel = new JLabel("Duration (minutes):");
		durationLabel.setForeground(new Color(255, 255, 255));
		durationLabel.setFont(new Font("Segoe UI", Font.ITALIC, 15));
		durationLabel.setBounds(170, 175, 150, 20);	
		
		duration = new JLabel(String.valueOf(theaterMovie.getDuration()));
		duration.setForeground(new Color(255, 255, 255));
		duration.setFont(new Font("Segoe UI", Font.BOLD, 15));
		duration.setBounds(180, 200, 165, 20);
		
		contentRatingLabel = new JLabel("Content Rating:");
		contentRatingLabel.setForeground(new Color(255, 255, 255));
		contentRatingLabel.setFont(new Font("Segoe UI",  Font.ITALIC, 15));
		contentRatingLabel.setBounds(170, 230, 129, 20);
		
		contentRating = new JLabel(theaterMovie.getContentRating());
		contentRating.setForeground(new Color(255, 255, 255));
		contentRating.setFont(new Font("Segoe UI", Font.BOLD, 15));
		contentRating.setBounds(180, 255, 250, 20);
	
		showDateTimePanel = new JPanel();
		showDateTimePanel.setBounds(400, 74, 542, 85);
		showDateTimePanel.setLayout(null);
		
		showDatePanel = new JPanel();
//		showDatePanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		showDatePanel.setBounds(10, 5, 522, 30);
		showDateTimePanel.add(showDatePanel);
		showDatePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
                String showDateStr = Helper.convertLocalDateToString(theaterMovie.getShowtimes().get(0).getShowDateTime().toLocalDate());
		showDate = new JLabel(showDateStr);
		showDate.setHorizontalAlignment(SwingConstants.LEFT);
		showDate.setFont(new Font("Segoe UI", Font.BOLD, 16));
		showDatePanel.add(showDate);
		
		updateMovieButton = new JButton("Update Movie");
		updateMovieButton.setForeground(new Color(255, 255, 255));
		updateMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		updateMovieButton.setBackground(new Color(255, 81, 90));
		updateMovieButton.setBounds(650, 260, 138, 30);
		
		
		updateMovieButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create a custom dialog
                        UpdateMovieDialog.theaterId = theaterData.getTheaterId();
                        UpdateMovieDialog.movieIdShowing = theaterData.getShowingMovie().getMovieId();
                        UpdateMovieDialog updateMovieDialog = new UpdateMovieDialog();
                        updateMovieDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
                        updateMovieDialog.setVisible(true);
                        
                        updateMovieDialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                             mainPanel.removeAll();
                             ArrayList<Theater> newTheatersData  = AdminDatabaseManager.retrieveTheaterData();
                             addTheatersDataToPanel(newTheatersData);
                             
                             revalidate();
                             repaint();
                            
                            }
                        });
                    }
                });
		
		archiveMovieButton = new JButton("Archive Movie");
		archiveMovieButton.setForeground(new Color(255, 255, 255));
		archiveMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		archiveMovieButton.setBackground(new Color(255, 81, 90));
		archiveMovieButton.setBounds(810, 259, 138, 30);
                archiveMovieButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Archive Movie", JOptionPane.YES_NO_OPTION);
                   
                        if (response == JOptionPane.YES_OPTION) {
                            AdminDatabaseManager.archiveMovie(theaterMovie.getMovieId());
                            mainPanel.removeAll();
                            theatersData = AdminDatabaseManager.retrieveTheaterData();
                            addTheatersDataToPanel(theatersData);
                            revalidate();
                             repaint();
                            
                        } 
                    }
                });
		
		MoviePanel.add(theaterPanel);
		MoviePanel.add(moviePosterLabel);
		MoviePanel.add(movieName);
		MoviePanel.add(moviePrice);
		MoviePanel.add(genreLabel);
		MoviePanel.add(durationLabel);
		MoviePanel.add(contentRatingLabel);
		MoviePanel.add(genre);
		MoviePanel.add(duration);
		MoviePanel.add(contentRating);
		MoviePanel.add(showDateTimePanel);
		
		showtimeTime1Panel = new JPanel();
		showtimeTime1Panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		showtimeTime1Panel.setBounds(3, 45, 172, 30);
		showDateTimePanel.add(showtimeTime1Panel);
		showtimeTime1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
                String showtime1 = Helper.getFormattedTime(theaterMovie.getShowtimes().get(0).getShowDateTime().toLocalTime());
		showtimeTime1 = new JLabel(showtime1);
		showtimeTime1.setHorizontalAlignment(SwingConstants.CENTER);
		showtimeTime1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		showtimeTime1Panel.add(showtimeTime1);
		
                String showtime2 = "";
                if(theaterMovie.getShowtimes().size() - 1 >= 1 ) {
                    showtime2 = Helper.getFormattedTime(theaterMovie.getShowtimes().get(1).getShowDateTime().toLocalTime());
                }
                
		showtimeTime2Panel = new JPanel();
		showtimeTime2Panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		showtimeTime2Panel.setBounds(185, 45, 172, 30);
		showDateTimePanel.add(showtimeTime2Panel);
		showtimeTime2Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showtimeTime2 = new JLabel(showtime2);
		showtimeTime2.setHorizontalAlignment(SwingConstants.CENTER);
		showtimeTime2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		showtimeTime2Panel.add(showtimeTime2);
		
		showtimeTime3Panel = new JPanel();
		showtimeTime3Panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		showtimeTime3Panel.setBounds(367, 45, 172, 30);
		showDateTimePanel.add(showtimeTime3Panel);
		showtimeTime3Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
                String showtime3 = "";
                if(theaterMovie.getShowtimes().size() - 1 >= 2 ) {
                    showtime3 = Helper.getFormattedTime(theaterMovie.getShowtimes().get(2).getShowDateTime().toLocalTime());
                }
		showtimeTime3 = new JLabel(showtime3);
		showtimeTime3.setHorizontalAlignment(SwingConstants.CENTER);
		showtimeTime3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		showtimeTime3Panel.add(showtimeTime3);
		MoviePanel.add(updateMovieButton);
		MoviePanel.add(archiveMovieButton);

		add(MoviePanel);
		
//		showtime1RemSeats = new JLabel("25/114");
//		showtime1RemSeats.setForeground(new Color(0, 255, 128));
//		showtime1RemSeats.setHorizontalAlignment(SwingConstants.CENTER);
//		showtime1RemSeats.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
//		showtime1RemSeats.setBounds(405, 168, 170, 21);
//		MoviePanel.add(showtime1RemSeats);
//		
//		showtime2RemSeats = new JLabel("25/114");
//		showtime2RemSeats.setHorizontalAlignment(SwingConstants.CENTER);
//		showtime2RemSeats.setForeground(new Color(0, 255, 128));
//		showtime2RemSeats.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
//		showtime2RemSeats.setBounds(585, 168, 170, 21);
//		MoviePanel.add(showtime2RemSeats);
//		
//		showtime3RemSeats = new JLabel("25/114");
//		showtime3RemSeats.setHorizontalAlignment(SwingConstants.CENTER);
//		showtime3RemSeats.setForeground(new Color(0, 255, 128));
//		showtime3RemSeats.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
//		showtime3RemSeats.setBounds(768, 168, 170, 21);
//		MoviePanel.add(showtime3RemSeats);
		
		add(MoviePanel);
		
		return MoviePanel;
	}

    private JPanel createEmptyMoviePanel(Theater theaterData) {
        emptyMoviePanel = new JPanel();
        emptyMoviePanel.setBackground(new Color(31, 41, 55));
        emptyMoviePanel.setPreferredSize(new Dimension(980, 300)); // Set preferred size
        emptyMoviePanel.setLayout(null);

        JPanel theaterPanel = new JPanel();
        theaterPanel.setBackground(new Color(249, 115, 22));
        theaterPanel.setBounds(0, 0, 980, 35);
        

        JLabel theaterLabel = new JLabel(theaterData.getTheaterName().toUpperCase());
        theaterLabel.setForeground(Color.WHITE);
        theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
        theaterPanel.add(theaterLabel);

        JButton addMovieButton = new JButton("Add Movie");
        addMovieButton.setBackground(new Color(255, 81, 90));
        addMovieButton.setForeground(Color.WHITE);
        addMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addMovieButton.setBounds(420, 140, 150, 40);
        addMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
                AddMovieDialog.theaterId = theaterData.getTheaterId();
            	AddMovieDialog addMovieDialog = new AddMovieDialog();
                
            	addMovieDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
            	addMovieDialog.setVisible(true);
                
                addMovieDialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                             mainPanel.removeAll();
                             ArrayList<Theater> newTheatersData  = AdminDatabaseManager.retrieveTheaterData();
                             addTheatersDataToPanel(newTheatersData);
                             
                             revalidate();
                             repaint();
                            
                            }
                        });
            }
        });
        
        emptyMoviePanel.add(theaterPanel);
        emptyMoviePanel.add(addMovieButton);

        return emptyMoviePanel;
    }
    
    private void addTheatersDataToPanel(ArrayList<Theater> theatersData) {
        for (Theater theaterData: theatersData) {
            JPanel newPanel;

            if(theaterData.getShowingMovie() == null) {
                newPanel = createEmptyMoviePanel(theaterData);
            } else {
                newPanel = createMoviePanel(theaterData);
            }
            mainPanel.add(newPanel);
            mainPanel.add(Box.createVerticalStrut(10)); // Add spacing between panels
        }
    }
    
}
