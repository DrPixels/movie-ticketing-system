package logingui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class RegisterAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField firstNameTF;
	private JTextField middleNameTF;
	private JTextField lastNameTF;
	private JTextField ageTF;
	private JTextField emailTF;
	private JTextField phoneNumTF;
	private JTextField usernameTF;
	private JTextField passwordTF;
	
	private JLabel profilePictureLabel;
	private JButton uploadProfPicButton;
	private JLabel personalInfoLabel;
	private JLabel firstNameLabel;
	private JLabel middleNameLabel;

	private JLabel lastNameLabel;
	private JLabel birthdayLabel;
	private JComboBox birthdayMonthComboBox;
	private JComboBox birthdayDayComboBox;
	private JComboBox birthdayYearComboBox;

	private JLabel ageLabel;
	private JLabel genderLabel;

	private JRadioButton maleRadButton;
	private JRadioButton femaleRadButton;
	private JLabel emailLabel;

	private JLabel phoneNumberLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton registerButton;

	private JButton cancelButton;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterAdmin frame = new RegisterAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public RegisterAdmin() {
		getContentPane().setBackground(new Color(31, 41, 55));
		setTitle("Register Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		setLayout(null);
		
		profilePictureLabel = new JLabel();
		profilePictureLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		profilePictureLabel.setBounds(30, 30, 150, 150);
		
		uploadProfPicButton = new JButton("Upload Picture");
		uploadProfPicButton.setForeground(Color.WHITE);
		uploadProfPicButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		uploadProfPicButton.setBackground(new Color(55, 65, 81));
		uploadProfPicButton.setBounds(30, 195, 150, 25);
		
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
		
		middleNameLabel = new JLabel("Middle Name:");
		middleNameLabel.setForeground(Color.WHITE);
		middleNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		middleNameLabel.setBounds(425, 75, 105, 20);
		
		middleNameTF = new JTextField();
		middleNameTF.setColumns(10);
		middleNameTF.setBounds(425, 100, 150, 25);
		
		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setForeground(Color.WHITE);
		lastNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lastNameLabel.setBounds(615, 75, 105, 20);
		
		lastNameTF = new JTextField();
		lastNameTF.setColumns(10);
		lastNameTF.setBounds(615, 100, 150, 25);
		
		birthdayLabel = new JLabel("Birthday:");
		birthdayLabel.setForeground(Color.WHITE);
		birthdayLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		birthdayLabel.setBounds(235, 155, 80, 20);
		
		birthdayMonthComboBox = new JComboBox();
		birthdayMonthComboBox.setBounds(235, 180, 100, 25);
		
		birthdayDayComboBox = new JComboBox();
		birthdayDayComboBox.setBounds(345, 180, 50, 25);
		
		birthdayYearComboBox = new JComboBox();
		birthdayYearComboBox.setBounds(405, 180, 60, 25);
		
		ageLabel = new JLabel("Age:");
		ageLabel.setForeground(Color.WHITE);
		ageLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		ageLabel.setBounds(515, 155, 80, 20);
		
		ageTF = new JTextField();
		ageTF.setEditable(false);
		ageTF.setColumns(10);
		ageTF.setBounds(515, 185, 85, 20);
		
		genderLabel = new JLabel("Gender:");
		genderLabel.setForeground(Color.WHITE);
		genderLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		genderLabel.setBounds(235, 230, 80, 20);
		
		maleRadButton = new JRadioButton("Male");
		maleRadButton.setForeground(Color.WHITE);
		maleRadButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		maleRadButton.setBounds(245, 260, 65, 25);
		
		femaleRadButton = new JRadioButton("Female");
		femaleRadButton.setForeground(Color.WHITE);
		femaleRadButton.setFont(new Font("Segoe UI", Font.BOLD, 13));
		femaleRadButton.setBounds(320, 260, 75, 25);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setForeground(Color.WHITE);
		emailLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		emailLabel.setBounds(230, 310, 80, 20);
	
		emailTF = new JTextField();
		emailTF.setColumns(10);
		emailTF.setBounds(230, 335, 250, 25);
		
		phoneNumberLabel = new JLabel("Phone Number:");
		phoneNumberLabel.setForeground(Color.WHITE);
		phoneNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		phoneNumberLabel.setBounds(530, 310, 110, 20);
		
		phoneNumTF = new JTextField();
		phoneNumTF.setColumns(10);
		phoneNumTF.setBounds(530, 335, 250, 25);
		
		usernameLabel = new JLabel("Username:");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		usernameLabel.setBounds(230, 390, 80, 20);
		
		usernameTF = new JTextField();
		usernameTF.setColumns(10);
		usernameTF.setBounds(230, 415, 250, 25);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
		passwordLabel.setBounds(530, 390, 80, 20);
		
		passwordTF = new JTextField();
		passwordTF.setColumns(10);
		passwordTF.setBounds(530, 415, 250, 25);
		
		registerButton = new JButton("Register Admin");
		registerButton.setForeground(Color.WHITE);
		registerButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		registerButton.setBackground(new Color(255, 81, 90));
		registerButton.setBounds(774, 515, 150, 35);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
		cancelButton.setBackground(new Color(255, 81, 90));
		cancelButton.setBounds(615, 515, 150, 35);
		
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
		add(phoneNumTF);
		add(usernameLabel);
		add(usernameTF);
		add(passwordLabel);
		add(passwordTF);
		add(registerButton);
		add(cancelButton);
	}
}
