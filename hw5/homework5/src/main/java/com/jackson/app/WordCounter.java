package com.jackson.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WordCounter {
    // The following are the ONLY variables we will modify for grading.
    // The rest of your code must run with no changes.

    // path to the folder where input text files are located
    public static final Path FOLDER_OF_TEXT_FILES = Paths.get("../../../../resources/words");
    // path to the output plain-text (.txt) file
    public static final Path WORD_COUNT_TABLE_FILE =
        Paths.get("../../../../resources/count/count.txt");
    // max. number of threads to spawn
    public static final int NUMBER_OF_THREADS = 2;

    public static ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static Map<String, Integer> data = new ConcurrentHashMap<>();

    public static void main(String[] args) { 
        System.out.println("Hello World!"); 
    }
}
