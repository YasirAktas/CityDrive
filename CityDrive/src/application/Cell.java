package application;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell {
    //cell position on grid pane
    private int cellID;
    //cell city ID if there is city on this cell. It is default (-1) if this is blank cell
    private int cityID = -1;
    //check if this cell is fixed
    private boolean isFixed;
    //cell rectangle to put on the grid pane
    private Rectangle rectangle;


    //create rectangle on given cell position
    public Cell(int cellID) {
        this.cellID = cellID;
        rectangle = new Rectangle(45, 45, Color.TRANSPARENT);

    }

    public Cell() {
    }

    public int getCellID() {
        return cellID;
    }

    public void setCellID(int cellID) {
        this.cellID = cellID;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public boolean isFixed() {
        return isFixed;
    }

    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }



    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    }