package com.github.abuhck.advent;

import java.util.*;
import java.util.stream.Collectors;

public class Day1 extends AdventPuzzle{
    public Day1() {
        super("1");
    }

    @Override
    void solve(List<String> input) {
        // Part 1
        Map<Integer, List<Long>> elvesToCalories = new HashMap<>();
        int elf = 1;
        List<Long> calories = new ArrayList<>();
        for (String val : input) {
            System.out.println("Elf: " + elf + " | " + "Val: " + val);
            if (val.isEmpty()) {
                elvesToCalories.put(elf, new ArrayList<>(calories));
                calories.clear();
                elf++;
            } else {
                calories.add(Long.parseLong(val));
            }
        }

        long maxCalories = 0;
        List<Long> allSums = new ArrayList<>();
        for (Map.Entry<Integer, List<Long>> entry: elvesToCalories.entrySet()) {
           long sum = entry.getValue().stream()
                   .mapToLong(Long::longValue)
                   .sum();
           allSums.add(sum);
           if (maxCalories < sum) {
               maxCalories = sum;
           }
        }
        allSums.sort(Collections.reverseOrder());
        List<Long> topThree = allSums.stream().limit(3).collect(Collectors.toList());
        lap(maxCalories);
        lap(topThree.stream()
                .mapToLong(Long::longValue)
                .sum());


    }
}
