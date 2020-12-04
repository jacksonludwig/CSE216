package com.jackson.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {
    // The following are the ONLY variables we will modify for grading.
    // The rest of your code must run with no changes.

    // path to the folder where input text files are located
    public static final Path FOLDER_OF_TEXT_FILES = Paths.get(
        "/home/jackson/Unmanaged/git_repos/cse216/hw5/homework5/src/main/resources/words");
    // path to the output plain-text (.txt) file
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get(
        "/home/jackson/Unmanaged/git_repos/cse216/hw5/homework5/src/resources/count/count.txt");
    // max. number of threads to spawn
    public static final int NUMBER_OF_THREADS = 2;

    public static ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    // <filename, <word, amount>
    public static Map<String, ConcurrentHashMap<String, Integer>> data = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        List<File> files = getListOfFiles();
        submitTasks(files);
        executorService.shutdown();
    }

    public static List<File> getListOfFiles() throws IOException {
        List<File> files;
        try (Stream<Path> walk = Files.walk(FOLDER_OF_TEXT_FILES)) {
            files = walk.filter(f -> Files.isRegularFile(f))
                        .map(f -> f.toFile())
                        .collect(Collectors.toList());
        }
        return files;
    }

    public static List<String> readLinesFromFile(File f) {
        List<String> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String[] separated = scanner.nextLine().split(" ");
                for (String s : separated)
                    words.add(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        };
        return words;
    }

    public static void submitTasks(List<File> files) {
        files.forEach(f -> executorService.submit(() -> {
            System.out.println(Thread.currentThread().toString() + " started");
            System.out.println(readLinesFromFile(f));
        }));
    }
}
