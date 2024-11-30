package Model;

public class Theater {

    private int theaterId;
    private String theaterName;
    private int numOfSeats;
    private Movie showingMovie;
    
    public Theater(int theaterId, String theaterName, int numOfSeats, Movie showingMovie) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.numOfSeats = numOfSeats;
        this.showingMovie = showingMovie;
    }

    public Movie getShowingMovie() {
        return showingMovie;
    }

    public void setShowingMovie(Movie showingMovie) {
        this.showingMovie = showingMovie;
    }
    

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }
    
}
