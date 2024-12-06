package Model;

import java.time.LocalDateTime;

public class BookingTransaction {

    public BookingTransaction(String bookingTransactionId, LocalDateTime bookingDate, String staffEmployeeId, int theaterId, String movieId, String showtimeId, int numberOfTickets, float transactionAmount, float cashTender, String paymentMethod, String referenceNumber) {
        this.bookingTransactionId = bookingTransactionId;
        this.bookingDate = bookingDate;
        this.staffEmployeeId = staffEmployeeId;
        this.theaterId = theaterId;
        this.movieId = movieId;
        this.showtimeId = showtimeId;
        this.numberOfTickets = numberOfTickets;
        this.transactionAmount = transactionAmount;
        this.cashTender = cashTender;
        this.paymentMethod = paymentMethod;
        this.referenceNumber = referenceNumber;
    }
    
    private String bookingTransactionId;
    private LocalDateTime bookingDate;
    private String staffEmployeeId;
    private int theaterId;
    private String movieId;
    private String showtimeId;
    private int numberOfTickets;
    private float transactionAmount;
    private float cashTender;
    private String paymentMethod;

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    private String referenceNumber;


    public String getBookingTransactionId() {
        return bookingTransactionId;
    }

    public void setBookingTransactionId(String bookingTransactionId) {
        this.bookingTransactionId = bookingTransactionId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStaffEmployeeId() {
        return staffEmployeeId;
    }

    public void setStaffEmployeeId(String staffEmployeeId) {
        this.staffEmployeeId = staffEmployeeId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getShowtimeId() {
        return showtimeId;
    }

    public void setShowtimeId(String showtimeId) {
        this.showtimeId = showtimeId;
    }

    public int getNumberOfTickets() {
        return numberOfTickets;
    }

    public void setNumberOfTickets(int numberOfTickets) {
        this.numberOfTickets = numberOfTickets;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public float getCashTender() {
        return cashTender;
    }

    public void setCashTender(float cashTender) {
        this.cashTender = cashTender;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
}
