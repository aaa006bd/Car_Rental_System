package carRental;

import java.util.LinkedList;

/**
 * Created by My PC on 5/11/2016.
 */
public class Station {
    private LinkedList<Passenger> terminalQueue;
    private int stationNo;

    public Station(int stationNo) {
        terminalQueue = new LinkedList<>();
        this.stationNo = stationNo;
    }

    public LinkedList<Passenger> getTerminalQueue() {
        return terminalQueue;
    }

    public void setTerminalQueue(LinkedList<Passenger> terminalQueue) {
        this.terminalQueue = terminalQueue;
    }

    public int getStationNo() {
        return stationNo;
    }

    public void setStationNo(int stationNo) {
        this.stationNo = stationNo;
    }
}
