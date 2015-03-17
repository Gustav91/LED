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
			for (int i = 0; i < pixels.length; i++){
				if (pixels[i] == (byte) 0x00 ){
					pixels[i] = (byte) (255 & 0xff);
				}
				else if(pixels[i] == (byte) 0xff){
					pixels[i] = (byte) 0x00;
				}
					
			}
			pixelWriter.setPixels(0, 0, (int)img.getWidth(), (int)img.getHeight(), WritablePixelFormat.getByteBgraInstance(), pixels, 0, (int)img.getWidth()*4);
			
			monitor.setTestImage(img);
			monitor.setMessage("Im running for my life!");
			try {
				sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}
}
