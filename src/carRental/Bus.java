package carRental;

import java.util.LinkedList;

/**
 * Created by My PC on 5/10/2016.
 */
public class Bus {

    private LinkedList<Passenger> passengerList;
    private double timeOfDeparture;
    private double nextTimeOfArrival;
    private int source,destination;


    public Bus() {
        passengerList = new LinkedList<>();
        timeOfDeparture = 0;
        nextTimeOfArrival = 0;
    }

    public LinkedList<Passenger> getPassengerList() {
        return passengerList;
    }

    public void setPassengerList(LinkedList<Passenger> passengerList) {
        this.passengerList = passengerList;
    }

    public double getTimeOfDeparture() {
        return timeOfDeparture;
    }

    public void setTimeOfDeparture(double timeOfDeparture) {
        this.timeOfDeparture = timeOfDeparture;
    }

    public double getNextTimeOfArrival() {
        return nextTimeOfArrival;
    }

    public void setNextTimeOfArrival(double nextTimeOfArrival) {
        this.nextTimeOfArrival = nextTimeOfArrival;
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
}
