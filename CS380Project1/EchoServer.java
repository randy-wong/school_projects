// By Randy Wong
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public final class EchoServer {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(22222)) { // Attempt creating new server
            while (true) {
                try (Socket socket = serverSocket.accept()) { // Create socket
                    String address = socket.getInetAddress().getHostAddress(); // Get address of the socket
                    System.out.printf("Client connected: %s%n", address); // PrintAddress
                    PrintStream out = new PrintStream(socket.getOutputStream()); // Use OutPut Stream
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Using BufferedReader communicate
                    while(true) {
                    	String get = in.readLine(); // Uses String to store information from Buffered Reader
                    	out.println(get); // Print out String
                    	out.flush();
                    }
                }
            }
        }
    }
}