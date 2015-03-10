package application;

public class Monitor {
	
	//Shared data
	private byte[] pixels;
	
	//Private attributes
	private boolean fetchedPixels;
	
	/**
	 * Constructor
	 */
	public Monitor(){
		fetchedPixels = false;
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
		return pixels;
	}
	
	/**
	 * Stores the new pixels in the monitor
	 * @param newPixels the new pixels
	 */
	public synchronized void setPixels(byte[] newPixels){
		pixels = newPixels;
		fetchedPixels = true;
	}
}
