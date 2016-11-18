import java.util.ArrayList;
import java.util.HashMap;

public class DFSA2 {

	private int numStates;
	private ArrayList<Boolean> finalStates;
	private HashMap<String, Integer> alphabet;
	private int[][] transitions;
	private ArrayList<String> states;
	private ArrayList<String> testsCases; 
	// Strings tested for if they are in the language

	public DFSA2() {
		finalStates = new ArrayList<Boolean>();
		alphabet = new HashMap<String, Integer>();
		states = new ArrayList<String>(); // States in the machine
		testsCases = new ArrayList<String>();
	}

	public DFSA2(int numStates, String[] finalStates, String[] alphabet,
			String[][] transitions, ArrayList<String> cases) {
		this();
		this.numStates = numStates;
		this.testsCases = cases;
		for (int i = 0; i < alphabet.length; i++) {
			this.alphabet.put(alphabet[i], i); // Assigns each key a value
		}
		for (int i = 0; i < transitions.length; i++) {
			if (states.contains(transitions[i][0]) == false) {
				// Checks if states are added
				states.add(transitions[i][0]); 
				// Adds all possible states if they are not in the machine
			}
		}
		states.add("" + numStates); // Adds the trap state
		for (int i = 0; i < states.size(); i++) {
			boolean temp = false;
			for (int j = 0; j < finalStates.length; j++) {
				if (finalStates[j].equals(states.get(i))) {
					temp = true; // Checks if a certain state is final
					break;
				}
			}
			this.finalStates.add(temp); // Adds the set of final states
		}
		this.transitions = new int[states.size() - 1][alphabet.length];
		// Transitions will be converted into numerical
		for (int i = 0; i < transitions.length; i++) {
			// Transitions are read by 3 numbers each row
			int row = states.indexOf(transitions[i][0]);
			// Index of initial state
			int column = this.alphabet.get(transitions[i][1]);
			// Index of alphabet used for transition
			this.transitions[row][column] = states.indexOf(transitions[i][2]);
			// Next State will be set by initial state and alphabet index
		}
	}

	public int getNumStates() {
		return numStates;
	}

	public ArrayList<Boolean> getFinalStates() {
		return finalStates;
	}

	public HashMap<String, Integer> getAlphabet() {
		return alphabet;
	}

	public int[][] getTransitions() {
		return transitions;
	}

	public ArrayList<String> getStates() {
		return states;
	}

	public ArrayList<String> getTestsCases() {
		return testsCases;
	}
}