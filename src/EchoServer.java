import java.net.*;
import java.io.*;

public class EchoServer {
    public static void main(String[] args) throws IOException {

        // Check the arguments
        if (args.length != 1) {
            System.err.println("Usage: java EchoServer <port number>");
            System.err.println("port should be a number between 1025 and 65535\n");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);
        byte[] buffer = new byte[256];
        DatagramPacket DpRecieve = null;
        DatagramSocket serverSocket = null;

        try {
            // Creates a DatagramSocket and binds to the specific port on the local machine
            serverSocket = new DatagramSocket (portNumber);
        } catch (SocketException e) {
            System.out.println("Socket could not be opened, or the socket could not bind to the specific port " + portNumber);
            System.out.println(e.getMessage());
        }

        while(true){
            System.out.println("Waiting ....");
            //clear the buffer
            buffer = new byte[256];

            //constructs a DatagramPacket for receiving the data of length buffer.length in the byte array buffer
            DpRecieve = new DatagramPacket(buffer, buffer.length);

            //the receive method blocks until a message arrives and it stores the message
            //inside the byte array of the DatagramPacket passed to it.
            serverSocket.receive(DpRecieve);

            // convert the byte array into a string message
            String received = new String(DpRecieve.getData()).trim();
            //or
            //String received = new Sting(buffer, 0, DpReceive.getLength());

            System.out.println("server received " + received.length() + " bytes" + "\n" + received);
            System.out.println("---------------------");
        }
    }
}
