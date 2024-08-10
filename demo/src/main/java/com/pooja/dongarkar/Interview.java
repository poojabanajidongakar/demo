package com.pooja.dongarkar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Interview {

    public static void main(String[] args) {
        String filePath = "C:\\Upload\\resume\\Pooja.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder contentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append(" ");
            }
            String resumeContent = contentBuilder.toString().toLowerCase();

            // Split text into words and count occurrences
            String[] words = resumeContent.replaceAll("[^a-zA-Z0-9 ]", "").split("\\s+");
            Map<String, Integer> wordCount = new HashMap<>();
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }

            // Print the formatted data
            printFormattedData(wordCount);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    private static void printFormattedData(Map<String, Integer> wordCount) {
        int maxCount = Integer.MIN_VALUE;
        int secondMaxCount = Integer.MIN_VALUE;
        int minCount = Integer.MAX_VALUE;
        int secondMinCount = Integer.MAX_VALUE;

        // Determine max and min counts
        for (int count : wordCount.values()) {
            if (count > maxCount) {
                secondMaxCount = maxCount;
                maxCount = count;
            } else if (count > secondMaxCount && count != maxCount) {
                secondMaxCount = count;
            }

            if (count < minCount) {
                secondMinCount = minCount;
                minCount = count;
            } else if (count < secondMinCount && count != minCount) {
                secondMinCount = count;
            }
        }

        // Print based on max and min counts
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();

            if (count == maxCount) {
                System.out.println("Max\n" + word + "\nUsed\nWord\n");
            } else if (count == secondMaxCount && secondMaxCount != Integer.MIN_VALUE) {
                System.out.println("2 nd Max\nused\nWord\n");
            } else if (count == minCount) {
                System.out.println("Min\n" + word + "\nUsed\nWord\n");
            } else if (count == secondMinCount && secondMinCount != Integer.MAX_VALUE) {
                System.out.println("2 nd Min\nused\nWord\n");
            }
        }
    }

}
