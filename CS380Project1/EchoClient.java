// By Randy Wong
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public final class EchoClient {

    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 22222)) { // Create socket
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Buffered Reader for input and communicates to EchoServer
            Scanner sc = new Scanner(System.in); // Scanner for input
            PrintStream out = new PrintStream(socket.getOutputStream()); // Socket communicates with EchoServer
            while(true) {
            	System.out.print("Client> ");
            	String talk = sc.nextLine(); // Scanner for input
            	out.println(talk); // Communicate with print Stream
            	System.out.println("Server> " + br.readLine()); // Prints out what is in the Buffered Reader Object
            }
        }
    }
}