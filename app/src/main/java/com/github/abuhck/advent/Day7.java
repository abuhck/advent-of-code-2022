package com.github.abuhck.advent;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Day7 extends AdventPuzzle {

    public Day7() {
        super("7");
    }

    static final String CMDPREFIX = "$";
    static final String ROOT = "/";
    static final String DIRPREFIX = "dir";
    static final int MAX_SIZE = 100000;
    static final int TOTAL_SPACE = 70000000;
    static final int NEEDED_SPACE = 30000000;

    void solve(List<String> input) {

        Map<String, Integer> dirSizes = new HashMap<>();
        // Use stack to keep track of our current position in the file tree
        Deque<String> dirs = new ArrayDeque<>();

        for(String line: input) {
            String[] args = line.split(" ");

            if (args[0].equals(CMDPREFIX)) {
                if (args[1].equals("cd")) {
                    if (args[2].equals("..")) {
                        dirs.pop();
                    } else if (args[2].equals(ROOT)) {
                        // Reset the stack
                        dirs.clear();
                        dirs.push(ROOT);
                    } else {
                        dirs.push(dirs.peek() + "/" + args[2]);
                    }
                    dirSizes.putIfAbsent(dirs.peek(), 0);
                }
            } else {
                // If is just a file
                if (!args[0].equals(DIRPREFIX)) {
                    int size = Integer.parseInt(args[0]);
                    Deque<String> copy = new ArrayDeque<>(dirs);
                    while (!copy.isEmpty()) {
                        // Update current dir and all parents with new size
                        dirSizes.compute(copy.pop(), (i, oldSize) -> oldSize + size);
                    }
                }
            }
        }

        // Part 1
        int p1Total = dirSizes.values()
            .stream()
            .mapToInt(n -> n)
            .filter(n -> n <= MAX_SIZE)
            .sum();

        lap(p1Total);

        // Part 2
        int totalUsedSpace = dirSizes.get(ROOT);
        int minDirSize = dirSizes.values()
            .stream()
            .mapToInt(n -> n)
            .filter(amountFreed -> (TOTAL_SPACE - totalUsedSpace + amountFreed) > NEEDED_SPACE)
            .min()
            .orElseThrow(NoSuchElementException::new);

        lap(minDirSize);

    }
}
