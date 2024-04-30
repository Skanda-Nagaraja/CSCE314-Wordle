package com.example.wordle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class SelectWord {

    private List<String> wordList;
    private Random random = new Random();

    public SelectWord(String filePath) {
        try {
            wordList = Files.readAllLines(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public String getRandomWord() {
        if (wordList == null || wordList.isEmpty()) {

            return null;
        }
        return wordList.get(random.nextInt(wordList.size()));
    }
}

