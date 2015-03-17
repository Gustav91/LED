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
	
	public Main(){
		
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			 * Start new threads here I think. In this method
			 */
			// Init the gui controller
			guiController guiCtrl = new guiController();
			//guiCtrl.setMainApp(this);
			//guiCtrl.setMonitor(monitor);
			
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
	public Monitor getMonitor(){
		return monitor;
	}
	public Main getMainApp(){
		return this;
	}
	public void setMonitor(Monitor monitor){
		this.monitor = monitor;
	}
	
	public static void main(String[] args) {
		//Application.launch(args);
		launch(args);
	}
}
