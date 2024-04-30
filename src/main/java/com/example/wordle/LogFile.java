package com.example.wordle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFile {
    private String fileName;

    public LogFile(String fileName) {
        this.fileName = fileName;
    }


    public void logSessionStart(String word) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append("Target Word: " + word + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void logGuess(String guess) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append(guess + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//
//
//    public void clearLogFile() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false))) {
//            writer.write("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public LoadGameSession loadLastGame() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> guesses = new ArrayList<>();
            String line;
            String word = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Target Word: ")) {
                    word = line.substring("Target Word: ".length());
                    guesses.clear();
                } else {
                    guesses.add(line);
                }
            }
            if (word != null) {
                return new LoadGameSession(word, guesses);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void logSessionEnd() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append("Session End\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
