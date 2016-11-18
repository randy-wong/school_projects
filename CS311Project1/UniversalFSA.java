///* 
// * This program was compiled using Eclipse on Windows 7
// * To run the program, place the input file in the same directory of the source file.
// * 
// * Name: Jose Lascano
// * Project 1
// * Due: 01/29/2014
// * Course:  CS-311-WR14
// * 
// * Description:
// * This implementation of project 1 reads input from a file then stores the values in various
// * variables that are passed along to the driver class. After initializing the variables in the
// * constructor, the program keeps looping in main inputing strings into the nfa to see if it's
// * in its particular language. When the scanner reads a # it moves on to the next machine. The
// * way I'm reading strings into the nfa is I convert them to type char then convert the again to
// * type int.
// */
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class UniversalFSA {
//
//	// declaration of variables
//	private int state = 0, symbol; // initial state = 0
//	private String string, transitions[], states, alphabet, fin;
//	private char uniSymbol;
//
//	private static int number = 0;
//
//	// constructor
//	UniversalFSA(String string, String finalStates,
//			ArrayList<String> transitions, String alpha, String states) {
//		this.string = string;
//		this.states = states;
//		convert(transitions);
//		this.fin = finalStates;
//		this.alphabet = alpha;
//		message();
//	}
//
//	// convert the transition from ArrayList to an array
//	private void convert(ArrayList<String> transitions2) {
//		// TODO Auto-generated method stub
//		transitions = new String[transitions2.size()];
//		for (int x = 0; x < transitions2.size(); x++) {
//			transitions[x] = transitions2.get(x);
//		}
//	}
//
//	// FSA machine
//	public void fsa(String str) {
//
//		this.string = str;
//		int x = 0;
//		state = 0;
//
//		while (x < str.length()) {
//			uniSymbol = str.charAt(x);
//			symbol = getSymbol();
//			state = getNextState();
//			if (symbol != -99 && x < string.length() - 1) {
//				if (state == -1) {
//					reject();
//					x++;
//					break;
//				}
//				x++;
//			} else {
//				if (x == string.length() - 1 && !isFinal()) {
//					x++;
//					reject();
//					break;
//				} else if (isFinal() && x == string.length() - 1) {
//					accept();
//					x++;
//					break;
//				}
//				x++;
//			}
//		}// end while loop
//	}
//
//	// print the accepted string
//	private void accept() {
//		// TODO Auto-generated method stub
//		System.out.println(string + "\t\t\t\tAccept");
//	}
//
//	// print the rejected string
//	private void reject() {
//		// TODO Auto-generated method stub
//		System.out.println(string + "\t\t\tReject");
//	}
//
//	// check if it's in the final state
//	public boolean isFinal() {
//		// TODO Auto-generated method stub
//		int one;
//		char a;
//		for (int x = 0; x < fin.length(); x++) {
//
//			a = (char) fin.charAt(x);
//			one = Character.getNumericValue(a);
//			if (state == one)
//				return true;
//		}
//
//		return false;
//	}
//
//	// return the next state
//	public int getNextState() {
//		// TODO Auto-generated method stub
//		char a, b, c;
//		int one, two, three;
//		for (int x = 0; x < transitions.length; x++) {
//			a = (char) transitions[x].charAt(1);
//			b = (char) transitions[x].charAt(3);
//			one = Character.getNumericValue(a);
//			two = Character.getNumericValue(b);
//
//			// charAt(1) is the current state in the array, charAt(3) the symbol
//			if (one == this.state && two == this.symbol) {
//				// if both the current state and symbol are in array, return new
//				// state
//				c = (char) transitions[x].charAt(5);
//				three = Character.getNumericValue(c);
//				return three;
//			}
//		}
//
//		// otherwise, return trap state
//		return -1;
//	}
//
//	// this gets the unicode value from the string and returns an int for the
//	// symbol
//	public int getSymbol() {
//		// TODO Auto-generated method stub
//
//		if (alphabet.length() > 10) {
//			// symbol is a digit
//			if (uniSymbol >= 48 && uniSymbol <= 57) {
//				return 1;
//			}
//			// symbol is a letter
//			else if (uniSymbol >= 65 && uniSymbol <= 90) {
//				return 0;
//			}
//			// symbol is a letter
//			else if (uniSymbol >= 97 && uniSymbol <= 122) {
//				return 0;
//			}
//			// symbol is a hyphen
//			else if (uniSymbol == 45) {
//				return 2;
//			}
//			// symbol is an underscore
//			else if (uniSymbol == 95) {
//				return 3;
//			}
//			// symbol is a period
//			else if (uniSymbol == 46) {
//				return 4;
//			}
//			// symbol is @
//			else if (uniSymbol == 64) {
//				return 5;
//			}
//
//			// symbol is $
//			else if (uniSymbol == 36) {
//				return 6;
//			} else
//				return -99;
//		}
//
//		else if (alphabet.length() < 4) {
//			// symbol is a
//			if (uniSymbol == 97) {
//				return 0;
//			}
//			// symbol is b
//			else if (uniSymbol == 98) {
//				return 1;
//			}
//		} else if (alphabet.length() == 5) {
//			if (uniSymbol == 48)
//				return 0;
//			else if (uniSymbol == 49)
//				return 1;
//			else if (uniSymbol == 50)
//				return 2;
//			else
//				return -99;
//		}
//		return -99;
//	}
//
//	private void message() {
//
//		System.out.println("\nFinate State Automaton #" + ++number
//				+ "\n1) Number Of States: " + states + "\n2) Final States: "
//				+ fin + "\n3) Alphabet: " + alphabet + "\n4) Transitions: ");
//
//		for (int x = 0; x < transitions.length; x++) {
//			System.out.println("\t" + transitions[x]);
//		}
//		System.out.println("5) strings: ");
//	}
//
//	/*************************************************************
//	 ************************************************************* 
//	 ************************** MAIN*******************************
//	 ************************************************************* 
//	 ************************************************************/
//	public static void main(String args[]) throws IOException {
//
//		ArrayList<String> trans = new ArrayList<String>();
//		String string, alpha, states, fin;
//
//		// open file
//		File file = new File("input.txt");
//		Scanner scanner = new Scanner(file);
//
//		while (scanner.hasNext()) {
//			// put info from file to variables
//			states = scanner.nextLine();
//			fin = scanner.nextLine();
//			alpha = scanner.nextLine();
//			string = scanner.nextLine();
//
//			// fill up the transition table in ArrayList
//			// using '(' because input file is formatted that way
//			while (string.charAt(0) == '(') {
//				trans.add(string);
//				string = scanner.nextLine();
//				if (string.length() < 2) {
//					break;
//				}
//			}
//
//			UniversalFSA driver = new UniversalFSA(string, fin, trans, alpha,
//					states);
//			// read Strings into NFA. # means new machine
//			while (!string.equals("#")) {
//				// System.out.println("I'm in here now!");
//				driver.fsa(string);
//				// if # is next symbol break the while loop
//				if (string.equals("#")) {
//					break;
//				}
//				string = scanner.nextLine();
//			}
//			trans = new ArrayList<String>();
//		}
//		scanner.close();
//	}
//}