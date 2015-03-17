package GUI;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import application.Main;
import application.Monitor;
import application.TestThread;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
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
	
	/*
	 * For testing only
	 */
	@FXML 
	private Canvas testArea;
	private PixelWriter pixelWriter;
	private PixelReader pixelReader;
	
	
	// The pixel format as byte rgb
	private WritablePixelFormat<ByteBuffer> pixelFormat = WritablePixelFormat.getByteBgraInstance();
	//Pixel buffer
	private byte[] pixelBuffer;
	
	private Image activeImage;
	private WritableImage snapshot;
	private Duration updateSpeed;
	private KeyFrame frame;
	private EventHandler<ActionEvent> frameUpdate;
	private Main main;
	private Monitor monitor;
	public guiController() {
		activeImage= new Image("/images/logo.jpg");
		monitor = new Monitor();
		TestThread testThread = new TestThread(monitor);
		testThread.start();
	}
	/*
	 * Automatically called at start up
	 */
	@FXML
	private void initialize(){
		
		//
		monitor.setHeight((int)activeImage.getHeight());
		monitor.setWidth((int)activeImage.getWidth());

		resizeDisplay(activeImage.getWidth(), activeImage.getHeight());
		GraphicsContext gc = displayArea.getGraphicsContext2D();
		GraphicsContext gc2 = testArea.getGraphicsContext2D();
		gc.drawImage(activeImage, 0, 0);
		pixelReader = snapshot.getPixelReader();
		pixelBuffer = new byte[(int) snapshot.getHeight()* (int) snapshot.getWidth()*4];
		frameUpdate = new EventHandler<ActionEvent>() {
			/*
			 * Take a snapshot, read the pixels and send them to the monitor
			 */
			@Override
			public void handle(ActionEvent event) {
				snapshot = displayArea.snapshot(null, snapshot);
				pixelReader.getPixels(0, 0, (int) snapshot.getWidth(), (int) snapshot.getHeight(), pixelFormat, pixelBuffer, 0, (int) snapshot.getWidth()*4); // <<< hade inte multiplicerat med 4 -.-
				monitor.setPixels(pixelBuffer);
				
				
				updateTest(gc2);
			}
		};
		setFrameRate(60); //60 fps default
		Timeline tl = new Timeline(frame);
		tl.setCycleCount(Animation.INDEFINITE);
		tl.play();
		
	}
	
	/*
	 * Resize the display area to the given dimensions
	 */
	private void resizeDisplay(double width, double height){
		displayArea.setHeight(activeImage.getHeight());
		displayArea.setWidth(activeImage.getWidth());
		snapshot = new WritableImage((int) displayArea.getWidth(), (int) displayArea.getHeight() );			
	}
	/*
	 * Set the frameRate of the animation. Default is 60 fps
	 */
	private void setFrameRate(int fps){
		updateSpeed = Duration.millis(1000/fps);
		frame = new KeyFrame(updateSpeed, frameUpdate);
	}
	/*
	 * Set a reference to the main application
	 * Not sure if needed
	 */
	public void setMainApp(Main main){
		this.main = main;
	}
	public Monitor getMonitor(){
		return monitor;
	}
	@FXML
	private void handleStart(){
			
	}

	/*
	 * Testing
	 */
	@FXML
	private void updateTest(GraphicsContext gc2){
		/*
		 * WritableImage test = new WritableImage( (int) displayArea.getWidth(), (int) displayArea.getHeight() );
		 
		byte[] wPix = monitor.fetchPixels();
		System.out.println(wPix.length + " : " + pixelBuffer.length);
		
		pixelWriter = test.getPixelWriter();
		pixelWriter.setPixels(0, 0, (int) test.getWidth(), (int) test.getHeight(), WritablePixelFormat.getByteBgraInstance() , wPix,  0, (int)test.getWidth()*4);
		gc2.drawImage(test,0,0);
		*/
		WritableImage test = monitor.getTestImg();
		gc2.drawImage(test, 0, 0);
		System.out.println(monitor.getMessage());
	}
}
