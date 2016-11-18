//
//import java.util.ArrayList;
//public class NewVersion1 {
//
//	/*
//	 * 
//	 * In[B]: set of variables live at the point immediately before block B
//	 * Out[B]: set of variables live at the point immediately after block B
//	 * Def[B]: set of variables definitely assigned values in B prior to any use of that variable in B
//	 * Use[B]: set of variables whose values may be used in B prior to any definition of the variable
//	 * Live variable analysis: variable x is live at point p if  the value of x at p could be used along some path in the flow graph starting at p; otherwise x is dead at p.
//	 * 
//	 * 
//	 */
//
//	public static void main(String[] args) {
//
//		int[][] block = new int[7][7];
//		
//		ArrayList<Integer> in1 = new ArrayList<Integer>();
//		ArrayList<Integer> in2 = new ArrayList<Integer>();
//		ArrayList<Integer> in3 = new ArrayList<Integer>();
//		ArrayList<Integer> in4 = new ArrayList<Integer>();
//		ArrayList<Integer> in5 = new ArrayList<Integer>();
//		ArrayList<Integer> in6 = new ArrayList<Integer>();
//
//		ArrayList<Integer> in = new ArrayList<Integer>(); 
//		// A flow graph with def and use computed for each block as well as successor information of a block.
//		ArrayList<Integer> out1 = new ArrayList<Integer>(); 
//		ArrayList<Integer> out2 = new ArrayList<Integer>(); 
//		ArrayList<Integer> out3 = new ArrayList<Integer>(); 
//		ArrayList<Integer> out4 = new ArrayList<Integer>(); 
//		ArrayList<Integer> out5 = new ArrayList<Integer>(); 
//		ArrayList<Integer> out6 = new ArrayList<Integer>(); 
//		// out[B], the set of variables live on exit from each block B of the flow graph.
//
//		ArrayList<Integer> use1 = new ArrayList<Integer>(); 
//		ArrayList<Integer> use2 = new ArrayList<Integer>(); 
//		ArrayList<Integer> use3 = new ArrayList<Integer>(); 
//		ArrayList<Integer> use4 = new ArrayList<Integer>(); 
//		ArrayList<Integer> use5 = new ArrayList<Integer>(); 
//		ArrayList<Integer> use6 = new ArrayList<Integer>(); 
//
//		ArrayList<Integer> def1 = new ArrayList<Integer>(); 
//		ArrayList<Integer> def2 = new ArrayList<Integer>(); 
//		ArrayList<Integer> def3 = new ArrayList<Integer>(); 
//		ArrayList<Integer> def4 = new ArrayList<Integer>(); 
//		ArrayList<Integer> def5 = new ArrayList<Integer>(); 
//		ArrayList<Integer> def6 = new ArrayList<Integer>(); 
//
//		ArrayList<Integer> old = new ArrayList<Integer>(); 
//
//		in.add(0);
//		in1.add(0);
//		in2.add(0);
//		in3.add(0);
//		in4.add(0);
//		in5.add(0);
//		in6.add(0);
//
//		boolean change = true;
//		//			while(change) {
//		change = false;
//		old = in1;
//		out1 = union(out1, in2);
//
//		use1.add(0);
//		def1.add(1);
//		def1.add(2);
//
//		in1 = union(use1, (subtract(out1, def1)));
//		//				if(in1 != old) {
//		//					change = true;
//		//				}
//		//			}
//		System.out.println("in1: " + in1);
//		System.out.println("out1: " + out1);
//		System.out.println("use1: " + use1);
//		System.out.println("def1: " + def1);
//
//
//	}
//
//	public static ArrayList<Integer> union(ArrayList<Integer> outb, ArrayList<Integer> inp) {
//		for(int i = 0; i < inp.size(); i++) {
//			if(!outb.contains(inp.get(i))) {
//				outb.add(inp.get(i));
//			}
//		}
//		return outb;
//	}
//	public static ArrayList<Integer> subtract(ArrayList<Integer> outb, ArrayList<Integer> defb) {
//		for(int i = 0; i < defb.size(); i++) {
//			if(outb.contains(defb.get(i))) {
//				outb.remove(defb.get(i));
//			}
//		}
//		return outb;
//	}
//
//
//}
//}
//
//
//
//
//old = in1;
//out1 = union(out1, in2);
//
//
//in1 = union(use1, (subtract(out1, def1)));
//if(in1 != old) {
//	change = true;
////	continue;
//}
//System.out.println("1");
//
//System.out.println("in1: " + in1);
//System.out.println("out1: " + out1);
//System.out.println("in2: " + in2);
//System.out.println("out2: " + out2);
//System.out.println("in3: " + in3);
//System.out.println("out3: " + out3);
//System.out.println("in4: " + in4);
//System.out.println("out4: " + out4);
//System.out.println("in5: " + in5);
//System.out.println("out5: " + out5);
//System.out.println("in6: " + in6);
//System.out.println("out6: " + out6);
//
////Block 2
//change = false;
//old = in2;
//out2 = union(union(out2, in3), in5);
//
//in2 = union(use2, (subtract(out2, def2)));
//if(in2 != old) {
//	change = true;
//}
//System.out.println("1");
//
//System.out.println("in1: " + in1);
//System.out.println("out1: " + out1);
//System.out.println("in2: " + in2);
//System.out.println("out2: " + out2);
//System.out.println("in3: " + in3);
//System.out.println("out3: " + out3);
//System.out.println("in4: " + in4);
//System.out.println("out4: " + out4);
//System.out.println("in5: " + in5);
//System.out.println("out5: " + out5);
//System.out.println("in6: " + in6);
//System.out.println("out6: " + out6);
//
//
//
////Block 3
//change = false;
//old = in3;
//out3 = union(union(out3, in4), in5);
//
//in3 = union(use3, (subtract(out3, def3)));
//if(in3 != old) {
//	change = true;
//}
//System.out.println("3");
//
//System.out.println("in1: " + in1);
//System.out.println("out1: " + out1);
//System.out.println("in2: " + in2);
//System.out.println("out2: " + out2);
//System.out.println("in3: " + in3);
//System.out.println("out3: " + out3);
//System.out.println("in4: " + in4);
//System.out.println("out4: " + out4);
//System.out.println("in5: " + in5);
//System.out.println("out5: " + out5);
//System.out.println("in6: " + in6);
//System.out.println("out6: " + out6);
//
////Block 4
//change = false;
//old = in4;
//out4 = union(out4, in3);
//
//in4 = union(use4, (subtract(out4, def4)));
//if(in4 != old) {
//	change = true;
//}
//System.out.println("4");
//
//System.out.println("in1: " + in1);
//System.out.println("out1: " + out1);
//System.out.println("in2: " + in2);
//System.out.println("out2: " + out2);
//System.out.println("in3: " + in3);
//System.out.println("out3: " + out3);
//System.out.println("in4: " + in4);
//System.out.println("out4: " + out4);
//System.out.println("in5: " + in5);
//System.out.println("out5: " + out5);
//System.out.println("in6: " + in6);
//System.out.println("out6: " + out6);
//
////Block 5
//change = false;
//old = in5;
//out5 = union(union(out5, in2), in6);
//
//in5 = union(use5, (subtract(out5,def5)));
//if(in5 != old) {
//	change = true;
//}
//System.out.println("5");
//
//System.out.println("in1: " + in1);
//System.out.println("out1: " + out1);
//System.out.println("in2: " + in2);
//System.out.println("out2: " + out2);
//System.out.println("in3: " + in3);
//System.out.println("out3: " + out3);
//System.out.println("in4: " + in4);
//System.out.println("out4: " + out4);
//System.out.println("in5: " + in5);
//System.out.println("out5: " + out5);
//System.out.println("in6: " + in6);
//System.out.println("out6: " + out6);
//
////Block 6
//change = false;
//old = in6;
//out6 = out6;
//
//in6 = union(use6, (subtract(out6,def6)));
//if(in6 != old) {
//	change = true;
//}
//System.out.println("6");
//
//System.out.println("in1: " + in1);
//System.out.println("out1: " + out1);
//System.out.println("in2: " + in2);
//System.out.println("out2: " + out2);
//System.out.println("in3: " + in3);
//System.out.println("out3: " + out3);
//System.out.println("in4: " + in4);
//System.out.println("out4: " + out4);
//System.out.println("in5: " + in5);
//System.out.println("out5: " + out5);
//System.out.println("in6: " + in6);
//System.out.println("out6: " + out6);
//
