package staffgui;

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
import java.awt.GridLayout;

public class PaymentDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JPanel dynamicPanel;
	private JTextField cashTenderTF;
	private JTextField textField;
	
	private JPanel moviePosterLabel;
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
		setBounds(100, 100, 500, 600);
		setLayout(null);
		
		moviePosterLabel = new JPanel();
		moviePosterLabel.setBounds(20, 20, 150, 240);

		movieName = new JLabel("Black to the Future");
		movieName.setFont(new Font("Segoe UI", Font.BOLD, 15));
		movieName.setBounds(195, 20, 200, 20);

		moviePrice = new JLabel("₱ 450");
		moviePrice.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		moviePrice.setBounds(195, 50, 200, 20);

		movieShowtime = new JLabel("November 29, 2024 09:58 PM");
		movieShowtime.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		movieShowtime.setBounds(195, 90, 256, 20);

		seats = new JLabel("A1, A2");
		seats.setFont(new Font("Segoe UI", Font.ITALIC, 14));
		seats.setBounds(255, 130, 219, 20);

		seatsLabel = new JLabel("Seat/s:");
		seatsLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		seatsLabel.setBounds(195, 130, 56, 20);
		
		numberOfTixLabel = new JLabel("No. of Tickets");
		numberOfTixLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		numberOfTixLabel.setBounds(195, 170, 107, 20);
		
		ticketSpinner = new JSpinner();
		ticketSpinner.setBounds(195, 201, 110, 30);
		
		paymentMethodLabel = new JLabel("Payment Method:");
		paymentMethodLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		paymentMethodLabel.setBounds(20, 310, 130, 20);
		
		String[] paymentOptions = {"Cash", "GCash"};
		paymentMethodDropdown = new JComboBox<>(paymentOptions);
		paymentMethodDropdown.setBounds(150, 310, 120, 25);
		
		dynamicPanel = new JPanel();
		dynamicPanel.setBounds(20, 350, 440, 95);
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
		add(payButton);
		
		add(moviePosterLabel);
		add(movieName);
		add(moviePrice);
		add(movieShowtime);
		add(seats);
		add(seatsLabel);
		add(numberOfTixLabel);
		add(ticketSpinner);
		add(paymentMethodLabel);
		add(paymentMethodDropdown);
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

			textField = new JTextField();
			dynamicPanel.add(textField);
			textField.setColumns(10);
        }
        
        dynamicPanel.revalidate();
        dynamicPanel.repaint();
	}
}
