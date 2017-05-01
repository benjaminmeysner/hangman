package application;

public class StopWatch {

	private long start = 0;
	private long elapsedTime = 0;
	
	public void start() {
		start = System.currentTimeMillis();
		
		}
	
	public void stop() {		
		long stoptime = System.currentTimeMillis();
		elapsedTime = (stoptime-start);
		System.out.println("The execution took " + elapsedTime + " milli seconds");
		
	}

	public long getElapsedtime() {
		return elapsedTime;
	}
}