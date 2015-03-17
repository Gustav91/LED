package GUI;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import application.Main;
import application.Monitor;
import javafx.animation.KeyFrame;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;
import javafx.util.Duration;


public class guiController {
	@FXML
	private Button start;
	
	@FXML
	private Button placeHolder2;
	
	@FXML
	private Canvas displayArea;
	
	// The pixel format as byte rgb
	private WritablePixelFormat<ByteBuffer> pixelFormat = WritablePixelFormat.getByteBgraInstance();
	//Pixel buffer
	private byte[] pixelBuffer;
	
	private Image activeImage;
	private WritableImage snapshot;
	private Duration updateSpeed;
	private KeyFrame frame;
	private EventHandler frameUpdate;
	private Main main;
	private Monitor monitor;
	public guiController() {
		activeImage= new Image("/images/logo.jpg");
	}
	/*
	 * Automatically called at start up
	 */
	@FXML
	private void initialize(){
		displayArea.setHeight(activeImage.getHeight());
		displayArea.setWidth(activeImage.getWidth());
		snapshot = new WritableImage((int) displayArea.getHeight(), (int) displayArea.getWidth());
		GraphicsContext gc = displayArea.getGraphicsContext2D();
		gc.drawImage(activeImage, 0, 0);
		snapshot = displayArea.snapshot(null, snapshot);
		PixelReader pixelReader = snapshot.getPixelReader();
		pixelBuffer = new byte[(int) snapshot.getHeight()* (int) snapshot.getWidth()*3];
		updateSpeed = Duration.millis(1000/60); // 60 fps
		frameUpdate = new EventHandler() {

			@Override
			public void handle(Event event) {
				pixelReader.getPixels(0, 0, (int) snapshot.getWidth(), (int) snapshot.getHeight(), pixelFormat, pixelBuffer, 0, (int) snapshot.getWidth());
				monitor.setPixels(pixelBuffer);
			}
		};
		frame = new KeyFrame(updateSpeed , frameUpdate);
		
	}
	
	/*
	 * Resize the display area to the given dimensions
	 */
	public void resizeDisplay(double width, double height){
		displayArea.setHeight(activeImage.getHeight());
		displayArea.setWidth(activeImage.getWidth());
		snapshot = new WritableImage((int) displayArea.getHeight(), (int) displayArea.getWidth());
		
		
	}
	/*
	 * Set a reference to the main application
	 * Not sure if needed
	 */
	public void setMainApp(Main main){
		this.main = main;
	}
	/*
	 * Set the reference to the monitor
	 */
	public void setMonitor(Monitor monitor){
		this.monitor = monitor;
	}
	@FXML
	private void handleStart(){
		
		
	}


}
