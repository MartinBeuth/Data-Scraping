package Kaffeebohnen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) throws Exception {

        // Files
        String serFile = "C:/Users/Martin/Desktop/Kaffeebohnen/ser.ser";
        String csvFile = "C:/Users/Martin/Desktop/Kaffeebohnen/ser_spread.csv";

        // Performance
        Perform performer1 = new Perform();
        performer1.performance(serFile, csvFile);

    }
}
