package com.example.wordle;

import java.util.ArrayList;
import java.util.List;

public class ShadowData {
    private List<String> guesses;
    //List of strings
    private String secretWord;
    private final int MAX_ATTEMPTS = 6;

    public ShadowData(String secretWord) {
        this.secretWord = secretWord.toUpperCase();
        this.guesses = new ArrayList<>(MAX_ATTEMPTS);
    }

    public boolean correctWord(String word) {
        return word.toUpperCase().equals(secretWord);
    }

    public void recordGuess(String guess, int attempt) {
        if (guess != null && guess.length() == secretWord.length() && attempt < MAX_ATTEMPTS) {
            while (guesses.size() <= attempt) {
                guesses.add(null);
            }
            guesses.set(attempt, guess.toUpperCase());
        }
    }

    public String getGuess(int attempt) {
        if (attempt < guesses.size()) {
            return guesses.get(attempt);
        }
        return null;
    }

    public boolean isCorrectPosition(char letter, int position, int attempt) {
        if (attempt < guesses.size()) {
            String guess = guesses.get(attempt);
            if (position >= 0 && position < guess.length() && position < secretWord.length()) {
                return guess.charAt(position) == secretWord.charAt(position);
            }
        }
        return false;
    }


    public boolean isInWord(char letter, int attempt) {
        if (attempt < guesses.size()) {
            return guesses.get(attempt).indexOf(letter) >= 0;
        }
        return false;
    }
}
