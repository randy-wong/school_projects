public class Block
{
	final  int LIMIT = 128;
	int[] set = new int[LIMIT];
//****************************************************************************************
//	Default Constructor initialize an empty set of an array
//****************************************************************************************
	IntSet gen, kill, pred, in, out, oldout;
	
	public Block(){};
	
	public void kill(Block B, int[] s) {
		B.kill = new IntSet(s);	
	}
	
	public void gen(Block B, int[] s) {
		B.gen = new IntSet(s);	
	}
	
	public void pred(Block B, int[] s) {
		B.pred = new IntSet(s);	
	}
		
	public void create(Block B) {
		B.in = new IntSet();
		B.out = B.gen;
	}	
	public IntSet oldOut(Block B) {
		B.oldout = B.out;
		return B.oldout;
	}
	
	public IntSet outP( Block B) {
		B.pred = B.out.Union(B.out);
		return B.pred;
	}
	
	public IntSet outP( Block B, Block B1) {
		B.pred = B1.out.Union(B1.out);
		return B.pred;
	}

	public IntSet outP( Block B,Block B1, Block B2, Block B3) {
		B.pred = B1.out.Union(B2.out.Union(B3.out));
		return B.pred;
	}
}
