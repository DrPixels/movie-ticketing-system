package Model;

import java.time.*;
import java.util.ArrayList;
public class Showtime {
    
    private String showtimeId;
    private LocalDateTime showDateTime;
    private ArrayList<Seat> seats;
    
    public Showtime(String showtimeId, LocalDateTime showDateTime) {
        this.showtimeId = showtimeId;
        this.showDateTime = showDateTime;
    }
    
    public Showtime(LocalDateTime showDateTime) {
        this.showtimeId = null;
        this.showDateTime = showDateTime;
    }
    
    public Showtime(String showtimeId, LocalDateTime showDateTime, ArrayList<Seat> seats) {
        this.showtimeId = showtimeId;
        this.showDateTime = showDateTime;
        this.seats = seats;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public LocalDateTime getShowDateTime() {
        return showDateTime;
    }

    public void setShowDateTime(LocalDateTime showDateTime) {
        this.showDateTime = showDateTime;
    }
    
    public ArrayList<Seat> getSeats() {
        return seats;
    }
    
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
 
 
}
