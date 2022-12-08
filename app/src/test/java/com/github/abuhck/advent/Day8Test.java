package com.github.abuhck.advent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day8Test {

    Day8 day8;

    List<List<Integer>> matrix = new ArrayList<>(
        List.of(
            List.of(3, 0, 3, 7, 3),
            List.of(2, 5, 5, 1, 2),
            List.of(6, 5, 3, 3, 2),
            List.of(3, 3, 5, 4, 9),
            List.of(3, 5, 3, 9, 0)
        )
    );

    final int max_x = matrix.get(0).size() - 1;
    final int max_y = matrix.size() - 1;

    @BeforeEach
    void setUp() {
        day8 = new Day8();
    }

    @Test
    public void testGetAdjacents() {

        Map<String, List<Integer>> expectedAdjacents = Map.of(
            "left", List.of(6, 5),
            "right", List.of(3, 2),
            "top", List.of(3, 5),
            "bottom", List.of(5, 3)
        );

        Map<String, List<Integer>> actualAdjacents = day8.getAdjacents(matrix, 2, 2, max_x, max_y);

        assertEquals(expectedAdjacents.get("left"), actualAdjacents.get("left"));
        assertEquals(expectedAdjacents.get("right"), actualAdjacents.get("right"));
        assertEquals(expectedAdjacents.get("top"), actualAdjacents.get("top"));
        assertEquals(expectedAdjacents.get("bottom"), actualAdjacents.get("bottom"));

    }

    @Test
    public void testGetVisibleTrees() {
        int expectedCount = 21;
        int actualCount = day8.getVisibleTrees(matrix, max_x, max_y);

        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testGetBestScenicScore() {
        int expectedBestScore = 8;
        int actualBestScore = day8.getBestScenicScore(matrix, max_x, max_y);

        assertEquals(expectedBestScore, actualBestScore);
    }

    @Test
    public void testGetViewDistance() {
        int currentPos = 5;
        Map<String, List<Integer>> adjacents = day8.getAdjacents(matrix, 2, 1, max_x, max_y);

        assertEquals(2, day8.getViewDistance(currentPos, adjacents.get("left")));
        assertEquals(2, day8.getViewDistance(currentPos, adjacents.get("right")));
        assertEquals(1, day8.getViewDistance(currentPos, adjacents.get("top")));
        assertEquals(2, day8.getViewDistance(currentPos, adjacents.get("bottom")));

    }
}
