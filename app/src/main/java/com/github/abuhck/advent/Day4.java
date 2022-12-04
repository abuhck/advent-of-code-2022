package com.github.abuhck.advent;

import java.util.List;

public class Day4 extends AdventPuzzle {

    public Day4() {
        super("4");
    }

    @Override
    void solve(List<String> input) {
        int count = 0;
        int p2Count = 0;
        for (String line : input) {
            String[] sections = line.split(",");
            int[] firstPart = convertToInts(sections[0].split("-"));
            int[] secondPart = convertToInts(sections[1].split("-"));
            if(firstPart[0] <= secondPart[0] && firstPart[1] >= secondPart[1] || secondPart[0] <= firstPart[0] && secondPart[1] >= firstPart[1]) {
                count++;
            }
            if(firstPart[0] <= secondPart[1] && secondPart[0] <= firstPart[1]) {
                p2Count++;
            }
        }
        lap(count);
        lap(p2Count);
    }
}
