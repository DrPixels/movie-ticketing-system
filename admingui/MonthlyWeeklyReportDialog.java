package admingui;

import Database.AdminDatabaseManager;
import Database.StaffDatabaseManager;
import Model.SalesData;
import Model.Theater;
import helper.Helper;
import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import static staffgui.PaymentDialog.selectedSeats;
import static staffgui.PaymentDialog.selectedShowtimeId;
import static staffgui.PaymentDialog.staffId;
import static staffgui.PaymentDialog.theaterData;

public class MonthlyWeeklyReportDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JComboBox date1MonthComboBox;
	private JComboBox date1DayComboBox;
	private JComboBox date1YearComboBox;
	private JLabel fromLabel;
	private JLabel toLabel;
	private JComboBox<String> date2MonthComboBox;
	private JComboBox<Integer> date2DayComboBox;
	private JComboBox<Integer> date2YearComboBox;
	private JPanel dailyReportMainPanel;
        
            //Fields
    private ArrayList<Theater> theatersData = AdminDatabaseManager.retrieveTheaterData();
    
    private LocalDate from;
    private LocalDate to;


	public static void main(String[] args) {
		try {
			MonthlyWeeklyReportDialog dialog = new MonthlyWeeklyReportDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MonthlyWeeklyReportDialog() {
            setResizable(false);
		getContentPane().setBackground(new Color(55, 65, 81));
		setTitle("Monthly/Weekly Report");
		setBounds(100, 100, 995, 600);
		setLayout(null);
		
		date1MonthComboBox = new JComboBox<>(new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        });
		date1MonthComboBox.setBounds(440, 55, 100, 25);
		
		date1DayComboBox = new JComboBox<>();
		date1DayComboBox.setBounds(550, 55, 50, 25);
		
		date1YearComboBox = new JComboBox<>();
		date1YearComboBox.setBounds(610, 55, 70, 25);
                
                Helper.setupDateComboBoxes(date1MonthComboBox, date1DayComboBox, date1YearComboBox);
		
		fromLabel = new JLabel("From:");
		fromLabel.setForeground(Color.WHITE);
		fromLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		fromLabel.setBounds(395, 60, 38, 20);
		
		toLabel = new JLabel("To:");
		toLabel.setForeground(Color.WHITE);
		toLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		toLabel.setBounds(700, 60, 30, 20);
		
		date2MonthComboBox = new JComboBox<>(new String[]{
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        });
		date2MonthComboBox.setBounds(735, 55, 100, 25);
		
		date2DayComboBox = new JComboBox<>();
		date2DayComboBox.setBounds(845, 55, 50, 25);	
		
		date2YearComboBox = new JComboBox<>();
		date2YearComboBox.setBounds(905, 55, 70, 25);	
                
                Helper.setupDateComboBoxes(date2MonthComboBox, date2DayComboBox, date2YearComboBox);
                
                JButton generateButton = new JButton("Generate");
		generateButton.setForeground(Color.WHITE);
		generateButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		generateButton.setBackground(new Color(255, 81, 90));
		generateButton.setBounds(815, 90, 150, 35);
                add(generateButton);
                
                generateButton.addActionListener(new ActionListener() {
    		@Override
                    public void actionPerformed(ActionEvent e) {
                        
                        String yearFrom = date1YearComboBox.getSelectedItem().toString();
                        String monthFrom = date1MonthComboBox.getSelectedItem().toString();
                        String dayFrom = date1DayComboBox.getSelectedItem().toString();
                        String fromDateStr = monthFrom + " " + dayFrom + ", " + yearFrom;
                        from = Helper.dateStringToLocalDate(fromDateStr);
                        
                        String yearTo = date2YearComboBox.getSelectedItem().toString();
                        String monthTo = date2MonthComboBox.getSelectedItem().toString();
                        String dayTo = date2DayComboBox.getSelectedItem().toString();
                        String toDateStr = monthTo + " " + dayTo + ", " + yearTo;
                        to = Helper.dateStringToLocalDate(toDateStr);
                        
                        if(from.isBefore(to) || from.isEqual(to)) {
                            dailyReportMainPanel.removeAll();
                        addElementsToDailyMainPanel();
                        } else {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Invalid: 'From' date is not before 'To' date.", "Successful Action", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
		
		dailyReportMainPanel = new JPanel();
		dailyReportMainPanel.setLayout(null);
		dailyReportMainPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		dailyReportMainPanel.setBounds(20, 150, 950, 380);	
		
		
    	
    	add(date1MonthComboBox);
    	add(date1DayComboBox);
    	add(date1YearComboBox);
    	add(fromLabel);
    	add(toLabel);
    	add(date2MonthComboBox);
    	add(date2DayComboBox);
    	add(date2YearComboBox);
    	add(dailyReportMainPanel);
	}
	
private JPanel createTheaterMoviePanel(Theater theaterData) {
    	
    	JPanel theaterMoviePanel = new JPanel();
    	theaterMoviePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//    	theaterMoviePanel.setBounds(26, 271, 180, 235);
    	theaterMoviePanel.setPreferredSize(new Dimension(180,225));
    	theaterMoviePanel.setLayout(null);
    	
    	JLabel theaterLabel = new JLabel(theaterData.getTheaterName());
    	theaterLabel.setBounds(10, 11, 140, 25);
    	theaterLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	
        SalesData theaterSalesData = StaffDatabaseManager.getTheaterTotalTicketsAndRevenueSold(theaterData.getTheaterId(),from,to );
    	JLabel theatreTicketsSold = new JLabel(theaterSalesData.getTotalTickets() + " tickets sold");
    	theatreTicketsSold.setBounds(20, 46, 110, 25);
    	theatreTicketsSold.setFont(new Font("Segoe UI", Font.ITALIC, 14));
    	
    	JLabel theaterRevenue = new JLabel("₱ " + theaterSalesData.getTotalAmount());
    	theaterRevenue.setBounds(20, 76, 110, 25);
    	theaterRevenue.setFont(new Font("Segoe UI", Font.ITALIC, 14));
    	
        if(theaterData.getShowingMovie() != null) { 
            JLabel movieNameLabel = new JLabel(theaterData.getShowingMovie().getMovieName());
            movieNameLabel.setBounds(10, 121, 140, 25);
            movieNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));

            SalesData movieSalesData = StaffDatabaseManager.getMovieTotalTicketsAndRevenueSold(theaterData.getShowingMovie().getMovieId(), from, to);
            JLabel movieTicketsSold = new JLabel(movieSalesData.getTotalTickets() + " tickets sold");
            movieTicketsSold.setBounds(20, 156, 110, 25);
            movieTicketsSold.setFont(new Font("Segoe UI", Font.ITALIC, 14));

            JLabel movieRevenue = new JLabel("₱ " + movieSalesData.getTotalAmount());
            movieRevenue.setBounds(20, 186, 110, 25);
            movieRevenue.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            
            theaterMoviePanel.add(movieNameLabel);
            theaterMoviePanel.add(movieTicketsSold);
            theaterMoviePanel.add(movieRevenue);
        }

    	theaterMoviePanel.add(theaterLabel);
    	theaterMoviePanel.add(theatreTicketsSold);
    	theaterMoviePanel.add(theaterRevenue);

    	return theaterMoviePanel;
    	
    }

private void addElementsToDailyMainPanel() {
    JPanel dailyReportPanel = new JPanel();
		dailyReportPanel.setBackground(new Color(251, 146, 60));
		dailyReportPanel.setBounds(0, 0, 350, 35);
		dailyReportMainPanel.add(dailyReportPanel);
		
		JLabel dailyReportLabel = new JLabel("Report (" + Helper.convertLocalDateToString(from) + " - " + Helper.convertLocalDateToString(to) + ")");
		dailyReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dailyReportLabel.setForeground(Color.WHITE);
		dailyReportLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		dailyReportPanel.add(dailyReportLabel);
		
		JPanel totalTicketsSoldMainPanel = new JPanel();
		totalTicketsSoldMainPanel.setLayout(null);
		totalTicketsSoldMainPanel.setBounds(26, 65, 200, 80);
		dailyReportMainPanel.add(totalTicketsSoldMainPanel);
		
		JPanel totalTicketsSoldPanel = new JPanel();
		totalTicketsSoldPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalTicketsSoldPanel.setBounds(0, 0, 200, 40);
		totalTicketsSoldMainPanel.add(totalTicketsSoldPanel);
		
                SalesData salesData = StaffDatabaseManager.getTotalTicketsAndRevenueSold(from,to );
		JLabel dailyTotalTicketsSoldLabel = new JLabel("Total Tickets Sold");
		dailyTotalTicketsSoldLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		totalTicketsSoldPanel.add(dailyTotalTicketsSoldLabel);
		
		JPanel ticketsSoldPanel = new JPanel();
		ticketsSoldPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ticketsSoldPanel.setBounds(0, 40, 200, 40);
		totalTicketsSoldMainPanel.add(ticketsSoldPanel);
		
		JLabel dailyTotalTicketsSold = new JLabel(salesData.getTotalTickets() + " tickets sold");
		dailyTotalTicketsSold.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		ticketsSoldPanel.add(dailyTotalTicketsSold);
		
		JLabel salesBreakDownLabel = new JLabel("Sales Breakdown");
		salesBreakDownLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		salesBreakDownLabel.setBounds(268, 65, 140, 25);
		dailyReportMainPanel.add(salesBreakDownLabel);
		
		JPanel totalRevGenMainPanel = new JPanel();
		totalRevGenMainPanel.setLayout(null);
		totalRevGenMainPanel.setBounds(26, 175, 200, 80);
		dailyReportMainPanel.add(totalRevGenMainPanel);
		
		JPanel totalRevGenPanel = new JPanel();
		totalRevGenPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		totalRevGenPanel.setBounds(0, 0, 200, 40);
		totalRevGenMainPanel.add(totalRevGenPanel);
		
		JLabel dailyTotalRevGenLabel = new JLabel("Revenue Generated");
		dailyTotalRevGenLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
		totalRevGenPanel.add(dailyTotalRevGenLabel);
		
		JPanel revGenPanel = new JPanel();
		revGenPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		revGenPanel.setBounds(0, 40, 200, 40);
		totalRevGenMainPanel.add(revGenPanel);
		
		JLabel dailyTotalRevGen = new JLabel("₱ " + salesData.getTotalAmount());
		dailyTotalRevGen.setFont(new Font("Segoe UI", Font.ITALIC, 16));
		revGenPanel.add(dailyTotalRevGen);
		
		JPanel salesBreakDownMainPanel = new JPanel();
		salesBreakDownMainPanel.setLayout(null);
		salesBreakDownMainPanel.setBounds(280, 100, 638, 260);
		dailyReportMainPanel.add(salesBreakDownMainPanel);
		
		JPanel salesBreakDownPanel = new JPanel();
		salesBreakDownPanel.setBounds(10, 11, 618, 238);
		salesBreakDownMainPanel.add(salesBreakDownPanel);
		
    	for (Theater theaterData: theatersData) {

                JPanel newPanel = createTheaterMoviePanel(theaterData);
    		salesBreakDownPanel.add(newPanel);
    		
    	}
        
        revalidate();
        repaint();
}

}
