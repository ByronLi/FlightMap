import Main.SearchMap;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class TestSearchMap {

    @Test
    public void TestMain() throws IOException{
        String[] args = {"resources/inputfile.txt", "resources/outputfile.txt"};
        SearchMap.main(args);
        File f = new File("resources/outputfile.txt");
        assert(f != null);
    }
}
