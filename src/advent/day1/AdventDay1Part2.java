package advent.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdventDay1Part2 {

    Map<String, String> digits;

    public static void main(String[] args) {
        AdventDay1Part2 d1 = new AdventDay1Part2();
        d1.run();
    }

    public AdventDay1Part2() {
        digits = new HashMap<>();
        digits.put("one", "o1ne");
        digits.put("two", "t2wo");
        digits.put("three", "t3hree");
        digits.put("four", "f4our");
        digits.put("five", "f5ive");
        digits.put("six", "s6ix");
        digits.put("seven", "s7even");
        digits.put("eight", "e8ight");
        digits.put("nine", "n9ine");
    }

    public void run() {
        List<String> lines = readFile();
        int sum = lines.stream()
        .map((String line) -> {
            for (String digitString : digits.keySet()) {
                line = line.replaceAll(digitString, digits.get(digitString));
            }
            line = line.replaceAll("[a-z]", "");
            return line;
        })
        .mapToInt( (String line) -> {
        return (line.charAt(0) - '0') * 10 + line.charAt(line.length()-1) - '0';
        })
        .sum();
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
