package javaDev;

import java.io.PrintWriter;

import javax.swing.JSlider;

import com.fazecast.jSerialComm.SerialPort;

public class storage {
	public static void writeThreadF(SerialPort chosenPort,JSlider servo,char color) {
		
		Thread writeThread = new Thread(){
			public void run() {
				try {Thread.sleep(100); } catch(Exception e) {}
				try {
				PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
				output.print(color+""+servo.getValue());
				output.flush();
				}
				catch(NullPointerException e) {}
				try {Thread.sleep(100); } catch(Exception e) {}
			}
		};
		writeThread.start();
	}
	public static void delay(int time) {
		try {
			Thread.sleep(time);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
}
