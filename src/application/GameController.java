package application;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

public class GameController implements Serializable{  
	
	private static final long serialVersionUID = 1L;
	
	// Audio declarations, sounds are free to use for non-commercial usage.
	
	final URL resource = getClass().getResource("sounds/error.wav");
	final AudioClip error = new AudioClip(resource.toString());
	final URL resource_2 = getClass().getResource("sounds/correct.mp3");
	final AudioClip tick = new AudioClip(resource_2.toString());
	final URL resource_3 = getClass().getResource("sounds/blip1.wav");
	final AudioClip blip = new AudioClip(resource_3.toString());
	final URL resource_4 = getClass().getResource("sounds/complete.wav");
	final AudioClip complete = new AudioClip(resource_4.toString());
	final URL resource_5 = getClass().getResource("sounds/lost.wav");
	final AudioClip gameOver = new AudioClip(resource_5.toString());
	final URL resource_6 = getClass().getResource("sounds/denied.wav");
	final AudioClip denied = new AudioClip(resource_6.toString());
	
	// Class object for core game logic and variables.
	
	private GameLogic game = new GameLogic();

	// Components for other areas of the view.
	
    @FXML public ChoiceBox<String> difficultyBox;
    @FXML public ObservableList<String> difficultyBoxList = FXCollections.observableArrayList("Easiest", "Easy", "Medium", "Hard");
	@FXML public String selectedDifficulty;	
	@FXML public TextField myName;
	@FXML public Label selectedName; 
	@FXML public Label selectedWord;
	@FXML public Label maskedWord;
	@FXML public Label gameMsg;
	@FXML public Label difficulty;
	
	// HangMan images for each incorrect guess, 10 lives.
	
	@FXML ImageView hangManImage;
	Image hangMan1 = new Image("application/images/hangman_1.png", true);
	Image hangMan2 = new Image("application/images/hangman_2.png", true);
	Image hangMan3 = new Image("application/images/hangman_3.png", true);
	Image hangMan4 = new Image("application/images/hangman_4.png", true);
	Image hangMan5 = new Image("application/images/hangman_5.png", true);
	Image hangMan6 = new Image("application/images/hangman_6.png", true);
	Image hangMan7 = new Image("application/images/hangman_7.png", true);
	Image hangMan8 = new Image("application/images/hangman_8.png", true);
	Image hangMan9 = new Image("application/images/hangman_9.png", true);
	Image hangMan10 = new Image("application/images/hangman_10.png", true);
	
	// Labels required for game statistics.
	
	@FXML public Label correctGuesses;
	@FXML public Label incorrectGuesses;
	@FXML public Label guessRatio;
	
	@FXML public Label numOfWins;
	@FXML public Label numOfLosses;
	@FXML public Label winRatio;
	
	@FXML public Label pointsTotal;
	
	// Show word toggle.
	
	@FXML private boolean toggle = true;
	
	@FXML public Button newWord;
	
	// Labels required for the alphabetic keypad.
	
	@FXML public Label label1;
	@FXML public Label label2;
	@FXML public Label label3;
	@FXML public Label label4;
	@FXML public Label label5;
	@FXML public Label label6;
	@FXML public Label label7;
	@FXML public Label label8;
	@FXML public Label label9;
	@FXML public Label label10;
	@FXML public Label label11;
	@FXML public Label label12;
	@FXML public Label label13;
	@FXML public Label label14;
	@FXML public Label label15;
	@FXML public Label label16;
	@FXML public Label label17;
	@FXML public Label label18;
	@FXML public Label label19;
	@FXML public Label label20;
	@FXML public Label label21;
	@FXML public Label label22;
	@FXML public Label label23;
	@FXML public Label label24;
	@FXML public Label label25;
	@FXML public Label label26;	
  
	/**
	 * initialize()
	 * @param runs first, sets up in-game options.
	 * @return n/a
	 *//**
	 */
	
	@FXML
	public void initialize(){
	
		// Set all labels to 'hide'.
		 
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label6.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);
		label9.setVisible(false);
		label10.setVisible(false);
		label11.setVisible(false);
		label12.setVisible(false);
		label13.setVisible(false);
		label14.setVisible(false);
		label15.setVisible(false);
		label16.setVisible(false);
		label17.setVisible(false);
		label18.setVisible(false);
		label19.setVisible(false);
		label20.setVisible(false);
		label21.setVisible(false);
		label22.setVisible(false);
		label23.setVisible(false);
		label24.setVisible(false);
		label25.setVisible(false);
		label26.setVisible(false);
		
		// Set up the keyboard Accelerators.
		
		// Set up the in-game options.
		
		selectedName.setText(MiniProject.getName());
		difficultyBox.setItems(difficultyBoxList);
        game.setDifficulty(difficultyBox.getSelectionModel().getSelectedItem().toString());
		difficulty.setText(difficultyBox.getSelectionModel().getSelectedItem().toString());		
		selectedWord.setText(game.getSelectedWord());	
		selectedWord.setVisible(false);
		gameMsg.setText("Click new word");
		game.gameInProgress(false);
		game.setPoints();
		
	}
	
	/**
	 * newWord()
	 * @param ActionEvent, when the button is clicked,
	 * if the gameState is "not running" already (ie. false)
	 * then you are allowed to request a new word.
	 * 
	 * @return n/a
	 *//**
	 */
	
    @FXML  
    public void newWord(ActionEvent ae){
    	
    	if(game.getState() == false){
    		
    		tick.play();
    		
    		// Set all labels to 'show'.
    		
    		label1.setVisible(true);
    		label2.setVisible(true);
    		label3.setVisible(true);
    		label4.setVisible(true);
    		label5.setVisible(true);
    		label6.setVisible(true);
    		label6.setVisible(true);
    		label7.setVisible(true);
    		label8.setVisible(true);
    		label9.setVisible(true);
    		label10.setVisible(true);
    		label11.setVisible(true);
    		label12.setVisible(true);
    		label13.setVisible(true);
    		label14.setVisible(true);
    		label15.setVisible(true);
    		label16.setVisible(true);
    		label17.setVisible(true);
    		label18.setVisible(true);
    		label19.setVisible(true);
    		label20.setVisible(true);
    		label21.setVisible(true);
    		label22.setVisible(true);
    		label23.setVisible(true);
    		label24.setVisible(true);
    		label25.setVisible(true);
    		label26.setVisible(true);
    		
    		// Updating the game settings.
    		
    		game.setDifficulty(difficultyBox.getSelectionModel().getSelectedItem().toString());
    		difficulty.setText(difficultyBox.getSelectionModel().getSelectedItem().toString());
    		game.setRandomWord();
    		selectedWord.setText(game.getSelectedWord()); 
    		selectedWord.setVisible(false);
    		game.gameInProgress(true);
    		game.setCompletion(false);
    		maskedWord.setVisible(true);
    		maskedWord.setText(game.getMask());    		
    		gameMsg.setText("Fire away!");
    		hangManImage.setImage(null);
 	
    	} else {
    		
    		// Cannot request newWord if game is already in progress, gameState == true.
    		
    		denied.play();
    		gameMsg.setText("Please finish game first before continuing, stop trying to cheat!");
    		
    	}

    	
    }
    
	/**
	 * makeGuess()
	 * @param MouseEvent, when a letter
	 * is clicked, it is checked to see
	 * whether it is contained inside
	 * selectedWord in gameLogic. 
	 * @return n/a
	 *//**
	 */
    
    @FXML
    public void makeGuess(MouseEvent e) {
    	
   		Label selectedLabel = new Label();
		selectedLabel = (Label) e.getSource();
		
		// Check the letter!
		
		if(game.checkLetter(selectedLabel.getText().toString())) {			
			
			blip.play();
	   		game.correctGuess();
	   		gameMsg.setText("Well done little menace!, you guessed a correct letter!");
	   		correctGuesses.setText("" + game.getcorrectGuess());
	   		guessRatio.setText("" + game.getGuessRatio() + "%");
	   		
	   		game.updateMask(selectedLabel.getText().toString());
    		maskedWord.setText(game.getMask());
    		
	   		((Node) e.getSource()).setVisible(false);
	   		
	   		// Is the game complete after you've guessed correctly, if so..?
	   		
			if(game.checkCompletion()){
				
				// Game Win!, hide all remaining letters and gameInProgress to false
				
				complete.play();
		   		maskedWord.setText(game.getSelectedWord());
				gameMsg.setText("You win!, You're getting good at this now!");
				
				label1.setVisible(false);
				label2.setVisible(false);
				label3.setVisible(false);
				label4.setVisible(false);
				label5.setVisible(false);
				label6.setVisible(false);
				label6.setVisible(false);
				label7.setVisible(false);
				label8.setVisible(false);
				label9.setVisible(false);
				label10.setVisible(false);
				label11.setVisible(false);
				label12.setVisible(false);
				label13.setVisible(false);
				label14.setVisible(false);
				label15.setVisible(false);
				label16.setVisible(false);
				label17.setVisible(false);
				label18.setVisible(false);
				label19.setVisible(false);
				label20.setVisible(false);
				label21.setVisible(false);
				label22.setVisible(false);
				label23.setVisible(false);
				label24.setVisible(false);
				label25.setVisible(false);
				label26.setVisible(false);
				
				game.gameWin();
				game.resetMask();
				game.resetLives();
				
				numOfWins.setText("" + game.getNumOfWins());
				winRatio.setText("" + game.getWinRatio() + "%");
		   		pointsTotal.setText("" + game.getPoints());
				
				game.gameInProgress(false);
				
			}	
			
		// The Letter is not contained inside so ..
	   		
		} else {
			
			// The number of hangMan lives used goes up by 1.
			
			game.hangMan();
			
			// If you've guessed incorrectly but still have remaining lives...
			
			if(game.hangManState() < 10){

				error.play();
				game.incorrectGuess();
		   		gameMsg.setText("Keep going!, I'm sure you'll guess correctly next time!");
		   		incorrectGuesses.setText("" + game.getIncorrectGuess());
		   		((Node) e.getSource()).setVisible(false);   		
		   		
				if (game.hangManState() == 1) {
					hangManImage.setImage(hangMan1);
				} else if (game.hangManState() == 2) {
					hangManImage.setImage(hangMan2);
				} else if (game.hangManState() == 3) {
					hangManImage.setImage(hangMan3);
				} else if (game.hangManState() == 4) {
					hangManImage.setImage(hangMan4);
				} else if (game.hangManState() == 5) {
					hangManImage.setImage(hangMan5);
				} else if (game.hangManState() == 6) {
					hangManImage.setImage(hangMan6);
				} else if (game.hangManState() == 7) {
					hangManImage.setImage(hangMan7);
				} else if (game.hangManState() == 8) {
					hangManImage.setImage(hangMan8);
				} else if (game.hangManState() == 9) {
					hangManImage.setImage(hangMan9);
				}
				
		   		guessRatio.setText("" + game.getGuessRatio() + "%");
		   		
			} else {
				
				// You've guessed incorrectly too many times. Game Lost!.
				
				hangManImage.setImage(hangMan10);
				
				gameOver.play();
		   		gameMsg.setText("You've lost the game!, better luck next time!");
		   		maskedWord.setText(game.getSelectedWord());
		   		
				label1.setVisible(false);
				label2.setVisible(false);
				label3.setVisible(false);
				label4.setVisible(false);
				label5.setVisible(false);
				label6.setVisible(false);
				label6.setVisible(false);
				label7.setVisible(false);
				label8.setVisible(false);
				label9.setVisible(false);
				label10.setVisible(false);
				label11.setVisible(false);
				label12.setVisible(false);
				label13.setVisible(false);
				label14.setVisible(false);
				label15.setVisible(false);
				label16.setVisible(false);
				label17.setVisible(false);
				label18.setVisible(false);
				label19.setVisible(false);
				label20.setVisible(false);
				label21.setVisible(false);
				label22.setVisible(false);
				label23.setVisible(false);
				label24.setVisible(false);
				label25.setVisible(false);
				label26.setVisible(false);
				
				game.gameLoss();
				game.resetMask();
				game.resetLives();

				numOfLosses.setText("" + game.getNumOfLosses());
				winRatio.setText("" + game.getWinRatio() + "%");
				
				game.gameInProgress(false);
	   		}	   		
		}	
		
    }
    
	/**
	 * saveGame()
	 * @param MouseEvent, this will
	 * save the current state of 
	 * the class object 'game'.
	 * in  a file called game_save.data
	 * @return n/a
	 *//**
	 */
    
    @FXML
    public void saveGame(MouseEvent m) {
    	
    	if (game.getState() == true) {
    		denied.play();
    		gameMsg.setText("You cannot save the game state until you've finished your current game.");
    	} else {
   
	    	try {
	    		
	    	FileOutputStream f_out = new 
	    	FileOutputStream("game_save.data");
	    	@SuppressWarnings("resource")
			ObjectOutputStream obj_out = new
	    		ObjectOutputStream (f_out);
	    	obj_out.writeObject (game);
	    	gameMsg.setText("Game successfully saved!");
	    	
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}   
    	}
    }
    
    @FXML
    public void saveGameKeyBoard() {
    	
      	if (game.getState() == true) {
    		denied.play();
    		gameMsg.setText("You cannot save the game state until you've finished your current game.");
    	} else {
	    	try {
	    		
	    	FileOutputStream f_out = new 
	    	FileOutputStream("game_save.data");
	    	@SuppressWarnings("resource")
			ObjectOutputStream obj_out = new
	    		ObjectOutputStream (f_out);
	    	obj_out.writeObject (game);
	    	gameMsg.setText("Game successfully saved!");
	    	
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	} 
    	}  
    }
    
    
	/**
	 * loadGame()
	 * @param MouseEvent, this will
	 * load the class object variables from
	 * game_save.data and update them
	 * into GameControllers version of
	 * the same class.
	 * @return n/a
	 *//**
	 */
    
    @FXML
    public void loadGame(MouseEvent m) {   	
		
    	if (game.getState() == true) {
    		denied.play();
    		gameMsg.setText("You cannot load the game while you are currently playing a game.");
    	} else {
    	try {
    	    FileInputStream myFileInputStream = new FileInputStream("game_save.data");
    	    ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
    	    game = (GameLogic) myObjectInputStream.readObject(); 
    	    myObjectInputStream.close();
        	gameMsg.setText("Game successfully loaded!, select a new word");
        	
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    	
    	//set all the loaded variables
    	
		game.resetMask();
		game.resetLives();
        game.setDifficulty(difficultyBox.getSelectionModel().getSelectedItem().toString());
		difficulty.setText(difficultyBox.getSelectionModel().getSelectedItem().toString());	
   		correctGuesses.setText("" + game.getcorrectGuess());
   		incorrectGuesses.setText("" + game.getIncorrectGuess());
   		guessRatio.setText("" + game.getGuessRatio() + "%");
		numOfLosses.setText("" + game.getNumOfLosses());
		numOfWins.setText("" + game.getNumOfWins());
		winRatio.setText("" + game.getWinRatio() + "%");
   		pointsTotal.setText("" + game.getPoints());
		
		/** CREATE LABELS for
		 * alphabet then
		 * Hide the game pad
		 * ie. set all letters 
		 * invisible
		 */
		
		 
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label6.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);
		label9.setVisible(false);
		label10.setVisible(false);
		label11.setVisible(false);
		label12.setVisible(false);
		label13.setVisible(false);
		label14.setVisible(false);
		label15.setVisible(false);
		label16.setVisible(false);
		label17.setVisible(false);
		label18.setVisible(false);
		label19.setVisible(false);
		label20.setVisible(false);
		label21.setVisible(false);
		label22.setVisible(false);
		label23.setVisible(false);
		label24.setVisible(false);
		label25.setVisible(false);
		label26.setVisible(false);
		
		/** OPTIONS
		 * Set the game options
		 *  on startup, ready
		 *  to begin loaded game
		 */
		
		selectedName.setText(MiniProject.getName());
		difficultyBox.setItems(difficultyBoxList);
        game.setDifficulty(difficultyBox.getSelectionModel().getSelectedItem().toString());
		difficulty.setText(difficultyBox.getSelectionModel().getSelectedItem().toString());		
		selectedWord.setText(game.getSelectedWord());	
		maskedWord.setVisible(false);
		game.gameInProgress(false);
		
    	}
    }
    
    @FXML
    public void loadGameKeyBoard() {   	
      	if (game.getState() == true) {
    		denied.play();
    		gameMsg.setText("You cannot load the game while you are currently playing a game.");
    	} else {
    	try {
    	    FileInputStream myFileInputStream = new FileInputStream("game_save.data");
    	    ObjectInputStream myObjectInputStream = new ObjectInputStream(myFileInputStream);
    	    game = (GameLogic) myObjectInputStream.readObject(); 
    	    myObjectInputStream.close();
        	gameMsg.setText("Game successfully loaded!, select a new word");
        	
    	} catch (Exception e) {
    	    e.printStackTrace();
    	}
    	
    	
    	//set all the loaded variables
    	
		game.resetMask();
		game.resetLives();
        game.setDifficulty(difficultyBox.getSelectionModel().getSelectedItem().toString());
		difficulty.setText(difficultyBox.getSelectionModel().getSelectedItem().toString());	
   		correctGuesses.setText("" + game.getcorrectGuess());
   		incorrectGuesses.setText("" + game.getIncorrectGuess());
   		guessRatio.setText("" + game.getGuessRatio() + "%");
		numOfLosses.setText("" + game.getNumOfLosses());
		numOfWins.setText("" + game.getNumOfWins());
		winRatio.setText("" + game.getWinRatio() + "%");
   		pointsTotal.setText("" + game.getPoints());
		
		/** CREATE LABELS for
		 * alphabet then
		 * Hide the game pad
		 * ie. set all letters 
		 * invisible
		 */
		
		 
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		label6.setVisible(false);
		label6.setVisible(false);
		label7.setVisible(false);
		label8.setVisible(false);
		label9.setVisible(false);
		label10.setVisible(false);
		label11.setVisible(false);
		label12.setVisible(false);
		label13.setVisible(false);
		label14.setVisible(false);
		label15.setVisible(false);
		label16.setVisible(false);
		label17.setVisible(false);
		label18.setVisible(false);
		label19.setVisible(false);
		label20.setVisible(false);
		label21.setVisible(false);
		label22.setVisible(false);
		label23.setVisible(false);
		label24.setVisible(false);
		label25.setVisible(false);
		label26.setVisible(false);
		
		/** OPTIONS
		 * Set the game options
		 *  on startup, ready
		 *  to begin loaded game
		 */
		
		selectedName.setText(MiniProject.getName());
		difficultyBox.setItems(difficultyBoxList);
        game.setDifficulty(difficultyBox.getSelectionModel().getSelectedItem().toString());
		difficulty.setText(difficultyBox.getSelectionModel().getSelectedItem().toString());		
		selectedWord.setText(game.getSelectedWord());	
		maskedWord.setVisible(false);
		game.gameInProgress(false);
    	}
    }
    
	/**
	 * openHelp()
	 * @param Opens the
	 * help menu for the player.
	 * @return n/a
	 *//**
	 */
    
    @FXML
    public void openHelp() {

		try {
				Parent root;
			   root = FXMLLoader.load(getClass().getResource("Help.fxml"));
			   root.setId("pane");

		       Scene scene = new Scene(root, 412, 400);
		       Stage primaryStage = new Stage();
		        
		       primaryStage.setTitle("Player help");    
		       primaryStage.setResizable(false);
		       primaryStage.setScene(scene);
		       primaryStage.show();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
     
    }
    
	/**
	 * showword()
	 * @param This is the toggle
	 * for the showing of the hidden
	 * word, for test use, assessment use.
	 * @return n/a
	 *//**
	 */
    
    @FXML public void showWord() {
		selectedWord.setVisible(toggle);
		
		// toggle's value is negated.
		
		toggle = !toggle;
		
    }
    
	/**
	 * keyPressed()
	 * @param Which key 
	 * on the keyboard
	 * has been pressed.
	 * used for save/load.
	 * @return n/a
	 *//**
	 */
    
    @FXML private void keyPressed(KeyEvent event) {
  		
    			// F5 for save.
    		
    			if(event.getCode().equals(KeyCode.F5))  {			
					saveGameKeyBoard();			
        	
				// F9 for load.
					
    			} else if ( event.getCode().equals(KeyCode.F9)) {
    				loadGameKeyBoard();
    				
    			}
    	}   	
    }
 