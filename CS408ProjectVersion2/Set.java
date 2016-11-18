import java.util.*;

public class Set {

	private ArrayList<Integer> array;

	public Set() {
		array = new ArrayList<Integer>();
	}

	public boolean add(int element) { // After you add the element, the data is still unique
		if(!array.contains(element)) {
			array.add(element);
			return true;
		}
		return false;
	}

	public ArrayList<Integer> getarray() {
		return this.array;
	}

	public boolean contains(int element) {
		return array.contains(element);
	}

	public void remove(int element) {
		array.remove(element);
	}
	
	public static boolean equals(Set s1, Set s2) {
		for(int i = 1; i < s2.size(); i++) {
			if(!s1.contains(s2.array.get(i))) {
				return false; 
			}
		}
		for(int i = 1; i < s1.size(); i++) {
			if(!s2.contains(s1.array.get(i))) {
				return false; 
			}
		}
		return true;
	}

	public int size() {
		return array.size();
	}
	
	public void display() {
		System.out.println(array);
	}

	public static Set subtract(Set s1, Set s2) {
		Set subtracted = new Set();
		for(int i = 0; i < s1.size(); i++) {
			subtracted.add(s1.array.get(i));
		}
		for(int i = 0; i < s2.size(); i++) {
			if(subtracted.array.contains(s2.array.get(i))) {
				subtracted.remove(s2.array.get(i));
			}
		}
		return subtracted;
	}

	public static Set union(Set s1, Set s2) {
		Set unioned = s1;
		for(int i = 0; i < s2.size(); i++) {
			if(!unioned.contains(s2.array.get(i))) {
				unioned.add(s2.array.get(i));
			}
		}
		return unioned;
	}
}