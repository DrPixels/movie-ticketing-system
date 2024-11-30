package admingui;

import Database.AdminDatabaseManager;
import Model.Movie;
import helper.Helper;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JTextArea;

public class ArchivedMoviesDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel titlePanel;
	private JLabel listOfArchiveStaffLabel;
	private JPanel staffMainPanel;
	private JScrollPane scrollPane;
	private JLabel searchLabel;
	private JTextField searchMovieTF;
        
        private ArrayList<Movie> archivedMovies = AdminDatabaseManager.retrieveArchivedMovies();

	public static void main(String[] args) {
		try {
			ArchivedMoviesDialog dialog = new ArchivedMoviesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArchivedMoviesDialog() {
        setBounds(100, 100, 1175, 685);
        getContentPane().setLayout(null);

        titlePanel = new JPanel();
        titlePanel.setForeground(Color.WHITE);
        titlePanel.setBackground(new Color(249, 115, 22));
        titlePanel.setBounds(0, 0, 1159, 40);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        listOfArchiveStaffLabel = new JLabel("LIST OF ARCHIVED MOVIES");
        listOfArchiveStaffLabel.setForeground(Color.WHITE);
        listOfArchiveStaffLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
        titlePanel.add(listOfArchiveStaffLabel);


        staffMainPanel = new JPanel();
//        staffMainPanel.setBounds(10, 0, 758, 296);
        staffMainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));

        staffMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        staffMainPanel.setPreferredSize(new Dimension(800, 1000));
        
        for (Movie archivedMovie: archivedMovies) {
            JPanel newPanel = createMoviePanel(archivedMovie);
            staffMainPanel.add(newPanel);
        }
  

        // Add scroll pane for the staffMainPanel
        scrollPane = new JScrollPane(staffMainPanel);
        scrollPane.setBounds(20, 120, 1120, 515);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Search label and text field
        searchLabel = new JLabel("Search:");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchLabel.setBounds(875, 85, 50, 25);

        searchMovieTF = new JTextField();
        searchMovieTF.setColumns(10);
        searchMovieTF.setBounds(930, 80, 190, 30);
        
        getContentPane().add(titlePanel);
        getContentPane().add(scrollPane);
        getContentPane().add(searchLabel);
        getContentPane().add(searchMovieTF);

    }
	
	private JPanel createMoviePanel(Movie archivedMovie) {
            JPanel moviePanel = new JPanel();
            moviePanel.setBackground(new Color(214, 217, 223));
            moviePanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
    //        moviePanel.setBounds(100, 6, 350, 327);
            moviePanel.setPreferredSize(new Dimension(350,327));
            moviePanel.setLayout(null);

            JLabel moviePosterLabel = new JLabel();
            moviePosterLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
            moviePosterLabel.setBounds(20, 20, 125, 175);  
            moviePosterLabel.setPreferredSize(new Dimension(125, 175));
            
            ImageIcon iconImage = new ImageIcon(archivedMovie.getMoviePosterPicturePath());
            Image originalImage = iconImage.getImage();
            Image scaledImage = originalImage.getScaledInstance(moviePosterLabel.getWidth(), moviePosterLabel.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
            moviePosterLabel.setIcon(scaledImageIcon);


            JButton restoreStaffButton = new JButton("Restore");
            restoreStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
            restoreStaffButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    }
            });

            restoreStaffButton.setBounds(225, 290, 115, 25);    

            JTextArea movieTitle = new JTextArea();
            movieTitle.setOpaque(false);
            movieTitle.setText(archivedMovie.getMovieName());
            movieTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
            movieTitle.setLineWrap(true);
            movieTitle.setWrapStyleWord(true);
            movieTitle.setBounds(155, 20, 185, 40);

            JLabel moviePrice = new JLabel("₱ " + archivedMovie.getMoviePrice());
            moviePrice.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            moviePrice.setBounds(155, 70, 185, 14);    

            String genres = (archivedMovie.getMovieGenre2() == null) ? archivedMovie.getMovieGenre1() : archivedMovie.getMovieGenre1() + ", " + archivedMovie.getMovieGenre2();
            JLabel movieGenre = new JLabel(genres);
            movieGenre.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            movieGenre.setBounds(155, 95, 185, 14);

            JLabel movieDuration = new JLabel(String.valueOf(archivedMovie.getDuration()));
            movieDuration.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            movieDuration.setBounds(155, 120, 185, 14);

            JLabel movieContentRating = new JLabel(archivedMovie.getContentRating());
            movieContentRating.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            movieContentRating.setBounds(155, 145, 185, 14);
            
            String showDateStr = Helper.convertLocalDateToString(archivedMovie.getShowtimes().get(0).getShowDateTime().toLocalDate());
            String showtime1Str = Helper.getFormattedTime(archivedMovie.getShowtimes().get(0).getShowDateTime().toLocalTime());
            JLabel showtime1 = new JLabel(showDateStr + " " + showtime1Str);
            showtime1.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
            showtime1.setBounds(20, 205, 320, 14);

            String showtime2Str = "";
                if(archivedMovie.getShowtimes().size() - 1 >= 1 ) {
                   showtime2Str = showDateStr + " " + Helper.getFormattedTime(archivedMovie.getShowtimes().get(1).getShowDateTime().toLocalTime());
                }
            JLabel showtime2 = new JLabel(showtime2Str);
            showtime2.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
            showtime2.setBounds(20, 225, 320, 14);  

            String showtime3Str = "";
                if(archivedMovie.getShowtimes().size() - 1 >= 1 ) {
                   showtime3Str = showDateStr + " " + Helper.getFormattedTime(archivedMovie.getShowtimes().get(2).getShowDateTime().toLocalTime());
                }
            JLabel showtime3 = new JLabel(showtime3Str);
            showtime3.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
            showtime3.setBounds(20, 245, 320, 14);   

            moviePanel.add(moviePosterLabel);
            moviePanel.add(restoreStaffButton); 
            moviePanel.add(movieTitle);
            moviePanel.add(moviePrice);
            moviePanel.add(movieGenre);
            moviePanel.add(movieDuration);
            moviePanel.add(movieContentRating);
            moviePanel.add(showtime1);
            moviePanel.add(showtime2);
            moviePanel.add(showtime3);

            return moviePanel;
    }

}
