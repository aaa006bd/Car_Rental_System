package carRental;

/**
 * Created by My PC on 5/10/2016.
 */
public class Passenger {
    private double arrivalTime;
    private int source;
    private int destination;
    private double departureTime;

    public Passenger(int destination, int source, double arrivalTime) {
        this.destination = destination;
        this.source = source;
        this.arrivalTime = arrivalTime;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }
}
