package staffgui;

import Model.Seat;
import Model.Showtime;
import helper.Helper;
import java.awt.FlowLayout;

import javax.swing.*;
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
import java.util.ArrayList;

public class ShowtimeSeatDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JButton saveMovieButton;
	private JPanel theaterPanel;
	private JLabel theaterLabel;
	private JLabel showtimesLabel;
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
	
	private JPanel showtimesMainPanel;
	
	private JButton saveChoiceButton;
	
	private ButtonGroup showtimesBtnGroup = new ButtonGroup();
        
        public static ArrayList<Showtime> showtimes;
        
        private String selectedShowtimeId;
        private ArrayList<String> selectedSeatNumbers = new ArrayList<>();
        private ArrayList<Seat> selectedSeats = new ArrayList<>();
        
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
		showtimesLabel.setBounds(10, 40, 175, 20);;
		
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
		
//		for (int row = 1; row <= 8; row++) {
//			for (int col = 1; col <= 16; col++) {
//				
//				String seatLabel = " ";
//				
//				if (col == 5 || col == 12) {
//					seatLabel = "  ";
//				} else if (col <= 4) {
//					seatLabel = (char) ('A' + row - 1) + String.valueOf(col);
//				} else if (col <= 11) {
//					seatLabel = (char) ('A' + row - 1) + String.valueOf(col - 1);
//				} else if (col <= 16) {
//					seatLabel = (char) ('A' + row - 1) + String.valueOf(col - 2);
//				}
//				
//
//				JButton seatButton = new JButton(seatLabel);
//				seatButton.setFocusable(false);
//				if (col == 5 || col == 12) {
//					seatButton.setVisible(false);
//				}
//				seatsPanel.add(seatButton);
//				
//			}
//		}
		
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
                
                saveChoiceButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                 });
		
		getContentPane().add(saveMovieButton);
		getContentPane().add(theaterPanel);
		getContentPane().add(showtimesLabel);
		getContentPane().add(seatsAvailableLabel);
		getContentPane().add(seatsAvailableMainPanel);
		getContentPane().add(colorTaken);
		getContentPane().add(takenLabel);
		getContentPane().add(availableLabel);
		getContentPane().add(colorAvailable);
		getContentPane().add(selectedLabel);
		getContentPane().add(colorSelected);
		getContentPane().add(saveChoiceButton);
		
		showtimesMainPanel = new JPanel();
		showtimesMainPanel.setBounds(20, 71, 237, 98);
		getContentPane().add(showtimesMainPanel);
		
		addRadButtonsToButtonPanel(showtimes);
	}
	
	private void addRadButtonsToButtonPanel(ArrayList<Showtime> showtimes) {

            for(Showtime showtime: showtimes) {
                String showDateStr = Helper.convertLocalDateToString(showtime.getShowDateTime().toLocalDate());
                String showtime1Str = Helper.getFormattedTime(showtime.getShowDateTime().toLocalTime());
                
                JRadioButton showtimeRadButton = new JRadioButton(showDateStr + " " + showtime1Str);
                showtimesBtnGroup.add(showtimeRadButton);
                
                showtimeRadButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectedShowtimeId = showtime.getShowtimeId();
                        addSeatsToSeatsPanel(showtime.getSeats());
                    }
            });
                showtimesMainPanel.add(showtimeRadButton);
            }
	}
        
        private void addSeatsToSeatsPanel(ArrayList<Seat> seats) {
            seatsPanel.removeAll();
            for (int row = 0; row <= 7; row++) {
		for (int col = 1; col <= 16; col++) {
                    
                    JToggleButton seatButton = new JToggleButton();
                    seatButton.setFocusable(false);
                    
                    Seat seat = null;
                    if (col == 5 || col == 12) {
                        seatButton.setVisible(false);
                        seatsPanel.add(seatButton);
                        continue;
                    } else if (col <= 4) {
                        seat = seats.get(14 * row + (col - 1));
                    } else if (col <= 11) {
                        seat = seats.get(14 * row + (col - 2));
                    } else if (col <= 16) {
                        seat = seats.get(14 * row + (col - 3));
                    }
                    
                    final Seat currentSeat = seat;
                    
                    String seatLabel = seat.getSeatNumber();
                    seatButton.setText(seatLabel);

                    seatButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(seatButton.isSelected()) {  
                                selectedSeats.add(currentSeat);
                            } else {
                                for(int i = 0; i < selectedSeats.size(); i++) {
                                    if(selectedSeats.get(i).getSeatNumber().equals(seatLabel)) {
                                        selectedSeats.remove(i);
                                    }
                                }   
                            }
                            
                            for(Seat selectedSeat: selectedSeats) {
                                System.out.println(selectedSeat.getSeatNumber());
                            }
                        }
                    });
                    
                    
                    if(seat.getStatus().equals("Reserved")) {
                        seatButton.setEnabled(false);
                    }
                    
                    seatsPanel.add(seatButton);		
		}
            }
            
            revalidate();
            repaint();
        }
        
        public String getSelectedShowtimeId() {
            return this.selectedShowtimeId;
        }
        
        public ArrayList<Seat> getSelectedSeats() {
            return this.selectedSeats;
        }
}


