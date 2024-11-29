package helper;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Helper {
    
    public static final String DEFAULT_PROFILE_PIC_PATH = "\"D:\\MovieTicketingSystem\\defaultPictures\\default_profile_picture.png\"";
    
        // Method to get the currently active frame
    public static JFrame getCurrentFrame() {
        Frame[] frames = Frame.getFrames();  // Get all frames
        for (Frame frame : frames) {
            if (frame.isVisible()) {
                return (JFrame) frame;  // Return the first visible frame
            }
        }
        return null;  // No frame is visible
    }
    
// Method to set up the dynamic day JComboBox and set the current date
    //Including the age to update textfield
   public static void setupDateComboBoxes(JComboBox<String> monthComboBox, JComboBox<Integer> dayComboBox, JComboBox<Integer> yearComboBox) {
    // Populate the yearComboBox (if needed)
    if (yearComboBox.getItemCount() == 0) {
        for (int year = 1970; year <= 2024; year++) {
            yearComboBox.addItem(year);
        }
    }
    
//    // Get the current date
    LocalDate currentDate = LocalDate.now();
    int currentYear = currentDate.getYear();
    int currentMonth = currentDate.getMonthValue() - 1; // Month is 0-based in JComboBox
    int currentDay = currentDate.getDayOfMonth();
//
//    // Set the selected values in the JComboBoxes
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

    private static void updateDays(JComboBox<Integer> dayComboBox, int month, int year) {
       int previouslySelectedDay = (dayComboBox.getSelectedItem() != null) ? (int) dayComboBox.getSelectedItem() : 1;

       dayComboBox.removeAllItems();  // Clear existing days

       int daysInMonth = getDaysInMonth(month, year);

       // Repopulate with the correct number of days
       for (int i = 1; i <= daysInMonth; i++) {
           dayComboBox.addItem(i);
       }

       // Set the previously selected day if still valid, otherwise select the last valid day
       if (previouslySelectedDay > daysInMonth) {
           dayComboBox.setSelectedItem(daysInMonth);
       } else {
           dayComboBox.setSelectedItem(previouslySelectedDay);
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
    
    public static String returnWhatisSelectedButton(ButtonGroup buttonGroup) {
        for (AbstractButton button : java.util.Collections.list(buttonGroup.getElements())) {
                        if (button.isSelected()) {
                               return button.getText();
                        }
        }
        
        return "0";
    }
    
    public static LocalDate dateStringToLocalDate(String dateString) {
        
        // Define a formatter matching the input date pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);

        // Parse the date string into a LocalDate object
        return LocalDate.parse(dateString, formatter);
    }
    
    
}
