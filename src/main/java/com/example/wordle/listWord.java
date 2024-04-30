package com.example.wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class listWord {
    private static final String FILENAME = "src/main/resources/wordle_words.txt";
    public static final List<String> wordList = createWordList();

    private static List<String> createWordList() {
        List<String> wordList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String word;
            while ((word = br.readLine()) != null) {
                wordList.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return wordList;
    }


    public static boolean isValidWord(String word) {
        return wordList.contains(word.toLowerCase());
    }

}
