package carRental;

/**
 * Created by My PC on 5/9/2016.
 */
public class Event implements Comparable {

    private double eventTime;
    private int type = -1;

    public static int ARRIVAL_0NE_EVENT = 1;
    public static int ARRIVAL_TWO_EVENT = 2;
    public static int ARRIVAL_THREE_EVENT = 3;
    public static int BUS_ARRIVAL_EVENT = 4;
    public static int BUS_UNLOAD_EVENT = 5;
    public static int BUS_LOAD_EVENT = 6;

    public Event(double eventTime, int type) {
        this.eventTime = eventTime;
        this.type = type;
    }

    public double getEventTime() {
        return eventTime;
    }

    public void setEventTime(double eventTime) {
        this.eventTime = eventTime;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {

        Event e = (Event) o;

        if (eventTime < e.getEventTime()){
            return -1;
        }else if (eventTime > e.getEventTime()){
            return 1;
        }else {
            return 0;
        }
    }
}
