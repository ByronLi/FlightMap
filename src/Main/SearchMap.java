package Main;

import flightmap.FlightMap;

public class SearchMap {

    public static void main(String[] args) {

        try {
            FlightMap flightMap = new FlightMap(args[1]);
            flightMap.readInput(args[0]);
            flightMap.generatePaths();
            flightMap.after();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
