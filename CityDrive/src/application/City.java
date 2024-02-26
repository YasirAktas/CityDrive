package application;

import javafx.scene.image.Image;

public class City {
    private int locationID;
    private int cityID;
    private String cityName;
    private Image image;

    public City() {
    }

    public City(String cityName, int locationID, int cityID, Image image) {
        this.cityName = cityName;
        this.locationID = locationID;
        this.cityID = cityID;
        this.image = image;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getCityName() {
        return cityName;
    }

    public int getLocationID() {
        return locationID;
    }

    public int getCityID() {
        return cityID;
    }

    public Image getImage() {
        return image;
    }

}