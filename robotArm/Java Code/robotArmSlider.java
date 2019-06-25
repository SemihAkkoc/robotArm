package javaDev;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import com.fazecast.jSerialComm.SerialPort;

public class robotArmSlider {
	
	//change this value according to code
	static int startingPoint = 50;

	static int pValue1 = 0;
	
	static SerialPort chosenPort;
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Robot Arm Control Interface 2.0");
		window.setSize(350,280);
		window.setLayout(new BorderLayout());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JComboBox<String> portList = new JComboBox<String>();
		JButton connectButton = new JButton("Connect");
		JPanel topPanel = new JPanel();
		
		JPanel main = new JPanel();
		
		JSlider servo1 = new JSlider();
		JSlider servo2 = new JSlider();
		JSlider servo3 = new JSlider();
		JSlider servo4 = new JSlider();
		JSlider servo5 = new JSlider();
		JSlider servo6 = new JSlider();
		
		JLabel servo1l = new JLabel();
		JLabel servo2l = new JLabel();
		JLabel servo3l = new JLabel();
		JLabel servo4l = new JLabel();
		JLabel servo5l = new JLabel();
		JLabel servo6l = new JLabel();
		
		servo1.setValue(startingPoint);
		servo1.setMinimum(50);
		servo1.setMaximum(180);
		servo2.setValue(startingPoint);
		servo2.setMaximum(180);
		servo3.setValue(startingPoint);
		servo3.setMaximum(180);
		servo4.setValue(startingPoint);
		servo4.setMaximum(180);
		servo5.setValue(startingPoint);
		servo5.setMaximum(180);
		servo6.setValue(startingPoint);
		servo6.setMaximum(180);
		
		servo1l.setText("Mouth Value: " + servo1.getValue());
		servo2l.setText("Head  Value: " + servo2.getValue());
		servo3l.setText("Wrist Value: " + servo3.getValue());
		servo4l.setText("Elbow Value: " + servo4.getValue());
		servo5l.setText("Arm   Value: " + servo5.getValue());
		servo6l.setText("Body  Value: " + servo6.getValue());
		
		main.add(servo1l);
		main.add(servo1);
		main.add(servo2l);
		main.add(servo2);
		main.add(servo3l);
		main.add(servo3);
		main.add(servo4l);
		main.add(servo4);
		main.add(servo5l);
		main.add(servo5);
		main.add(servo6l);
		main.add(servo6);
		
		topPanel.add(portList);
		topPanel.add(connectButton);
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
					}
				}
				else {
					chosenPort.closePort();
					portList.setEnabled(true);
					connectButton.setText("Connect");
				}
			}
		});
		
		window.setVisible(true);
		
		while(true) {
			storage.writeThreadF(chosenPort, servo1, 'm');
			servo1l.setText("Mouth Servo Value: " + servo1.getValue());
			storage.delay(50);
			storage.writeThreadF(chosenPort, servo2, 'h');
			servo2l.setText("Head Servo Value: " + servo2.getValue());
			storage.delay(50);
			storage.writeThreadF(chosenPort, servo3, 'w');
			servo3l.setText("Wrist Value: " + servo3.getValue());
			storage.delay(50);
			storage.writeThreadF(chosenPort, servo4, 'e');
			servo4l.setText("Elbow Value: " + servo4.getValue());
			storage.delay(50);
			storage.writeThreadF(chosenPort, servo5, 'a');
			servo5l.setText("Arm   Value: " + servo5.getValue());
			storage.delay(50);
			storage.writeThreadF(chosenPort, servo6, 'b');
			servo6l.setText("Body  Value: " + servo6.getValue());
			storage.delay(50);
		}
  }
}
