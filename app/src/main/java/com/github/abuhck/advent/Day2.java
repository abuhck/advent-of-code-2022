package com.github.abuhck.advent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;

public class Day2 extends AdventPuzzle {

    public Day2() {
        super("2");
    }

    // A = Rock (1)
    // B = Paper (2)
    // C = Scissors (3)

    // X = Rock (1)
    // Y = Paper (2)
    // Z = Scissors (3)

    // Loss = 0
    // Draw = 3
    // Win = 6
    @Override
    void solve(List<String> input) {
        List<char[]> moves = new ArrayList<>();
        for (String move : input) {
            char[] chars = move.toCharArray();
            moves.add(new char[] {chars[0], chars[2]});
        }

        int r1TotalScore = 0;
        int r2TotalScore = 0;

        for (char[] m : moves) {
            r1TotalScore = r1TotalScore + scoreGame(m);
            r2TotalScore = r2TotalScore + scoreGameRound2(m);
        }

        lap(r1TotalScore);
        lap(r2TotalScore);

    }

    public int scoreGame(char[] moves) {
        char opponentMove = moves[0];
        char myMove = moves[1];

        Map<Character, Integer> pointValues = Map.ofEntries(
                entry('A', 1),
                entry('B', 2),
                entry('C', 3),
                entry('X', 1),
                entry('Y', 2),
                entry('Z', 3)
        );
        Map<String, Integer> outcomes = Map.ofEntries(
                entry("WIN", 6),
                entry("DRAW", 3),
                entry("LOSS", 0)
        );

        // Starting score is the value of the move we played
        int score = pointValues.get(myMove);
        String result = null;

        switch (opponentMove) {
            case 'A': if (myMove == 'X') {
                result = "DRAW";
                break;
            } else if (myMove == 'Y') {
                result = "WIN";
                break;
            } else if (myMove == 'Z') {
                result = "LOSS";
                break;
            }
            case 'B': if (myMove == 'X') {
                result = "LOSS";
                break;
            } else if (myMove == 'Y') {
                result = "DRAW";
                break;
            } else if (myMove == 'Z') {
                result = "WIN";
                break;
            }
            case 'C': if (myMove == 'X') {
                result = "WIN";
                break;
            } else if (myMove == 'Y') {
                result = "LOSS";
                break;
            } else if (myMove == 'Z') {
                result = "DRAW";
                break;
            }
        }

        score = score + outcomes.get(result);
        
        return score;

    }

    public int scoreGameRound2(char[] moves) {
        char opponentMove = moves[0];
        char desiredResult = moves[1];

        Map<Character, Integer> pointValues = Map.ofEntries(
                entry('A', 1),
                entry('B', 2),
                entry('C', 3),
                entry('X', 1),
                entry('Y', 2),
                entry('Z', 3)
        );

        Map<String, Integer> outcomes = Map.ofEntries(
                entry("WIN", 6),
                entry("DRAW", 3),
                entry("LOSS", 0)
        );
        
        Map<Character, String> desiredOutcomes = Map.ofEntries(
                entry('X', "LOSS"),
                entry('Y', "DRAW"),
                entry('Z', "WIN")
        );

        // Starting score is the value of the outcome we want (e.g. "LOSE" = 0)
        int score = outcomes.get(desiredOutcomes.get(desiredResult));
        char toPlay = 0;

        switch (opponentMove) {
            case 'A': if (desiredResult == 'X') {
                toPlay = 'Z';
                break;
            } else if (desiredResult == 'Y') {
                toPlay = 'X';
                break;
            } else if (desiredResult == 'Z') {
                toPlay = 'Y';
                break;
            }
            case 'B': if (desiredResult == 'X') {
                toPlay = 'X';
                break;
            } else if (desiredResult == 'Y') {
                toPlay = 'Y';
                break;
            } else if (desiredResult == 'Z') {
                toPlay = 'Z';
                break;
            }
            case 'C': if (desiredResult == 'X') {
                toPlay = 'Y';
                break;
            } else if (desiredResult == 'Y') {
                toPlay = 'Z';
                break;
            } else if (desiredResult == 'Z') {
                toPlay = 'X';
                break;
            }
        }
        
        score = score + pointValues.get(toPlay);

        return score;
        
    }
}
