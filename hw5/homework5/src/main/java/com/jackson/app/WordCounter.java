package com.jackson.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCounter {
    // The following are the ONLY variables we will modify for grading.
    // The rest of your code must run with no changes.

    // path to the folder where input text files are located
    public static final Path FOLDER_OF_TEXT_FILES = Paths.get(
        "/home/jackson/Unmanaged/git_repos/cse216/hw5/homework5/src/main/resources/words");
    // path to the output plain-text (.txt) file
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get(
        "/home/jackson/Unmanaged/git_repos/cse216/hw5/homework5/src/main/resources/count/count.txt");
    // max. number of threads to spawn
    public static final int NUMBER_OF_THREADS = 2;

    public static ExecutorService executorService;
    // <filename, <word, amount>
    public static Map<String, ConcurrentHashMap<String, Integer>> data = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        initializeExecutor();
        submitTasks();
        shutdownAndAwaitTermination(executorService);
        createCountFile();
    }

    public static void initializeExecutor() {
        if (NUMBER_OF_THREADS <= 1)
            executorService = Executors.newFixedThreadPool(1);
        else
            executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    }

    public static void createCountFile() throws IOException {
        try (Writer fileWriter = new FileWriter(WORD_COUNT_TABLE_FILE.toFile(), false)) {
            fileWriter.write(createCountString());
        }
    }

    public static String createCountString() {
        List<Map.Entry<String, ConcurrentHashMap<String, Integer>>> sortedEntries =
            new ArrayList<>(data.entrySet());
        sortedEntries.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
        Map<String, Integer> totals = getFinalCountMap();

        int longestWordLength = totals.keySet()
                                    .stream()
                                    .map(s -> s.length())
                                    .max((s1, s2) -> s1.compareTo(s2))
                                    .orElse(0);
        int longestFileNameLength = sortedEntries.stream()
                                        .map(s -> s.getKey().length())
                                        .max((s1, s2) -> s1.compareTo(s2))
                                        .orElse(0);
        int longest =
            longestWordLength >= longestFileNameLength ? longestWordLength : longestFileNameLength;

        StringBuilder output = new StringBuilder();
        
        ListIterator<Map.Entry<String, ConcurrentHashMap<String, Integer>>> iter = sortedEntries.listIterator();
        while(iter.hasNext()){
            Map.Entry<String, ConcurrentHashMap<String, Integer>> next;
            if (iter.hasPrevious()) {
                 Map.Entry<String, ConcurrentHashMap<String, Integer>> prev = iter.previous();
                 iter.next();
                 next = iter.next();
                 output.append(getCorrectSpaces(longestFileNameLength, prev.getKey().length()) + "\t" + next.getKey());
            } else {
                 next = iter.next();
                output.append(" " + getCorrectSpaces(longestWordLength, 0) + next.getKey());
            }
            if (!iter.hasNext()) {
                output.append(getCorrectSpaces(longestFileNameLength, next.getKey().length()) + " ");
            }
        }
        output.append("total\n");
        for (Map.Entry<String, Integer> e : totals.entrySet()) {
            String word = e.getKey();
            Integer amountTotal = e.getValue();
            output.append(word);
            output.append(getCorrectSpaces(longestWordLength, word.length()) + " ");

            for (Map.Entry<String, ConcurrentHashMap<String, Integer>> fileData : sortedEntries) {
                Map<String, Integer> words = fileData.getValue();
                if (words.get(word) != null) {
                    output.append(words.get(word));
                    output.append(
                        getCorrectSpaces(longest, (int)(Math.floor(Math.log10(words.get(word)))) + 1) +
                        "\t");
                } else {
                    output.append(0);
                    output.append(getCorrectSpaces(longest, 1) + "\t");
                }
            }
            output.append(amountTotal);
            output.append("\n");
        }
        return output.toString();
    }

    public static String getCorrectSpaces(int longest, int current) {
        StringBuilder s = new StringBuilder();
        int range = longest - current;
        if (range >= 0) {
            IntStream stream = IntStream.range(0, range);
            stream.forEach(i -> s.append("."));
            return s.toString();
        }
        return "";
    }

    public static List<File> getListOfFiles() throws IOException {
        List<File> files;
        try (Stream<Path> path = Files.walk(FOLDER_OF_TEXT_FILES)) {
            files = path.filter(f -> Files.isRegularFile(f))
                        .map(f -> f.toFile())
                        .collect(Collectors.toList());
        }
        return files;
    }

    public static List<String> readLinesFromFile(File f) {
        List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String[] separated = scanner.nextLine().trim().split(" ");
                for (String s : separated)
                    words.add(s.toLowerCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        };
        return words;
    }

    public static void addToWordMap(File f, List<String> words) {
        data.put(f.getName(), new ConcurrentHashMap<String, Integer>());
        for (String word : words) {
            Integer c = data.get(f.getName()).get(word);
            if (c != null)
                data.get(f.getName()).put(word, c + 1);
            else
                data.get(f.getName()).put(word, 1);
        }
    }

    public static Map<String, Integer> getFinalCountMap() {
        Map<String, Integer> finalCount = new TreeMap<>();
        for (ConcurrentHashMap<String, Integer> map : data.values()) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String word = entry.getKey();
                Integer amount = entry.getValue();
                if (finalCount.get(word) != null)
                    finalCount.put(word, finalCount.get(word) + amount);
                else
                    finalCount.put(word, amount);
            }
        }
        return finalCount;
    }

    public static void submitTasks() throws IOException {
        getListOfFiles().forEach(
            f -> executorService.submit(() -> { addToWordMap(f, readLinesFromFile(f)); }));
    }

    // shutdown executor as per Oracle docs (wait for all threads to finish their current task
    // before continuing main thread)
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown();
        try {
            if (!pool.awaitTermination(60, TimeUnit.SECONDS)) {
                pool.shutdownNow();
                if (!pool.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
