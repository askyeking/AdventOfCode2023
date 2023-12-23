package advent.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventDay1Part1 {

    public static void main(String[] args) {
        AdventDay1Part1 d1 = new AdventDay1Part1();
        d1.run();
    }

    public void run() {
        List<String> lines = readFile();
        int sum = lines.stream().map( (String line) -> {return line.replaceAll("[a-z]", "");})
        .mapToInt( (String line) -> { 
            return (line.charAt(0) - '0') * 10 + line.charAt(line.length()-1) -'0';
        }).sum();
        System.out.println(sum);
    }

   
    public List<String> readFile() {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufIn = new BufferedReader(new FileReader("files/day1.txt"))) {
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
