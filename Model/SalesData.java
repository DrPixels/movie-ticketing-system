package Model;

public class SalesData {
    private float totalAmount;
    private int totalTickets;

    // Constructor
    public SalesData(float totalAmount, int totalTickets) {
        this.totalAmount = totalAmount;
        this.totalTickets = totalTickets;
    }

    // Getters
    public float getTotalAmount() {
        return totalAmount;
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}
