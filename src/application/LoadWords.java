package application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**************************************************************************/
/** REMEMBER TO CHANGE THE RELATIVE FILE PATH WHEN TRANSFERRING OVER    **/
/************************************************************************/

public class LoadWords{

	// Load class object of the stop watch to show execution time.
	
	private static StopWatch timer = new StopWatch();
	
	// all word lists. Master List.
	
	@SuppressWarnings("rawtypes")
	static List[] allLists = new List[4];
	 	
	// Four sub lists.
	
	 private static List<String> list1 = new ArrayList<String>();
	 private static List<String> list2 = new ArrayList<String>();
	 private static List<String> list3 = new ArrayList<String>();
	 private static List<String> list4 = new ArrayList<String>();
	 
	 // Locations of each text file to load.	 
	 
	 InputStream file1 = this.getClass().getResourceAsStream("files/lists/file1.txt");
	 InputStream file2 = this.getClass().getResourceAsStream("files/lists/file2.txt");
	 InputStream file3 = this.getClass().getResourceAsStream("files/lists/file3.txt");
	 InputStream file4 = this.getClass().getResourceAsStream("files/lists/file4.txt");
 
	public LoadWords(){
		
		//empty constructor
	
	}
	
	/**
	 * readAndWrite()
	 * @param read text files.
	 * @return n/a
	 *//**
	 */

	public static void readAndWrite(InputStream input, List<String> list){
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line;
		while ((line = br.readLine()) != null) {
		    list.add(line);
		}
		br.close();
		
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
		sortFifty(list);
		
	}


	/**
	 * sortFifty()
	 * @param Keeps removing random indexes
	 * from the list until only 50 remain.
	 * @return n/a
	 *//**
	 */

	public static void sortFifty(List<String> aList){
		
		int sizeOfList = aList.size();	
		
		Random rnd = new Random();
		int randomInt = rnd.nextInt(sizeOfList-1);
		
		// Keep removing until only 50 remain.
		
		while(aList.size() > 50){
			aList.remove(randomInt);
			sizeOfList -= 1;
			randomInt = rnd.nextInt(sizeOfList);
			}
		
		}

	/**
	 * startSequential()
	 * @param execute commands in 
	 * sequential form.
	 * @return n/a
	 *//**
	 */

 	public void startSequential() {
	        	
	        	//start timer
    	   		timer.start();
    	   		
    	   		//reading and writing four lists sequentially
    	   		readAndWrite(file1, list1);
   				readAndWrite(file2, list2);
   				readAndWrite(file3, list3);
   				readAndWrite(file4, list4);
   				
   				//writing to the master List
   				allLists[0] = list1;
   				allLists[1] = list2;
   				allLists[2] = list3;
   				allLists[3] = list4;
   				
   				//end timer
   				timer.stop();
 				}
	    
	/**
	 * startParallel()
	 * @param execute commands in 
	 * parallel.
	 * @return n/a
	 *//**
	 */
 	
 	public void startParallel() {
	    	
	    	Thread[] thread = new Thread[4];
    	        	
	        	/*****************************************/
	   			/** 	Four thread instances			**/
	   			/*****************************************/
	   			
       			thread[0] = new Thread(new Runnable(){

					public void run() {
		    	   		readAndWrite(file1, list1);
		   				allLists[0] = list1;
					}
       			});
       
       			thread[1] = new Thread(new Runnable(){

       				public void run() {
       	   				readAndWrite(file2, list2);
       	   				allLists[1] = list2;
       							
       				}
       	       	});
       	      
       			thread[2] = new Thread(new Runnable(){

					public void run() {
		    	   		readAndWrite(file3, list3);
		   				allLists[2] = list3;
					}
     			});
     
       			thread[3] = new Thread(new Runnable(){

     				public void run() {
     	   				readAndWrite(file4, list4);
     	   				allLists[3] = list4;
     							
     				}
     	       	});  
     	      
     	      	//start timer
	   			timer.start();
  	      	
	   			for(int i = 0; i <= 3; i++){
	   				thread[i].start();
	   			}
	   			
       	      	try{

    	   			for(int i = 0; i <= 3; i++){
    	   				thread[i].join();
    	   			}
       	      		
       	      	}catch(InterruptedException ex){
       	      		ex.getStackTrace();
       	      	}
       	      	
   				//end timer
   				timer.stop();

	        }

@SuppressWarnings("rawtypes")
public List[] getAllLists() {
	return allLists;
	}


public static void setAllLists(@SuppressWarnings("rawtypes") List[] allLists) {
	LoadWords.allLists = allLists;
	}

public void clearLists () {
	list1.clear();
	list2.clear();
	list3.clear();
	list4.clear();
	}
}



