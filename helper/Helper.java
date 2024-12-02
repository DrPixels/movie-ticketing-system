package helper;

import Model.Showtime;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Helper {
    
    public static final String DEFAULT_PROFILE_PIC_PATH = "D:\\MovieTicketingSystem\\defaultPictures\\default_profile_picture.png";
    public static final String DEFAULT_MOVIE_POSTER_PATH = "D:\\MovieTicketingSystem\\defaultPictures\\default_movie_poster.png";
    
    public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
    
    public static final String[] GENRES = {
            "Select Genre",
            "Action", 
            "Adventure", 
            "Comedy", 
            "Drama", 
            "Horror", 
            "Science Fiction", 
            "Fantasy", 
            "Romance", 
            "Thriller", 
            "Mystery", 
            "Animation", 
            "Documentary", 
            "Musical", 
            "Western", 
            "Biographical", 
            "War", 
            "Family", 
            "Crime", 
            "Historical", 
            "Experimental"
        };
    
    public static final String[] RATINGS = {
            "G", "PG", "PG-13", "R", "NC-17"
        };
    
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
    
        // Function to set up time JComboBoxes
    public static void setUpTimeComboBoxes(JComboBox<String> hoursComboBox,
                                           JComboBox<String> minutesComboBox,
                                           JComboBox<String> amPmComboBox) {
        // Populate hours (1 to 12)
        for (int i = 1; i <= 12; i++) {
            hoursComboBox.addItem(String.format("%02d", i));
        }

        // Populate minutes (00 to 59)
        for (int i = 0; i < 60; i++) {
            minutesComboBox.addItem(String.format("%02d", i));
        }

        // Populate AM/PM options
        amPmComboBox.addItem("AM");
        amPmComboBox.addItem("PM");
    }
    
// Method to set up the dynamic day JComboBox and set the current date
    //Including the age to update textfield
   public static void setupDateComboBoxes(JComboBox<String> monthComboBox, JComboBox<Integer> dayComboBox, JComboBox<Integer> yearComboBox) {
    // Populate the yearComboBox (if needed)
    if (yearComboBox.getItemCount() == 0) {
        for (int year = 1970; year <= 2040; year++) {
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
    
    public static String returnActionCommandOfWhatisSelectedButton(ButtonGroup buttonGroup) {
        for (AbstractButton button : java.util.Collections.list(buttonGroup.getElements())) {
                        if (button.isSelected()) {
                               return button.getActionCommand();
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
    
        // Method to convert string times to LocalDateTime
    public static ArrayList<LocalDateTime> convertStringsToLocalDateTimes(ArrayList<String> startTimesStr) {
        ArrayList<LocalDateTime> startTimes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a"); // Format: "November 3, 2024 3:30 PM"
        
        for (String startTimeStr : startTimesStr) {
            startTimes.add(LocalDateTime.parse(startTimeStr, formatter));
        }
        
        return startTimes;
    }
    
    public static ArrayList<Showtime> convertLocalDateTimesToShowtimes(ArrayList<String> startTimesStr) {
        ArrayList<LocalDateTime> startTimes = convertStringsToLocalDateTimes(startTimesStr);
        
        ArrayList<Showtime> showtimes = new ArrayList<>();
        
        for (LocalDateTime startTime : startTimes) {
            showtimes.add(new Showtime(startTime));
        }
        
        return showtimes;
    }
    
    public static ArrayList<Showtime> convertLocalDateTimesToShowtimes(ArrayList<String> startTimesStr, String showtimeId) {
        ArrayList<LocalDateTime> startTimes = convertStringsToLocalDateTimes(startTimesStr);
        
        ArrayList<Showtime> showtimes = new ArrayList<>();
        
        for (LocalDateTime startTime : startTimes) {
            showtimes.add(new Showtime(showtimeId,startTime));
        }
        
        return showtimes;
    }
        
    public static boolean checkConflicts(List<LocalDateTime> startTimes, int duration) {
        // List to store the end times of all showtimes
        List<LocalDateTime> endTimes = new ArrayList<>();
        
        // Calculate the end times for each showtime and check for conflicts
        for (LocalDateTime startTime : startTimes) {
            // Calculate end time for the current showtime
            LocalDateTime endTime = startTime.plusMinutes(duration);
            
            // Check if the current showtime conflicts with any of the previous showtimes
            for (LocalDateTime existingEndTime : endTimes) {
                if (startTime.isBefore(existingEndTime)) {
                    // Conflict found if the new start time is before the existing end time
                    return true;
                }
            }
            
            // Add the end time of the current showtime to the list
            endTimes.add(endTime);
        }
        
        // No conflicts found
        return false;
    }
    
    public static String getFormattedTime(LocalTime time) {
        // Format the time into "hh:mm a" format (12-hour with AM/PM)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return time.format(formatter);  // E.g., "03:45 PM"
    }
    
    public static String convertLocalDateToString(LocalDate date) {
        // Define the output format ("MMMM dd, yyyy")
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        
        // Format the LocalDate object to the desired string format
        return date.format(outputFormatter);
    }
    
}
