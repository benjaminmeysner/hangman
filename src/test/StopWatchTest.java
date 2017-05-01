package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import application.StopWatch;

public class StopWatchTest {

	// Load class objects.
	StopWatch stopWatch = new StopWatch();
	
	public StopWatchTest () {		
		
	}
		
	@Test
	public void stopWatchRecordsTime () {
		
		stopWatch.start();
		
		// The illusion of time passing.
		// ... and continues to pass ...
		// ... until it finally stops ..
		
		stopWatch.stop();
		
		assertThat(stopWatch.getElapsedtime(), is(notNullValue()));				
	}
	
}
