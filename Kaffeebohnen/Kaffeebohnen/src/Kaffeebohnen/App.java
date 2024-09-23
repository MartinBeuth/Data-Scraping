package Kaffeebohnen;

import Kaffeebohnen.Performanceverbesserung.*;

public class App {
    public static void main(String[] args) throws Exception {

        // Files and Database
        String serFile = "C:/Users/Martin/Desktop/Kaffeebohnen/ser.ser";
        String csvFile = "C:/Users/Martin/Desktop/Kaffeebohnen/ser_spread.csv";
        Database data = new Database();

        // Performance
        // Perform performer1 = new Perform();
        // performer1.performance(serFile, csvFile);

        // Performance Verbesserung
        Performupdate performer2 = new Performupdate();
        performer2.performance(serFile, csvFile, data);

        data.deleteDatabase();

    }
}
