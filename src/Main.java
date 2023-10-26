import java.io.IOException;
import java.io.InputStream;
import com.fazecast.jSerialComm.SerialPort;


public class Main{ 

    public static void main(String args[]){ 

        get_ports(); 

    }


    static void get_ports(){ 
        
        SerialPort[] ports = SerialPort.getCommPorts(); 
        SerialPort single_port = ports[0]; 

        /*
        take first element of comm ports list eventually add feature for user select
        */
        single_port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0); //stops timeout error if == 0 
        single_port.setBaudRate(single_port.getBaudRate()); //add flexibility with baud rate, find baud based on port? (Hope this works)

        read_port(single_port); 
                        
    }


    static void read_port(SerialPort port){ 

        InputStream in = port.getInputStream(); 

        try{ 
            while(true){ 

                int data = in.read(); 
                System.out.print((char) data); 
            }
        }catch(IOException e){ 

            e.printStackTrace(); 
        }finally{ 

            port.closePort(); 
        }
    }
}