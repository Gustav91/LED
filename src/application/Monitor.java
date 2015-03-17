package application;

import javafx.scene.image.WritableImage;

public class Monitor {
	
	//Shared data
	private byte[] pixels;
	
	//Private attributes
	private boolean fetchedPixels;
	
	//For testing
	private int width, height;
	private WritableImage testImg;
	private String testMessage;
	/**
	 * Constructor
	 */
	public Monitor(){
		fetchedPixels = false;
		width = 112;
		height = 820;
		testImg = new WritableImage(820, 112);
		testMessage = "I am a lazy motherfucker!";
	}
	
	/**
	 * Waits for new pixels to be stored in the monitor 
	 * @return the new pixels
	 */
	public synchronized byte[] fetchPixels(){
		while(!fetchedPixels){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fetchedPixels = false;
		
		//Kanske ha logiken f�r att h�mta "r�tt" pixlar h�r
		return pixels;
	}
	
	/**
	 * Stores the new pixels in the monitor
	 * @param newPixels the new pixels
	 */
	public synchronized void setPixels(byte[] newPixels){
		pixels = newPixels;
		fetchedPixels = true;
		notifyAll();
	}
	
	/*
	 * Testing purposes
	 */
	public synchronized void setWidth(int w){
		width = w;
	}
	public synchronized void setHeight(int h){
		height = h;
	}
	public synchronized int getHeight(){
		return height;
	}
	public synchronized int getWidth(){
		return width;
	}

	public synchronized void setTestImage(WritableImage img) {
		testImg = img;
		
	}
	public synchronized WritableImage getTestImg(){
		return testImg;
	}
	public synchronized void setMessage(String m){
		testMessage = m;
	}
	public synchronized String getMessage(){
		return testMessage;
	}
}
