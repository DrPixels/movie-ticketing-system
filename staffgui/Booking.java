package staffgui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

public class Booking extends JPanel {

    private static final long serialVersionUID = 1L;
    JFrame parent;
    private JPanel mainPanel;
    private JPanel moviesPanel;
    private JButton chooseShowtimeSeatButton;
    private JButton proceedToPaymentButton;

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
                // Create a custom dialog
            	ShowtimeSeatDialog showtimeSeatDialog = new ShowtimeSeatDialog();

            	showtimeSeatDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
            	showtimeSeatDialog.setVisible(true);
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
                // Create a custom dialog
            	PaymentDialog paymentDialog = new PaymentDialog();

            	paymentDialog.setLocationRelativeTo(parent); // Center the dialog relative to the frame
            	paymentDialog.setVisible(true);
            }
    	});
    	proceedToPaymentButton.setForeground(Color.WHITE);
    	proceedToPaymentButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	proceedToPaymentButton.setBackground(new Color(255, 81, 90));
    	proceedToPaymentButton.setBounds(740, 624, 230, 35);
    	mainPanel.add(proceedToPaymentButton);
    	
    	for (int i = 0; i < 3; i++) {
    		JPanel newPanel = createMoviePanel();
    		
    		moviesPanel.add(newPanel);
    	}
    	
    	add(mainPanel);
    }
    
    private JPanel createMoviePanel() {
    	JPanel moviePanel = new JPanel();
    	moviePanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
    	moviePanel.setPreferredSize(new Dimension(260, 425));
    	moviePanel.setLayout(null);
    	
    	JPanel moviePosterPanel = new JPanel();
    	moviePosterPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    	moviePosterPanel.setBounds(55, 10, 150, 240);
    	
    	JLabel movieName = new JLabel("Hello, Love, Again");
    	movieName.setFont(new Font("Segoe UI", Font.BOLD, 15));
    	movieName.setHorizontalAlignment(SwingConstants.CENTER);
    	movieName.setBounds(0, 260, 260, 25);
    	
    	JLabel moviePrice = new JLabel("₱ 450");
    	moviePrice.setHorizontalAlignment(SwingConstants.CENTER);
    	moviePrice.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
    	moviePrice.setBounds(0, 290, 260, 25);
    	
    	JLabel movieGenre = new JLabel("Horror, Comedy");
    	movieGenre.setHorizontalAlignment(SwingConstants.CENTER);
    	movieGenre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    	movieGenre.setBounds(0, 330, 260, 25);
    	
    	JLabel movieDuration = new JLabel("3hrs 4mins");
    	movieDuration.setHorizontalAlignment(SwingConstants.CENTER);
    	movieDuration.setFont(new Font("Segoe UI", Font.ITALIC, 14));
    	movieDuration.setBounds(0, 360, 260, 25);
    	
    	JRadioButton movieRadButton = new JRadioButton("");
    	movieRadButton.setBorder(new EmptyBorder(0, 0, 0, 0));
    	movieRadButton.setForeground(new Color(15, 23, 42));
    	movieRadButton.setBounds(120, 390, 20, 25);	
    	
    	JPanel theaterPanel = new JPanel();
    	theaterPanel.setForeground(new Color(255, 255, 255));
    	theaterPanel.setBackground(new Color(249, 115, 22));
    	theaterPanel.setBounds(0, 0, 45, 44);
    	theaterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	JLabel theaterLabel = new JLabel("1");
    	theaterLabel.setForeground(new Color(255, 255, 255));
    	theaterLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 25));
    	theaterLabel.setHorizontalAlignment(SwingConstants.CENTER);
    	theaterPanel.add(theaterLabel);
    	
    	moviePanel.add(moviePosterPanel);
    	moviePanel.add(movieName);
    	moviePanel.add(moviePrice);
    	moviePanel.add(movieGenre);
    	moviePanel.add(movieDuration);
    	moviePanel.add(movieRadButton);
    	moviePanel.add(theaterPanel);
    	
    	return moviePanel;
    }
}
