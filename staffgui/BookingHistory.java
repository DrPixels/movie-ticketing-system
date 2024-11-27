package staffgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;

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

    private JPanel receiptTitlePanel;

    private JLabel systemName;
    private JLabel movieNameLabel;

    private JTextArea movieName;
    private JTextArea bookingTransactionValue;
    private JLabel bookingTransactionIDLabel;

    private JLabel priceLabel;
    private JLabel priceValue;

    private JLabel noOfTicketsLabel;
    private JLabel noOfTickets;
    private JLabel dateLabel;

    private JLabel date;
    private JLabel showtimLabel;
    private JLabel showTime;
    private JPanel blackPanel;

    private JButton modifyBookingButton;

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
    	receiptPanel.setBounds(320, 190, 325, 375);
    	receiptPanel.setLayout(null);
    	
    	receiptTitlePanel = new JPanel();
    	receiptTitlePanel.setBackground(new Color(249, 115, 22));
    	receiptTitlePanel.setBounds(0, 0, 325, 60);
    	receiptPanel.add(receiptTitlePanel);
    	
    	systemName = new JLabel("CineBook");
    	receiptTitlePanel.add(systemName);
    	systemName.setForeground(new Color(255, 255, 255));
    	systemName.setBackground(new Color(249, 115, 22));
    	systemName.setHorizontalAlignment(SwingConstants.CENTER);
    	systemName.setFont(new Font("Segoe UI", Font.BOLD, 40));
    	
    	movieNameLabel = new JLabel("Movie Name:");
    	movieNameLabel.setForeground(new Color(0, 0, 0));
    	movieNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	movieNameLabel.setBounds(10, 150, 90, 20);
    	receiptPanel.add(movieNameLabel);
    	
    	movieName = new JTextArea();
    	movieName.setOpaque(false);
    	movieName.setEditable(false);
    	movieName.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	movieName.setLineWrap(true);
    	movieName.setWrapStyleWord(true);
    	movieName.setText("Black to The Future Black to The Future Black to The Future");
    	movieName.setBounds(110, 150, 205, 40);
    	receiptPanel.add(movieName);
    	
    	bookingTransactionValue = new JTextArea();
    	bookingTransactionValue.setWrapStyleWord(true);
    	bookingTransactionValue.setText("Black to The Future Black to The Future Black to The Future");
    	bookingTransactionValue.setOpaque(false);
    	bookingTransactionValue.setLineWrap(true);
    	bookingTransactionValue.setFont(new Font("Segoe UI", Font.ITALIC, 12));
    	bookingTransactionValue.setEditable(false);
    	bookingTransactionValue.setBounds(10, 90, 305, 40);
    	receiptPanel.add(bookingTransactionValue);
    	
    	bookingTransactionIDLabel = new JLabel("Booking Transaction ID:");
    	bookingTransactionIDLabel.setForeground(Color.BLACK);
    	bookingTransactionIDLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	bookingTransactionIDLabel.setBounds(10, 70, 305, 20);
    	receiptPanel.add(bookingTransactionIDLabel);
    	
    	priceLabel = new JLabel("Price:");
    	priceLabel.setForeground(Color.BLACK);
    	priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	priceLabel.setBounds(10, 230, 90, 20);
    	receiptPanel.add(priceLabel);
    	
    	priceValue = new JLabel("₱ 4500.50");
    	priceValue.setForeground(Color.BLACK);
    	priceValue.setFont(new Font("Segoe UI", Font.ITALIC, 13));
    	priceValue.setBounds(110, 230, 205, 20);
    	receiptPanel.add(priceValue);
    	
    	noOfTicketsLabel = new JLabel("No. of Tickets:");
    	noOfTicketsLabel.setForeground(Color.BLACK);
    	noOfTicketsLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	noOfTicketsLabel.setBounds(10, 200, 90, 20);
    	receiptPanel.add(noOfTicketsLabel);
    	
    	noOfTickets = new JLabel("2");
    	noOfTickets.setForeground(Color.BLACK);
    	noOfTickets.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	noOfTickets.setBounds(110, 200, 205, 20);
    	receiptPanel.add(noOfTickets);
    	
    	dateLabel = new JLabel("Date:");
    	dateLabel.setForeground(Color.BLACK);
    	dateLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	dateLabel.setBounds(10, 260, 90, 20);
    	receiptPanel.add(dateLabel);
    	
    	date = new JLabel("November 20, 2024");
    	date.setForeground(Color.BLACK);
    	date.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	date.setBounds(110, 260, 205, 20);
    	receiptPanel.add(date);
    	
    	showtimLabel = new JLabel("Showtime:");
    	showtimLabel.setForeground(Color.BLACK);
    	showtimLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    	showtimLabel.setBounds(10, 290, 90, 20);
    	receiptPanel.add(showtimLabel);
    	
    	showTime = new JLabel("03:00 PM");
    	showTime.setForeground(Color.BLACK);
    	showTime.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	showTime.setBounds(110, 290, 205, 20);
    	receiptPanel.add(showTime);
    	
    	blackPanel = new JPanel();
    	blackPanel.setBounds(0, 345, 325, 30);
    	receiptPanel.add(blackPanel);
    	blackPanel.setBackground(new Color(0, 0, 0));
    	
    	modifyBookingButton = new JButton("Modify Booking");
		modifyBookingButton.addActionListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
				ModifyShowtimeSeatDialog modifyShowtimeSeatDialog = new ModifyShowtimeSeatDialog();

				modifyShowtimeSeatDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
				modifyShowtimeSeatDialog.setVisible(true);
            }
		});
		
    	modifyBookingButton.setForeground(Color.WHITE);
    	modifyBookingButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	modifyBookingButton.setBorder(UIManager.getBorder("Button.border"));
    	modifyBookingButton.setBackground(new Color(55, 65, 81));
    	modifyBookingButton.setBounds(495, 576, 150, 35);
    	
    	add(enterBookTransacIdLabel);
    	add(enterBookTransacIdTF);
    	add(searchButton);
    	add(titlePanel);
    	add(receiptPanel);
    	add(modifyBookingButton);
    }
}
