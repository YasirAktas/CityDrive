package application;

import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Main extends Application {
    static int levelNumber = 1;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private double score = 0;
    private boolean point1 = true;
    private boolean reachPoint = false;
    private Stage primaryStage = new Stage();
    Button nextLevelButton = new Button("Next Level");
    Button newGame = new Button("New Game");
    BorderPane rootPane = new BorderPane();
    VBox rightUpperPane;
    VBox rightBottomPane;
    boolean con = true;

    @Override
    public void start(Stage primaryStage) throws Exception {
    	//this method will set the next level button disabled until all pasengers are carried
        nextLevelButton.setDisable(true);
        //reader object will read the level files based on level number
        Reader reader = new Reader(new File
                ("src/levels/level" + levelNumber + ".txt"));
        reader.read();

        new Level(reader);

        TheGame game = new TheGame();
        game.readLevels();
        levelNumber = 1;
       
        //this loop will iterate if the level is not completed and will set level to completed 
        while (!game.isCompleted()) {
            while (!game.getLevels().get(levelNumber).isCompleted()) {
                init(game.getLevels().get(levelNumber));
                game.getLevels().get(levelNumber).setCompleted(true);
            }
            game.setCompleted(true);
        }
        
        //if current passengers and routes are completed this button will be available and initializes the next level
        nextLevelButton.setOnAction(event -> {
            levelNumber++;
            nextLevelButton.setDisable(true);
            rootPane.getChildren().clear();
            Reader reader2 = new Reader(new File
                    ("src/levels/level" + levelNumber + ".txt"));
            try {
                reader2.read();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            new Level(reader);

            TheGame game2 = new TheGame();
            try {
                game2.readLevels();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            init(game2.getLevels().get(levelNumber));
            game2.getLevels().get(levelNumber).setCompleted(true);


        });
        //these statements starts a new game by making the levelNumber=1
        newGame.setOnAction(event -> {
        	levelNumber=1;
        	score=0;
//            nextLevelButton.setDisable(true);
            rootPane.getChildren().clear();
            Reader reader2 = new Reader(new File
                    ("src/levels/level" + levelNumber + ".txt"));
            try {
                reader2.read();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            new Level(reader);

            TheGame game2 = new TheGame();
            try {
                game2.readLevels();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            init(game2.getLevels().get(levelNumber));
            game2.getLevels().get(levelNumber).setCompleted(true);
        });

    }
    //this function consists all the panes and nodes of the game
    public void init(Level level) {

        // Create top pane
        Label levelLabel = new Label("Level: " + level.getNumber());
        Label scoreLabel = new Label("Score: "+score);
        HBox topPane = new HBox(levelLabel, scoreLabel, nextLevelButton);
        topPane.setAlignment(Pos.CENTER);
        topPane.setSpacing(200);
        topPane.setPadding(new Insets(10));

        // Create center pane
        GridPane centerPane = new GridPane();
        centerPane.setPadding(new Insets(10));
        centerPane.setHgap(10);
        centerPane.setVgap(10);
        centerPane.setAlignment(Pos.CENTER);
        rightUpperPane = new VBox(15);
        rightUpperPane.setAlignment(Pos.TOP_CENTER);
        rightUpperPane.setPadding(new Insets(15, 18, 5, 5));
        rightBottomPane = new VBox();
        rightBottomPane.setAlignment(Pos.BOTTOM_CENTER);
        rightBottomPane.setPadding(new Insets(15, 18, 5, 5));
        rightUpperPane.getChildren().addAll(newGame, rightBottomPane);


        redrawLabels(level);


        // Create bottom pane
        Label destinationLabel = new Label("Destination: City X");
        Label distanceLabel = new Label("Distance: X");
        Label capacityLabel = new Label("Capacity: " + level.getVehicle().getPassengerCapacity());
        Button driveButton = new Button("DRIVE");
        HBox bottomPane = new HBox(destinationLabel, distanceLabel, capacityLabel, driveButton);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setSpacing(20);
        bottomPane.setPadding(new Insets(10));

        // Create pane for drawing lines
        Pane linePane = new Pane();

        rootPane.setTop(topPane);
        rootPane.setCenter(centerPane);
        rootPane.setBottom(bottomPane);
        rootPane.setRight(rightUpperPane);

        
        System.out.println("Current car pos ID: " + level.getVehicle().getCurrentPosition());
        System.out.println("Current car city ID: " + level.getVehicle().getCurrentCityId());
        //this loop will calculate the row and column indexes of the gridPane
        Cell[][] boxes = new Cell[10][10];
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {

                int row = i + 1;
                int col = j + 1;
                int position = (row - 1) * 10 + col;
                boxes[i][j] = level.getcellList().get(position - 1);
                boxes[i][j].getRectangle().setOnMouseClicked(event -> {
                    System.out.println("Cell: " + row + ", " + col + ", and the position is: " + position);
                    boolean isCity = false;
                    
                    for (City c : level.getCities()) {
                        if (c.getLocationID() == position) {
                        	//this method will update the position of the vehicle based on next cityID
                            level.getVehicle().update(position, c.getCityID(),c.getCityName());
                            System.out.println("New car pos ID: " + position);
                            System.out.println("cars city ID: " + level.getVehicle().getCurrentCityId());
                            isCity = true;
                            break;
                        }
                    }
                    if(!isCity){
                        level.getVehicle().update(position);
                    }


                });
            }
        }
        //the if statements will put the appropriate vehicle image based on the given capacity 
        String vehicleName="";
        if (level.getVehicle().getPassengerCapacity() <= 5) {
            vehicleName="car";
        } else if (level.getVehicle().getPassengerCapacity() <= 15) {
        	 vehicleName="minibus";
        } else {
        	 vehicleName="bus";
        }
        //create images and imageView objects
        Images imagec = new Images();
        ImageView vehicle = imagec.getImage(vehicleName);
        driveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	
            @Override
            public void handle(MouseEvent mouseEvent) {
            	//this lines will remove the vehicle from the current position and update its image position
                centerPane.getChildren().remove(vehicle);
                int carriedPassengers = 0;
                centerPane.add(vehicle, (level.getVehicle().getCurrentPosition() % 10) - 1, (level.getVehicle().getCurrentPosition() / 10));
                //this loop will check every routes and if there is appropriate passengers they will be removed from the board
                for (Route r : level.getRoutes()) {
                    if (r.getStartCityID() == level.getVehicle().getOldCityID() &&
                            r.getDestinationCityID() == level.getVehicle().getCurrentCityId()) {
                        if (r.getPassengerCount() > level.getVehicle().getPassengerCapacity()) {
                            r.setPassengerCount(r.getPassengerCount() - level.getVehicle().getPassengerCapacity());
                            carriedPassengers=level.getVehicle().getPassengerCapacity();
                        } else {
                        	carriedPassengers=r.getPassengerCount();
                            r.setPassengerCount(0);
                        }
                    }
                }
                rightBottomPane.getChildren().clear();
                redrawLabels(level);
               //this line will calculate and update the score value
                score+=(int)(calculateScore((int)distance(level.getVehicle().getOldPosition(),level.getVehicle().getCurrentPosition()),carriedPassengers));
                scoreLabel.setText("Score: "+ score);

                //check if level completed
                boolean isCompleted = true;
                //these statements will check if the level is completed and make the next level button available
                for(Route r : level.getRoutes()){
                    if(r.getPassengerCount()>0){
                        isCompleted=false;
                    }
                }
                if(isCompleted){
                    nextLevelButton.setDisable(false);
                    Label finalScore = new Label("LEVEL COMPLETED! Your score is: "+score);
                    finalScore.setStyle("-fx-text-fill: red; -fx-font-family: Arial; -fx-font-size: 10px; -fx-font-weight: bold;");
                    rightBottomPane.getChildren().add(finalScore);
                }
            }
        });

        //this loop will create the cells in the gridPane
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                centerPane.add(boxes[i][j].getRectangle(), j, i);
            }
        }
        //this loop will put the fixed cell images to the Pane
        for (int i = 0; i < level.getcellList().size(); i++) {
            if (level.getcellList().get(i).isFixed()) {
                Images images = new Images();
                ImageView fixed = images.getImage("fixed");
                centerPane.add(fixed, level.getXPosition(i), level.getYPosition(i));
                fixed.toBack();
            }

        }
       
        //this loop will put the city pictures to the pane
        for (int i = 0; i < level.getCities().size(); i++) {
            Label cLabel = new Label("" + level.getCities().get(i).getCityName());
            cLabel.setStyle("-fx-text-fill: white; -fx-font-family: Arial; -fx-font-size: 10px; -fx-font-weight: bold;");
            Images image = new Images();
            ImageView cityImage=image.getImage(level.getCities().get(i).getCityName()); 
            
            centerPane.add(cityImage, level.getXPosition(level.getCities().get(i).getLocationID()), level.getYPosition(level.getCities().get(i).getLocationID()));
            centerPane.add(cLabel, level.getXPosition(level.getCities().get(i).getLocationID()), level.getYPosition(level.getCities().get(i).getLocationID()));
            cLabel.toBack();
            cityImage.toBack();
            cityImage.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
            
           
            //this loop will put the vehicle images to the pane in the beggining of each level
            if (level.getCities().get(i).getCityID() == level.getVehicle().getCurrentCityId()) {

                if (level.getVehicle().getPassengerCapacity() <= 5) {
                   
                    centerPane.add(vehicle, level.getXPosition(level.getCities().get(i).getLocationID()), level.getYPosition(level.getCities().get(i).getLocationID()));
                  
                } else if (level.getVehicle().getPassengerCapacity() <= 20) {
                
                    centerPane.add(vehicle, level.getXPosition(level.getCities().get(i).getLocationID()), level.getYPosition(level.getCities().get(i).getLocationID()));
                
                } else {
                    
                    centerPane.add(vehicle, level.getXPosition(level.getCities().get(i).getLocationID()), level.getYPosition(level.getCities().get(i).getLocationID()));

                }

            }

        }

        //these statements will draw the roads 
        centerPane.setOnMouseClicked(e -> {

            if (point1) {
                startX = e.getX();
                startY = e.getY();
                point1 = false;
            } else {
                endX = e.getX();
                endY = e.getY();
                while (!(reachPoint)) {

                   
                    Line line1 = new Line(startX, startY, startX, endY);
                    line1.setStrokeWidth(5);
                    line1.setStroke(Color.BLACK);
                    linePane.getChildren().add(line1);

                    Line line2 = new Line(startX, endY, endX, endY);
                    line2.setStrokeWidth(5);
                    line2.setStroke(Color.BLACK);
                    linePane.getChildren().add(line2);
                    reachPoint = true;

                }
                point1 = true;
                reachPoint = false;
                //last clicked cell will be shown in the stage
                destinationLabel.setText("Destination: "+ level.getVehicle().getDestinationCityName());
                distanceLabel.setText("Distance: "+ distance(level.getVehicle().getOldPosition(),level.getVehicle().getCurrentPosition()));
            }
        });
        centerPane.getChildren().add(linePane);
        linePane.toBack();
        new Translate();
        centerPane.setMaxSize(550, 550);
        //these statements will put the background images based on the level number
        Image image = new Image("backGroundImages/"+levelNumber+".jpg");
        ImageView background = new ImageView(image);
        background.setOpacity(0.4);
        background.setFitWidth(1000);
        background.setFitHeight(750);
        rootPane.getChildren().add(background);
        background.toBack();
        rootPane.setStyle("-fx-background-color: rgba(149, 65, 0, 0.15);-fx-background-repeat: stretch; -fx-background-size: 900 750; ");
        

        Scene scene = new Scene(rootPane, 900, 750);

        primaryStage.setTitle("City Driving Game");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

    }
    //this function will updates and prints the current passenger situations
    private void redrawLabels(Level level) {
    	
        for (Route r : level.getRoutes()) {
            String startCity = "";
            String endCity = "";
            for (City c : level.getCities()) {
                if (c.getCityID() == r.getStartCityID()) {
                    startCity = c.getCityName();
                } else if (c.getCityID() == r.getDestinationCityID()) {
                    endCity = c.getCityName();
                }
            }
            Label pInfo = new Label(startCity + " -> " + endCity + " (" + r.getPassengerCount() + " Passengers)");
            pInfo.setStyle("-fx-text-fill: blue; -fx-font-family: Times New Roman; -fx-font-size: 10px; -fx-font-weight: bold;");
            pInfo.setAlignment(Pos.CENTER_LEFT);   
            rightBottomPane.getChildren().add(pInfo);
        }
    }

    
    public int getLevelNum(Level level) {
        return level.getNumber();
    }
    //this method calcutes the distance between 2 points according to Eucledian formula
    public double distance (int p1,int p2){
        int x1=p1/10,y1=p1%10;
        int x2=p2/10,y2=p2%10;

        double distance = Math.sqrt(Math.pow(Math.abs(x1-x2),2)+Math.pow(Math.abs(y1-y2),2));
        return Math.round(distance)+1;

    }
    //this method calculates the score according to given distance and number of passengers
    public double calculateScore(int distance, int numbersOfPassengers){
        return (numbersOfPassengers*distance*0.2)-distance;
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }
}