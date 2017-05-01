package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MiniProject extends Application {

	private static String player_identity = "Unknown_player";
	
	@Override
	public void start(Stage primaryStage) {
		
		// LET'S PLAY!

		try {		

				Parent root = FXMLLoader.load(getClass().getResource("Intro.fxml"));	
		        root.setId("pane_main");
		        Scene scene = new Scene(root, 900, 506);	
		        primaryStage.setTitle("Hangman [Mini-Project HCI & User Interace Module]");
		        scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
		        primaryStage.setResizable(false);
		        primaryStage.setScene(scene);
		        primaryStage.show();
		        		        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static void setUserName(String userName){
		player_identity = userName;
	}
	
	public static String getName(){
		return player_identity;
	}
}
