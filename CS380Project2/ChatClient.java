// Randy Wong
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public final class ChatClient {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("76.91.123.97", 38002)) {
			final BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Reads input string from socket
			PrintStream out = new PrintStream(socket.getOutputStream()); // Prints to output stream from input stream from server/ socket
			Scanner scan = new Scanner(System.in);

			Runnable r = new Runnable() { // Inner class for thread
				public void run() {	// method function for thread
					while (true) { // continuous loop to read line
						try {
							System.out.println(br.readLine()); // prints input from server
						}
						catch (Exception e) {
							return;
						}
					}
				}
			};	

			System.out.print ("User: ");    	
			String s = scan.nextLine(); // User name
			out.println(s); // Print out user name

			Thread t = new Thread(r);
			t.start(); // Create thread at this instance and start it

			while(true) {
				s = scan.nextLine(); // Keep taking input from the user input 
				out.println(s); // Print out the String from user input
			}
		}
	}
}