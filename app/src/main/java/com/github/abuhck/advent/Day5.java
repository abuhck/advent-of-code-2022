package com.github.abuhck.advent;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java. util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Optional;

public class Day5 extends AdventPuzzle {

    public Day5() {
        super("5");
    }

//    [G]                 [D] [R]
//    [W]         [V]     [C] [T] [M]
//    [L]         [P] [Z] [Q] [F] [V]
//    [J]         [S] [D] [J] [M] [T] [V]
//    [B]     [M] [H] [L] [Z] [J] [B] [S]
//    [R] [C] [T] [C] [T] [R] [D] [R] [D]
//    [T] [W] [Z] [T] [P] [B] [B] [H] [P]
//    [D] [S] [R] [D] [G] [F] [S] [L] [Q]
//    1   2   3   4   5   6   7   8   9


    private Optional<String> extractFirstMatch(Pattern regexPattern, String toSearch) {
        Matcher m = regexPattern.matcher(toSearch);
        if(m.find()) {
            return Optional.of(m.group(1));
        } else {
            return Optional.empty();
        }
    }

    static final Pattern Q_PATTERN = Pattern.compile("(?<=move )(.*)(?= from)");
    static final Pattern O_PATTERN = Pattern.compile("(?<=from )(.*)(?= to)");
    static final Pattern D_PATTERN = Pattern.compile("(?<=to )(.*)");

    private String moveCratesP1(List<Deque<String>> crateStacks, List<String> instructions) {

        for(String line : instructions) {
            int quantity = Integer.parseInt(extractFirstMatch(Q_PATTERN, line).orElseThrow(IllegalStateException::new));
            int origin = Integer.parseInt(extractFirstMatch(O_PATTERN, line).orElseThrow(IllegalStateException::new));
            int destination = Integer.parseInt(extractFirstMatch(D_PATTERN, line).orElseThrow(IllegalStateException::new));

            for(int i = 1; i <= quantity; i++) {
                String toMove = crateStacks.get(origin - 1).pop();
                crateStacks.get(destination - 1).push(toMove);
            }
        }

        List<String> topCrates = new ArrayList<>();
        for(Deque<String> stack : crateStacks) {
            String top = stack.peekFirst();
            topCrates.add(String.valueOf(top));
        }

        return String.join("", topCrates);
    }

    private String moveCratesP2(List<Deque<String>> crateStacks, List<String> instructions) {
        for (String line : instructions) {
            int quantity = Integer.parseInt(
                extractFirstMatch(Q_PATTERN, line).orElseThrow(IllegalStateException::new));
            int origin = Integer.parseInt(
                extractFirstMatch(O_PATTERN, line).orElseThrow(IllegalStateException::new));
            int destination = Integer.parseInt(
                extractFirstMatch(D_PATTERN, line).orElseThrow(IllegalStateException::new));

            String[] toMove = new String[quantity];
            for(int i = 0; i <= quantity - 1; i++) {
                toMove[i] = crateStacks.get(origin - 1).pop();
            }
            Collections.reverse(Arrays.asList(toMove));
            for(String c : toMove) {
                crateStacks.get(destination - 1).push(c);
            }
        }

        List<String> topCrates = new ArrayList<>();
        for(Deque<String> stack : crateStacks) {
            String top = stack.peekFirst();
            topCrates.add(String.valueOf(top));
        }

        return String.join("", topCrates);
    }


    void solve(List<String> input) {

        Deque<String> s1 = new ArrayDeque<>(List.of("G", "W", "L", "J", "B", "R", "T", "D"));
        Deque<String> s2 = new ArrayDeque<>(List.of("C", "W", "S"));
        Deque<String> s3 = new ArrayDeque<>(List.of("M", "T", "Z", "R"));
        Deque<String> s4 = new ArrayDeque<>(List.of("V", "P", "S", "H", "C", "T", "D"));
        Deque<String> s5 = new ArrayDeque<>(List.of("Z", "D", "L", "T", "P", "G"));
        Deque<String> s6 = new ArrayDeque<>(List.of("D", "C", "Q", "J", "Z", "R", "B", "F"));
        Deque<String> s7 = new ArrayDeque<>(List.of("R", "T", "F", "M", "J", "D", "B", "S"));
        Deque<String> s8 = new ArrayDeque<>(List.of("M", "V", "T", "B", "R", "H", "L"));
        Deque<String> s9 = new ArrayDeque<>(List.of("V", "S", "D", "P", "Q"));
        List<Deque<String>> stacks = new ArrayList<>(List.of(s1, s2, s3, s4, s5, s6, s7, s8, s9));
//        TODO: add deep copy so both parts can run in sequence
//        lap(moveCratesP1(stacks, input));
        lap(moveCratesP2(stacks, input));

    }
}
