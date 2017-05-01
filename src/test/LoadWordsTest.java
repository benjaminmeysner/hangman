package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import application.LoadWords;


public class LoadWordsTest {
	
	// Load class objects.
	LoadWords lw = new LoadWords();
	
	public LoadWordsTest () {
		
	lw.clearLists();	

	}
	
	@Test
	public void listsStartAsEmpty () {

				assertThat(lw.getAllLists().length, is(4));		
	}
	
	@Test
	public void canRunListsInSerial () {
		
		lw.startSequential();		
		assertThat(lw.getAllLists(), is(notNullValue()));				
	}
	
	@Test
	public void canRunListsInParallel () {
		
		lw.startParallel();		
		assertThat(lw.getAllLists(), is(notNullValue()));				
	}
	
}