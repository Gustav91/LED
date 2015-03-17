package application;
	
import GUI.guiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Monitor monitor;
	private AnchorPane root;
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Start new threads here I think. In this method
			 */
			monitor = new Monitor();
			// Init the gui controller
			guiController guiCtrl = new guiController();
			guiCtrl.setMainApp(this);
			guiCtrl.setMonitor(monitor);
			
			// Load the gui
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/GUI/gui.fxml"));
			root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
