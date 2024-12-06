package Database;

import static Database.DatabaseManager.connection;
import Model.BookingTransaction;
import Model.Movie;
import Model.SalesData;
import Model.Seat;
import Model.Showtime;
import Model.StaffEmployee;
import Model.Theater;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.*;
import java.util.UUID;

public class StaffDatabaseManager extends DatabaseManager{
    
    public static ArrayList<Theater> retrieveTheaterData() {
        
        connect();
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
    
    public static Showtime retrieveShowtimeByShowtimeId(String showtimeId) {
        String query = "SELECT * FROM showtimes WHERE showtime_id = ? ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, showtimeId);
            
            ResultSet rs = prepSt.executeQuery();
            
            
            if(rs.next()) { 
                
                Timestamp showDateTime = rs.getTimestamp("start_time");
                
                // Convert Timestamp to LocalDate and LocalTime
                LocalDateTime localShowDateTime = showDateTime.toLocalDateTime();
                
                ArrayList<Seat> showtimeSeats = retrieveSeatsByShowtimeId(showtimeId);
                
                return new Showtime(showtimeId, localShowDateTime, showtimeSeats);
                
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
                
                ArrayList<Seat> showtimeSeats = retrieveSeatsByShowtimeId(showtimeId);
                
                Showtime showtime = new Showtime(showtimeId, localShowDateTime, showtimeSeats);
                movieShowtimes.add(showtime);  
            }
            
            return movieShowtimes;
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
    } 
        
    public static ArrayList<Seat> retrieveSeatsByShowtimeId(String showtimeId) {
        String query = "SELECT * FROM seats WHERE showtime_id = ? ORDER BY LEFT(seat_number, 1), CAST(SUBSTRING(seat_number, 2) AS UNSIGNED) ";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);       
            
            prepSt.setString(1, showtimeId);
            
            ResultSet rs = prepSt.executeQuery();
            
            ArrayList<Seat> showtimeSeats = new ArrayList<>();
            
            while(rs.next()) {
                
                String seatId = rs.getString("seat_id");
                String seatNumber = rs.getString("seat_number");
                String status = rs.getString("status");
                
                showtimeSeats.add(new Seat(seatId, seatNumber, status));
            }

            return showtimeSeats;
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return null;
    }
    
        public static ArrayList<Seat> retrieveSeatsFromBookingSeatasByBookingTransactionId(String bookingTransactionId) {
        String query = """
                       SELECT * FROM booking_seats JOIN seats ON seats.seat_id = booking_seats.seat_id
                       WHERE booking_transaction_id = ? 
                       ORDER BY LEFT(seats.seat_number, 1), CAST(SUBSTRING(seats.seat_number, 2) AS UNSIGNED);""";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);       
            
            prepSt.setString(1, bookingTransactionId);
            
            ResultSet rs = prepSt.executeQuery();
            
            ArrayList<Seat> showtimeSeats = new ArrayList<>();
            
            while(rs.next()) {
                
                String seatId = rs.getString("seat_id");
                String seatNumber = rs.getString("seat_number");
                String status = rs.getString("status");
                
                showtimeSeats.add(new Seat(seatId, seatNumber, status));
            }

            return showtimeSeats;
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return null;
    }
   
    
     public static boolean addCashTransactionToBookingTransaction(String employeeId, Theater theaterData, String showtimeId, int numberOfTickets, float transactionAmount, float cashTender, String paymentMethod, ArrayList<Seat> selectedSeats) {
        String archiveStaffQuery = "INSERT INTO booking_transactions (booking_transaction_id, employee_id, theater_id, movie_id, showtime_id, no_of_tickets, transaction_amount, cash_tender, payment_method) VALUES(?,?,?,?,?,?,?,?,?)";
        
                //Fix, auto-generated
        String bookingTransactionId = UUID.randomUUID().toString();
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(archiveStaffQuery);
            
            prepSt.setString(1, bookingTransactionId);
            prepSt.setString(2, employeeId);
            prepSt.setInt(3, theaterData.getTheaterId());
            prepSt.setString(4, theaterData.getShowingMovie().getMovieId());
            prepSt.setString(5, showtimeId);
            prepSt.setInt(6, numberOfTickets);
            prepSt.setFloat(7, transactionAmount);
            prepSt.setFloat(8, cashTender);
            prepSt.setString(9, paymentMethod);
            
            prepSt.executeUpdate();
            
            addSeatsToBookingSeats(bookingTransactionId, selectedSeats);

            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
    }
     
     public static boolean addGCashTransactionToBookingTransaction(String employeeId, Theater theaterData, String showtimeId, int numberOfTickets, float transactionAmount, String paymentMethod, ArrayList<Seat> selectedSeats, String referenceNumber) {
        String archiveStaffQuery = "INSERT INTO booking_transactions (booking_transaction_id, employee_id, theater_id, movie_id, showtime_id, no_of_tickets, transaction_amount, payment_method, reference_number) VALUES(?,?,?,?,?,?,?,?,?)";
        
                //Fix, auto-generated
        String bookingTransactionId = UUID.randomUUID().toString();
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(archiveStaffQuery);
            
            prepSt.setString(1, bookingTransactionId);
            prepSt.setString(2, employeeId);
            prepSt.setInt(3, theaterData.getTheaterId());
            prepSt.setString(4, theaterData.getShowingMovie().getMovieId());
            prepSt.setString(5, showtimeId);
            prepSt.setInt(6, numberOfTickets);
            prepSt.setFloat(7, transactionAmount);
            prepSt.setString(8, paymentMethod);
            prepSt.setString(9, referenceNumber);
            
            prepSt.executeUpdate();
            
            addSeatsToBookingSeats(bookingTransactionId, selectedSeats);

            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
    }
    
    private static boolean addSeatsToBookingSeats(String bookingTransactionId, ArrayList<Seat> selectedSeats) {
        String archiveStaffQuery = "INSERT INTO booking_seats VALUES(?,?,?)";
        
                //Fix, auto-generated

        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(archiveStaffQuery);
            
            for(Seat selectedSeat:selectedSeats) {
                String bookingSeatId = UUID.randomUUID().toString();
                
                prepSt.setString(1, bookingSeatId);
                prepSt.setString(2, bookingTransactionId);
                prepSt.setString(3, selectedSeat.getSeatId());
                
                prepSt.addBatch();
            }

            prepSt.executeBatch();
            
            updateSeatStatusInSeats(selectedSeats);
            
            return true;
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
    }
    
    private static boolean updateSeatStatusInSeats(ArrayList<Seat> selectedSeats) {
        String archiveStaffQuery = "UPDATE seats SET status = ? WHERE seat_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(archiveStaffQuery);
            
            for(Seat selectedSeat:selectedSeats) {
                
                prepSt.setString(1, "Reserved");
                prepSt.setString(2, selectedSeat.getSeatId());
                
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
    
    public static SalesData getTotalTicketsAndRevenueSold(LocalDate from, LocalDate to) {
        String query = "SELECT SUM(transaction_amount), SUM(no_of_tickets) FROM booking_transactions WHERE DATE(booking_date) BETWEEN ? AND ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            Date fromSqlDate = Date.valueOf(from);
            Date toSqlDate = Date.valueOf(to);
            
            prepSt.setDate(1,fromSqlDate);
            prepSt.setDate(2, toSqlDate);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                float totalAmount = rs.getFloat(1);
                int totalTickets = rs.getInt(2);
                
                return new SalesData(totalAmount, totalTickets);
            }

//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
   
    }
    
    public static SalesData getTheaterTotalTicketsAndRevenueSold(int theaterId, LocalDate from, LocalDate to) {
        String query = "SELECT SUM(transaction_amount), SUM(no_of_tickets) FROM booking_transactions WHERE DATE(booking_date) BETWEEN ? AND ? AND theater_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            Date fromSqlDate = Date.valueOf(from);
            Date toSqlDate = Date.valueOf(to);
            
            prepSt.setDate(1,fromSqlDate);
            prepSt.setDate(2, toSqlDate);
            prepSt.setInt(3, theaterId);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                float totalAmount = rs.getFloat(1);
                int totalTickets = rs.getInt(2);
                
                return new SalesData(totalAmount, totalTickets);
            }
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
   
    }
        
    public static SalesData getMovieTotalTicketsAndRevenueSold(String movieId, LocalDate from, LocalDate to) {
        String query = "SELECT SUM(transaction_amount), SUM(no_of_tickets) FROM booking_transactions WHERE DATE(booking_date) BETWEEN ? AND ? AND movie_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            Date fromSqlDate = Date.valueOf(from);
            Date toSqlDate = Date.valueOf(to);
            
            prepSt.setDate(1,fromSqlDate);
            prepSt.setDate(2, toSqlDate);
            prepSt.setString(3, movieId);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                float totalAmount = rs.getFloat(1);
                int totalTickets = rs.getInt(2);
                
                return new SalesData(totalAmount, totalTickets);
            }
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
   
    }
    
    public static BookingTransaction retrieveBookingTransaction(String bookingTransactionId) {
        String query = "SELECT * FROM booking_transactions JOIN booking_seats ON booking_transactions.booking_transaction_id = booking_seats.booking_transaction_id WHERE booking_transactions.booking_transaction_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            prepSt.setString(1, bookingTransactionId);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                Timestamp bookingDateTimestamp = rs.getTimestamp("booking_date");
                LocalDateTime bookingDate = bookingDateTimestamp.toLocalDateTime();
                String staffEmployeeId = rs.getString("employee_id");
                int theaterId = rs.getInt("theater_id");
                String movieId = rs.getString("movie_id");
                String showtimeId = rs.getString("showtime_id");
                int numberOfTickets = rs.getInt("no_of_tickets");
                float transactionAmount = rs.getFloat("transaction_amount");
                float cashTender = rs.getFloat("cash_tender");
                String paymentMethod = rs.getString("payment_method");
                String referenceNumber = rs.getString("reference_number");
                
                return new BookingTransaction(bookingTransactionId, bookingDate, staffEmployeeId, theaterId, movieId, showtimeId, numberOfTickets, transactionAmount, cashTender, paymentMethod, referenceNumber );
            }
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
   
    }
    
    public static BookingTransaction retrieveLatestTransaction() {
        String query = """
                       SELECT *
                       FROM booking_transactions 
                       ORDER BY booking_date DESC
                       LIMIT 1""";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                String bookingTransactionId = rs.getString("booking_transaction_id");
                Timestamp bookingDateTimestamp = rs.getTimestamp("booking_date");
                LocalDateTime bookingDate = bookingDateTimestamp.toLocalDateTime();
                String staffEmployeeId = rs.getString("employee_id");
                int theaterId = rs.getInt("theater_id");
                String movieId = rs.getString("movie_id");
                String showtimeId = rs.getString("showtime_id");
                int numberOfTickets = rs.getInt("no_of_tickets");
                float transactionAmount = rs.getFloat("transaction_amount");
                float cashTender = rs.getFloat("cash_tender");
                String paymentMethod = rs.getString("payment_method");
                String referenceNumber = rs.getString("reference_number");
                
                return new BookingTransaction(bookingTransactionId, bookingDate, staffEmployeeId, theaterId, movieId, showtimeId, numberOfTickets, transactionAmount, cashTender, paymentMethod, referenceNumber );
            }
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return null;
   
    }
    
    
    
    public static boolean verifyReferenceNumber(String referenceNumber) {
        String query = "SELECT * FROM gcash_reference_number WHERE gcash_reference_number = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            prepSt.setString(1, referenceNumber);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
               return true;
            }
            
//            return movieShowtimes;  
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        
        return false;
   
    }
    
    
}
