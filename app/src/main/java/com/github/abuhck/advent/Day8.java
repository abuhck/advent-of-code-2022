package com.github.abuhck.advent;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 extends AdventPuzzle {

    public Day8() {
        super("8");
    }

    public Map<String, List<Integer>> getAdjacents (List<List<Integer>> matrix, int xPos, int yPos, int max_X, int max_Y) {
        Map<String, List<Integer>> adjacents = new HashMap<>();

        List<Integer> leftVals = new ArrayList<>();
        List<Integer> rightVals = new ArrayList<>();
        List<Integer> topVals = new ArrayList<>();
        List<Integer> bottomVals = new ArrayList<>();

        for (int i = 0; i < xPos; i++) {
            leftVals.add(matrix.get(yPos).get(i));
        }
        adjacents.put("left", leftVals);

        for (int i = xPos + 1; i < max_X + 1; i++) {
            rightVals.add(matrix.get(yPos).get(i));
        }
        adjacents.put("right", rightVals);

        for (int i = 0; i < yPos; i++) {
            topVals.add(matrix.get(i).get(xPos));
        }
        adjacents.put("top", topVals);

        for (int i = yPos + 1; i < max_Y + 1; i++) {
            bottomVals.add(matrix.get(i).get(xPos));
        }
        adjacents.put("bottom", bottomVals);

        return adjacents;
    }
    
    public int getVisibleTrees (List<List<Integer>> matrix, int max_X, int max_Y) {
        int count = (max_X * 2) + (max_Y * 2);
        for (int i = 1; i < max_Y; i++) {
            for (int j = 1; j < max_X; j++) {
                int currentPos = matrix.get(i).get(j);
                Map<String, List<Integer>> adjacents = getAdjacents(matrix, j, i, max_X, max_Y);
                if (Collections.max(adjacents.get("left")) < currentPos) {
                    count++;
                } else if (Collections.max(adjacents.get("right")) < currentPos) {
                    count++;
                } else if (Collections.max(adjacents.get("top")) < currentPos) {
                    count++;
                } else if (Collections.max(adjacents.get("bottom")) < currentPos) {
                    count++;
                }
            }
        }
        return count;
    }

    public int getViewDistance(int currentTree, List<Integer> adjacents) {
        int count = 0;
        for (int tree: adjacents) {
            if (tree < currentTree) {
                count++;
            } else {
                count++;
                break;
            }
        }
        return count;
    }

    public int getBestScenicScore (List<List<Integer>> matrix, int max_X, int max_Y) {
        List<Integer> scores = new ArrayList<>();
        for (int i = 1; i < max_Y; i++) {
            for (int j = 1; j < max_X; j++) {
                int currentPos = matrix.get(i).get(j);
                Map<String, List<Integer>> adjacents = getAdjacents(matrix, j, i, max_X, max_Y);
                List<Integer> treeViewDistances = List.of(
                    getViewDistance(currentPos, new ArrayList<>(Lists.reverse(adjacents.get("left")))),
                    getViewDistance(currentPos, new ArrayList<>(adjacents.get("right"))),
                    getViewDistance(currentPos, new ArrayList<>(Lists.reverse(adjacents.get("top")))),
                    getViewDistance(currentPos, new ArrayList<>(adjacents.get("bottom")))
                );
                scores.add(treeViewDistances.stream().reduce(1, (a, b) -> a * b));
            }
        }
        return Collections.max(scores);
    }

    void solve(List<String> input) {
        List<List<Integer>> forestMatrix = new ArrayList<>();
        for (String line : input) {
            List<Integer> row = new ArrayList<>(convertToInts(Arrays.asList(line.split(""))));
            forestMatrix.add(row);
        }

        final int max_X = forestMatrix.get(0).size() - 1;
        final int max_Y = forestMatrix.size() - 1;

        int countVisibleTrees = getVisibleTrees(forestMatrix, max_X, max_Y);
        int bestScenicScore = getBestScenicScore(forestMatrix, max_X, max_Y);

        // Part 1
        lap(countVisibleTrees);

        // Part 2
        lap(bestScenicScore);

    }

}
