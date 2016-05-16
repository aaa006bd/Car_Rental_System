package carRental;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Created by My PC on 5/10/2016.
 */
public class CarRentalSystem {

    private double simulationClock = 0;
    private Random randomGenerator;
    private Station[] stations;
    private PriorityQueue<Event> eventPriorityQueue ;

    private Bus bus ;

    public CarRentalSystem() {
        bus = new Bus();
        randomGenerator = new Random(System.currentTimeMillis());
        eventPriorityQueue = new PriorityQueue<>();

        stations = new Station[3];

        for (int i = 0; i < stations.length; i++) {
            stations[i]=new Station(i+1);
        }
    }

    private void initialize(){//starting simulation with the assumption that atleast one customer for each destinaton has  arrived
        for (int i = 0; i < 2; i++) {
            processPassengerArrivalAtTerminalEvent(i+1,stations[i].getTerminalQueue());
        }
        scheduleArrivalEvent(1);
        scheduleArrivalEvent(2);
        scheduleArrivalEvent(3);
    }

    public void simulate(){

        initialize();
        int flag = 1;
        while(simulationClock<80){
            System.out.println(eventPriorityQueue.size());
            Event event = eventPriorityQueue.poll();
            simulationClock = event.getEventTime();

            if(event.getType() == Event.ARRIVAL_0NE_EVENT ){
                processPassengerArrivalAtTerminalEvent(2,stations[1].getTerminalQueue());
            }else if( event.getType()== Event.ARRIVAL_TWO_EVENT){
                processPassengerArrivalAtTerminalEvent(3,stations[2].getTerminalQueue());
            }else if(event.getType() == Event.ARRIVAL_THREE_EVENT){
                processPassengerArrivalAtTerminalEvent(1,stations[0].getTerminalQueue());
            }else if(event.getType() == Event.BUS_ARRIVAL_EVENT){
                if(flag == 3){
                   flag = 1;
                }
                processBusArrivalAtterminalEvent(flag);
                flag++;
            }

        }

    }

    public void processBusArrivalAtterminalEvent(int source){

        double unloadTime=0,loadTime=0;

        if(source == 3){
            bus.setDestination(1);
        }
        else if (source == 1){
            bus.setDestination(2);
        }else bus.setDestination(3);


        if(bus.getPassengerList().size() >0){
            unloadTime = processUnloadEvent(bus.getSource());
        }
        /*
        array is being offset by one
        i.e stations[0] is actually stations 1
        we use the array to access where the bus is stationed
         */
        if(bus.getPassengerList().size()<20&& stations[bus.getSource()-1].getTerminalQueue().size()>0){
            loadTime = processLoadEvent(bus.getSource());
        }
        
        double processTime = loadTime+unloadTime;
        processTime = processTime<5?5:processTime;
        
        if(bus.getDestination() == 3|| bus.getDestination() == 1){
            bus.setNextTimeOfArrival(simulationClock+9+processTime);
        }else{
            bus.setNextTimeOfArrival(simulationClock+2+processTime);
        }

    }

    private double processLoadEvent(int station) {
        
        double loadTime = 0;

        int noOfEmptySeat = 20-bus.getPassengerList().size();
        int numberOfPssengersInStation = stations[station-1].getTerminalQueue().size();
        int end = noOfEmptySeat>=numberOfPssengersInStation?(numberOfPssengersInStation+bus.getPassengerList().size()-1):20;

        if (numberOfPssengersInStation>0){
            for (int i = bus.getPassengerList().size(); i < end; i++) {
                loadTime+=uniformTimeWithinRange(15,25);
                bus.getPassengerList().add(stations[station-1].getTerminalQueue().remove());
            }
        }
        System.out.println("load time: "+loadTime);
        return loadTime/60;

    }



    private double processUnloadEvent(int station) {
        double uloadTime =0;
        LinkedList<Passenger> tempList = bus.getPassengerList();

        for (int i = 0; i < tempList.size(); i++) {
            if(tempList.get(i).getDestination() == station){
                uloadTime+=uniformTimeWithinRange(16,24);
                tempList.remove(i);
            }
        }
        System.out.println("unload Time : "+uloadTime);

        return uloadTime/60;
    }

    private void processPassengerArrivalAtTerminalEvent(int destination,LinkedList<Passenger>stationQueue){
        int source;
        if(destination == 1){
            source = 3;
        }else if (destination == 2){
            source =1;
        }else{
            source = 2;
        }
        Passenger passenger = new Passenger(source,destination,simulationClock);
        stationQueue.add(passenger);

    }

    private boolean hasAnyOneArrived(double rate){
        double val = randomGenerator.nextDouble();//uses the rate of the exponential distribution
        if(val<=rate){
            return true;
        }else return false;
    }

    private int destinationFromCarRental(){
        double val = randomGenerator.nextDouble();
        if(val<=0.417){
            return 2;
        }else return 1;
    }

    private int uniformTimeWithinRange(int min,int max){
        return min+randomGenerator.nextInt((max-min)+1);
    }

    private void scheduleArrivalEvent(int station){
        double nextArrival = simulationClock+1;
        if(station == 1){
            nextArrival = simulationClock+getGeometricRandomNumber(14/60);
           eventPriorityQueue.add(new Event(nextArrival,Event.ARRIVAL_0NE_EVENT));
        }else if(station == 2){
            nextArrival = simulationClock+getGeometricRandomNumber(10/60);
            eventPriorityQueue.add(new Event(nextArrival,Event.ARRIVAL_TWO_EVENT));
        }else if(station == 3){
            nextArrival = simulationClock+getGeometricRandomNumber(24/60);
            eventPriorityQueue.add(new Event(nextArrival,Event.ARRIVAL_THREE_EVENT));
        }
    }
    private void scheduleBusArrivalEvent(){
        double nextArrival = simulationClock+bus.getNextTimeOfArrival();
        eventPriorityQueue.add(new Event(nextArrival,Event.BUS_ARRIVAL_EVENT));
    }
    private double getGeometricRandomNumber(double probabilityOfSuccess) {
        double count = 1;
        double y;
        Random rand = new Random();
        while (true) {
            y = rand.nextDouble();
            if (y <= probabilityOfSuccess) {
                break;
            } else {
                count++;
            }
        }
        return count;
    }

}
