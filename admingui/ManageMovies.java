package admingui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageMovies extends JPanel {

    private static final long serialVersionUID = 1L;
    JFrame parent;
    
    private JScrollPane scrollPane;
    private JPanel mainPanel;

    private JButton viewArchiveButton;
    
    private JPanel MoviePanel;
    
    private JPanel theaterPanel;
    private JLabel theaterLabel;
    
    private JPanel moviePosterPanel;
    private JLabel movieName;
    private JLabel moviePrice;
    
    private JLabel genreLabel;
    private JLabel genre;
    
    private JLabel durationLabel;
    private JLabel duration;
    
    private JLabel contentRatingLabel;
    private JLabel contentRating;
    
    private JPanel showtime1Panel;
    private JPanel showtimeDate1Panel;
    private JLabel showtime1Date;
    private JPanel showtimeTime1Panel;
    private JLabel showtimeTime1;
    
    private JPanel showtime2Panel;
    private JPanel showtimeDate2Panel;
    private JLabel showtime2Date;
    private JPanel showtimeTime2Panel;
    private JLabel showtimeTime2;
    
    private JPanel showtime3Panel;
    private JPanel showtimeDate3Panel;
    private JLabel showtime3Date;
    private JPanel showtimeTime3Panel;
    private JLabel showtimeTime3;
    
    private JButton updateMovieButton;
    private JButton archiveMovieButton;

    private JPanel emptyMoviePanel;

    public ManageMovies(JFrame parent) {
    	this.parent = parent;
    	
    	setLayout(null);
    	
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    	


        for (int i = 0; i < 3; i++) {
            JPanel newPanel;

            if (i % 2 == 0) {
                newPanel = createMoviePanel();
            } else {
                newPanel = createEmptyMoviePanel();
            }

            mainPanel.add(newPanel);
            mainPanel.add(Box.createVerticalStrut(10)); // Add spacing between panels
        }
        
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

            	archivedMoviesDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
            	archivedMoviesDialog.setVisible(true);
            }
        });
    	
    	add(scrollPane);
    	add(viewArchiveButton);
    }

    private JPanel createMoviePanel() {
		MoviePanel = new JPanel();
		MoviePanel.setBackground(new Color(31, 41, 55));
		MoviePanel.setBounds(10, 11, 980, 300);
		MoviePanel.setPreferredSize(new Dimension(980, 300)); // Set preferred size
		MoviePanel.setLayout(null);
		
		theaterPanel = new JPanel();
		theaterPanel.setBackground(new Color(249, 115, 22));
		theaterPanel.setBounds(0, 0, 980, 35);	
		theaterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		theaterLabel = new JLabel("THEATRE 1");
		theaterLabel.setForeground(new Color(255, 255, 255));
		theaterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		theaterPanel.add(theaterLabel);
		theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		
		moviePosterPanel = new JPanel();
		moviePosterPanel.setBounds(10, 46, 150, 243);
		
		movieName = new JLabel("Black to the Future");
		movieName.setForeground(new Color(255, 255, 255));
		movieName.setFont(new Font("Segoe UI", Font.BOLD, 20));
		movieName.setBounds(170, 46, 250, 20);
		
		moviePrice = new JLabel("₱ 450");
		moviePrice.setForeground(new Color(255, 255, 255));
		moviePrice.setFont(new Font("Segoe UI", Font.BOLD, 17));
		moviePrice.setBounds(170, 80, 250, 20);
		
		genreLabel = new JLabel("Genre:");
		genreLabel.setForeground(new Color(255, 255, 255));
		genreLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		genreLabel.setBounds(170, 120, 129, 20);
		
		genre = new JLabel("Horror, Comedy");
		genre.setForeground(new Color(255, 255, 255));
		genre.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		genre.setBounds(180, 145, 250, 20);
		
		durationLabel = new JLabel("Duration:");
		durationLabel.setForeground(new Color(255, 255, 255));
		durationLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		durationLabel.setBounds(170, 175, 129, 20);	
		
		duration = new JLabel("3hr 1min");
		duration.setForeground(new Color(255, 255, 255));
		duration.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		duration.setBounds(180, 200, 165, 20);
		
		contentRatingLabel = new JLabel("Content Rating:");
		contentRatingLabel.setForeground(new Color(255, 255, 255));
		contentRatingLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentRatingLabel.setBounds(170, 230, 129, 20);
		
		contentRating = new JLabel("Rated 18");
		contentRating.setForeground(new Color(255, 255, 255));
		contentRating.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		contentRating.setBounds(180, 255, 250, 20);
	
		showtime1Panel = new JPanel();
		showtime1Panel.setBounds(400, 74, 172, 60);
		showtime1Panel.setLayout(null);
		
		showtimeDate1Panel = new JPanel();
		showtimeDate1Panel.setBounds(0, 0, 172, 30);
		showtime1Panel.add(showtimeDate1Panel);
		showtimeDate1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showtime1Date = new JLabel("November 03, 2024");
		showtime1Date.setHorizontalAlignment(SwingConstants.LEFT);
		showtime1Date.setFont(new Font("Segoe UI", Font.BOLD, 14));
		showtimeDate1Panel.add(showtime1Date);
		
		showtimeTime1Panel = new JPanel();
		showtimeTime1Panel.setBounds(0, 30, 172, 30);
		showtime1Panel.add(showtimeTime1Panel);
		showtimeTime1Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showtimeTime1 = new JLabel("03:00 PM");
		showtimeTime1.setHorizontalAlignment(SwingConstants.CENTER);
		showtimeTime1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		showtimeTime1Panel.add(showtimeTime1);
		
		showtime2Panel = new JPanel();
		showtime2Panel.setLayout(null);
		showtime2Panel.setBounds(585, 74, 172, 60);	
		
		showtimeDate2Panel = new JPanel();
		showtimeDate2Panel.setBounds(0, 0, 172, 30);
		showtime2Panel.add(showtimeDate2Panel);
		showtimeDate2Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showtime2Date = new JLabel("November 03, 2024");
		showtime2Date.setHorizontalAlignment(SwingConstants.LEFT);
		showtime2Date.setFont(new Font("Segoe UI", Font.BOLD, 14));
		showtimeDate2Panel.add(showtime2Date);
		
		showtimeTime2Panel = new JPanel();
		showtimeTime2Panel.setBounds(0, 30, 172, 30);
		showtime2Panel.add(showtimeTime2Panel);
		showtimeTime2Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showtimeTime2 = new JLabel("03:00 PM");
		showtimeTime2.setHorizontalAlignment(SwingConstants.CENTER);
		showtimeTime2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		showtimeTime2Panel.add(showtimeTime2);
		
		showtime3Panel = new JPanel();
		showtime3Panel.setLayout(null);
		showtime3Panel.setBounds(770, 74, 172, 60);
		
		showtimeDate3Panel = new JPanel();
		showtimeDate3Panel.setBounds(0, 0, 172, 30);
		showtime3Panel.add(showtimeDate3Panel);
		showtimeDate3Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showtime3Date = new JLabel("November 03, 2024");
		showtime3Date.setHorizontalAlignment(SwingConstants.LEFT);
		showtime3Date.setFont(new Font("Segoe UI", Font.BOLD, 14));
		showtimeDate3Panel.add(showtime3Date);
		
		showtimeTime3Panel = new JPanel();
		showtimeTime3Panel.setBounds(0, 30, 172, 30);
		showtime3Panel.add(showtimeTime3Panel);
		showtimeTime3Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		showtimeTime3 = new JLabel("03:00 PM");
		showtimeTime3.setHorizontalAlignment(SwingConstants.CENTER);
		showtimeTime3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		showtimeTime3Panel.add(showtimeTime3);
		
		updateMovieButton = new JButton("Update Movie");
		updateMovieButton.setForeground(new Color(255, 255, 255));
		updateMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		updateMovieButton.setBackground(new Color(255, 81, 90));
		updateMovieButton.setBounds(650, 260, 138, 30);
		
		
		updateMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
            	UpdateMovieDialog updateMovieDialog = new UpdateMovieDialog();

            	updateMovieDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
            	updateMovieDialog.setVisible(true);
            }
        });
		
		archiveMovieButton = new JButton("Archive Movie");
		archiveMovieButton.setForeground(new Color(255, 255, 255));
		archiveMovieButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		archiveMovieButton.setBackground(new Color(255, 81, 90));
		archiveMovieButton.setBounds(810, 259, 138, 30);
		
		MoviePanel.add(theaterPanel);
		MoviePanel.add(moviePosterPanel);
		MoviePanel.add(movieName);
		MoviePanel.add(moviePrice);
		MoviePanel.add(genreLabel);
		MoviePanel.add(durationLabel);
		MoviePanel.add(contentRatingLabel);
		MoviePanel.add(genre);
		MoviePanel.add(duration);
		MoviePanel.add(contentRating);
		MoviePanel.add(showtime1Panel);
		MoviePanel.add(showtime2Panel);
		MoviePanel.add(showtime3Panel);
		MoviePanel.add(updateMovieButton);
		MoviePanel.add(archiveMovieButton);

		add(MoviePanel);
		
		return MoviePanel;
	}

    private JPanel createEmptyMoviePanel() {
        emptyMoviePanel = new JPanel();
        emptyMoviePanel.setBackground(new Color(31, 41, 55));
        emptyMoviePanel.setPreferredSize(new Dimension(980, 300)); // Set preferred size
        emptyMoviePanel.setLayout(null);

        JPanel theaterPanel = new JPanel();
        theaterPanel.setBackground(new Color(249, 115, 22));
        theaterPanel.setBounds(0, 0, 980, 35);
        

        JLabel theaterLabel = new JLabel("THEATRE 1");
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
            	AddMovieDialog addMovieDialog = new AddMovieDialog();

            	addMovieDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
            	addMovieDialog.setVisible(true);
            }
        });
        
        emptyMoviePanel.add(theaterPanel);
        emptyMoviePanel.add(addMovieButton);

        return emptyMoviePanel;
    }
}
