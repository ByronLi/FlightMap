package flightmap;

import java.io.*;
import java.util.HashMap;

public class FlightMap {

    HashMap<String, HashMap<String, Integer>> flightGraph;

    public FlightMap() {
        flightGraph = new HashMap<>();
    }

    public void readInput(String filePath) throws FileNotFoundException, IOException{
        File f = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(f));
        String originCity = br.readLine();
        flightGraph.put(originCity, new HashMap<>()); //initialize map

        String inputLine;

        while ((inputLine = br.readLine()) != null);

        String[] splitInput = inputLine.split(" ");

        String currentOrigin = splitInput[0];
        String currentDestination = splitInput[1];
        int cost = new Integer(splitInput[2]);

        flightGraph.get(currentOrigin).put(currentDestination, cost);
        if (!flightGraph.containsKey(currentDestination)){
            flightGraph.put(currentDestination, new HashMap<>());
        }


    }

}
