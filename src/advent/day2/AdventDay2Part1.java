package advent.day2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import advent.utilities.AdventFileReader;

public class AdventDay2Part1 {

    Map<String, Integer> colorDensities;

    public AdventDay2Part1() {
        colorDensities = new HashMap<>();
        colorDensities.put("green", 13);
        colorDensities.put("blue", 14);
        colorDensities.put("red", 12);

    }

    public static void main(String[] args) {
        AdventDay2Part1 adp = new AdventDay2Part1();
        adp.run();
    }

    public void run() {
        AdventFileReader afr = new AdventFileReader();
        List<String> games = afr.readFile("files/day2.txt");
        Map<Integer, String> gameData = seperateIDs(games);
        List<Integer> validGameIds = validateGames(gameData);
        int answer = validGameIds.stream().reduce(0, (Integer a, Integer b) -> {
            return a + b;
        });
        System.out.println(answer);
        System.out.println(validGameIds.size());
    }

    public List<Integer> validateGames(Map<Integer, String> gameData) {
        List<Integer> validGameIds = new ArrayList<>();
        
        for (Integer id : gameData.keySet()) {
            boolean valid = true;
            String game = gameData.get(id);
            String[] rounds = game.split("; ");
            for (String round : rounds) {
                String[] draws = round.split(", ");
                for (String draw : draws) {
                    String[] drawInfo = draw.split(" ");
                    if (colorDensities.get(drawInfo[1]) < Integer.parseInt(drawInfo[0])) {
                        valid = false;
                        System.out.println(drawInfo[1] + " : " + colorDensities.get(drawInfo[1]) + " ; " + Integer.parseInt(drawInfo[0]));
                    }
                }
            }
            if(valid) {
                validGameIds.add(id);
            }
        }
        return validGameIds;
    }

    public Map<Integer, String> seperateIDs(List<String> games) {
        Map<Integer, String> gameMap = new HashMap<>();
        for (String game : games) {
            // example:
            // Game 1: 2 blue, 3 red; 3 green, 3 blue, 6 red; 4 blue, 6 red; 2 green, 2
            // blue, 9 red; 2 red, 4 blue
            // becomes...
            // index 0: "Game 1"
            // index 1: 2 blue, 3 red; 3 green, 3 blue, 6 red; 4 blue, 6 red; 2 green, 2
            // blue, 9 red; 2 red, 4 blue
            String[] gameInfo = game.split(": ");
            // example: "Game 1" -> "1"
            gameInfo[0] = gameInfo[0].split(" ")[1];
            // convert Id as string into Number, use as key to game info.
            gameMap.put(Integer.parseInt(gameInfo[0]), gameInfo[1]);
        }
        return gameMap;
    }
}
