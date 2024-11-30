package Database;

//import static Database.DatabaseManager.connect;
//import static Database.DatabaseManager.connection;
import Model.AdminEmployee;
import Model.Movie;
import Model.Showtime;
import Model.Theater;
import helper.Helper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.UUID;
import java.time.*;
import java.util.ArrayList;
import javax.swing.JButton;

public class AdminDatabaseManager extends DatabaseManager{
    
        public static boolean registerAdmin(AdminEmployee admin) {
        
        connect();
        
        //Fix, auto-generated
        String employeeId = UUID.randomUUID().toString();
        
        if(!addAdminDataToEmployees(employeeId, admin)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error adding admin data to employees. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!addAdminDataToAuthentication(employeeId, admin)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error adding admin data to authentication. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!addAdminDataToRoles(employeeId, admin)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error adding admin data to roles. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true; 
    }
        
    private static boolean addAdminDataToEmployees(String employeeId, AdminEmployee admin) {
        
        String registerAdminQuery = "INSERT INTO employees VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(registerAdminQuery);
            
            //Convert the photo into InputStream
            InputStream is = new FileInputStream(new File(admin.getPicturePath()));
            
            Date birthDay = Date.valueOf(admin.getBirthday());
            
            prepSt.setString(1, employeeId);
            prepSt.setBlob(2, is);
            prepSt.setString(3, admin.getPicturePath());
            prepSt.setString(4, admin.getFirstName());
            prepSt.setString(5, admin.getMiddleName());
            prepSt.setString(6, admin.getLastName());
            prepSt.setDate(7, birthDay);
            prepSt.setInt(8, admin.getAge());
            prepSt.setString(9, admin.getGender());
            prepSt.setString(10, admin.getEmail());
            prepSt.setString(11, admin.getPhoneNumber());
            
            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
        
    private static boolean addAdminDataToAuthentication(String employeeId, AdminEmployee admin) {
        
        String query = "INSERT INTO authentication VALUES(?,?,?)";
        
        try{

            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, employeeId);
            prepSt.setString(2, admin.getUsername());
            prepSt.setString(3, admin.getPassword());

            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        }
        return false;
    }
    
    private static boolean addAdminDataToRoles(String employeeId, AdminEmployee admin) {
        
        String query = "INSERT INTO roles VALUES(?,?)";
        
        try{

            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, employeeId);
            prepSt.setString(2, admin.getRole());

            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static AdminEmployee retrieveAdminDataById(String employeeId) {
        String query = "SELECT profile_picture, profile_picture_path, first_name, middle_name, last_name, birthday, age, gender, email, phone_number, username, password FROM employees "
                + "INNER JOIN authentication ON authentication.employee_id = employees.employee_id WHERE employees.employee_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, employeeId);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                Blob profilePictureImage = rs.getBlob("profile_picture");
//                String path = "D:\\MovieTicketingSystem\\adminstaffpics\\" + employeeId + ".jpg";
                String path = rs.getString("profile_picture_path");
                byte[] bytes = profilePictureImage.getBytes(1, (int)profilePictureImage.length());
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);

                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String lastName = rs.getString("last_name");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String username = rs.getString("username");
                String password = rs.getString("password");
                
                return new AdminEmployee(path, 
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
            }

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateAdminDataById(AdminEmployee adminData) {
        
        if(!updateAdminDataInEmployees(adminData)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error updating admin data to employees. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!updateAdminDataInAuthentication(adminData)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error updating admin data to employees. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private static boolean updateAdminDataInEmployees(AdminEmployee adminData) {
                String query = "UPDATE employees "
                + "SET profile_picture = ?, "
                        + "profile_picture_path = ?, "
                + "first_name = ?, "
                + "middle_name = ?, "
                + "last_name = ?, "
                + "birthday = ?, "
                + "age = ?, "
                + "gender = ?, "
                + "email = ?, "
                + "phone_number = ? "
                        + "WHERE employee_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            //Convert the photo into InputStream
            InputStream is = new FileInputStream(new File(adminData.getPicturePath()));
            
            Date birthDay = Date.valueOf(adminData.getBirthday());
            
            prepSt.setBlob(1, is);
            prepSt.setString(2, adminData.getPicturePath());
            prepSt.setString(3, adminData.getFirstName());
            prepSt.setString(4, adminData.getMiddleName());
            prepSt.setString(5, adminData.getLastName());
            prepSt.setDate(6, birthDay);
            prepSt.setInt(7, adminData.getAge());
            prepSt.setString(8, adminData.getGender());
            prepSt.setString(9, adminData.getEmail());
            prepSt.setString(10, adminData.getPhoneNumber());
            prepSt.setString(11, adminData.getEmployeeId());
            
            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private static boolean updateAdminDataInAuthentication(AdminEmployee adminData) {
                String query = "UPDATE authentication "
                + "SET username = ?, "
                + "password = ? "
                        + "WHERE employee_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);       
            
            prepSt.setString(1, adminData.getUsername());
            prepSt.setString(2, adminData.getPassword());
            prepSt.setString(3, adminData.getEmployeeId());
            
            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return false;
    }
    
    public static ArrayList<Theater> retrieveTheaterData() {
        String query = "SELECT * FROM theaters";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);       
            
            ResultSet rs = prepSt.executeQuery();
            
            ArrayList<Theater> theatersData = new ArrayList<>();
            
            while(rs.next()) {
                
                int theaterId = rs.getInt("theater_id");
                String theaterName = rs.getString("theater_name");
                int numSeats = rs.getInt("num_seats");
                String movieId = rs.getString("movie_id");
                
                Movie movieInTheater = retrieveMovieDataById(movieId);
                
                Theater theaterData = new Theater(theaterId, theaterName, numSeats, movieInTheater);
                theatersData.add(theaterData);
            }

            return theatersData;
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return null;
    }
    
    public static Movie retrieveMovieDataById(String movieId) {
        String query = "SELECT * FROM movies WHERE movie_id = ? ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, movieId);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                 
                String moviePosterPicturePath = rs.getString("movie_poster_picture_path");
                String movieName = rs.getString("movie_name");
                float moviePrice = rs.getFloat("movie_price");
                String movieGenre1 = rs.getString("movie_genre_1");
                String movieGenre2 = rs.getString("movie_genre_2");
                int duration = rs.getInt("duration_total_minutes");
                String contentRating = rs.getString("content_rating");
                ArrayList<Showtime> movieShowtimes = retrieveMovieShowtimeDataById(movieId);
                
                Movie movie = new Movie(movieId, 
                moviePosterPicturePath, 
                        movieName, 
                        moviePrice, 
                                movieGenre1, 
                                movieGenre2, 
                                        duration, 
                                                contentRating, 
                                                movieShowtimes);
                
                return movie;               
            }
            

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
    }
    
    public static ArrayList<Showtime> retrieveMovieShowtimeDataById(String movieId) {
        String query = "SELECT * FROM showtimes WHERE movie_id = ? ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, movieId);
            
            ResultSet rs = prepSt.executeQuery();
            
            ArrayList<Showtime> movieShowtimes = new ArrayList<>();
            
            while(rs.next()) {
                String showtimeId = rs.getString("showtime_id");
                
                Timestamp showDateTime = rs.getTimestamp("start_time");
                
                // Convert Timestamp to LocalDate and LocalTime
                LocalDateTime localShowDateTime = showDateTime.toLocalDateTime();
                
                Showtime showtime = new Showtime(showtimeId, localShowDateTime);
                movieShowtimes.add(showtime);  
            }
            
            return movieShowtimes;
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
    } 
    
    public static boolean addMovieToDatabase(int theaterId, Movie movieData) {
        
        if(!addMovieToMovies(theaterId, movieData)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error adding movie. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
        
    }
        
    
    private static boolean addMovieToMovies(int theaterId, Movie movieData) {
        
        String query = "INSERT INTO movies VALUES (?,?,?,?,?,?,?,?,?, ?) ";
        //Fix, auto-generated
        String movieId = UUID.randomUUID().toString();
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            //Convert the photo into InputStream
            InputStream is = new FileInputStream(new File(movieData.getMoviePosterPicturePath()));
            
            prepSt.setString(1, movieId);
            prepSt.setBlob(2, is);
            prepSt.setString(3, movieData.getMoviePosterPicturePath());
            prepSt.setString(4, movieData.getMovieName());
            prepSt.setFloat(5, movieData.getMoviePrice());
            prepSt.setString(6, movieData.getMovieGenre1());
            prepSt.setString(7, movieData.getMovieGenre2());
            prepSt.setInt(8, movieData.getDuration());
            prepSt.setString(9, movieData.getContentRating());
            prepSt.setBoolean(10, false);
            
            
            prepSt.executeUpdate();
            
            
            //adding movie to the theater
            if(!addMovieToTheater(theaterId, movieId)) {
                return false;
            }
            
            //Adding movie showtimes to showtimes
            if(!addMovieShowtimeToShowtime(theaterId, movieId, movieData.getShowtimes(), movieData.getDuration())) {
                return false;
            }
            
            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
    }
    
    private static boolean addMovieToTheater(int theaterId, String movieId) {
        
        String query = "UPDATE theaters SET movie_id = ? WHERE theater_id = ? ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            
            prepSt.setString(1, movieId);
            prepSt.setInt(2, theaterId);

            prepSt.executeUpdate();
            
            return true;
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        }   
        return false;
    }
    
    public static boolean addMovieShowtimeToShowtime(int theaterId, String movieId, ArrayList<Showtime> showtimes, int movieDuration) {
        
        String query = "INSERT INTO showtimes VALUES(?, ?, ?, ?, ?) ";
        
        ArrayList<String> showtimeIds = new ArrayList<>();
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            for (Showtime showtime: showtimes) {
                String showtimeId = UUID.randomUUID().toString(); 
                Timestamp timestamp = Timestamp.valueOf(showtime.getShowDateTime());

                prepSt.setString(1, showtimeId);
                prepSt.setString(2, movieId);
                prepSt.setInt(3, theaterId);
                prepSt.setTimestamp(4, timestamp);
                prepSt.setInt(5, movieDuration);
                
                prepSt.addBatch();
                showtimeIds.add(showtimeId);
            }
 
            prepSt.executeBatch();
            
            for(String showtimeId: showtimeIds) {
                addShowtimeSeatsToSeats(showtimeId);
            }
  
            return true;
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return false;
    }
    private static boolean addShowtimeSeatsToSeats(String showtimeId) {
        
        String query = "INSERT INTO seats (seat_id, showtime_id, seat_number) VALUES(?, ?, ?) ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            for (int row = 1; row <= 8; row++) {
		for (int col = 1; col <= 14; col++) {
                        
                        String seatId = UUID.randomUUID().toString(); 
                        String seatLabel = (char) ('A' + row - 1) + String.valueOf(col);
                 
                        prepSt.setString(1, seatId);
                        prepSt.setString(2, showtimeId);
                        prepSt.setString(3, seatLabel);
                        
                        prepSt.addBatch();
				
                    }
            }        
 
            prepSt.executeBatch();
            
            return true;
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return false;
    }
    
    public static Movie retrieveMovieById(String movieId) {
        
        String query = "SELECT * FROM movies WHERE movie_id = ? ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, movieId);
 
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                String retrievedMovieId = rs.getString("movie_id");
                Blob moviePoster = rs.getBlob("movie_poster_picture");
//                String path = "D:\\MovieTicketingSystem\\adminstaffpics\\" + employeeId + ".jpg";
                String path = rs.getString("movie_poster_picture_path");
                byte[] bytes = moviePoster.getBytes(1, (int)moviePoster.length());
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);
                
                String movieName = rs.getString("movie_name");
                float moviePrice = rs.getFloat("movie_price");
                String movieGenre1 = rs.getString("movie_genre_1");
                String movieGenre2 = rs.getString("movie_genre_2");
                int duration = rs.getInt("duration_total_minutes");
                String contentRating = rs.getString("content_rating");
                
                ArrayList<Showtime> showtimes = retrieveMovieShowtimesById(movieId);
                    
                return new Movie(retrievedMovieId,path, movieName, moviePrice, movieGenre1, movieGenre2, duration, contentRating, showtimes);
                
                
            }
   
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (IOException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return null;
        
    }
    
    private static ArrayList<Showtime> retrieveMovieShowtimesById(String movieId) {
        
        String query = "SELECT * FROM showtimes WHERE movie_id = ? ";
        ArrayList<Showtime> showtimes = new ArrayList<>();
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, movieId);
 
            ResultSet rs = prepSt.executeQuery();
            
            while(rs.next()) {
                
                String showtimeId = rs.getString("showtime_id");
                LocalDateTime showDateTime = rs.getTimestamp("start_time").toLocalDateTime();
                
                showtimes.add(new Showtime(showtimeId, showDateTime));  
            } 
            
            return showtimes;
   
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return null;
        
    }
    
    public static boolean updateMovies(Movie movieData) {
        if(!updateMovieInMovies(movieData)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error updating movie. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!updateMoviesShowtimeInShowtime(movieData)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error updating movie. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    } 
    private static boolean updateMovieInMovies(Movie movieData) {
        String query = "UPDATE movies SET movie_poster_picture = ?, "
                + "movie_poster_picture_path = ?, "
                + "movie_name = ?, "
                + "movie_price = ?, "
                + "movie_genre_1 = ?, "
                + "movie_genre_2 = ? ,"
                + "duration_total_minutes = ?, "
                + "content_rating = ? "
                + "WHERE movie_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            //Convert the photo into InputStream
            InputStream is = new FileInputStream(new File(movieData.getMoviePosterPicturePath()));
            
            prepSt.setBlob(1, is);
            prepSt.setString(2, movieData.getMoviePosterPicturePath());
            prepSt.setString(3, movieData.getMovieName());
            prepSt.setFloat(4, movieData.getMoviePrice());
            prepSt.setString(5, movieData.getMovieGenre1());
            prepSt.setString(6, movieData.getMovieGenre2());
            prepSt.setInt(7, movieData.getDuration());
            prepSt.setString(8, movieData.getContentRating());
            prepSt.setString(9, movieData.getMovieId());

            prepSt.executeUpdate();       
            
            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
    }
    
    private static boolean updateMoviesShowtimeInShowtime(Movie movieData) {
        
        String query = "UPDATE showtimes SET start_time = ? "
                + "WHERE showtime_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            ArrayList<Showtime> showtimes = movieData.getShowtimes();
            for(Showtime showtime: showtimes) {
 
                prepSt.setTimestamp(1, Timestamp.valueOf(showtime.getShowDateTime()));
                prepSt.setString(2, showtime.getShowtimeId());
                
                prepSt.addBatch();
            }
            
            prepSt.executeBatch();       

            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
        
    }
    
    public static boolean deleteShowtimeInShowtime(ArrayList<String> showtimeIds) {
        
        String deleteSeatsQuery = "DELETE FROM seats WHERE showtime_id = ?";
        String deleteShowtimeQuery = "DELETE FROM showtimes WHERE showtime_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(deleteSeatsQuery);
            
            for(String showtimeId: showtimeIds) {
 
                prepSt.setString(1, showtimeId);
                
                prepSt.addBatch();
            }
            
            prepSt.executeBatch(); 
            
            PreparedStatement prepSt2 = connection.prepareStatement(deleteShowtimeQuery);

            for(String showtimeId: showtimeIds) {
 
                prepSt2.setString(1, showtimeId);
                
                prepSt2.addBatch();
            }
            prepSt2.executeBatch(); 
            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
        
    }
    
    public static boolean archiveMovie(String movieId) {
        String archiveMovieQuery = "UPDATE movies SET is_archived = ? WHERE movie_id = ? ";
        String removeMovieFromTheater = "UPDATE theaters SET movie_id = ? WHERE movie_id = ? ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(archiveMovieQuery);
            
            prepSt.setBoolean(1, true);
            prepSt.setString(2, movieId);
            
            prepSt.executeUpdate();
            
            PreparedStatement prepSt2 = connection.prepareStatement(removeMovieFromTheater);
            
            prepSt2.setString(1, null);
            prepSt2.setString(2, movieId);
            
            prepSt2.executeUpdate();

            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
    }
    
    public static ArrayList<Movie> retrieveArchivedMovies() {
        
        String retrieveArchiveMoviesQuery = "SELECT * FROM movies WHERE is_archived = 1";
        
        ArrayList<Movie> archivedMovies = new ArrayList<>();
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(retrieveArchiveMoviesQuery);
            
            ResultSet rs = prepSt.executeQuery();
            
            while(rs.next()) {
                String retrievedMovieId = rs.getString("movie_id");
                Blob moviePoster = rs.getBlob("movie_poster_picture");
//                String path = "D:\\MovieTicketingSystem\\adminstaffpics\\" + employeeId + ".jpg";
                String path = rs.getString("movie_poster_picture_path");
                byte[] bytes = moviePoster.getBytes(1, (int)moviePoster.length());
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);
                
                String movieName = rs.getString("movie_name");
                float moviePrice = rs.getFloat("movie_price");
                String movieGenre1 = rs.getString("movie_genre_1");
                String movieGenre2 = rs.getString("movie_genre_2");
                int duration = rs.getInt("duration_total_minutes");
                String contentRating = rs.getString("content_rating");
                
                ArrayList<Showtime> showtimes = retrieveMovieShowtimesById(retrievedMovieId);
                    
                archivedMovies.add(new Movie(retrievedMovieId,path, movieName, moviePrice, movieGenre1, movieGenre2, duration, contentRating, showtimes));
            }


            return archivedMovies;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (IOException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
        
    }
    
}
