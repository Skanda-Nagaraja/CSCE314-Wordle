package com.example.wordle;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class gameBoard {
    private LogFile logger;
    @FXML private Label keyA;
    @FXML private Label keyB;
    @FXML private Label keyC;
    @FXML private Label keyD;
    @FXML private Label keyE;
    @FXML private Label keyF;
    @FXML private Label keyG;
    @FXML private Label keyH;
    @FXML private Label keyI;
    @FXML private Label keyJ;
    @FXML private Label keyK;
    @FXML private Label keyL;
    @FXML private Label keyM;
    @FXML private Label keyN;
    @FXML private Label keyO;
    @FXML private Label keyP;
    @FXML private Label keyQ;
    @FXML private Label keyR;
    @FXML private Label keyS;
    @FXML private Label keyT;
    @FXML private Label keyU;
    @FXML private Label keyV;
    @FXML private Label keyW;
    @FXML private Label keyX;
    @FXML private Label keyY;
    @FXML private Label keyZ;
    @FXML
    private TextField guessInput;
    private boolean isRestartingFromStats = false;
    @FXML
    private Label pos00;
    @FXML
    private Label pos10;
    @FXML
    private Label pos20;
    @FXML
    private Label pos30;
    @FXML
    private Label pos40;
    @FXML
    private Label pos01;
    @FXML
    private Label pos11;
    @FXML
    private Label pos21;
    @FXML
    private Label pos31;
    @FXML
    private Label pos41;
    @FXML
    private Label pos02;
    @FXML
    private Label pos12;
    @FXML
    private Label pos22;
    @FXML
    private Label pos32;
    @FXML
    private Label pos42;
    @FXML
    private Label pos03;
    @FXML
    private Label pos13;
    @FXML
    private Label pos23;
    @FXML
    private Label pos33;
    @FXML
    private Label pos43;
    @FXML
    private Label pos04;
    @FXML
    private Label pos14;
    @FXML
    private Label pos24;
    @FXML
    private Label pos34;
    @FXML
    private Label pos44;
    @FXML
    private Label pos05;
    @FXML
    private Label pos15;
    @FXML
    private Label pos25;
    @FXML
    private Label pos35;
    @FXML
    private Label pos45;
    @FXML private Button resetButton;

    @FXML

    //private Label displayRand;
    private ShadowData shadowData;
    public int currentAttempt = 0;
    private SelectWord selectWord;
    public String randomWord;
    public int winCount = 0;
    //public int currStreak = 0;
    public int gamesPlayed = 0;
    public int currStreak = 0;
    public String message;
    public int tempStreak = 0;

    private Label[][] grid;
    public boolean lastGameWon;
    private int currentRow = 0;
    private int currentColumn = 0;
    private int attemptNum = 0;
    @FXML
    private Button resetGameButton;

    @FXML
    public VBox mainVBox;

    private Map<Character, Label> keyboardMap = new HashMap<>();


    @FXML
    private void onLoadGameClicked() {
        LoadGameSession session = logger.loadLastGame();
        if (session != null) {
            restoreGame(session);
        } else {
            showOverlayAlert("Restore Game", "No unfinished game to restore.");
        }
    }

    private void restoreGame(LoadGameSession session) {
        this.randomWord = session.getWord();
        //displayRand.setText(randomWord);
        System.out.println(randomWord);
        List<String> guesses = session.getGuesses();

        for (int row = 0; row < guesses.size() && row < grid.length; row++) {
            String guess = guesses.get(row);
            for (int col = 0; col < guess.length() && col < grid[row].length; col++) {
                grid[row][col].setText(String.valueOf(guess.charAt(col)));
            }
        }

        currentRow = guesses.size() - 1;
        currentColumn = 0;
        currentAttempt = guesses.size();
        if (currentAttempt < 6) {
            resetButton.setText("Continue");
        }
    }

    @FXML
    public void initialize() {
        setupKeyboardMap();




        grid = new Label[][]{
                {pos00, pos10, pos20, pos30, pos40},
                {pos01, pos11, pos21, pos31, pos41},
                {pos02, pos12, pos22, pos32, pos42},
                {pos03, pos13, pos23, pos33, pos43},
                {pos04, pos14, pos24, pos34, pos44},
                {pos05, pos15, pos25, pos35, pos45},
        };

        selectWord = new SelectWord("src/main/resources/wordle_words.txt");
        randomWord = selectWord.getRandomWord();
        shadowData = new ShadowData(randomWord);
        //displayRand.setText(randomWord);
        System.out.println(randomWord);
        logger = new LogFile("guesses_log.txt");
        logger.logSessionStart(randomWord);

        mainVBox.setFocusTraversable(true);
        mainVBox.requestFocus();
        mainVBox.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            System.out.println("Key Pressed: " + code);

            if (code.isLetterKey() && currentColumn < 5) {
                String letter = event.getText().toUpperCase();
                enterLetter(letter);
            } else if (code == KeyCode.BACK_SPACE && currentColumn > 0) {
                deleteLetter();
            } else if (code == KeyCode.ENTER && currentColumn == 5) {
                submitGuess();
            }
            event.consume();
        });
    }



    private void setupKeyboardMap() {
        keyboardMap.put('Q', keyQ);
        keyboardMap.put('W', keyW);
        keyboardMap.put('E', keyE);
        keyboardMap.put('R', keyR);
        keyboardMap.put('T', keyT);
        keyboardMap.put('Y', keyY);
        keyboardMap.put('U', keyU);
        keyboardMap.put('I', keyI);
        keyboardMap.put('O', keyO);
        keyboardMap.put('P', keyP);
        keyboardMap.put('A', keyA);
        keyboardMap.put('S', keyS);
        keyboardMap.put('D', keyD);
        keyboardMap.put('F', keyF);
        keyboardMap.put('G', keyG);
        keyboardMap.put('H', keyH);
        keyboardMap.put('J', keyJ);
        keyboardMap.put('K', keyK);
        keyboardMap.put('L', keyL);
        keyboardMap.put('Z', keyZ);
        keyboardMap.put('X', keyX);
        keyboardMap.put('C', keyC);
        keyboardMap.put('V', keyV);
        keyboardMap.put('B', keyB);
        keyboardMap.put('N', keyN);
        keyboardMap.put('M', keyM);
    }
    public void updateKeyboardColors(String guess) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : randomWord.toUpperCase().toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }


        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.toUpperCase().charAt(i);
            Label keyLabel = keyboardMap.get(letter);


            if (randomWord.toUpperCase().charAt(i) == letter) {
                keyLabel.setStyle("-fx-background-color: #6DD65F; -fx-text-fill: white; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px;");
                int count = frequencyMap.getOrDefault(letter, 0);
                frequencyMap.put(letter, count - 1);
            }
        }


        for (int i = 0; i < guess.length(); i++) {
            char letter = guess.toUpperCase().charAt(i);
            Label keyLabel = keyboardMap.get(letter);


            if (!keyLabel.getStyle().contains("#6DD65F")) {

                if (frequencyMap.getOrDefault(letter, 0) > 0 && randomWord.toUpperCase().contains(String.valueOf(letter))) {
                    keyLabel.setStyle("-fx-background-color: #d6cc5f; -fx-text-fill: white; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px;");
                    int count = frequencyMap.getOrDefault(letter, 0);
                    frequencyMap.put(letter, count - 1);
                } else {
                    keyLabel.setStyle("-fx-background-color: #686c75; -fx-text-fill: white; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 2px;");
                }
            }
        }
    }




    @FXML
    public void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code.isLetterKey() && currentColumn < 5) {
            String letter = event.getText().toUpperCase();
            enterLetter(letter);
        } else if (code == KeyCode.BACK_SPACE && currentColumn > 0) {
            deleteLetter();
        } else if (code == KeyCode.ENTER && currentColumn == 5) {
            submitGuess();
        }
        event.consume();
    }


    private void enterLetter(String letter) {
        if (currentColumn < 5) {
            Label currentLabel = grid[currentRow][currentColumn];
            currentLabel.setText(letter);
            currentColumn++;
        }
    }

    private void deleteLetter() {
        if (currentColumn > 0) {
            currentColumn--;
            Label currentLabel = grid[currentRow][currentColumn];
            currentLabel.setText(""); // Clear the label
        }
    }

    private void submitGuess() {
        String currentGuess = getCurrentGuess().toUpperCase();
        logger.logGuess(currentGuess);
        if (currentGuess.length() == 5) {
            // Check if the word is valid
            if (!listWord.isValidWord(currentGuess)) {
                // Word is not valid
                showOverlayAlert("Invalid Word", "The word you guessed is not in the word list.");
            } else {
                // Proceed

                checkGuess(currentGuess);
                if (currentAttempt < 5) {
                    currentRow++;
                    currentColumn = 0;
                    currentAttempt++;
                }
            }
        }
    }
    private void showOverlayAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainVBox.getScene().getWindow());//Overlay perhaps?
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);


        alert.initModality(Modality.APPLICATION_MODAL);
        alert.showAndWait();
    }




    private String getCurrentGuess() {
        StringBuilder guess = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            guess.append(grid[currentRow][i].getText());
        }
        return guess.toString();
    }



    private void displayRandomWord() {
        //randomWord = selectWord.getRandomWord();
        //displayRand.setText(randomWord);
        System.out.println(randomWord);
    }


    @FXML
    private void handleSubmitGuessButtonClicked() {
        if (currentColumn == 5) { // Only submit if all columns are filled
            submitGuess();
        }
    }



    public boolean isValidWord(String word) {
        return listWord.isValidWord(word);
    }


    public void checkGuess(String guess) {
        guess = guess.toUpperCase();

        if (guess.length() == 5) {
            shadowData.recordGuess(guess, currentRow);
            updateLabels(guess);
            updateKeyboardColors(guess);

            if (shadowData.correctWord(guess)) {
                gamesPlayed++;
                winCount++;
                tempStreak ++;
                if (tempStreak > currStreak){
                    currStreak = tempStreak;
                }

                message = "You Won, Congratulations!";
                logger.logSessionEnd();
                showResults();
            } else if (currentAttempt == 5) { // Check if it's the last attempt
                gamesPlayed++;
                tempStreak = 0;
                message = "You lose!";
                lastGameWon = false;
                logger.logSessionEnd();
                showResults();
            }
        }
    }

    private void showResults() {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("statBoard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            StatBoard controller = fxmlLoader.getController();
            controller.setStats(winCount, gamesPlayed, currStreak);
            controller.setMessage(message);

            Stage popupStage = new Stage();
            popupStage.initOwner(mainVBox.getScene().getWindow());
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setScene(scene);
            popupStage.showAndWait();
            //resetGame(false);
            resetButton.setText("Play Again");
            System.out.println("c row: " + currentRow);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void resetKeyboardColors() {
        for (Label keyLabel : keyboardMap.values()) {
            keyLabel.setStyle("-fx-padding: 10px;-fx-border-color: black; -fx-border-width: 2px; -fx-text-fill: black; -fx-font-size: 16px; -fx-cursor: hand");
        }
    }
    private void updateLabels(String guess) {
        String upperGuess = guess.toUpperCase();
        String upperRandomWord = randomWord.toUpperCase();


        Map<Character, Integer> randomWordFrequency = new HashMap<>();
        Map<Character, Integer> guessFrequency = new HashMap<>();

        // First pass
        for (int i = 0; i < 5; i++) {
            char guessChar = upperGuess.charAt(i);
            char wordChar = upperRandomWord.charAt(i);

            randomWordFrequency.put(wordChar, randomWordFrequency.getOrDefault(wordChar, 0) + 1);


            if (guessChar != wordChar) {
                guessFrequency.put(guessChar, guessFrequency.getOrDefault(guessChar, 0) + 1);
            }
        }


        for (int i = 0; i < 5; i++) {
            Label label = grid[currentRow][i];
            char guessChar = upperGuess.charAt(i);
            char wordChar = upperRandomWord.charAt(i);

            label.setText(String.valueOf(guessChar));

            String style = "-fx-border-color: black; -fx-border-width: 2px; -fx-font-size: 16px; ";

            if (guessChar == wordChar) {
                label.setStyle(style + "-fx-background-color: #6DD65F;");
                int freq = randomWordFrequency.get(guessChar) - 1;
                randomWordFrequency.put(guessChar, freq); // Decrease frequency if  found
            } else if (randomWordFrequency.getOrDefault(guessChar, 0) > 0 && guessFrequency.getOrDefault(guessChar, 0) > 0) {
                label.setStyle(style + "-fx-background-color: #d6cc5f;");
                int freq = randomWordFrequency.get(guessChar) - 1;
                randomWordFrequency.put(guessChar, freq);
                guessFrequency.put(guessChar, guessFrequency.get(guessChar) - 1);
            } else {
                label.setStyle(style + "-fx-background-color: #686c75;");
            }
        }
    }


    @FXML
    private void onResetButtonClicked() {
        resetGame(false);
    }
    @FXML
    private void onAgainClick() {
        resetGame(false);

            resetButton.setText("Reset");

    }




    public void resetVar(){
        currentRow = 0;
        currentAttempt = 0;
        currentColumn = 0;

    }
    @FXML
    void resetGame(boolean gameCompleted) {
        // Clear all labels in the grid
        for (Label[] row : grid) {
            for (Label label : row) {
                label.setText("");
                label.setStyle("-fx-background-color: none; -fx-border-color: black; -fx-border-width: 2px; -fx-font-size: 16px;");
            }
        }


        resetKeyboardColors();


        if (guessInput != null) {
            guessInput.clear();
        }

        // Fetch a new random word
        randomWord = selectWord.getRandomWord();
        shadowData = new ShadowData(randomWord);
        //displayRand.setText(randomWord);
        System.out.println(randomWord);
        logger.logSessionStart(randomWord);


        currentRow = 0;
        //System.out.Println(currentRow)
        currentColumn = 0;
        currentAttempt = 0;
        resetButton.setText("Reset");
    }

    private Label[] getCurrentRowLabels() {
        switch (currentAttempt) {
            case 0: return new Label[]{pos00, pos10, pos20, pos30, pos40};
            case 1: return new Label[]{pos01, pos11, pos21, pos31, pos41};
            case 2: return new Label[]{pos02, pos12, pos22, pos32, pos42};
            case 3: return new Label[]{pos03, pos13, pos23, pos33, pos43};
            case 4: return new Label[]{pos04, pos14, pos24, pos34, pos44};
            case 5: return new Label[]{pos05, pos15, pos25, pos35, pos45};
            default: return new Label[]{};
        }
    }

    private Label[] getAllGuessLabels() {
        return new Label[]{pos00, pos10, pos20, pos30, pos40,
                pos01, pos11, pos21, pos31, pos41,
                pos02, pos12, pos22, pos32, pos42,
                pos03, pos13, pos23, pos33, pos43,
                pos04, pos14, pos24, pos34, pos44,
                pos05, pos15, pos25, pos35, pos45};
    }
}