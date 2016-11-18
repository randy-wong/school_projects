/* 
 * This program was compiled using Eclipse on Windows 7
 * To run the program, place the input file in the same directory of the source files. 
 * 
 * Name: Randy Wong
 * Project
 * Due: 9AM, Monday, 7/148/2014
 * Course:  CS-311-E01-SUMMER14
 * 
 * Description:
 * This implementation of DFSA. I was unable to finish the NFSA.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

public class UniversalFiniteStateAutomata {
	public static void main(String[] args) {

		int h = 1;
		File file = new File("input11.txt");	//Reads source file.
		try {
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				System.out.println("Finite State Automaton #" + ++h);
				int i = scanner.nextInt();
				System.out.println("1) number of states: " + i);
				scanner.nextLine();
				String[] finalStates = scanner.nextLine().split(" ");
				String[] alphabet = scanner.nextLine().split(" ");

				System.out.println("2) final states: "
						+ Arrays.toString(finalStates));
				System.out.println("3) alphabet: " + Arrays.toString(alphabet));
				ArrayList<String> cases = new ArrayList<String>();
				String[][] matrix = new String[i * alphabet.length][3];
				int j = 0;
				System.out.println("4) transitions:");
				String check = scanner.nextLine();
				while (check.charAt(0) == '(') {
					check = check.replace("(", "");
					check = check.replace(")", "");
					String[] temp = check.split(" ");

					matrix[j][0] = temp[0];
					matrix[j][1] = temp[1];
					matrix[j][2] = temp[2];
					System.out.println("\t" + Arrays.toString(matrix[j]));
					j++;
					check = scanner.nextLine();
				}
				while (!(check.charAt(0) == '/')) {
					cases.add(check);
					check = scanner.nextLine();
				}
				DFSA2 FiniteStateAutomaton = new DFSA2(i, finalStates,
						alphabet, matrix, cases);
				for (int m = 0; m < cases.size(); m++) {
					System.out.printf("\t%s\t\t%s \n", cases.get(m),
							caseTest(FiniteStateAutomaton).get(m));
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> caseTest(DFSA2 toTest) {
		ArrayList<String> toReturn = new ArrayList<String>();
		//Returns a String of accepted and failed cases
		for (int i = 0; i < toTest.getTestsCases().size(); i++) {
		//Iterates through each case
			int initState = 0;
			boolean exit = false;
			for (int j = 0; j < toTest.getTestsCases().get(i).length(); j++) {
			//Iterates through each case's String
				String symbol = ("" + toTest.getTestsCases().get(i).charAt(j));
				if (toTest.getAlphabet().containsKey(symbol) == true) {
				//Checks if it in alphabet
					initState = toTest.getTransitions()[initState][toTest
							.getAlphabet().get(symbol)];
					//Sets initial to what the index of transition it is at
					if (initState == toTest.getNumStates()) {
						//If initial transition is equal to number of states reject
						toReturn.add("Rejected");
						exit = true;
						break;
					}
				} else {
				//Symbols have ended
					if (toTest.getFinalStates().get(initState) == false) {
						//If it is not at a final state then reject
						toReturn.add("Rejected");
						exit = true;
						break;
					} else if (toTest.getFinalStates().get(initState) == true) {
						//If it is at final state then accept
						toReturn.add("Accepted");
						exit = true;
						break;
					} else {
						//Else reject
						toReturn.add("Rejected");
						exit = true;
						break;
					}
				}
			}
			if (exit != true) {
				if (toTest.getFinalStates().get(initState) == false) {
					toReturn.add("Rejected");
				} else if (toTest.getFinalStates().get(initState) == true) {
					toReturn.add("Accepted");
				} else {
					toReturn.add("Rejected");
				}
			}
		}
		return toReturn;
	}
}