package application;

import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.Image;

public class Vehicle {
    //vehicle old city ID
    private int oldCityID;
    //old position on the grid pane
    private int oldPosition;
    //current city's ID after travel
    private int currentCityId;
    //target destination city's name
    private String destinationCityName;
    //current position after travel
    private int currentPosition;
    private int passengerCapacity;
    //passengers
    private ArrayList<Passenger> passengers;
    private Image image;


    public Vehicle() {

    }

    public Vehicle(int currentCityId, int passengerCapacity, ArrayList passengers) {

        this.currentCityId = currentCityId;
        this.passengerCapacity = passengerCapacity;
        this.passengers = passengers;

    }
    //update vehicle's position and city ID if it landed on a city
    public void update(int newPos){
        oldPosition=this.currentPosition;
        this.currentPosition=newPos;
    }
    public void update(int newPos,int currentCityId,String cityName){
        oldCityID=this.currentCityId;
        oldPosition=this.currentPosition;
        this.currentPosition=newPos;
        this.currentCityId=currentCityId;
        this.destinationCityName=cityName;

    }

    public int getCurrentCityId() {
        return currentCityId;
    }

    public void setCurrentCityId(int currentCityId) {
        this.currentCityId = currentCityId;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }



    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getOldCityID() {
        return oldCityID;
    }

    public void setOldCityID(int oldCityID) {
        this.oldCityID = oldCityID;
    }

    public int getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(int oldPosition) {
        this.oldPosition = oldPosition;
    }

    public String getDestinationCityName() {
        return destinationCityName;
    }

    public void setDestinationCityName(String destinationCityName) {
        this.destinationCityName = destinationCityName;
    }
 }