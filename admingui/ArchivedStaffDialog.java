package admingui;

import Database.AdminDatabaseManager;
import Model.Movie;
import Model.StaffEmployee;
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
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class ArchivedStaffDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	
	private JPanel titlePanel;
	private JLabel listOfArchiveStaffLabel;
	private JPanel staffMainPanel;
	private JScrollPane scrollPane;
	private JLabel searchLabel;
        
            private ArrayList<StaffEmployee> archivedStaffEmployees = AdminDatabaseManager.retrieveArchivedStaffEmployees();

	
	
	public static void main(String[] args) {
		try {
			ArchivedStaffDialog dialog = new ArchivedStaffDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArchivedStaffDialog() {
		
		setBounds(100, 100, 1000, 685);
		getContentPane().setLayout(null);

		titlePanel = new JPanel();
		titlePanel.setForeground(Color.WHITE);
		titlePanel.setBackground(new Color(249, 115, 22));
		titlePanel.setBounds(0, 0, 985, 40);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
		listOfArchiveStaffLabel = new JLabel("LIST OF ARCHIVED STAFF");
		listOfArchiveStaffLabel.setForeground(Color.WHITE);
		listOfArchiveStaffLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
		titlePanel.add(listOfArchiveStaffLabel);

		staffMainPanel = new JPanel();
		staffMainPanel.setPreferredSize(new Dimension(960,1000)); 
		staffMainPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
//			staffMainPanel.setBounds(10, 117, 965, 518);
		staffMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
		
		scrollPane = new JScrollPane(staffMainPanel);
		scrollPane.setBounds(10, 130, 965, 510);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
        for (StaffEmployee staffEmployee: archivedStaffEmployees) {
        	JPanel newPanel = createStaffPanel(staffEmployee);
        	
        	staffMainPanel.add(newPanel);
        }

		searchLabel = new JLabel("Search:");
		searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		searchLabel.setBounds(734, 83, 52, 25);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(789, 78, 190, 30);
		
		add(titlePanel);
		add(scrollPane);
		add(searchLabel);
		add(textField);
	}
	
	private JPanel createStaffPanel(StaffEmployee staffData) {
    	JPanel staffPanel = new JPanel();
       	staffPanel.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
//        staffPanel.setBounds(44, 11, 200, 280);
        staffPanel.setPreferredSize(new Dimension(220,280));
        staffPanel.setLayout(null);
        
        JLabel staffProfilePictureLabel = new JLabel();
        staffProfilePictureLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        staffProfilePictureLabel.setBounds(60, 15, 100, 100);
        
        
        ImageIcon iconImage = new ImageIcon(staffData.getPicturePath());
        Image originalImage = iconImage.getImage();
        Image scaledImage = originalImage.getScaledInstance(staffProfilePictureLabel.getWidth(), staffProfilePictureLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        staffProfilePictureLabel.setIcon(scaledImageIcon);
        
//        JLabel staffID = new JLabel("ID No. 112345");
//        staffID.setFont(new Font("Segoe UI", Font.PLAIN, 14));
//        staffID.setHorizontalAlignment(SwingConstants.CENTER);
//        staffID.setBounds(0, 125, 220, 14);    
        
        JLabel staffName = new JLabel(staffData.getFirstName() + " " + staffData.getMiddleName().charAt(0) + ". " + staffData.getLastName());
        staffName.setFont(new Font("Segoe UI", Font.BOLD , 14));
        staffName.setHorizontalAlignment(SwingConstants.CENTER);
        staffName.setBounds(0, 125, 220, 14); 
        
        JButton restoreStaffButton = new JButton("Restore");
        restoreStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        restoreStaffButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        restoreStaffButton.setBounds(90, 240, 115, 25);  
        
        staffPanel.add(staffProfilePictureLabel);
//        staffPanel.add(staffID);
        staffPanel.add(staffName);
        staffPanel.add(restoreStaffButton);
        
        return staffPanel;
    }

}
