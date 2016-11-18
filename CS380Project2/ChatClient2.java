//Written By Kevin Ali for CS380 Project 2
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public final class ChatClient2 {

	public static void main(String[] args) throws Exception, IOException {
		Socket s = new Socket("76.91.123.97", 22222);

		Runnable recieve = () -> {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				String str;
				while (true) {
					str = br.readLine();
					if(str!= null)
						System.out.println(str);
					else
						// this occurred when the client side was timed out or if user did not
						// got d/c'ed by inputing a name already in use it kept sending nulls,
						//  this part stops the nulls spam and disconnects and ends the program
						throw new Exception("Disconnected, username was in use");
				}
				
			} catch (Exception e) {
				//e.printStackTrace();// used my own exception to simplify errors
				System.out.println("exited and disconnected");
				System.exit(0);//so the client program terminate after server d/c's the user for idle
			}

		};
		
		Runnable messege = () -> {
			try {
				BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				String str;
				while ((str=user.readLine())!=null){
					if(str.compareToIgnoreCase("Exit")==0)// type exit to close socket and disconnect
						s.close();
					out.println(str);
				}
				s.close();
			} catch (Exception e) {
				//e.printStackTrace();// was here for testing making sure if any errors occurred client thread
			}

		};
		
		Thread rc = new Thread(recieve);
		Thread mess = new Thread(messege);
		mess.start();
		rc.start();
		
		
	}
}
