package admingui;

import Database.AdminDatabaseManager;
import Database.StaffDatabaseManager;
import Model.SalesData;
import Model.Theater;
import helper.Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Reports extends JPanel {

    private static final long serialVersionUID = 1L;

    private JPanel titlePanel;
    private JLabel reportsLabel;
    
    private JButton generateCustomReport;
    
    private JPanel dailyReportMainPanel;
    private JLabel dailyReportLabel;
    
    private JPanel totalTicketsSoldMainPanel;
    private JLabel dailyTotalTicketsSoldLabel;
    private JPanel totalTicketsSoldPanel;
    private JPanel ticketsSoldPanel;
    private JLabel dailyTotalTicketsSold;
    
    
    private JPanel totalRevGenMainPanel;
    private JPanel totalRevGenPanel;
    private JLabel dailyTotalRevGenLabel;
    private JPanel revGenPanel;
    private JLabel dailyTotalRevGen;
    
    private JLabel salesBreakDownLabel;
    private JPanel salesBreakDownMainPanel;
    private JPanel salesBreakDownPanel;

    private JLabel theaterLabel;
    private JPanel dailyReportPanel;
    
    //Fields
    private ArrayList<Theater> theatersData = AdminDatabaseManager.retrieveTheaterData();
    LocalDate currentDate = LocalDate.now();

    public Reports() {
                
    	setLayout(null);
    	
    	titlePanel = new JPanel();
    	titlePanel.setForeground(new Color(255, 255, 255));
    	titlePanel.setBackground(new Color(249, 115, 22));
    	titlePanel.setBounds(10, 10, 980, 40);
    	titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	reportsLabel = new JLabel("REPORTS");
    	reportsLabel.setForeground(new Color(255, 255, 255));
    	reportsLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
    	titlePanel.add(reportsLabel);
    	
    	dailyReportMainPanel = new JPanel();
    	dailyReportMainPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
    	dailyReportMainPanel.setBounds(25, 175, 950, 380);
    	dailyReportMainPanel.setLayout(null);
    	
    	dailyReportPanel = new JPanel();
    	dailyReportPanel.setBackground(new Color(251, 146, 60));
    	dailyReportPanel.setBounds(0, 0, 270, 35);
    	dailyReportMainPanel.add(dailyReportPanel);
    	

        String dateStr = Helper.convertLocalDateToString(currentDate);
    	dailyReportLabel = new JLabel("Daily Report (" + dateStr+")");
    	dailyReportLabel.setForeground(new Color(255, 255, 255));
    	dailyReportLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	dailyReportLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
    	dailyReportPanel.add(dailyReportLabel);
    	
    	totalTicketsSoldMainPanel = new JPanel();
    	totalTicketsSoldMainPanel.setBounds(26, 65, 200, 80);
    	dailyReportMainPanel.add(totalTicketsSoldMainPanel);
    	totalTicketsSoldMainPanel.setLayout(null);
    	
    	totalTicketsSoldPanel = new JPanel();
    	totalTicketsSoldPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    	totalTicketsSoldPanel.setBounds(0, 0, 200, 40);
    	totalTicketsSoldMainPanel.add(totalTicketsSoldPanel);
    	
    	dailyTotalTicketsSoldLabel = new JLabel("Total Tickets Sold");
    	totalTicketsSoldPanel.add(dailyTotalTicketsSoldLabel);
    	dailyTotalTicketsSoldLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	
    	ticketsSoldPanel = new JPanel();
    	ticketsSoldPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    	ticketsSoldPanel.setBounds(0, 40, 200, 40);
    	totalTicketsSoldMainPanel.add(ticketsSoldPanel);
    	
        //For Sales Data
        
        SalesData salesData = StaffDatabaseManager.getTotalTicketsAndRevenueSold(currentDate, currentDate);
    	dailyTotalTicketsSold = new JLabel(salesData.getTotalTickets() + " tickets sold");
    	ticketsSoldPanel.add(dailyTotalTicketsSold);
    	dailyTotalTicketsSold.setFont(new Font("Segoe UI", Font.ITALIC, 16));
    	
    	salesBreakDownLabel = new JLabel("Sales Breakdown");
    	salesBreakDownLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
    	salesBreakDownLabel.setBounds(268, 65, 140, 25);
    	dailyReportMainPanel.add(salesBreakDownLabel);
    	
    	totalRevGenMainPanel = new JPanel();
    	totalRevGenMainPanel.setLayout(null);
    	totalRevGenMainPanel.setBounds(26, 175, 200, 80);
    	dailyReportMainPanel.add(totalRevGenMainPanel);
    	
    	totalRevGenPanel = new JPanel();
    	totalRevGenPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    	totalRevGenPanel.setBounds(0, 0, 200, 40);
    	totalRevGenMainPanel.add(totalRevGenPanel);
    	
    	dailyTotalRevGenLabel = new JLabel("Revenue Generated");
    	dailyTotalRevGenLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	totalRevGenPanel.add(dailyTotalRevGenLabel);
    	
    	revGenPanel = new JPanel();
    	revGenPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    	revGenPanel.setBounds(0, 40, 200, 40);
    	totalRevGenMainPanel.add(revGenPanel);
    	
    	dailyTotalRevGen = new JLabel("₱ " + salesData.getTotalAmount());
    	dailyTotalRevGen.setFont(new Font("Segoe UI", Font.ITALIC, 16));
    	revGenPanel.add(dailyTotalRevGen);

    	salesBreakDownMainPanel = new JPanel();
    	salesBreakDownMainPanel.setBounds(280, 100, 638, 260);
    	dailyReportMainPanel.add(salesBreakDownMainPanel);
    	salesBreakDownMainPanel.setLayout(null);
    	
    	salesBreakDownPanel = new JPanel();
    	FlowLayout flowLayout = (FlowLayout) salesBreakDownPanel.getLayout();
    	flowLayout.setAlignment(FlowLayout.CENTER);
    	salesBreakDownPanel.setBounds(10, 11, 618, 238);
    	salesBreakDownMainPanel.add(salesBreakDownPanel);
    	
    	generateCustomReport = new JButton("Generate Custom Report");
    	generateCustomReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
            	MonthlyWeeklyReportDialog monthlyWeeklyReportDialog = new MonthlyWeeklyReportDialog();

            	monthlyWeeklyReportDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
            	monthlyWeeklyReportDialog.setVisible(true);
            }
        });
    	
    	generateCustomReport.setForeground(Color.WHITE);
    	generateCustomReport.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	generateCustomReport.setBackground(new Color(255, 81, 90));
    	generateCustomReport.setBounds(740, 129, 235, 35);
    	
    	add(titlePanel);
    	add(dailyReportMainPanel);
    	add(generateCustomReport);
    	
    	for (Theater theaterData: theatersData) {
                JPanel newPanel = createTheaterMoviePanel(theaterData);
    		salesBreakDownPanel.add(newPanel);

    		
    	}
    		
    }
    
    private JPanel createTheaterMoviePanel(Theater theaterData) {
    	
    	JPanel theaterMoviePanel = new JPanel();
    	theaterMoviePanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
//    	theaterMoviePanel.setBounds(26, 271, 180, 235);
    	theaterMoviePanel.setPreferredSize(new Dimension(180,225));
    	theaterMoviePanel.setLayout(null);
    	
    	theaterLabel = new JLabel(theaterData.getTheaterName());
    	theaterLabel.setBounds(10, 11, 140, 25);
    	theaterLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	
        SalesData theaterSalesData = StaffDatabaseManager.getTheaterTotalTicketsAndRevenueSold(theaterData.getTheaterId(), currentDate, currentDate);
    	JLabel theatreTicketsSold = new JLabel(theaterSalesData.getTotalTickets() + " tickets sold");
    	theatreTicketsSold.setBounds(20, 46, 110, 25);
    	theatreTicketsSold.setFont(new Font("Segoe UI", Font.ITALIC, 14));
    	
    	JLabel theaterRevenue = new JLabel("₱ " + (theaterSalesData.getTotalAmount()));
    	theaterRevenue.setBounds(20, 76, 110, 25);
    	theaterRevenue.setFont(new Font("Segoe UI", Font.ITALIC, 14));
    	
        if(theaterData.getShowingMovie() != null) {
            JLabel movieNameLabel = new JLabel(theaterData.getShowingMovie().getMovieName());
            movieNameLabel.setBounds(10, 121, 140, 25);
            movieNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));

            SalesData movieSalesData = StaffDatabaseManager.getMovieTotalTicketsAndRevenueSold(theaterData.getShowingMovie().getMovieId(), currentDate, currentDate);
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
}
