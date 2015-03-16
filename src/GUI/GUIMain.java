package GUI;

import application.Monitor;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class GUIMain extends Application {
	private Monitor monitor;
	public GUIMain(Monitor monitor){
		this.monitor = monitor;
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = new AnchorPane();
			Scene scene = new Scene(root,640,480);
			scene.getStylesheets().add(getClass().getResource("gui.fxml").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void init(){
		
	}

	
}
