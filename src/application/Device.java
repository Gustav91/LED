package application;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Device {
	
	private Socket socket;
	private OutputStream output;
	private String host;
	private int port;
	private byte firmwareConfig;
	private byte[] dataPacket;
	private byte[] configPacket;
	private static final int MAX_LEDS = 512;
	private int nbrOfLeds;
	private int[] pixels;
	
	public Device(String host, int port, int nbrOfLeds) {
		this.host = host;
		this.port = port;
		this.nbrOfLeds = nbrOfLeds;
		init();
		connect();
	}
	/*
	 * Connect the device to the fcServer
	 */
	private void connect() {
		try {
			socket = new Socket(host, port);
			socket.setTcpNoDelay(true);
			output = socket.getOutputStream();

		} catch (ConnectException e){
			System.out.println("Could not connect to server");
			Dispose();
			
		} catch (IOException e){
			System.out.println("IO error");
			Dispose();
		}
		sendFirmwareConfiguration();
		
	}

	private void init() {
		if (nbrOfLeds > MAX_LEDS) {
			pixels = new int[MAX_LEDS];
		} else if (nbrOfLeds < 0) {
			nbrOfLeds = 0;
		} else {
			pixels = new int[nbrOfLeds];
			
		}
		dataPacket = new byte[nbrOfLeds*3 + 4];
		configPacket = new byte[9];
	}

	/*
	 * Enable or disable Dithering
	 */
	public void setDithering(boolean enable){
		if (enable) {
			firmwareConfig &= 0x01;
		} else {
			firmwareConfig |= 0x01;
		}
		sendFirmwareConfiguration();
	}
	
	private void sendFirmwareConfiguration() {
		if (output != null) {
			configPacket[0] = 0;
			configPacket[1] = (byte)0xFF;
			configPacket[2] = 0;
			configPacket[3] = 5;
			configPacket[4] = 0x00;
			configPacket[5] = 0x01;
			configPacket[6] = 0x00;
			configPacket[7] = 0x02;
			configPacket[8] = firmwareConfig;
			
			try {
				output.write(configPacket);
			} catch (Exception e) {
				Dispose();
			}
		}
		
	}
	
	private void Dispose() {
		socket = null;
		output = null;
		
	}
	

}
