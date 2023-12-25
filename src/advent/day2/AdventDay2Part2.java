package advent.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import advent.utilities.AdventFileReader;

public class AdventDay2Part2 {

    public static void main(String[] args) {
        AdventDay2Part2 adp = new AdventDay2Part2();
        adp.run();
    }

    public void run() {
        AdventFileReader afr = new AdventFileReader();
        List<String> games = removeIDs(afr.readFile("files/day2.txt"));
        List<Integer> powers = getPowers(games);
        int total = powers.stream().reduce(0, (Integer a, Integer b) -> {
            return a + b;
        });
        System.out.println(total);
    }

    public List<Integer> getPowers(List<String> games) {
        List<Integer> powers = new ArrayList<>();
        for (String game : games) {
            Map<String, Set<Integer>> colors = new HashMap<>();
            colors.put("blue", new TreeSet<>((a, b) -> b - a));
            colors.put("red", new TreeSet<>((a, b) -> b - a));
            colors.put("green", new TreeSet<>((a, b) -> b - a));
            String[] drawsArray = game.split(", |; ");
            for (String draw : drawsArray) {
                int num = Integer.parseInt(draw.split(" ")[0]);
                if (draw.contains("blue")) {
                    colors.get("blue").add(num);
                } else if (draw.contains("red")) {
                    colors.get("red").add(num);
                } else if (draw.contains("green")) {
                    colors.get("green").add(num);
                }
            }
            List<Integer> lowestNums = new ArrayList<>();
            for (String key : colors.keySet()) {
                System.out.println(colors.get(key));
                lowestNums.add(colors.get(key).iterator().next());
            }
            powers.add(lowestNums.stream().reduce(1, (Integer a, Integer b) -> {
                return a * b;
            }));

        }
        return powers;
    }

    public List<String> removeIDs(List<String> games) {
        List<String> gameMap = new ArrayList<>();
        for (String game : games) {
            String[] gameInfo = game.split(": ");
            gameMap.add(gameInfo[1]);
        }
        return gameMap;
    }
}
