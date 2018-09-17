import flightmap.FlightMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestFlightMap {

    FlightMap flightMap;

    @Before
    public void setUp() throws IOException{
        flightMap = new FlightMap("resources/outputfile.txt");
    }

    @After
    public void clean() throws IOException{
        flightMap.after();
    }

    @Test
    public void TestReadInput() throws IOException{
        flightMap.readInput("resources/inputfile.txt");
        assert(flightMap.getOriginCity().equals("P"));
    }

    @Test
    public void TestGeneratePaths() throws IOException{
        flightMap.generatePaths();
        assert(flightMap.getPath("Z").equals("PWYZ"));
        assert(flightMap.getCosts("Z") == 1300);
    }

    @Test
    public void TestWriteToFile() throws IOException{

        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        flightMap.writeToFile("B", list, 1234);
    }


}
