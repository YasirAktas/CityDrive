package application;

public class Passenger {
    private int startCityID;
    private int destinationCityID;


    public Passenger(int startCityID, int destinationCityID) {
        this.startCityID = startCityID;
        this.destinationCityID = destinationCityID;
    }

    public Passenger() {
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

    public void setDestinationCityID(int destinationCityID) {
        this.destinationCityID = destinationCityID;
    }


}