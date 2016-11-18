import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class TicTacToeClient {
	static BoardMessage board;
//	static boolean flag = true;

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("45.50.5.238", 38007);
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

		System.out.println("Instructions: 3 in a row to win, X is the player");
		System.out.print("Enter Username: ");
		Scanner sc = new Scanner(System.in);
		String name = sc.next();

//		while(flag) {
			ConnectMessage message = new ConnectMessage(name); // Initializes message name to connect with
			CommandMessage command = new CommandMessage(CommandMessage.Command.NEW_GAME); // Initializes command new game to connect
			out.writeObject(message); // Sends to server message
			out.writeObject(command); // Sends to server command
			board = (BoardMessage) in.readObject(); // Receives board
			while(board.getStatus().equals(BoardMessage.Status.IN_PROGRESS)) { // Loop while game is in progress
				printBoard(board.getBoard()); // Print board
				int set = playMove(); // Plays move
				MoveMessage move = new MoveMessage((byte) ((set - 1) / 3), (byte) ((set - 1 ) % 3)); // Calculates move from int to row and column
				out.writeObject(move); // Sends move to server
                Object obj = in.readObject(); // Checks objects incase of ErrorMEssage or BoardMessage
                while (obj.getClass().getName().equals("ErrorMessage")) { // If Board Message
                	printError(obj); // Print Error
    				printBoard(board.getBoard()); // Print Board
                	set = playMove(); // Replay move
    				move = new MoveMessage((byte) ((set - 1) / 3), (byte) ((set - 1 ) % 3)); // Calculates move from int to row and column
    				out.writeObject(move); // Plays move
    				obj = in.readObject(); // Receives board or error
                }
                board = (BoardMessage) obj;	// Casts message to board

			}

			if(board.getStatus().equals(BoardMessage.Status.STALEMATE)) { printBoard(board.getBoard()); System.out.print("Tie game. "); } // Printing
			if(board.getStatus().equals(BoardMessage.Status.PLAYER1_VICTORY)) { printBoard(board.getBoard()); System.out.print(name + " won. "); } // Printing
			if(board.getStatus().equals(BoardMessage.Status.PLAYER2_VICTORY)) { printBoard(board.getBoard()); System.out.print(name + " lost. "); } // Printing
//			System.out.print("Play again? (0 for no): "); String entry = sc.next();
//			if(entry.equals("0")) { flag = false; }
//		}
	}
	//	}

	public static void printBoard(byte [][] serverBoard) { // Prints Board
		int counter = 1;
		for(int i = 0; i < serverBoard.length; i++) {
			for(int j = 0; j < serverBoard.length; j++) {
				if(serverBoard[i][j] == 0) {
					System.out.print(" "+ counter +" ");
				}
				if(serverBoard[i][j] == 1) {
					System.out.print(" X ");
				}
				if(serverBoard[i][j] == 2) {
					System.out.print(" O ");
				}
				counter++;
			}
			System.out.println();
		}
	}

	public static int playMove() { // Plays move
		int setMove = 0;
		Scanner sc = new Scanner(System.in);
		while(setMove > 9 || setMove < 1) {
			System.out.println("Input Number between 1 - 9");
			setMove = sc.nextInt();
		}
		return setMove;
	}
	
	public static void printError(Object message) { // Casts Object message to ErrorMessage
		ErrorMessage error = (ErrorMessage) message;
		System.out.println(error.getError() + "Input an unoccupied number");
	}
}