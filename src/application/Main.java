package application;
	
import GUI.guiController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	private Monitor monitor;
	@Override
	public void start(Stage primaryStage) {
		try {
			monitor = new Monitor();
			// Init the gui controller
			guiController guiCtrl = new guiController();
			guiCtrl.setMainApp(this);
			guiCtrl.setMonitor(monitor);
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
