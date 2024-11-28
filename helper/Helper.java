package helper;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Helper {
    
    public static final String defaultProfilePicPath = "D:\\Users\\Rhem Giou\\Downloads\\java pics\\default_profile_picture.png";
    
// Method to set up the dynamic day JComboBox and set the current date
   public static void setupDateComboBoxes(JComboBox<String> monthComboBox, JComboBox<Integer> dayComboBox, JComboBox<Integer> yearComboBox) {
    // Populate the yearComboBox (if needed)
    if (yearComboBox.getItemCount() == 0) {
        for (int year = 1970; year <= 2024; year++) {
            yearComboBox.addItem(year);
        }
    }

    // Get the current date
    LocalDate currentDate = LocalDate.now();
    int currentYear = currentDate.getYear();
    int currentMonth = currentDate.getMonthValue() - 1; // Month is 0-based in JComboBox
    int currentDay = currentDate.getDayOfMonth();

    // Set the selected values in the JComboBoxes
    monthComboBox.setSelectedIndex(currentMonth);
    yearComboBox.setSelectedItem(currentYear);
    updateDays(dayComboBox, currentMonth, currentYear);
    dayComboBox.setSelectedItem(currentDay);

    // Action listener to update days based on month and year selection
    ActionListener updateDaysListener = e -> {
        int selectedMonth = monthComboBox.getSelectedIndex(); // 0-based index
        int selectedYear = (int) yearComboBox.getSelectedItem();
        updateDays(dayComboBox, selectedMonth, selectedYear);
    };

    // Add the listener to month and year JComboBoxes
    monthComboBox.addActionListener(updateDaysListener);
    yearComboBox.addActionListener(updateDaysListener);
}

    // Function to update the day JComboBox based on the number of days
    private static void updateDays(JComboBox<Integer> dayComboBox, int month, int year) {
        dayComboBox.removeAllItems();
        int daysInMonth = getDaysInMonth(month, year);
        for (int i = 1; i <= daysInMonth; i++) {
            dayComboBox.addItem(i);
        }

        // Ensure the selected day is valid in the new month
        int selectedDay = (int) dayComboBox.getSelectedItem();
        if (selectedDay > daysInMonth) {
            dayComboBox.setSelectedItem(daysInMonth); // If the selected day is not valid, select the last valid day
        }
    }

// Function to determine the number of days in a month
private static int getDaysInMonth(int month, int year) {
    switch (month) {
        case 1: // February
            return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
        case 3: case 5: case 8: case 10: // April, June, September, November
            return 30;
        default: // January, March, May, July, August, October, December
            return 31;
    }
}

    
    public static int calculateAge(LocalDate birthDate) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Calculate the period between the birthDate and the current date
        Period period = Period.between(birthDate, currentDate);

        // Return the age (years)
        return period.getYears();
    }
    
//    public static void setScaledImage(JLabel label, ImageIcon icon) {
//        //For scaling the file
//		int profPicLabelWidth = label.getWidth();
//		int profPicLabelHeight = label.getHeight();
//
//		//Open as model dialog
//		JFileChooser fileChooser = new JFileChooser();
//
//		//Filtering the file based on their file extensions
//		fileChooser.setFileFilter(new FileNameExtensionFilter("jpg/png/gif", "jpg", "png", "gif"));
//
//		int result = fileChooser.showOpenDialog(AddProductPanel.this);
//		if (result == JFileChooser.APPROVE_OPTION) {
//			File selectedFile = fileChooser.getSelectedFile();
//			ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
//
//			//Scaling the image
//			Image originalImage = imageIcon.getImage();
//			Image scaledImage = originalImage.getScaledInstance(profPicLabelWidth, profPicLabelHeight, Image.SCALE_SMOOTH);
//			ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
//			productPicture.setIcon(scaledImageIcon);
//			productPicture.putClientProperty("imagePath", selectedFile.getAbsolutePath());
//		}
//    }
    
}
