package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import application.GameLogic;
import application.LoadWords;

public class GameLogicTest {

	// Load class objects.
	
	GameLogic game = new GameLogic();
	LoadWords lw = new  LoadWords();
	
	public GameLogicTest () {
		
		// Set all the word lists and transfer to GameLogic.
		
		lw.startSequential();
		game.setAllLists(lw.getAllLists());
	}
	
	@Test
	public void startOfGameConditions() {
		
		assertThat(game.getState(), is(true));
		assertThat(game.getMask(), equalTo(""));
		assertThat(game.getSelectedWord(), equalTo(null));
	}
	
	@Test
	public void selectANewWordOnEasiest () {
		
		game.setDifficulty("Easiest");
		game.setRandomWord();
		
		assertThat(game.getMask().length(), is(game.getSelectedWord().length()));
		assertThat(game.getSelectedWord().length(), is(5));
	}
	
	@Test
	public void selectANewWordOnEasy () {
		
		game.setDifficulty("Easy");
		game.setRandomWord();
		
		assertThat(game.getMask().length(), is(game.getSelectedWord().length()));
		assertThat(game.getSelectedWord().length(), is(6));
	}
	
	@Test
	public void selectANewWordOnMedium () {
		
		game.setDifficulty("Medium");
		game.setRandomWord();
		
		assertThat(game.getMask().length(), is(game.getSelectedWord().length()));
		assertThat(game.getSelectedWord().length(), is(7));
	}
	
	@Test
	public void selectANewWordOnHard () {
		
		game.setDifficulty("Hard");
		game.setRandomWord();
		
		assertThat(game.getMask().length(), is(game.getSelectedWord().length()));
		assertThat(game.getSelectedWord().length(), is(8));
	}
	
	@Test
	public void makeAnIncorrectGuess () {
		
		game.incorrectGuess();
		assertThat(game.getIncorrectGuess(), is(1));
	}
	
	@Test
	public void makeACorrectGuess () {
		
		game.correctGuess();
		assertThat(game.getcorrectGuess(), is(1));
	}
	
	@Test
	public void loosingAGame () {
		
		game.gameLoss();
		assertThat(game.getNumOfLosses(), is(1));
	}
	
	@Test
	public void winningAGame () {
		
		game.gameWin();
		assertThat(game.getNumOfWins(), is(1));
	}
	
}
