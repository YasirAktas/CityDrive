package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SplittableRandom;

public class Reader {
    //file witch level information about to be read
    private File file;
    //scanner to read files
    private Scanner scanner;
    //every lines in the file
    private ArrayList<String> inputLine = new ArrayList<>();

    //need a file to construct Reader
    public Reader(File file) {
        this.file = file;
    }
    //while there are inputs in the file, scanner reads lines and saves them as strings
    public void read() throws FileNotFoundException {
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            inputLine.add(new String(scanner.nextLine()));
        }
    }


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public ArrayList<String> getInputLine() {
        return inputLine;
    }

    public void setInputLine(ArrayList<String> inputLine) {
        this.inputLine = inputLine;
    }

}


