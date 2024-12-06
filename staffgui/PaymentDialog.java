package staffgui;

import Database.StaffDatabaseManager;
import Model.Authentication;
import Model.Movie;
import Model.Seat;
import Model.Showtime;
import Model.Theater;
import helper.Helper;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import static java.awt.Component.TOP_ALIGNMENT;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import static java.awt.image.ImageObserver.PROPERTIES;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PaymentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel dynamicPanel;
	private JTextField cashTenderTF;
	private JTextField refNumTF;
	
	private JLabel moviePosterLabel;
	private JLabel movieName;
	private JLabel moviePrice;
	private JLabel movieShowtime;
	private JLabel seats;
	private JLabel seatsLabel;
	private JLabel numberOfTixLabel;
	private JSpinner ticketSpinner;
	private JLabel paymentMethodLabel;
	private JComboBox<String> paymentMethodDropdown;
	private JButton payButton;
        
        public static String staffId;
        public static Theater theaterData;
        public static String selectedShowtimeId;
        public static ArrayList<Seat> selectedSeats;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PaymentDialog dialog = new PaymentDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PaymentDialog() {
            Movie movieChosen = theaterData.getShowingMovie();
		setBounds(100, 100, 500, 600);
		setLayout(null);
                setTitle("Payment Window");
		
		moviePosterLabel = new JLabel();
		moviePosterLabel.setBounds(20, 20, 150, 240);
                moviePosterLabel.setPreferredSize(new Dimension(150, 240));
                
                ImageIcon iconImage = new ImageIcon(theaterData.getShowingMovie().getMoviePosterPicturePath());
                Image originalImage = iconImage.getImage();
                Image scaledImage = originalImage.getScaledInstance(moviePosterLabel.getWidth(), moviePosterLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                moviePosterLabel.setIcon(scaledImageIcon);

		movieName = new JLabel(movieChosen.getMovieName());
		movieName.setFont(new Font("Segoe UI", Font.BOLD, 15));
		movieName.setBounds(195, 20, 200, 20);

		moviePrice = new JLabel("₱ " + movieChosen.getMoviePrice());
		moviePrice.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		moviePrice.setBounds(195, 50, 200, 20);

                String showDateStr = "";
                String showtime1Str = "";
                for(Showtime showtime: movieChosen.getShowtimes()) {
                    if(showtime.getShowtimeId().equals(selectedShowtimeId)) {
                        showDateStr = Helper.convertLocalDateToString(showtime.getShowDateTime().toLocalDate());
                        showtime1Str = Helper.getFormattedTime(showtime.getShowDateTime().toLocalTime());
                    }
                }
                
		movieShowtime = new JLabel(showDateStr + " " + showtime1Str);
		movieShowtime.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		movieShowtime.setBounds(195, 90, 256, 20);
                
                String seatLabel = "";
                for(Seat selectedSeat: selectedSeats){
                    seatLabel += selectedSeat.getSeatNumber() + ", ";
                }
		seats = new JLabel(seatLabel);
		seats.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		seats.setBounds(255, 130, 219, 20);

		seatsLabel = new JLabel("Seat/s:");
		seatsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		seatsLabel.setBounds(195, 130, 56, 20);
		
		numberOfTixLabel = new JLabel(selectedSeats.size() + " Tickets");
		numberOfTixLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		numberOfTixLabel.setBounds(195, 170, 107, 20);
		
		
		paymentMethodLabel = new JLabel("Payment Method:");
		paymentMethodLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		paymentMethodLabel.setBounds(20, 310, 130, 20);
		
		String[] paymentOptions = {"Cash", "GCash"};
		paymentMethodDropdown = new JComboBox<>(paymentOptions);
		paymentMethodDropdown.setBounds(150, 310, 120, 25);
                paymentMethodLabel.setBounds(20, 310, 130, 20);
                
                JLabel totalLabel = new JLabel("Total:");
                totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		totalLabel.setBounds(20, 340, 130, 20);
               
                float totalPrice = movieChosen.getMoviePrice() * selectedSeats.size();
                JLabel total = new JLabel("₱ " + totalPrice);
                total.setFont(new Font("Segoe UI", Font.BOLD, 14));
		total.setBounds(70, 340, 130, 20);
                
		dynamicPanel = new JPanel();
		dynamicPanel.setBounds(20, 380, 440, 95);
		dynamicPanel.setLayout(new GridLayout(0, 1, 0, 0));
		updateDynamicPanel("Cash");
		
		
		paymentMethodDropdown.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedPaymentMethod = (String) paymentMethodDropdown.getSelectedItem();
                        updateDynamicPanel(selectedPaymentMethod);

                }
            

        });
		

		
		payButton = new JButton("Pay");
		payButton.setForeground(Color.WHITE);
		payButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		payButton.setBackground(new Color(255, 81, 90));
		payButton.setBounds(324, 515, 150, 35);
                payButton.addActionListener(new ActionListener() {
    		@Override
                    public void actionPerformed(ActionEvent e) {
                        
                        int numberOfTickets = selectedSeats.size();
                        
                        String selectedPaymentMethod = (String) paymentMethodDropdown.getSelectedItem();
                        
 
 
                        
                        if(selectedPaymentMethod.equals("Cash")) {
                            float cashTender = Float.parseFloat(cashTenderTF.getText());
                            
                            if(cashTender < totalPrice) {
                                JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Insufficient amount. Please try again", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            
                            if(!StaffDatabaseManager.addCashTransactionToBookingTransaction(staffId, 
                                theaterData, 
                                selectedShowtimeId, 
                                numberOfTickets, 
                                totalPrice, 
                                cashTender, 
                                selectedPaymentMethod, 
                                selectedSeats)) {
                             JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Invalid action. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                             
                         } else {
                                JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Payment Successful.", "Payment Success", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                
                                 ReceiptDialog receiptDialog = new ReceiptDialog();

                                    receiptDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
                                    receiptDialog.setVisible(true);
                            }
                        } else {
                            String refNum = refNumTF.getText();
                            System.out.println(refNum);
                            
                            if(!StaffDatabaseManager.verifyReferenceNumber(refNum)) {
                                JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Invalid reference number. Please try again.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                            if(!StaffDatabaseManager.addGCashTransactionToBookingTransaction(staffId, 
                                theaterData, 
                                selectedShowtimeId, 
                                numberOfTickets, 
                                totalPrice, 
                                selectedPaymentMethod, 
                                selectedSeats, refNum)) {
                             JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Invalid action. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                         } else {
                                JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Payment Successful.", "Payment Success", JOptionPane.INFORMATION_MESSAGE);
                                
                                dispose();
                                
                                ReceiptDialog receiptDialog = new ReceiptDialog();

                                    receiptDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
                                    receiptDialog.setVisible(true);
                                    
  

                                    
                            }
                        } 
                         
                        
                    }
                });
		add(payButton);
		
		add(moviePosterLabel);
		add(movieName);
		add(moviePrice);
		add(movieShowtime);
		add(seats);
		add(seatsLabel);
		add(numberOfTixLabel);
		add(paymentMethodLabel);
		add(paymentMethodDropdown);
                add(totalLabel);
                add(total);
		add(dynamicPanel);
		add(payButton);

	}
	
	private void updateDynamicPanel(String selectedPaymentMethod) {
		dynamicPanel.removeAll();
        
        if ("Cash".equals(selectedPaymentMethod)) {
    		JLabel cashTenderLabel = new JLabel("Cash Tender:");
    		cashTenderLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
    		
    		
    		cashTenderTF = new JTextField();
    		
    		cashTenderTF.setColumns(10);
    		dynamicPanel.add(cashTenderLabel);
    		dynamicPanel.add(cashTenderTF);
        } else if ("GCash".equals(selectedPaymentMethod)) {
			JLabel lblReferenceNumber = new JLabel("Reference Number:");
			lblReferenceNumber.setFont(new Font("Segoe UI", Font.BOLD, 14));
			dynamicPanel.add(lblReferenceNumber);

			refNumTF = new JTextField();
			dynamicPanel.add(refNumTF);
			refNumTF.setColumns(10);
        }
        
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
	}
}
