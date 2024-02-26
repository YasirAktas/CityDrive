package application;

import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

//this class takes string from Reader class and creates appropriate Objects written in level inputs.

public class Level {
    //level number
    private int number;
    //true if level completed
    private boolean isCompleted;
    //cities list
    private ArrayList<City> cities = new ArrayList<>();
    //cell list
    private ArrayList<Cell> cellList = new ArrayList<>();
    //passenger list
    private ArrayList<Passenger> passengers = new ArrayList<>();
   //create a vehicle in every level
    private Vehicle vehicle = new Vehicle();
    //reader
    private Reader readInput;
    //list for every different routes
    private ArrayList<Route> routes = new ArrayList<>();

    public Level(Reader readInput) {
        this.readInput = readInput;
        this.isCompleted = false;
    }

    public void init() {
        Iterator<String> iterator = readInput.getInputLine().iterator();
        //creates 100 cells every level
        for (int i = 1; i < 101; i++) {
            cellList.add(new Cell(i));
        }
        //after Iterator separates words when there is comma, here first checks the type of the appropriate Object.
        //Then set its properties with given information.
        while (iterator.hasNext()) {
            int startIndex = 0;
            String currentS = iterator.next();
            String type = "";

            for (int i = 0; i < currentS.length(); i++) {
                if (currentS.charAt(i) == ',') {
                    type = currentS.substring(startIndex, i);
                    startIndex = i + 1;
                    break;
                }
            }

            switch (type) {
                case ("City"):
                    City city = new City();
                    for (int i = startIndex; i < currentS.length(); i++) {
                        if (currentS.charAt(i) == ',') {
                            city.setCityName(currentS.substring(startIndex, i));
                            startIndex = i + 1;
                            break;
                        }
                    }
                    for (int i = startIndex; i < currentS.length(); i++) {
                        if (currentS.charAt(i) == ',') {
                            city.setLocationID(Integer.parseInt(currentS.substring(startIndex, i)));
                            startIndex = i + 1;

                        }
                    }
                    city.setCityID(Integer.parseInt(currentS.substring(startIndex)));
                    /*System.out.println("City name: " + city.getCityName() + ", City Cell: " +
                            city.getLocationID() + ", City ID: " + city.getCityID());*/
                    cities.add(city);
                    cellList.get(city.getLocationID()).setCityID(city.getCityID());
                    break;

                case ("Fixed"):
                    cellList.get(Integer.parseInt(currentS.substring(startIndex)) + 1).setFixed(true);

                    break;

                case ("Passenger"):
                    int countStart = passengers.size();
                    int countEnd = passengers.size();
                    for (int i = startIndex; i < currentS.length(); i++) {
                        if (currentS.charAt(i) == ',') {
                            countEnd = countStart + Integer.parseInt(currentS.substring(startIndex, i));
                            for (int j = 0; j < Integer.parseInt(currentS.substring(startIndex, i)); j++) {
                                passengers.add(new Passenger());
                            }
                            startIndex = i + 1;
                            break;
                        }
                    }
                    for (int i = startIndex; i < currentS.length(); i++) {
                        if (currentS.charAt(i) == ',') {
                            for (int j = countStart; j < countEnd; j++) {
                                passengers.get(j).setStartCityID(Integer.parseInt(currentS.substring(startIndex, i)));
                            }
                            startIndex = i + 1;
                            break;
                        }
                    }
                    for (int i = countStart; i < countEnd; i++) {
                        passengers.get(i).setDestinationCityID(Integer.parseInt(currentS.substring(startIndex)));
                        boolean routeFound = false;
                        for (Route r : routes) {
                            if (r.getStartCityID() == passengers.get(i).getStartCityID() &&
                                    r.getDestinationCityID() == passengers.get(i).getDestinationCityID()) {
                                r.increasePassengerCount();
                                routeFound = true;
                            }
                        }

                        if (!routeFound) {
                            Route route = new Route(passengers.get(i).getStartCityID(), passengers.get(i).getDestinationCityID(), 1);
                            routes.add(route);
                        }

                    }



                    break;

                case ("Vehicle"):
                    for (int i = startIndex; i < currentS.length(); i++) {
                        if (currentS.charAt(i) == ',') {
                            vehicle.setCurrentCityId(Integer.parseInt(currentS.substring(startIndex, i)));
                            startIndex = i + 1;
                            break;
                        }
                    }
                    vehicle.setPassengerCapacity(Integer.parseInt(currentS.substring(startIndex)));

                    /*System.out.println("current city ID of Vehicle: " + vehicle.getCurrentCityId() + ", passenger " +
                            "capacity: " + vehicle.getPassengerCapacity());*/
                    break;
            }
        }
        vehicle.setPassengers(passengers);
        number = Integer.parseInt(readInput.getFile().getName().substring(5, 6));
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<Cell> getcellList() {
        return cellList;
    }

    public void setcellList(ArrayList<Cell> cellList) {
        this.cellList = cellList;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getYPosition(int positionID) {
        int numberOfDigits = 0;
        int yPosition;
        if (positionID <= 10) {
            yPosition = 0;
        } else
            yPosition = positionID / 10;

        return yPosition;
    }

    public int getXPosition(int positionID) {
        int xPosition;
        if (positionID <= 10) {
            xPosition = positionID-1;
        } else if (positionID % 10 == 0)
            xPosition = 10;
        else
            xPosition = positionID % 10 - 1;

        return xPosition;
    }

    public ArrayList<Cell> getCellList() {
        return cellList;
    }

    public void setCellList(ArrayList<Cell> cellList) {
        this.cellList = cellList;
    }


    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }
}    