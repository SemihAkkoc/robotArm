package javaDev;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.fazecast.jSerialComm.*;

public class robotArm {

	static int value1s=90;
	static int value2s=90;
	static int value3s=90;
	static int value4s=90;
	static int value5s=90;
	static int value6s=90;

	static SerialPort chosenPort;

	public static void main(String[] args) {

		JFrame window = new JFrame("Robot Arm Control Interface");
		window.setSize(530,210);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComboBox<String> portList = new JComboBox<String>();
		JButton connectButton = new JButton("Connect");
		ImageIcon upIcon = new ImageIcon("");
		ImageIcon downIcon = new ImageIcon("");
		ImageIcon leftIcon = new ImageIcon("");
		ImageIcon rightIcon = new ImageIcon("");
		//servo1
		JLabel servo1 = new JLabel("Mouth Servo (1)");
		JButton servo1l = new JButton("-10",leftIcon);
		JButton servo1r = new JButton("+10",rightIcon);
		JLabel value1 = new JLabel("90");
		//servo2
		JLabel servo2 = new JLabel("Head Servo  (2)");
		JButton servo2l = new JButton("-10",downIcon);
		JButton servo2r = new JButton("+10",upIcon);
		JLabel value2 = new JLabel("90");
		//servo3
		JLabel servo3 = new JLabel("Wrist Servo (3)");
		JButton servo3l = new JButton("-10",leftIcon);
		JButton servo3r = new JButton("+10",rightIcon);
		JLabel value3 = new JLabel("90");
		//servo4
		JLabel servo4 = new JLabel("Elbow Servo (4)");
		JButton servo4l = new JButton("-10",downIcon);
		JButton servo4r = new JButton("+10",upIcon);
		JLabel value4 = new JLabel("90");
		//servo5
		JLabel servo5 = new JLabel("Arm Servo   (5)");
		JButton servo5l = new JButton("-10",downIcon);
		JButton servo5r = new JButton("+10",upIcon);
		JLabel value5 = new JLabel("90");
		//servo6
		JLabel servo6 = new JLabel("Body Servo  (6)");
		JButton servo6l = new JButton("-10",leftIcon);
		JButton servo6r = new JButton("+10",rightIcon);
		JLabel value6 = new JLabel("90");
		JLabel connectionStat = new JLabel("Not Connected");
		JPanel topPanel = new JPanel();
		JPanel main = new JPanel();
		//adding servo1
		main.add(servo1);
		main.add(servo1l);
		main.add(value1);
		main.add(servo1r);
		//adding servo2
		main.add(servo2);
		main.add(servo2l);
		main.add(value2);
		main.add(servo2r);
		//adding servo3
		main.add(servo3);
		main.add(servo3l);
		main.add(value3);
		main.add(servo3r);
		//adding servo4
		main.add(servo4);
		main.add(servo4l);
		main.add(value4);
		main.add(servo4r);
		//adding servo5
		main.add(servo5);
		main.add(servo5l);
		main.add(value5);
		main.add(servo5r);
		//adding servo6
		main.add(servo6);
		main.add(servo6l);
		main.add(value6);
		main.add(servo6r);
		topPanel.add(portList);
		topPanel.add(connectButton);
		topPanel.add(connectionStat);
		window.add(topPanel, BorderLayout.NORTH);
		window.add(main, BorderLayout.CENTER);

		SerialPort[] portNames = SerialPort.getCommPorts();
		for(int i=0; i<portNames.length;i++) {
			portList.addItem(portNames[i].getSystemPortName());
		}

		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(connectButton.getText().equals("Connect")) {
					chosenPort = SerialPort.getCommPort(portList.getSelectedItem().toString());
					chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
					if(chosenPort.openPort()) {
						connectButton.setText("Disconnect");
						portList.setEnabled(false);
						connectionStat.setText("Succesfully Connected");


					}
				}
				else {
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Connect");
					connectionStat.setText("Disconnected");
				}
			}
		});

		//first servo button configuration to right
		servo1r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo1r.getText().equals("+10")) {
					value1s=value1s+10;
					if(value1s>179)
						value1s=179;
					value1.setText(Integer.toString(value1s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s1r");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//first servo button configuration to left
		servo1l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo1l.getText().equals("-10")) {
					value1s=value1s-10;
					if(value1s<1)
						value1s=1;
					value1.setText(Integer.toString(value1s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s1l");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//second servo button configuration to right
		servo2r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo2r.getText().equals("+10")) {
					value2s=value2s+10;
					if(value2s>179)
						value2s=179;
					value2.setText(Integer.toString(value2s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s2r");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//second servo button configuration to left
		servo2l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo2l.getText().equals("-10")) {
					value2s=value2s-10;
					if(value2s<1)
						value2s=1;
					value2.setText(Integer.toString(value2s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s2l");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//third servo button configuration to right
		servo3r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo3r.getText().equals("+10")) {
					value3s=value3s+10;
					if(value3s>179)
						value3s=179;
					value3.setText(Integer.toString(value3s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s3r");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//third servo button configuration to left
		servo3l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo3l.getText().equals("-10")) {
					value3s=value3s-10;
					if(value3s<1)
						value3s=1;
					value3.setText(Integer.toString(value3s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s3l");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//forth servo button configuration to right
		servo4r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo4r.getText().equals("+10")) {
					value4s=value4s+10;
					if(value4s>179)
						value4s=179;
					value4.setText(Integer.toString(value4s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s4r");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//forth servo button configuration to left
		servo4l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo4l.getText().equals("-10")) {
					value4s=value4s-10;
					if(value4s<1)
						value4s=1;
					value4.setText(Integer.toString(value4s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s4l");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//fifth servo button configuration to right
		servo5r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo5r.getText().equals("+10")) {
					value5s=value5s+10;
					if(value5s>179)
						value5s=179;
					value5.setText(Integer.toString(value5s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s5r");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//fifth servo button configuration to left
		servo5l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo5l.getText().equals("-10")) {
					value5s=value5s-10;
					if(value5s<1)
						value5s=1;
					value5.setText(Integer.toString(value5s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s5l");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//sixth servo button configuration to right
		servo6r.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo6r.getText().equals("+10")) {
					value6s=value6s+10;
					if(value6s>179)
						value6s=179;
					value6.setText(Integer.toString(value6s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s6r");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		//sixth servo button configuration to left
		servo6l.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(servo6l.getText().equals("-10")) {
					value6s=value6s-10;
					if(value6s<1)
						value6s=1;
					value6.setText(Integer.toString(value6s));
					Thread thread = new Thread(){
						public void run() {
							try {Thread.sleep(100); } catch(Exception e) {}
							PrintWriter output = new PrintWriter(chosenPort.getOutputStream());
							output.print("s6l");
							output.flush();
							try {Thread.sleep(100); } catch(Exception e) {}
						}
					};
					thread.start();
				}
			}
		});
		window.setVisible(true);
	}

}
