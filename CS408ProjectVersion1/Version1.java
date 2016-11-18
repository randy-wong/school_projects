import java.util.ArrayList;

/*
 * 
 * In[B]: set of variables live at the point immediately before block B
 * Out[B]: set of variables live at the point immediately after block B
 * Def[B]: set of va0iables definitely assigned values in B prior to any use of that variable in B
 * Use[B]: set of variables whose values may be used in B prior to any definition of the variable
 * Live variable analysis: variable x is live at point p if  the value of x at p could be used along some path in the flow graph starting at p; otherwise x is dead at p.
 * 
 * 
 */

public class Version1 {
	public static void main(String[] args) {
		
		System.out.println("Note that a correspondes to 1, 2:b, 3:c and so on and so forth. 0 refers to empty set.");

		ArrayList<Integer> fill = new ArrayList<Integer>(); 		
		ArrayList<ArrayList<Integer>> SetIn = new ArrayList<ArrayList<Integer>>();
		SetIn.add(fill);
		ArrayList<Integer> in1 = new ArrayList<Integer>(); SetIn.add(in1);
		ArrayList<Integer> in2 = new ArrayList<Integer>(); SetIn.add(in2);
		ArrayList<Integer> in3 = new ArrayList<Integer>(); SetIn.add(in3);
		ArrayList<Integer> in4 = new ArrayList<Integer>(); SetIn.add(in4);
		ArrayList<Integer> in5 = new ArrayList<Integer>(); SetIn.add(in5);
		ArrayList<Integer> in6 = new ArrayList<Integer>(); SetIn.add(in6);

		ArrayList<ArrayList<Integer>> SetOut = new ArrayList<ArrayList<Integer>>();
		SetOut.add(fill);
		// A flow graph with def and use computed for each block as well as successor information of a block.
		ArrayList<Integer> out1 = new ArrayList<Integer>(); SetOut.add(out1);
		ArrayList<Integer> out2 = new ArrayList<Integer>(); SetOut.add(out2);
		ArrayList<Integer> out3 = new ArrayList<Integer>(); SetOut.add(out3);
		ArrayList<Integer> out4 = new ArrayList<Integer>(); SetOut.add(out4);
		ArrayList<Integer> out5 = new ArrayList<Integer>(); SetOut.add(out5);
		ArrayList<Integer> out6 = new ArrayList<Integer>(); SetOut.add(out6);
		// out[B], the set of variables live on exit from each block B of the flow graph.

		ArrayList<ArrayList<Integer>> SetUse= new ArrayList<ArrayList<Integer>>();

		SetUse.add(fill);
		ArrayList<Integer> use1 = new ArrayList<Integer>(); SetUse.add(use1);
		ArrayList<Integer> use2 = new ArrayList<Integer>(); SetUse.add(use2);
		ArrayList<Integer> use3 = new ArrayList<Integer>(); SetUse.add(use3);
		ArrayList<Integer> use4 = new ArrayList<Integer>(); SetUse.add(use4);
		ArrayList<Integer> use5 = new ArrayList<Integer>(); SetUse.add(use5);
		ArrayList<Integer> use6 = new ArrayList<Integer>(); SetUse.add(use6);

		ArrayList<ArrayList<Integer>> SetDef = new ArrayList<ArrayList<Integer>>();

		SetDef.add(fill);
		ArrayList<Integer> def1 = new ArrayList<Integer>(); SetDef.add(def1);
		ArrayList<Integer> def2 = new ArrayList<Integer>(); SetDef.add(def2);
		ArrayList<Integer> def3 = new ArrayList<Integer>(); SetDef.add(def3);
		ArrayList<Integer> def4 = new ArrayList<Integer>(); SetDef.add(def4);
		ArrayList<Integer> def5 = new ArrayList<Integer>(); SetDef.add(def5);
		ArrayList<Integer> def6 = new ArrayList<Integer>(); SetDef.add(def6);

		ArrayList<Integer> old = new ArrayList<Integer>(); 

		in1.add(0);
		in2.add(0);
		in3.add(0);
		in4.add(0);
		in5.add(0);
		in6.add(0);

		use1.add(0);
		def1.add(1);
		def1.add(2);

		use2.add(1);
		use2.add(2);
		def2.add(3);
		def2.add(4);

		use3.add(2);
		use3.add(4);
		def3.add(0);

		use4.add(1);
		use4.add(2);
		use4.add(5);
		def4.add(4);
		
		use5.add(1);
		use5.add(2);
		use5.add(3);
		def5.add(4);
		
		use6.add(2);
		use6.add(4);
		def6.add(1);
		
		boolean change = true;
		while(change) {
			change = false;
			for(int i = 1; i < 7; i++)
			{
				System.out.println("In Before: B" + i + " " + SetIn.get(i).toString());
				System.out.println("Out Before: B" + i + " " + SetOut.get(i).toString());
				System.out.println("Use Before: B" + i + " " + SetUse.get(i).toString());
				System.out.println("Def Before: B" + i + " " + SetDef.get(i).toString());

				old = SetIn.get(i);
				switch (i) {
				case 1:
					out1 = union(out1, in2);
					break;
				case 2:
					out2 = union(union(out2, in3), in5);
					break;
				case 3:
					out3 = union(union(out3, in4), in5);
					break;
				case 4:
					out4 = union(out4, in3);
					break;
				case 5:
					out5 = union(union(out5, in2), in6);
					break;
				case 6:
					out6 = out6;
					break;
				default:
					break;
				}
				SetIn.set(i, union(SetUse.get(i), subtract(SetOut.get(i), SetDef.get(i))));
				if (SetIn.get(i) != old) {
					change = true;
				}

				System.out.println("In After: B" + i + " " + SetIn.get(i).toString());
				System.out.println("Out After: B" + i + " " + SetOut.get(i).toString());
				System.out.println("Use After: B" + i + " " + SetUse.get(i).toString());
				System.out.println("Def After: B" + i + " " + SetDef.get(i).toString());

			}
		}
	}

	public static ArrayList<Integer> union(ArrayList<Integer> outb, ArrayList<Integer> inp) {
		for(int i = 0; i < inp.size(); i++) {
			if(!outb.contains(inp.get(i))) {
				outb.add(inp.get(i));
			}
		}
		return outb;
	}
	public static ArrayList<Integer> subtract(ArrayList<Integer> outb, ArrayList<Integer> defb) {
		for(int i = 0; i < defb.size(); i++) {
			if(outb.contains(defb.get(i))) {
				outb.remove(defb.get(i));
			}
		}
		return outb;
	}
}