package admingui;

import Database.AdminDatabaseManager;
import Model.Movie;
import Model.Theater;
import helper.Helper;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ChooseTheaterToRestore extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChooseTheaterToRestore dialog = new ChooseTheaterToRestore();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
        public static Movie movieToRestore;
        private ArrayList<Theater> theatersData = AdminDatabaseManager.retrieveTheaterData();
	public ChooseTheaterToRestore() {
            setResizable(false);
            setTitle("Restore Movie");
		setBounds(100, 100, 320, 215);
		getContentPane().setLayout(null);
		
		JLabel questionLabel = new JLabel("Restore the movie to what theater?");
		questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		questionLabel.setBounds(20, 30, 250, 14);
		getContentPane().add(questionLabel);
		
		JComboBox<Integer> theaterComboBox = new JComboBox<>();
		theaterComboBox.setBounds(80, 67, 87, 22);
		getContentPane().add(theaterComboBox);
                
                for (Theater theaterData: theatersData) {
                    if(theaterData.getShowingMovie() == null) {
                        theaterComboBox.addItem(theaterData.getTheaterId());
                    }
                }
		
		JLabel theaterLabel = new JLabel("Theater:");
		theaterLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		theaterLabel.setBounds(20, 70, 60, 14);
		getContentPane().add(theaterLabel);
		
		JButton saveButton = new JButton("Save");
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveButton.setBackground(new Color(255, 81, 90));
		saveButton.setBounds(194, 140, 100, 25);
		getContentPane().add(saveButton);
                
                saveButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(!AdminDatabaseManager.restoreMovie(movieToRestore.getMovieId(), (int) theaterComboBox.getSelectedItem())) {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Invalid action. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                            return;
                        } else {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "The movie was restored successfully.", "Successful Action", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }
                        
                    }
            });
	}
}
