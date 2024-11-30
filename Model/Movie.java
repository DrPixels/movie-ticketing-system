package Model;

import java.util.ArrayList;

public class Movie {


    private String movieId;
    private String moviePosterPicturePath;
    private String movieName;
    private float moviePrice;
    private String movieGenre1;
    private String movieGenre2;
    private int duration;
    private String contentRating;
    private ArrayList<Showtime> showtimes;
    
        public Movie(String movieId, String moviePosterPicturePath, String movieName, float moviePrice, String movieGenre1, String movieGenre2, int duration, String contentRating, ArrayList<Showtime> showtimes) {
        this.movieId = movieId;
        this.moviePosterPicturePath = moviePosterPicturePath;
        this.movieName = movieName;
        this.moviePrice = moviePrice;
        this.movieGenre1 = movieGenre1;
        this.movieGenre2 = movieGenre2;
        this.duration = duration;
        this.contentRating = contentRating;
        this.showtimes = showtimes;
    }

    public Movie(String moviePosterPicturePath, String movieName, float moviePrice, String movieGenre1, String movieGenre2, int duration, String contentRating, ArrayList<Showtime> showtimes) {
        this.moviePosterPicturePath = moviePosterPicturePath;
        this.movieName = movieName;
        this.moviePrice = moviePrice;
        this.movieGenre1 = movieGenre1;
        this.movieGenre2 = movieGenre2;
        this.duration = duration;
        this.contentRating = contentRating;
        this.showtimes = showtimes;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMoviePosterPicturePath() {
        return moviePosterPicturePath;
    }

    public void setMoviePosterPicturePath(String moviePosterPicturePath) {
        this.moviePosterPicturePath = moviePosterPicturePath;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public float getMoviePrice() {
        return moviePrice;
    }

    public void setMoviePrice(float moviePrice) {
        this.moviePrice = moviePrice;
    }

    public String getMovieGenre1() {
        return movieGenre1;
    }

    public void setMovieGenre1(String movieGenre1) {
        this.movieGenre1 = movieGenre1;
    }

    public String getMovieGenre2() {
        return movieGenre2;
    }

    public void setMovieGenre2(String movieGenre2) {
        this.movieGenre2 = movieGenre2;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getContentRating() {
        return contentRating;
    }

    public void setContentRating(String contentRating) {
        this.contentRating = contentRating;
    }

    public ArrayList<Showtime> getShowtimes() {
        return showtimes;
    }

    public void setShowtimes(ArrayList<Showtime> showtimes) {
        this.showtimes = showtimes;
    }
}
