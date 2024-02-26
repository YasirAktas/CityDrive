package application;

import java.util.ArrayList;

public class Route {
    private int startCityID;
    private int destinationCityID;
    //how many passengers are there on the current route
    private int passengerCount;


    public Route(int startCityID, int destinationCityID, int passengerCount) {
        this.startCityID = startCityID;
        this.destinationCityID = destinationCityID;
        this.passengerCount = passengerCount;
    }

    public int getStartCityID() {
        return startCityID;
    }

    public void setStartCityID(int startCityID) {
        this.startCityID = startCityID;
    }

    public int getDestinationCityID() {
        return destinationCityID;
    }

    public void increasePassengerCount() {
        passengerCount++;
    }

    public void setDestinationCityID(int destinationCityID) {
        this.destinationCityID = destinationCityID;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }
}