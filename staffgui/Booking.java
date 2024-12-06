package staffgui;

import Database.AdminDatabaseManager;
import Database.StaffDatabaseManager;
import Model.Authentication;
import Model.Seat;
import Model.StaffEmployee;
import Model.Theater;
import helper.Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import logingui.RegisterAdmin;

public class Booking extends JPanel {

    private static final long serialVersionUID = 1L;
    JFrame parent;
    private JPanel mainPanel;
    private JPanel moviesPanel;
    private JButton chooseShowtimeSeatButton;
    private JButton proceedToPaymentButton;
    
    //Fields
    private ButtonGroup buttonGroup = new ButtonGroup();
    private ArrayList<Theater> theatersData = StaffDatabaseManager.retrieveTheaterData();
    private String selectedTheaterId;
    private String selectedShowtimeId;
    private ArrayList<Seat> selectedSeats;

    public Booking(JFrame parent) {
    	this.parent = parent;
    	
    	setLayout(null);
    	
    	mainPanel = new JPanel();
    	mainPanel.setBounds(10, 11, 980, 670);
    	mainPanel.setLayout(null);
    	
    	moviesPanel = new JPanel();
    	moviesPanel.setBounds(0, 0, 980, 450);
    	mainPanel.add(moviesPanel);
    	moviesPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
    	
    	chooseShowtimeSeatButton = new JButton("Choose showtime and seat");
    	chooseShowtimeSeatButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
                

                String selectedTheaterId = Helper.returnActionCommandOfWhatisSelectedButton(buttonGroup);
                
                if(selectedTheaterId.equals("0")) {
                    JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Select a movie first.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                for(Theater theaterData: theatersData) {
                    if(String.valueOf(theaterData.getTheaterId()).equals(selectedTheaterId)) {
                        ShowtimeSeatDialog.showtimes = theaterData.getShowingMovie().getShowtimes();
                    }
                }
                
                // Create a custom dialog
            	ShowtimeSeatDialog showtimeSeatDialog = new ShowtimeSeatDialog();
            	showtimeSeatDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
            	showtimeSeatDialog.setVisible(true);
                
                showtimeSeatDialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                System.out.println("hey");
                    selectedShowtimeId= showtimeSeatDialog.getSelectedShowtimeId();
                    selectedSeats = showtimeSeatDialog.getSelectedSeats();
                            }
                        });
                    
            }
    	});
    	chooseShowtimeSeatButton.setForeground(Color.WHITE);
    	chooseShowtimeSeatButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	chooseShowtimeSeatButton.setBackground(new Color(255, 81, 90));
    	chooseShowtimeSeatButton.setBounds(365, 460, 230, 35);
    	mainPanel.add(chooseShowtimeSeatButton);
    	
    	proceedToPaymentButton = new JButton("Proceed to Payment");
    	proceedToPaymentButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
                
                if(selectedSeats == null || selectedSeats.isEmpty()) {
                    JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Select a showtime and seats first.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                    return;
                } 
                
                
                    for(Theater theaterData: theatersData) {
                        if(String.valueOf(theaterData.getTheaterId()).equals(selectedTheaterId)) {
                            PaymentDialog.theaterData = theaterData;
                        }
                    }
                    PaymentDialog.staffId = Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID;
                    PaymentDialog.selectedShowtimeId = selectedShowtimeId;
                    PaymentDialog.selectedSeats = selectedSeats;
                    PaymentDialog paymentDialog = new PaymentDialog();
                    paymentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


                    paymentDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
                    paymentDialog.setVisible(true);
                    
                    paymentDialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                            moviesPanel.removeAll();
                            theatersData = StaffDatabaseManager.retrieveTheaterData();
                            for(Theater theaterData: theatersData) {
                                if(theaterData.getShowingMovie() != null) {
                                     JPanel newPanel = createMoviePanel(theaterData);

                                    moviesPanel.add(newPanel);
                                }
                            }

                            revalidate();
                            repaint();
                            
                            }
                        });
                
                
                // Create a custom dialog 

            }
    	});
    	proceedToPaymentButton.setForeground(Color.WHITE);
    	proceedToPaymentButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	proceedToPaymentButton.setBackground(new Color(255, 81, 90));
    	proceedToPaymentButton.setBounds(740, 624, 230, 35);
    	mainPanel.add(proceedToPaymentButton);
        
        
        for(Theater theaterData: theatersData) {
            if(theaterData.getShowingMovie() != null) {
                 JPanel newPanel = createMoviePanel(theaterData);
    		
                moviesPanel.add(newPanel);
            }
        }
    	
    	add(mainPanel);
    }
    
    private JPanel createMoviePanel(Theater theaterData) {
    	JPanel moviePanel = new JPanel();
    	moviePanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
    	moviePanel.setPreferredSize(new Dimension(260, 425));
    	moviePanel.setLayout(null);
    	
    	JLabel moviePosterLabel = new JLabel();
    	moviePosterLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
    	moviePosterLabel.setBounds(55, 10, 150, 240);
        moviePosterLabel.setPreferredSize(new Dimension(150, 240));
        ImageIcon iconImage = new ImageIcon(theaterData.getShowingMovie().getMoviePosterPicturePath());
        Image originalImage = iconImage.getImage();
        Image scaledImage = originalImage.getScaledInstance(moviePosterLabel.getWidth(), moviePosterLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        moviePosterLabel.setIcon(scaledImageIcon);
    	
    	JLabel movieName = new JLabel(theaterData.getShowingMovie().getMovieName());
    	movieName.setFont(new Font("Segoe UI", Font.BOLD, 17));
    	movieName.setHorizontalAlignment(SwingConstants.CENTER);
    	movieName.setBounds(0, 260, 260, 25);
    	
    	JLabel moviePrice = new JLabel("₱ " + theaterData.getShowingMovie().getMoviePrice());
    	moviePrice.setHorizontalAlignment(SwingConstants.CENTER);
    	moviePrice.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 16));
    	moviePrice.setBounds(0, 290, 260, 25);
    	
        String genres = (theaterData.getShowingMovie().getMovieGenre2() == null) ? theaterData.getShowingMovie().getMovieGenre1() : theaterData.getShowingMovie().getMovieGenre1() + ", " + theaterData.getShowingMovie().getMovieGenre2();
    	JLabel movieGenre = new JLabel(genres);
    	movieGenre.setHorizontalAlignment(SwingConstants.CENTER);
    	movieGenre.setFont(new Font("Segoe UI", Font.BOLD, 14));
    	movieGenre.setBounds(0, 330, 260, 25);
    	
    	JLabel movieDuration = new JLabel(String.valueOf(theaterData.getShowingMovie().getDuration()) + " minutes");
    	movieDuration.setHorizontalAlignment(SwingConstants.CENTER);
    	movieDuration.setFont(new Font("Segoe UI", Font.ITALIC, 14));
    	movieDuration.setBounds(0, 360, 260, 25);
    	
    	JRadioButton movieRadButton = new JRadioButton("");
        buttonGroup.add(movieRadButton);
        movieRadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTheaterId = String.valueOf(theaterData.getTheaterId());
            }
         });
        movieRadButton.setActionCommand(String.valueOf(theaterData.getTheaterId()));
    	movieRadButton.setBorder(new EmptyBorder(0, 0, 0, 0));
    	movieRadButton.setForeground(new Color(15, 23, 42));
    	movieRadButton.setBounds(120, 390, 20, 25);	
    	
    	JPanel theaterPanel = new JPanel();
    	theaterPanel.setForeground(new Color(255, 255, 255));
    	theaterPanel.setBackground(new Color(249, 115, 22));
    	theaterPanel.setBounds(0, 0, 45, 44);
    	theaterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	JLabel theaterLabel = new JLabel(String.valueOf(theaterData.getTheaterId()));
    	theaterLabel.setForeground(new Color(255, 255, 255));
    	theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
    	theaterLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	theaterPanel.add(theaterLabel);
    	
    	moviePanel.add(moviePosterLabel);
    	moviePanel.add(movieName);
    	moviePanel.add(moviePrice);
    	moviePanel.add(movieGenre);
    	moviePanel.add(movieDuration);
    	moviePanel.add(movieRadButton);
    	moviePanel.add(theaterPanel);
    	
    	return moviePanel;
    }
}
