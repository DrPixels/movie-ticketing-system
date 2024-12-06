package admingui;

import Database.AdminDatabaseManager;
import Model.Movie;
import Model.StaffEmployee;
import helper.Helper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.*;

public class ManageStaff extends JPanel {

    private static final long serialVersionUID = 1L;
    
    private JScrollPane scrollPane;
    private JPanel titlePanel;
    private JPanel staffMainPanel;
    private JLabel listOfStaffLabel;
    private JButton addStaffButton;
    private JLabel searchLabel;
    private JTextField searchField;
    private JButton restoreStaffButton;
    
    private ArrayList<StaffEmployee> staffEmployees = AdminDatabaseManager.retrieveStaffEmployees();

    public ManageStaff() {
    	
    	setLayout(null);
    	
    	staffMainPanel = new JPanel();
    	staffMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));  // FlowLayout aligned to the left with gaps

    	staffMainPanel.setPreferredSize(new Dimension(935, 1000)); 

        addStaffToPanel(staffEmployees);

    	// ScrollPane configuration
    	scrollPane = new JScrollPane(staffMainPanel);
    	scrollPane.setBounds(32, 160, 939, 520);  // Ensure it fits your window size
    	scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	
    	titlePanel = new JPanel();
    	titlePanel.setForeground(new Color(255, 255, 255));
    	titlePanel.setBackground(new Color(249, 115, 22));
    	titlePanel.setBounds(10, 10, 980, 40);
    	titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
    	
    	listOfStaffLabel = new JLabel("LIST OF STAFF");
    	listOfStaffLabel.setForeground(new Color(255, 255, 255));
    	listOfStaffLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 18));
    	titlePanel.add(listOfStaffLabel);
    	
    	addStaffButton = new JButton("Add Staff");
    	addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
            	AddStaffDialog addStaffDialog = new AddStaffDialog();

            	addStaffDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
            	addStaffDialog.setVisible(true);
            }
        });
    	
    	addStaffButton.setForeground(new Color(255, 255, 255));
    	addStaffButton.setBackground(new Color(255, 81, 90));
    	addStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	addStaffButton.setBounds(655, 75, 150, 35);
    	
    	searchField = new JTextField();
    	searchField.setBounds(780, 120, 190, 30);
    	searchField.setColumns(10);
        
               // Add DocumentListener to the search field
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterProducts();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterProducts();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterProducts();
            }
        });
        
    	
    	searchLabel = new JLabel("Search:");
    	searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    	searchLabel.setBounds(725, 125, 50, 25);
    	
    	restoreStaffButton = new JButton("Restore Staff");
    	restoreStaffButton.addActionListener(new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
            	ArchivedStaffDialog archiveStaffDialog = new ArchivedStaffDialog();
                archiveStaffDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            	archiveStaffDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
            	archiveStaffDialog.setVisible(true);
                
                
                archiveStaffDialog.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                            staffMainPanel.removeAll();
                            ArrayList<StaffEmployee> newStaffEmployees = AdminDatabaseManager.retrieveStaffEmployees();
                            addStaffToPanel(newStaffEmployees);

                            revalidate();
                            repaint();
                            
                            }
                        });

                
            }
    	});
    	restoreStaffButton.setForeground(Color.WHITE);
    	restoreStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
    	restoreStaffButton.setBackground(new Color(255, 81, 90));
    	restoreStaffButton.setBounds(820, 75, 150, 35);
    	
    	add(scrollPane);
    	add(titlePanel);
    	add(addStaffButton);
    	add(searchField);
    	add(searchLabel);
    	add(restoreStaffButton);
    		
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
        staffProfilePictureLabel.setPreferredSize(new Dimension(100, 100));
        
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
        
        JButton editStaffButton = new JButton("Edit Staff");
        editStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        editStaffButton.setBounds(90, 210, 115, 25);
        
        editStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog
                EditStaffDialog.staffId = staffData.getEmployeeId();
                System.out.println(EditStaffDialog.staffId);
            	EditStaffDialog editStaffDialog = new EditStaffDialog();

            	editStaffDialog.setLocationRelativeTo(Helper.getCurrentFrame()); // Center the dialog relative to the frame
            	editStaffDialog.setVisible(true);
            }
        });
        
        JButton archiveStaffButton = new JButton("Archive Staff");
        archiveStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 12));

        archiveStaffButton.setBounds(90, 240, 115, 25);
        archiveStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Archive Movie", JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) {
                    AdminDatabaseManager.archiveStaff(staffData.getEmployeeId());
                    staffMainPanel.removeAll();
                    staffEmployees = AdminDatabaseManager.retrieveStaffEmployees();
                    addStaffToPanel(staffEmployees);
                    staffMainPanel.revalidate();
                    staffMainPanel.repaint();
                } 
            }
        });
        
        staffPanel.add(staffProfilePictureLabel);
//        staffPanel.add(staffID);
        staffPanel.add(staffName);
        staffPanel.add(editStaffButton);
        staffPanel.add(archiveStaffButton);
        
        return staffPanel;
    }
    
    private void addStaffToPanel(ArrayList<StaffEmployee> staffEmployees) {
            	// Add panels dynamically
    	for (StaffEmployee staffEmployee: staffEmployees) {
            JPanel newPanel = createStaffPanel(staffEmployee);  // Create custom panels
            staffMainPanel.add(newPanel);
    	}
    }
    
    private void filterProducts() {
        String searchText = searchField.getText().toLowerCase();
        staffMainPanel.removeAll(); // Remove all products from the container
        
        for (StaffEmployee staffEmployee: staffEmployees) {
            String staffFullName = staffEmployee.getFirstName() + " " + staffEmployee.getMiddleName() + " " + staffEmployee.getLastName();
            if(staffFullName.toLowerCase().contains(searchText)) {
                JPanel newPanel = createStaffPanel(staffEmployee);  // Create custom panels
                staffMainPanel.add(newPanel);
            }
            
    	}

        staffMainPanel.revalidate();
        staffMainPanel.repaint();
    }


}
