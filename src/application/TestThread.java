package application;

import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.image.WritablePixelFormat;

public class TestThread extends Thread{
	private Monitor monitor;
	private WritableImage img;
	private byte[] pixels;
	private PixelWriter pixelWriter;
	public TestThread(Monitor monitor){
		this.monitor = monitor;
	}
	public void run(){
		
		while(true){
			img = new WritableImage(monitor.getWidth(), monitor.getHeight());
			pixelWriter = img.getPixelWriter();
			pixels = monitor.fetchPixels();
			pixelWriter.setPixels(0, 0, (int)img.getWidth(), (int)img.getHeight(), WritablePixelFormat.getByteBgraInstance(), pixels, 0, (int)img.getWidth()*4);
			
			monitor.setTestImage(img);
			try {
				sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
}
