package application;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;


public class GameLogic implements Serializable {

	private static final long serialVersionUID = 1L;

	// Default_name for the player if not entered.
	
	private String myName = "Illusive Phantom";

	// Array Lists for the word lists. gameList is
	// ..the whole list which has been transferred and
	// ..workingList is the current list associated with
	// ..the difficulty.
	
	@SuppressWarnings("rawtypes")
	private static List[] gameList = new List[4];
	@SuppressWarnings("rawtypes")
	private static List[] workingList = new List[1];
	
	// The randomly selected word from workingList.
	
	private String selectedWord;
	
	// The length of the randomly selected word.
	private int selectedWordLength;
	
	// Difficulty of the game.
	
	private String difficulty;
	
	//The Mask, or " _ "'s reflecting the selectedWord.
	
	private StringBuilder mask = new StringBuilder();
	
	// Variables for the statistical box.
	
	private int incorrectGuesses = 0;
	private int correctGuesses = 0;
	private float totalGuesses = 0;
	private float guessRatio = 0;	
	private int points = 0;	
	private int gamesWon = 0;
	private int gamesLost = 0;
	private float totalGames = 0;
	private float winRatio = 0;	
	private int inGameError = 0;
	
	// Boolean variable for authorisational checks.
	
	private boolean inProgress = true;
	private boolean isComplete = false;
	

	public GameLogic(){
		
		// Empty
		
	}
	
	/**
	 * setDifficulty()
	 * @param difficulty, takes a string
	 * which is then checked and workingList
	 * is assigned the corresponding list from 
	 * getAllLists() as per its the difficulty.
	 * @return n/a
	 *//**
	 */
	
	public void setDifficulty(String difficulty){
		
		int listID = 0;
		
		if(difficulty.equals("Easiest")){
			listID = 0;	
			}
		else if(difficulty.equals("Easy")){
			listID = 1;
		}
		else if(difficulty.equals("Medium")){
			listID = 2;
		}
		else if(difficulty.equals("Hard")){
			listID = 3;
		}
		
		this.difficulty = difficulty;
		workingList[0] = getAllLists()[listID];	
	}
	
	/**
	 * updateMask()
	 * @param After you've guessed correctly
	 * the mask is updated to visually reflect your 
	 * correct and show the letter at the right 
	 * indexes.
	 * @return n/a
	 *//**
	 */
	
	public void updateMask(String letter) {
		
		int indexOfLetter;
		indexOfLetter = selectedWord.toLowerCase().indexOf(letter.toLowerCase());
		
		while(indexOfLetter >= 0) {
				
				// Replace each instance of guessed letter at its index in selectedWord, inside the Mask.  
			
			   mask.replace(indexOfLetter, indexOfLetter+1, letter);
			   indexOfLetter = selectedWord.toLowerCase().indexOf(letter.toLowerCase(), indexOfLetter+1);
			   
			   // If the mask does not contain any _'s, then you've won! add points.
			   
			   if(!mask.toString().contains("_")) {
				   
				   if(difficulty.equals("Easiest")) {
					   points += 2;
				   } else if (difficulty.equals("Easy")) {
					   points += 4;
				   } else if (difficulty.equals("Medium")) {
					   points += 8;
				   } else if (difficulty.equals("Hard")) {
					   points += 12;
				   }
				   
				// Set game as complete and reset the mask.
				   
			   isComplete = true;
			   mask.setLength(0);
			   }
			}	
		}
	
	/**
	 * setRandomWord()
	 * @param Select a random word
	 * from workingList and assign it to
	 * selectedWord. Append a "_" to 
	 * the mask for each letter.
	 * @return n/a
	 *//**
	 */
	
	public void setRandomWord() {
		this.selectedWord = (String) getWorkingList()[0].get(new Random().nextInt(getWorkingList()[0].size()));
		this.selectedWordLength = selectedWord.length();
		
		if(selectedWordLength == 5) {
			difficulty = "Easiest";
		} else if (selectedWordLength == 6) {
			difficulty = "Easy";
		} else if (selectedWordLength == 7) {
			difficulty = "Medium";
		} else if (selectedWordLength == 8) {
			difficulty = "Hard";
		}
		
		// Append underscores to the mask.
		
		for(int i = 1; i <= selectedWordLength; i++){
			mask.append("_");
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public void setAllLists(List[] allList) {
		gameList = allList;
		//serialise the game!
				try{
					FileOutputStream fos = new FileOutputStream("lists.bin");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
			    	oos.writeObject(gameList);
			    	oos.close();
		    	}
				catch(IOException e){
				e.printStackTrace();
					}
	}
		
	public String getName(){
		return myName;
	}
	
	@SuppressWarnings("rawtypes")
	public List[] getAllLists() {
		return gameList;
	}

	@SuppressWarnings("rawtypes")
	public List[] getWorkingList() {
		return workingList;
	}
	
	public boolean getState(){
		return inProgress;
	}
	
	public void gameInProgress(boolean truefalse){
		inProgress = truefalse;
	}
	
	public String getSelectedWord(){
		return selectedWord;
	}

	// Is the letter inside the selectedWord?.
	
	public boolean checkLetter(String letter) {
		if(selectedWord.toLowerCase().contains(letter.toLowerCase())){
			return true;
		} else {
			return false;
		}
	}
	
	public int getWordLength(){
		return selectedWordLength;
	}
	
	public String getDifficulty(){
		return difficulty;
	}
	
	public String getMask(){
		return mask.toString();
	}
	
	public void resetMask(){
		mask.setLength(0);
	}
	
	public void incorrectGuess(){
		this.incorrectGuesses++;
		this.totalGuesses++;
	}
	
	public int getIncorrectGuess(){
		return incorrectGuesses;
	}
	
	public int getcorrectGuess(){
		return correctGuesses;
	}
	
	public void correctGuess(){
		this.correctGuesses++;
		this.totalGuesses++;
	}
	
	public void gameWin(){
		this.gamesWon++;
		this.totalGames++;
	}
	
	public void gameLoss(){
		this.gamesLost++;
		this.totalGames++;
	}
	
	public int getNumOfWins(){
		return gamesWon;
	}
	
	public int getNumOfLosses(){
		return gamesLost;
	}
	
	public void hangMan(){
		this.inGameError++;			
	}
	
	public int hangManState(){
		return this.inGameError;
	}
	
	public void resetLives(){
		this.inGameError = 0;
	}
	
	public String getWinRatio(){
		
		DecimalFormat df = new DecimalFormat("0.0");
		this.winRatio = (gamesWon/totalGames)*100;	
		return df.format(winRatio);
	}
	
	public String getGuessRatio(){
		
		DecimalFormat df = new DecimalFormat("0.0");
		this.guessRatio = (correctGuesses/totalGuesses)*100;	
		return df.format(guessRatio);
	}

	public float getTotalGuesses() {
		return totalGuesses;
	}
	
	
	public boolean checkCompletion(){
		return isComplete;
	}

	public void setCompletion(boolean b) {
		this.isComplete = b;
		
	}
	public void setPoints(){
		this.points = 0;
	}
	public int getPoints(){
		return points;
	}
}
	
	



