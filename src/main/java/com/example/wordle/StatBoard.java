package com.example.wordle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StatBoard {
    public Button playAgain;
    @FXML private Label gamesPlayed;
    @FXML private Button resetButton;
    @FXML private Label winPercentage;
    @FXML private Label winStreak;

    @FXML public Label winLose;

    private gameBoard gameBoard;

    @FXML private void playAgain() {
        // Close the stats window
        Stage stage = (Stage) gamesPlayed.getScene().getWindow();
        stage.close();
        if (gameBoard != null) {
            resetButton.setText("Reset");;
        }
    }

    public void setStats(int winCount, int totalGamesPlayed, int currentWinStreak) {
        gamesPlayed.setText("" + totalGamesPlayed);
        double winRate = (double) winCount / totalGamesPlayed * 100;
        winPercentage.setText(String.format("%.2f%%", winRate));
        winStreak.setText("" + currentWinStreak);
    }

    public void setMessage(String message) {
        winLose.setText(message);
    }

    public void setGameBoard(gameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}