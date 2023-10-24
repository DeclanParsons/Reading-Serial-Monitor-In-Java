import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;

public class test {

    public static void main(String[] args) {
        // List all available serial ports
        SerialPort[] ports = SerialPort.getCommPorts();
        
        if (ports.length == 0) {
            System.out.println("No serial ports found.");
            return;
        }
        
        // Choose the serial port you want to use
        SerialPort chosenPort = ports[0];  // You can change this index as needed
        chosenPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        
        // Open the chosen serial port
        if (chosenPort.openPort()) {
            System.out.println("Serial port opened: " + chosenPort.getSystemPortName());

            chosenPort.setBaudRate(9600);

            // Create an InputStream to read data from the port
            InputStream in = chosenPort.getInputStream();

            try {
                while (true) {
                    int data = in.read();
                    System.out.print((char) data);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                chosenPort.closePort();
            }
        } else {
            System.out.println("Failed to open the serial port.");
        }
    }
}


    

