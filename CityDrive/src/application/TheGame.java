package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;

public class TheGame {
    //all levels constructed and stored in here
    private ArrayList<Level> levels;
    //check if current level completed
    private boolean isCompleted;


    public TheGame() {
        this.isCompleted=false;
        levels= new ArrayList<>();
    }


    //this method read every level with Reader object and stores here
    public void readLevels() throws FileNotFoundException {
        File f = new File("src/levels");


        for (int i = 0; i < f.listFiles().length; i++) {
            Reader reader = new Reader(f.listFiles()[i]);
            reader.read();
            Level l = new Level(reader);
            l.init();
            levels.add(l);
        }
        levels.sort(new Comparator<Level>() {
            @Override
            public int compare(Level o1, Level o2) {
                if (o1.getNumber() < o2.getNumber()) {
                    return -1;
                } else if (o1.getNumber() > o2.getNumber()) {
                    return 1;
                }
                return 0;
            }
        });
    }


    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

}