import java.util.ArrayList;
import java.util.HashMap;

public class NFSA {

	private int numStates;
	private ArrayList<Boolean> finalStates;
	private HashMap<String, Integer> alphabet; // alphabet in Strings
	private int[][] transitions;
	private ArrayList<String> states;
	private ArrayList<String> testsCases;

	public NFSA() {
		finalStates = new ArrayList<Boolean>();
		alphabet = new HashMap<String, Integer>();
		states = new ArrayList<String>();
		testsCases = new ArrayList<String>();
	}

	public NFSA(int numStates, String[] finalStates, String[] alphabet,
			ArrayList<String>[] transitions, ArrayList<String> cases) {
		ArrayList<ArrayList<String>> state = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		temp.add("0");
		state.add(temp);
		int numberState = 0, currentState = 0;
		while (currentState <= numberState) {
			for (int i = 0; i < alphabet.length; i++) {
				computeDFSANextState(i, transitions);
			}
		}
	}

	public void computeDFSANextState(int i, ArrayList<String>[] transitions) {
		transitions[i];
	}
}
// for(int i = 0; i < transitions.length; i++)
// {
// ComputeDFSANextState.add(transitions[i]);
// }
// }