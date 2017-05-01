package application;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;


public class IntroController{  

	// Class object declarations.
		
	private LoadWords loadWords = new LoadWords();
	private GameLogic game = new GameLogic();
	
	// Audio declarations, sounds are free to use for non-commercial usage.
	
	final URL resource = getClass().getResource("sounds/beep.wav");
	final AudioClip menuClick = new AudioClip(resource.toString());

	// Components for other areas of the view.
	
   	@FXML public String selectedDifficulty;	
	@FXML public TextField myName;
	@FXML private GameLogic currentState;
	@FXML int testpass = 1;
	
	@FXML
	public void initialize(){

		// Do nothing.
	}
	
	/**
	 * loadSerial()
	 * @param load words sequentially.
	 * @return n/a
	 *//**
	 */
	
    @FXML
    public void loadSerial(MouseEvent mouseEvent) {	
    	
        menuClick.play();
        loadWords.startSequential();    
        MiniProject.setUserName(myName.getText().toString());
        game.setAllLists(loadWords.getAllLists());
        
      
        try{
        	
            Node  source = (Node)  mouseEvent.getSource(); 
            Stage intro  = (Stage) source.getScene().getWindow();
            intro.close();       	
        	   
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));	           
	        root.setId("pane");

	        Scene scene = new Scene(root, 900, 506);
	        Stage primaryStage = new Stage();
	        
	        primaryStage.setTitle("The Hangman [Mini-Project HCI & User Interace Module]");
	        scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
	        primaryStage.setResizable(false);
	        primaryStage.setScene(scene);
	        primaryStage.show();

          }catch(Exception e) {
        	  e.printStackTrace();
          }

 	
    }
    
	/**
	 * loadParallel()
	 * @param load words in parallel.
	 * Using threads.
	 * @return n/a
	 *//**
	 */
    
    @FXML
    public void loadParallel(MouseEvent mouseEvent) {
    	
        menuClick.play();
        loadWords.startParallel();        
        MiniProject.setUserName(myName.getText().toString());
        game.setAllLists(loadWords.getAllLists());
   
        try{
        	
            Node  source = (Node)  mouseEvent.getSource(); 
            Stage intro  = (Stage) source.getScene().getWindow();
            intro.close();       	
        	
            
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));	           
	        root.setId("pane");

	        Scene scene = new Scene(root, 900, 506);
	        Stage primaryStage = new Stage();
	        
	        primaryStage.setTitle("The Hangman [Mini-Project HCI & User Interace Module]");
	        scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
	        primaryStage.setResizable(false);
	        primaryStage.setScene(scene);
	        primaryStage.show();	        

          }catch(Exception e) {
        	  System.out.println("OOPS, something went wrong opening the main window");        	  
          }

    	}
    

    
    @FXML  
    public void zombieHover(MouseEvent event){
    	// Do nothing.
    	
    }
    
    @FXML  
    public void skulHover(MouseEvent event){
    	// Do nothing.
    	
    }
}