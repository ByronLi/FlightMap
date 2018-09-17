package flightmap;

import java.io.*;
import java.util.*;

public class FlightMap {

    HashMap<String, HashMap<String, Integer>> flightGraph;
    HashMap<String, ArrayList<String>> paths;
    HashMap<String, Integer> totalCost;
    String originCity;
    String outputFile;
    BufferedWriter writer;

    public FlightMap(String outputFile) throws IOException{
        flightGraph = new HashMap<>();
        totalCost = new HashMap<>();
        paths = new HashMap<>();
        this.outputFile = outputFile;

        writer = new BufferedWriter(new FileWriter(outputFile));
        writer.write("Destination    Flight Route from P     Total Cost\n");
    }

    public void readInput(String filePath) throws FileNotFoundException, IOException {
        File f = new File(filePath);

        BufferedReader br = new BufferedReader(new FileReader(f));
        originCity = br.readLine();
        flightGraph.put(originCity, new HashMap<>()); //initialize map

        String inputLine;

        while ((inputLine = br.readLine()) != null) {

            if (inputLine == null){
                break;
            }
            String[] splitInput = inputLine.split(" ");

            String currentOrigin = splitInput[0];
            String currentDestination = splitInput[1];
            int cost = new Integer(splitInput[2]);

            if (!flightGraph.containsKey(currentOrigin)) {
                flightGraph.put(currentOrigin, new HashMap<>());
            }

            flightGraph.get(currentOrigin).put(currentDestination, cost);
        }

        br.close();
    }

    public void generatePaths(){

        Queue<String> bfs = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();

        totalCost.put(originCity, 0); //start at origin city
        bfs.add(originCity);
        visited.add(originCity);

        ArrayList<String> previousCities = new ArrayList<>();
        previousCities.add(originCity);
        paths.put(originCity, previousCities);

        while (!bfs.isEmpty()){
            //start with origin city
            String currentCity = bfs.remove();

            //get all destination cities
            if (flightGraph.get(currentCity) != null) {
                for (Map.Entry<String, Integer> pair : flightGraph.get(currentCity).entrySet()) {


                    //get all the info for current destination
                    String destination = pair.getKey();
                    int currentCost = pair.getValue();
                    ArrayList<String> pathToCity = new ArrayList<>();
                    //deep copy origin path list
                    for (String city : paths.get(currentCity)) {
                        pathToCity.add(city);
                    }

                    //update data structures
                    pathToCity.add(destination);
                    paths.put(destination, pathToCity);
                    totalCost.put(destination, totalCost.get(currentCity) + currentCost);


                    //add destinations to bfs and write to file
                    if (!visited.contains(destination)) {

                        try {
                            writeToFile(destination, paths.get(destination), totalCost.get(destination));
                        }
                        catch(IOException ioe){
                            ioe.printStackTrace();
                        }
                        bfs.add(destination);
                        visited.add(destination);
                    }
                }
            }
        }
    }

    public void writeToFile(String destination, ArrayList<String> pathsToDestination, int cost) throws IOException{
        System.out.print("Destination: " + destination + " Cost: " + cost);
        for (String city : pathsToDestination){
            System.out.print(city + ", ");
        }
        System.out.println();

        writer.append(destination + "               ");
        StringBuilder sb = new StringBuilder();
        for (String city : pathsToDestination){
            sb.append(city + ", ");
        }
        sb.delete(sb.length()-2, sb.length());
        writer.append(sb.toString() + "                    ");
        writer.append("$" + cost + "\n");

    }

    public void after() throws IOException{
        writer.close();
    }

}
