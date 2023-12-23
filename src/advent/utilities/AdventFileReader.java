package advent.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * AdventFileReader
 */
public class AdventFileReader {

    public List<String> readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufIn = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufIn.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return lines;
    }

}