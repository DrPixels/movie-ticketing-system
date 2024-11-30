package Model;

import java.time.*;
public class Showtime {
    
    private String showtimeId;
    private LocalDateTime showDateTime;
    
    public Showtime(String showtimeId, LocalDateTime showDateTime) {
        this.showtimeId = showtimeId;
        this.showDateTime = showDateTime;
    }
    
    public Showtime(LocalDateTime showDateTime) {
        this.showtimeId = null;
        this.showDateTime = showDateTime;
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

 
}
