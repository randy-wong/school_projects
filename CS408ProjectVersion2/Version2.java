import javax.jws.soap.SOAPBinding.Use;


public class Version2 {
	public static void main(String[] args) {

		Set[] eachset = new Set[6];
		Set in1 = new Set(); eachset[0] = in1; // In set is empty
		Set in2 = new Set(); eachset[1] = in2;
		Set in3 = new Set(); eachset[2] = in3;
		Set in4 = new Set(); eachset[3] = in4;
		Set in5 = new Set(); eachset[4] = in5;
		Set in6 = new Set(); eachset[5] = in6;

		Set[] outset = new Set[6];
		Set out1 = new Set(); outset[0] = out1; // Out Set
		Set out2 = new Set(); outset[1] = out2;
		Set out3 = new Set(); outset[2] = out3;
		Set out4 = new Set(); outset[3] = out4;
		Set out5 = new Set(); outset[4] = out5;
		Set out6 = new Set(); outset[5] = out6;

		Set[] useset = new Set[6];
		Set use1 = new Set(); useset[0] = use1; // Use set
		Set use2 = new Set(); useset[1] = use2;
		Set use3 = new Set(); useset[2] = use3;
		Set use4 = new Set(); useset[3] = use4;
		Set use5 = new Set(); useset[4] = use5;
		Set use6 = new Set(); useset[5] = use6;

		Set[] defset = new Set[6];
		Set def1 = new Set(); defset[0] = def1; // Def set
		Set def2 = new Set(); defset[1] = def2;
		Set def3 = new Set(); defset[2] = def3;
		Set def4 = new Set(); defset[3] = def4;
		Set def5 = new Set(); defset[4] = def5;
		Set def6 = new Set(); defset[5] = def6;

		Set old = new Set(); // Used as a temp

		// Initialize each block;

		def1.add(1); def1.add(2); // Block 1
		use2.add(1); use2.add(2);
		def2.add(3); def2.add(4);
		use3.add(2); use3.add(4);
		use4.add(1); use4.add(2); use4.add(5);
		def4.add(4);
		use5.add(1); use5.add(2); use5.add(3);
		def5.add(5);
		use6.add(2); use6.add(4);
		def6.add(1);


		boolean change = true;

		while(change) {
			change = false;
			for(int i = 0; i < 6; i++) {

				//				System.out.println(in1);

				//				System.out.print("Before in B"  + (i + 1) + " "); eachset[i].display();
				//				System.out.print("Before out B" + (i + 1) + " "); outset[i].display();
				System.out.print("Before in B" + (i + 1) + " "); useset[i].display();
				System.out.print("Before out B" + (i + 1) + " "); defset[i].display();

				old = eachset[i];
				switch (i) {
				case 1:
					out1 = Set.union(out1, in2);
					break;
				case 2:
					out2 = Set.union(Set.union(out2, in3), in5);
					break;
				case 3:
					out3 = Set.union(Set.union(out3, in4), in5);
					break;
				case 4:
					out4 = Set.union(out4, in3);
					break;
				case 5:
					out5 = Set.union(Set.union(out5, in2), in6);
					break;
				case 6:
					break;
				default:
					break;
				}
				useset[i] = Set.union(outset[i], Set.subtract(outset[i], defset[i]));
				if(!Set.equals(useset[i],old)) {
					change = true;
				}
				//				System.out.print("After in B" + (i + 1) + " "); eachset[i].display();
				//				System.out.print("After out B" + (i + 1) + " "); outset[i].display();
				System.out.print("After in B" + (i + 1) + " "); useset[i].display();
				System.out.print("After out B" + (i + 1) + " "); defset[i].display();

			}	
		}
		//		change = false;}
	}
}
