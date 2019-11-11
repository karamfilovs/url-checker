import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> retrieveLinksFromFile(String filePath) {
        List<String> links = new ArrayList<>();
        // pass the path to the file as a parameter
        File file =
                new File(filePath);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (sc.hasNextLine()) {
            links.add(sc.next());

        }
        System.out.println("Links found in file:" + links.size());
        return links;
    }

}