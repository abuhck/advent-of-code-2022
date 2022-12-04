package com.github.abuhck.advent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3 extends AdventPuzzle {
    public Day3() {
        super("3");
    }

    @Override
    void solve(List<String> input) {

        Map<Character, Integer> priorityValues = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++) {
            // a = 1
            priorityValues.put(c, Character.getNumericValue(c) - 9);
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            // A = 27
            priorityValues.put(c, Character.getNumericValue(c) + 17);
        }

        int total = 0;

        for (String line : input) {
            int midPoint = line.length() / 2;
            String[] halves = {line.substring(0, midPoint), line.substring(midPoint)};
            for (char c : halves[0].toCharArray()) {
                if (halves[1].contains(Character.toString(c))) {
                    total = total + priorityValues.get(c);
                    break;
                }
            }
        }

        // Part 1
        lap(total);

        int p2Total = 0;
        for (int i = 0; i <= input.size() - 1; i+= 3) {
            String first = input.get(i);
            String second = input.get(i + 1);
            String third = input.get(i + 2);
            for (char c : first.toCharArray()) {
                if (second.contains(Character.toString(c)) && third.contains(Character.toString(c))) {
                    p2Total = p2Total + priorityValues.get(c);
                    break;
                }
            }
        }

        // Part 2
        lap(p2Total);

    }

}
