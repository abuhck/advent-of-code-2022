package com.github.abuhck.advent;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Day6 extends AdventPuzzle {

    public Day6() {
        super("6");
    }

    private int findMarker(int markerSize, String message) {
        char[] characters = message.toCharArray();
        int result = 0;
        Deque<Character> lastFour = new ArrayDeque<>();
        for (int i = 0; i < characters.length; i++) {
            if (lastFour.size() < markerSize) {
                lastFour.addLast(characters[i]);
            } else {
                lastFour.removeFirst();
                lastFour.addLast(characters[i]);
            } if (lastFour.size() == markerSize && lastFour.stream().distinct().count() == markerSize) {
                result = i + 1;
                break;
            }
        }
        return result;
    }

    @Override
    void solve (List<String> input) {
        String message = input.get(0);
        int p1Result = findMarker(4, message);
        int p2Result = findMarker(14, message);
        lap(p1Result);
        lap(p2Result);
    }
}
