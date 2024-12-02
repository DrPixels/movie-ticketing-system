package admingui;

import Database.AdminDatabaseManager;
import Model.AdminEmployee;
import Model.Authentication;
import helper.Helper;
import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import logingui.RegisterAdmin;

public class EditAdminProfileDialog extends JDialog {

	private static final long serialVersionUID = 1L;
        
	private JLabel profilePictureLabel;
	private JButton uploadProfPicButton;
	
	private JLabel personalInfoLabel;
	
	private JLabel firstNameLabel;
	private JTextField firstNameTF;
	
	private JLabel middleNameLabel;
	private JTextField middleNameTF;
	
	private JLabel lastNameLabel;
	private JTextField lastNameTF;
	
	private JLabel birthdayLabel;
	private JComboBox<String> birthdayMonthComboBox;
	private JComboBox<Integer> birthdayDayComboBox;
	private JComboBox<Integer> birthdayYearComboBox;
	
	private JLabel ageLabel;
	private JTextField ageTF;
	
	private JLabel genderLabel;
        private ButtonGroup genderGroup;
	private JRadioButton maleRadButton;
	private JRadioButton femaleRadButton;

	private JLabel emailLabel;
	private JTextField emailTF;
	
	private JLabel phoneNumberLabel;
	private JTextField phoneNumberTF;

	private JLabel usernameLabel;
	private JTextField usernameTF;
	private JLabel passwordLabel;
	private JTextField passwordTF;
	
	private JButton saveStaffButton;
        
        //More fields
        //Admin Data
        private AdminEmployee adminData = AdminDatabaseManager.retrieveAdminDataById(Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID);
        private String profilePicturePath = adminData.getPicturePath();

	public static void main(String[] args) {
		try {
			EditAdminProfileDialog dialog = new EditAdminProfileDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EditAdminProfileDialog() {
		getContentPane().setBackground(new Color(55, 65, 81));
		setTitle("Add Staff");
		setBounds(100, 100, 950, 600);
		getContentPane().setLayout(null);
		
		profilePictureLabel = new JLabel();
		profilePictureLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		profilePictureLabel.setBounds(30, 30, 150, 150);
                profilePictureLabel.setPreferredSize(new Dimension(150, 150));
                
                ImageIcon iconImage = new ImageIcon(adminData.getPicturePath());
                Image originalImage = iconImage.getImage();
                Image scaledImage = originalImage.getScaledInstance(profilePictureLabel.getWidth(), profilePictureLabel.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
                profilePictureLabel.setIcon(scaledImageIcon);
		
		uploadProfPicButton = new JButton("Upload Picture");
		uploadProfPicButton.setForeground(Color.WHITE);
		uploadProfPicButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		uploadProfPicButton.setBackground(new Color(55, 65, 81));
		uploadProfPicButton.setBounds(30, 196, 150, 25);
                            // Add action listener to the button
            uploadProfPicButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //For scaling the file
		int profPicLabelWidth = profilePictureLabel.getWidth();
		int profPicLabelHeight = profilePictureLabel.getHeight();

		//Open as model dialog
		JFileChooser fileChooser = new JFileChooser();

		//Filtering the file based on their file extensions
		fileChooser.setFileFilter(new FileNameExtensionFilter("jpg/png/gif", "jpg", "png", "gif"));

		int result = fileChooser.showOpenDialog(Helper.getCurrentFrame());
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
                        profilePicturePath = selectedFile.getAbsolutePath();
                        
			ImageIcon imageIcon = new ImageIcon(profilePicturePath);

			//Scaling the image
			Image originalImage = imageIcon.getImage();
			Image scaledImage = originalImage.getScaledInstance(profPicLabelWidth, profPicLabelHeight, Image.SCALE_SMOOTH);
			ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
			profilePictureLabel.setIcon(scaledImageIcon);
			profilePictureLabel.putClientProperty("imagePath", selectedFile.getAbsolutePath());
		}
                }
            });
		
		personalInfoLabel = new JLabel("Personal Information");
		personalInfoLabel.setForeground(Color.WHITE);
		personalInfoLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 14));
		personalInfoLabel.setBounds(230, 30, 150, 20);
		
		firstNameLabel = new JLabel("First Name:");
		firstNameLabel.setForeground(Color.WHITE);
		firstNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		firstNameLabel.setBounds(235, 75, 80, 20);           
		
		firstNameTF = new JTextField();
		firstNameTF.setColumns(10);
		firstNameTF.setBounds(235, 100, 150, 25);
                firstNameTF.setText(adminData.getFirstName());
		
		middleNameLabel = new JLabel("Middle Name:");
		middleNameLabel.setForeground(Color.WHITE);
		middleNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		middleNameLabel.setBounds(425, 75, 105, 20);
		
		middleNameTF = new JTextField();
		middleNameTF.setColumns(10);
		middleNameTF.setBounds(425, 100, 150, 25);
                middleNameTF.setText(adminData.getMiddleName());
		
		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setForeground(Color.WHITE);
		lastNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lastNameLabel.setBounds(615, 75, 105, 20);
		
		lastNameTF = new JTextField();
		lastNameTF.setColumns(10);
		lastNameTF.setBounds(615, 100, 150, 25);
                lastNameTF.setText(adminData.getLastName());
		
		birthdayLabel = new JLabel("Birthday:");
		birthdayLabel.setForeground(Color.WHITE);
		birthdayLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		birthdayLabel.setBounds(235, 155, 80, 20);	
		
		birthdayMonthComboBox = new JComboBox<>(new String[]{
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                });
		birthdayMonthComboBox.setBounds(235, 180, 100, 25);
		
		birthdayDayComboBox = new JComboBox<>();
		birthdayDayComboBox.setBounds(345, 180, 50, 25);
		
		birthdayYearComboBox = new JComboBox<>();
		birthdayYearComboBox.setBounds(407, 180, 60, 25);
                
                Helper.setupDateComboBoxes(birthdayMonthComboBox, birthdayDayComboBox, birthdayYearComboBox);
                
                //Setting up the dates
                birthdayMonthComboBox.setSelectedIndex(adminData.getBirthday().getMonthValue() - 1);
                birthdayYearComboBox.setSelectedItem(adminData.getBirthday().getYear());
                birthdayDayComboBox.setSelectedItem(adminData.getBirthday().getDayOfMonth());
		
		ageLabel = new JLabel("Age:");
		ageLabel.setForeground(Color.WHITE);
		ageLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		ageLabel.setBounds(515, 155, 80, 20);
		
		ageTF = new JTextField();
		ageTF.setColumns(10);
		ageTF.setBounds(515, 185, 86, 20);	
                ageTF.setText(String.valueOf(adminData.getAge()));
		
		genderLabel = new JLabel("Gender:");
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		genderLabel.setBounds(235, 230, 80, 20);
                
                genderGroup = new ButtonGroup();
		maleRadButton = new JRadioButton("Male");
		maleRadButton.setForeground(Color.WHITE);
		maleRadButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		maleRadButton.setBounds(245, 260, 65, 25);
		
		femaleRadButton = new JRadioButton("Female");
		femaleRadButton.setForeground(Color.WHITE);
		femaleRadButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		femaleRadButton.setBounds(320, 260, 75, 25);
                
                genderGroup.add(maleRadButton);
                genderGroup.add(femaleRadButton);
                if(adminData.getGender().equals("MALE")) {
                    maleRadButton.setSelected(true);
                } else {
                    femaleRadButton.setSelected(true);
                }
		
		emailLabel = new JLabel("Email:");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		emailLabel.setBounds(230, 310, 80, 20);
		
		emailTF = new JTextField();
		emailTF.setColumns(10);
		emailTF.setBounds(230, 335, 250, 25);
                emailTF.setText(adminData.getEmail());
		
		phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setForeground(Color.WHITE);
		phoneNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		phoneNumberLabel.setBounds(530, 310, 110, 20);	
		
		phoneNumberTF = new JTextField();
		phoneNumberTF.setColumns(10);
		phoneNumberTF.setBounds(530, 335, 250, 25);
                phoneNumberTF.setText(adminData.getPhoneNumber());	
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		usernameLabel.setBounds(230, 390, 80, 20);
		
		usernameTF = new JTextField();
		usernameTF.setColumns(10);
		usernameTF.setBounds(230, 415, 250, 25);	
                usernameTF.setText(adminData.getUsername());
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		passwordLabel.setBounds(530, 390, 80, 20);	
		
		passwordTF = new JTextField();
		passwordTF.setColumns(10);
		passwordTF.setBounds(530, 415, 250, 25);
                passwordTF.setText(adminData.getPassword());
                
                saveStaffButton = new JButton("Save Staff");
		saveStaffButton.setForeground(Color.WHITE);
		saveStaffButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		saveStaffButton.setBackground(new Color(255, 81, 90));
		saveStaffButton.setBounds(774, 515, 150, 35);	
                
                saveStaffButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(!isFieldsValid()) {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Missing fields. Please try again.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        //For getting the birthday
                        String year = birthdayYearComboBox.getSelectedItem().toString();
                        String month = birthdayMonthComboBox.getSelectedItem().toString();
                        String day = birthdayDayComboBox.getSelectedItem().toString();
                        String birthdate = month + " " + day + ", " + year;
                        LocalDate birthday = Helper.dateStringToLocalDate(birthdate);
                        System.out.println(birthday);

                        String picturePath = profilePicturePath;
                        String firstName = firstNameTF.getText();
                        String middleName = middleNameTF.getText();
                        String lastName = lastNameTF.getText();
                        int age = Integer.parseInt(ageTF.getText());
                        String gender = Helper.returnWhatisSelectedButton(genderGroup);
                        String email = emailTF.getText();
                        String phoneNumber = phoneNumberTF.getText();
                        String username = usernameTF.getText();
                        String password = passwordTF.getText();

                        AdminEmployee updateAdminData = new AdminEmployee(Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID, 
                                picturePath, 
                                firstName, 
                                middleName, 
                                lastName, 
                                birthday, 
                                age, 
                                gender, 
                                email, 
                                phoneNumber, 
                                username, 
                                password);

                        if(!AdminDatabaseManager.updateAdminDataById(updateAdminData)) {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Invalid action. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Your information was update successfully.", "Successful Action", JOptionPane.WARNING_MESSAGE);
                            adminData = AdminDatabaseManager.retrieveAdminDataById(Authentication.CURRENTLY_LOGIN_EMPLOYEE_ID);
                            
                            dispose();
                        }     
                    }
                });
		
		add(profilePictureLabel);
		add(uploadProfPicButton);
		add(personalInfoLabel);
		add(firstNameLabel);
		add(firstNameTF);
		add(middleNameLabel);
		add(middleNameTF);
		add(lastNameLabel);
		add(lastNameTF);
		add(birthdayLabel);
		add(birthdayMonthComboBox);
		add(birthdayDayComboBox);
		add(birthdayYearComboBox);
		add(ageLabel);
		add(ageTF);
		add(genderLabel);
		add(maleRadButton);
		add(femaleRadButton);
		add(emailLabel);
		add(emailTF);
		add(phoneNumberLabel);
		add(phoneNumberTF);
		add(phoneNumberTF);
		add(usernameLabel);
		add(usernameTF);
		add(passwordLabel);
		add(passwordTF);
		add(saveStaffButton);	
	}
        
        private boolean isFieldsValid() {
         return !(firstNameTF.getText().isEmpty() || 
                 middleNameTF.getText().isEmpty() || 
                 lastNameTF.getText().isEmpty() || 
                 ageTF.getText().isEmpty() ||
                 emailTF.getText().isEmpty() || 
                 phoneNumberTF.getText().isEmpty() ||
                 usernameTF.getText().isEmpty() ||
                 passwordTF.getText().isEmpty() || 
                 (!maleRadButton.isSelected() && !femaleRadButton.isSelected()));
     }
}
