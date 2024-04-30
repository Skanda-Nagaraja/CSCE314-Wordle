package com.example.wordle;

import java.util.ArrayList;
import java.util.List;

public class LoadGameSession {
    private String word;
    private List<String> guesses;

    public LoadGameSession() {
        this.guesses = new ArrayList<>();
    }

    public LoadGameSession(String word, List<String> guesses) {
        this.word = word;
        this.guesses = new ArrayList<>(guesses);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<String> guesses) {
        this.guesses = new ArrayList<>(guesses);
    }

    public void addGuess(String guess) {
        this.guesses.add(guess);
    }

    public boolean isComplete(int attempts) {
        if(attempts == 6){
            return true;
        }
        return false;
    }


}
