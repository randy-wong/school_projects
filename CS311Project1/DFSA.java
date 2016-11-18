/*import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


public class DFSA {

	public static void main(String args[]) throws IOException
	{		
		ArrayList<String> trans = new ArrayList<String>();
		String string, alpha, storedfinal, states;
		//open file
		File file = new File("input2.txt");
		Scanner scanner = new Scanner(file);

		while(scanner.hasNext()){
			//put info from file to variables
			states = scanner.nextLine();
			storedfinal = scanner.nextLine();
			alpha = scanner.nextLine();
			string = scanner.nextLine();

			//fill up the transition table in ArrayList
			//using '(' because input file is formatted that way
			while(string.charAt(0) == '(')
			{
				trans.add(string);
				string = scanner.nextLine();
				if(string.length() < 2)
				{
					break;
				}
			}
			
			ArrayList<Boolean> finalstates = new ArrayList<Boolean>(Arrays.asList(new Boolean[Integer.parseInt(states)]));
			Collections.fill(finalstates, new Boolean(false));
			StringTokenizer tokens = new StringTokenizer(storedfinal);  
			while(tokens.hasMoreTokens())
			{
				ArrayList<Boolean> finalstates[Integer.parseInt(tokens.nextToken())] = true;
			}
			
			String[] finals = storedfinal.split(" ");
			DFSA driver = new DFSA(string, finalstates, trans, alpha, states);


		}
		private int state = 0, symbol; 		//initial state = 0
		private String string, transitions[], states, alphabet;
		private ArrayList<Boolean> fin;
		
		private char uniSymbol;

		private static int number = 0;

		//constructor
		DFSA(String string, ArrayList<Boolean> finalStates, ArrayList<String> transitions, String alpha, String states)
		{				
			this.string = string;
			this.states = states;
			convert(transitions);
			this.fin = finalStates;
			this.alphabet = alpha;
			message();
		}
		//convert the transition from ArrayList to an array
		private void convert(ArrayList<String> transitions2)
		{
			// TODO Auto-generated method stub
			transitions = new String[transitions2.size()];
			for(int x = 0; x < transitions2.size(); x++)
			{
				transitions[x] = transitions2.get(x);
			}
		}
	}*/