package staffgui;

import Database.AdminDatabaseManager;
import Database.StaffDatabaseManager;
import Model.BookingTransaction;
import Model.Movie;
import Model.Seat;
import Model.Showtime;
import Model.StaffEmployee;
import helper.Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import logingui.RegisterAdmin;
import static staffgui.PaymentDialog.selectedSeats;
import static staffgui.PaymentDialog.selectedShowtimeId;
import static staffgui.PaymentDialog.staffId;
import static staffgui.PaymentDialog.theaterData;

public class BookingHistory extends JPanel {

    private static final long serialVersionUID = 1L;
    JFrame parent;
    private JPanel mainPanel;
    
    private JLabel enterBookTransacIdLabel;
    private JTextField enterBookTransacIdTF;
    private JButton searchButton;

    private JPanel titlePanel;
    private JLabel viewBookingHistoryLabel;

    private JPanel receiptPanel;
    

    public BookingHistory(JFrame parent) {
    	this.parent = parent;
    	
    	setLayout(null);
    	
    	enterBookTransacIdLabel = new JLabel("Enter Booking Transaction ID:");
    	enterBookTransacIdLabel.setForeground(new Color(0, 0, 0));
    	enterBookTransacIdLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	enterBookTransacIdLabel.setBounds(10, 105, 220, 20);
    	
    	enterBookTransacIdTF = new JTextField();
    	enterBookTransacIdTF.setBounds(224, 100, 600, 30);
    	enterBookTransacIdTF.setColumns(10);
    	
    	searchButton = new JButton("Search");
    	searchButton.setForeground(Color.WHITE);
    	searchButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	searchButton.setBackground(new Color(255, 81, 90));
    	searchButton.setBounds(845, 100, 144, 30);
        searchButton.addActionListener(new ActionListener() {
    		@Override
                    public void actionPerformed(ActionEvent e) {
                        String bookingTransactionId = enterBookTransacIdTF.getText();  
                        BookingTransaction bookingTransaction = StaffDatabaseManager.retrieveBookingTransaction(bookingTransactionId);
                        
                        if(bookingTransaction != null) {
                            addReceiptToPanel(bookingTransaction); 
                        } else {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "The booking transaction ID you entered doesn't exist.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                        }
                          
                    }
        });
    	
    	titlePanel = new JPanel();
    	titlePanel.setForeground(Color.WHITE);
    	titlePanel.setBackground(new Color(249, 115, 22));
    	titlePanel.setBounds(9, 11, 980, 40);
    	titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	viewBookingHistoryLabel = new JLabel("VIEW BOOKING HISTORY");
    	viewBookingHistoryLabel.setForeground(Color.WHITE);
    	viewBookingHistoryLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
    	titlePanel.add(viewBookingHistoryLabel);
        
        receiptPanel = new JPanel();
    	receiptPanel.setBorder(new LineBorder(new Color(255, 81, 90), 2));
    	receiptPanel.setBounds(320, 155, 325, 500);
    	receiptPanel.setLayout(null);
        
        add(enterBookTransacIdLabel);
    	add(enterBookTransacIdTF);
    	add(searchButton);
    	add(titlePanel);
        add(receiptPanel);
        
    }
    
    private void addReceiptToPanel(BookingTransaction bookingTransaction) {
        
        receiptPanel.removeAll();
        
        JPanel receiptTitlePanel = new JPanel();
    	receiptTitlePanel.setBackground(new Color(249, 115, 22));
    	receiptTitlePanel.setBounds(0, 0, 325, 60);
    	receiptPanel.add(receiptTitlePanel);
    	
    	JLabel systemName = new JLabel("CineBook");
    	receiptTitlePanel.add(systemName);
    	systemName.setForeground(new Color(255, 255, 255));
    	systemName.setBackground(new Color(249, 115, 22));
    	systemName.setHorizontalAlignment(SwingConstants.CENTER);
    	systemName.setFont(new Font("Segoe UI", Font.BOLD, 40));

        JLabel movieNameLabel = new JLabel("Movie Name:");
    	movieNameLabel.setForeground(new Color(0, 0, 0));
    	movieNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	movieNameLabel.setBounds(10, 211, 90, 20);
    	receiptPanel.add(movieNameLabel);
    	
        Movie movie = AdminDatabaseManager.retrieveMovieById(bookingTransaction.getMovieId());
    	JLabel movieName = new JLabel();
    	movieName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	movieName.setText(movie.getMovieName());
    	movieName.setBounds(110, 210, 205, 20);
    	receiptPanel.add(movieName);
    	
    	JLabel bookingTransactionValue = new JLabel();
    	bookingTransactionValue.setText(bookingTransaction.getBookingTransactionId());
    	bookingTransactionValue.setFont(new Font("Segoe UI", Font.ITALIC, 12));
    	bookingTransactionValue.setBounds(10, 90, 305, 20);
    	receiptPanel.add(bookingTransactionValue);
    	
    	JLabel bookingTransactionIDLabel = new JLabel("Booking Transaction ID:");
    	bookingTransactionIDLabel.setForeground(Color.BLACK);
    	bookingTransactionIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	bookingTransactionIDLabel.setBounds(10, 70, 305, 20);
    	receiptPanel.add(bookingTransactionIDLabel);
    	
    	JLabel amountLabel = new JLabel("Amount:");
    	amountLabel.setForeground(Color.BLACK);
    	amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	amountLabel.setBounds(10, 352, 90, 20);
    	receiptPanel.add(amountLabel);
    	
    	JLabel amount = new JLabel("₱ " + bookingTransaction.getTransactionAmount());
    	amount.setForeground(Color.BLACK);
    	amount.setFont(new Font("Segoe UI", Font.ITALIC, 13));
    	amount.setBounds(110, 352, 205, 20);
    	receiptPanel.add(amount);
    	
    	JLabel noOfTicketsLabel = new JLabel("No. of Tickets:");
    	noOfTicketsLabel.setForeground(Color.BLACK);
    	noOfTicketsLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	noOfTicketsLabel.setBounds(10, 261, 90, 20);
    	receiptPanel.add(noOfTicketsLabel);
    	
    	JLabel noOfTickets = new JLabel(String.valueOf(bookingTransaction.getNumberOfTickets()));
    	noOfTickets.setForeground(Color.BLACK);
    	noOfTickets.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	noOfTickets.setBounds(110, 261, 205, 20);
    	receiptPanel.add(noOfTickets);
    	
    	JLabel showtimeLabel = new JLabel("Showtime:");
    	showtimeLabel.setForeground(Color.BLACK);
    	showtimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	showtimeLabel.setBounds(10, 292, 90, 20);
    	receiptPanel.add(showtimeLabel);
    	
        Showtime showtimeData = StaffDatabaseManager.retrieveShowtimeByShowtimeId(bookingTransaction.getShowtimeId());
        String showDateStr = Helper.convertLocalDateToString(showtimeData.getShowDateTime().toLocalDate());
        String showtime1Str = Helper.getFormattedTime(showtimeData.getShowDateTime().toLocalTime());
    	JLabel showtime = new JLabel(showDateStr + " " + showtime1Str);
    	showtime.setForeground(Color.BLACK);
    	showtime.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	showtime.setBounds(110, 292, 205, 20);
    	receiptPanel.add(showtime);
    	
    	JLabel seatsLabel = new JLabel("Seats:");
    	seatsLabel.setForeground(Color.BLACK);
    	seatsLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	seatsLabel.setBounds(10, 321, 90, 20);
    	receiptPanel.add(seatsLabel);
    	
        ArrayList<Seat> seats = StaffDatabaseManager.retrieveSeatsFromBookingSeatasByBookingTransactionId(bookingTransaction.getBookingTransactionId());
        String seatLabel = Helper.formatSeatList(seats);
    	JLabel seat = new JLabel(seatLabel);
    	seat.setForeground(Color.BLACK);
    	seat.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	seat.setBounds(110, 320, 205, 20);
    	receiptPanel.add(seat);
    	
    	JLabel bookingDateLabel = new JLabel("Booking Date:");
    	bookingDateLabel.setForeground(Color.BLACK);
    	bookingDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	bookingDateLabel.setBounds(10, 120, 97, 20);
    	receiptPanel.add(bookingDateLabel);
    	
        String bookingDateStr = Helper.convertLocalDateToString(bookingTransaction.getBookingDate().toLocalDate());
        String bookingTimeStr = Helper.getFormattedTime(bookingTransaction.getBookingDate().toLocalTime());
    	JLabel bookingDate = new JLabel();
    	bookingDate.setText(bookingDateStr + " " + bookingTimeStr);
    	bookingDate.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	bookingDate.setBounds(103, 120, 212, 20);
    	receiptPanel.add(bookingDate);
    	
    	JLabel staffLabel = new JLabel("Staff:");
    	staffLabel.setForeground(Color.BLACK);
    	staffLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	staffLabel.setBounds(10, 150, 43, 20);
    	receiptPanel.add(staffLabel);
    	
        StaffEmployee staff = AdminDatabaseManager.retrieveStaffDataById(bookingTransaction.getStaffEmployeeId());
    	JLabel staffTextArea = new JLabel();
    	staffTextArea.setText(staff.getFirstName() + " " + staff.getMiddleName().charAt(0) + ". " + staff.getLastName());
    	staffTextArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	staffTextArea.setBounds(50, 150, 212, 20);
    	receiptPanel.add(staffTextArea);
    	
    	JLabel theaterLabel = new JLabel("Theater Number:");
    	theaterLabel.setForeground(Color.BLACK);
    	theaterLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	theaterLabel.setBounds(10, 180, 110, 20);
    	receiptPanel.add(theaterLabel);
    	
    	JLabel theaterNumber = new JLabel();
    	theaterNumber.setText(String.valueOf(bookingTransaction.getTheaterId()));
    	theaterNumber.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	theaterNumber.setBounds(124, 180, 212, 20);
    	receiptPanel.add(theaterNumber);
    	
    	JLabel paymentMethodLabel = new JLabel("Payment:");
    	paymentMethodLabel.setForeground(Color.BLACK);
    	paymentMethodLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	paymentMethodLabel.setBounds(10, 383, 90, 20);
    	receiptPanel.add(paymentMethodLabel);
    	
    	JLabel paymentMethod = new JLabel(bookingTransaction.getPaymentMethod());
    	paymentMethod.setForeground(Color.BLACK);
    	paymentMethod.setFont(new Font("Segoe UI", Font.ITALIC, 13));
    	paymentMethod.setBounds(110, 383, 205, 20);
    	receiptPanel.add(paymentMethod);
    	
        
    	JLabel lblCashTender = new JLabel(bookingTransaction.getPaymentMethod().equals("CASH") ? "Cash Tender" : "Reference No.");
    	lblCashTender.setForeground(Color.BLACK);
    	lblCashTender.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	lblCashTender.setBounds(10, 414, 90, 20);
    	receiptPanel.add(lblCashTender);
    	
    	JLabel lblCash_1 = new JLabel(bookingTransaction.getPaymentMethod().equals("CASH") ? "₱ " + bookingTransaction.getCashTender() : bookingTransaction.getReferenceNumber() );
    	lblCash_1.setForeground(Color.BLACK);
    	lblCash_1.setFont(new Font("Segoe UI", Font.ITALIC, 13));
    	lblCash_1.setBounds(110, 414, 205, 20);
    	receiptPanel.add(lblCash_1);
    	
        if(bookingTransaction.getPaymentMethod().equals("CASH")) {
            JLabel lblChange = new JLabel("Change:");
            lblChange.setForeground(Color.BLACK);
            lblChange.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lblChange.setBounds(10, 444, 90, 20);
            receiptPanel.add(lblChange);

            JLabel lblCash_2 = new JLabel("₱ " + Helper.formatPrice((bookingTransaction.getCashTender() - bookingTransaction.getTransactionAmount())));
            lblCash_2.setForeground(Color.BLACK);
            lblCash_2.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblCash_2.setBounds(110, 444, 205, 20);
            receiptPanel.add(lblCash_2);
        }

        
        JPanel blackPanel = new JPanel();
    	blackPanel.setBounds(0, 470, 325, 30);
    	receiptPanel.add(blackPanel);
    	blackPanel.setBackground(new Color(0, 0, 0));
        
        revalidate();
        repaint();
    }
}
