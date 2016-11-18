/*  
Program: CS 408 Programming Project
Author: Randy Wong
Class: CS 408
Date: 02/18/2015
Description: In the second version, you implement a set ADT. 
 */

public class Algorithm
{
	public static void main(String[] args)
	{
		Block B1, B2, B3, B4, B5, B6;

		B1 = new Block();
		B2 = new Block();
		B3 = new Block();
		B4 = new Block();
		B5 = new Block();
		B6 = new Block();
		
		int gen1[] = {1,2,3};
		int kill1[] = {4,5,6,7};
		int pred1[] = {0};	 
//		int gen1[] = {1, 2};
//		int kill1[] = {0};
//		int pred1[] = {2};	 

		B1.kill(B1,kill1);		
		B1.gen(B1,gen1);
		B1.pred(B1,pred1);

		int gen2[] = {4,5};
		int kill2[] = {1,2,7}; 
		int pred2[] = {1,3,4};	 
//		int gen2[] = {3, 4};
//		int kill2[] = {1, 2};
//		int pred2[] = {3, 5};	 

		B2.kill(B2,kill2);		
		B2.gen(B2,gen2);
		B2.pred(B2,pred2);

		int gen3[] = {6};
		int kill3[] = {3};
		int pred3[] = {2};
//		int gen3[] = {0};
//		int kill3[] = {2, 4};
//		int pred3[] = {4, 5};

		B3.kill(B3,kill3);		
		B3.gen(B3,gen3);
		B3.pred(B3,pred3);

		int gen4[] = {7};
		int kill4[] = {1,4};	
		int pred4[] = {2};
//		int gen4[] = {4};
//		int kill4[] = {1, 2, 5};
//		int pred4[] = {3};
		
		B4.kill(B4,kill4);		
		B4.gen(B4,gen4);
		B4.pred(B4,pred4);
//
//		int gen5[] = {5};
//		int kill5[] = {1, 2, 3};
//		int pred5[] = {2, 6};
//		
//		B5.kill(B5,kill5);		
//		B5.gen(B5,gen5);
//		B5.pred(B5,pred5);
//		
//		int gen6[] = {1};
//		int kill6[] = {2, 4};
//		int pred6[] = {0};
//		
//		B6.kill(B6,kill6);		
//		B6.gen(B6,gen6);
//		B6.pred(B6,pred6);
		
		B1.create(B1);
		B2.create(B2);
		B3.create(B3);
		B4.create(B4);
//		B5.create(B5);
//		B6.create(B6);

		boolean change = true;
		while(change) {
			change = false;
			System.out.println( "\n\n\tBlock B1    ");
			B1.in = B1.outP(B1);	 		
			generate(B1,B1.in, change,pred1);		 

			System.out.println( "\n\n\tBlock B2    ");
			B2.in = B2.outP(B2,B1,B3,B4);
			generate(B2,B2.in, change,pred2);	

			System.out.println( "\n\n\tBlock B3    ");
			B3.in = B3.outP(B3,B2);
			generate(B3,B3.in, change,pred3);	

			System.out.println( "\n\n\tBlock B4    ");
			B4.in = B4.outP(B4,B2);
			generate(B4,B4.in, change,pred4);

//			System.out.println( "\n\n\tBlock B5    ");
//			B4.in = B4.outP(B5,B2);
//			generate(B5,B5.in, change,pred5);
//
//			System.out.println( "\n\n\tBlock B6    ");
//			B4.in = B6.outP(B6,B2);
//			generate(B6,B6.in, change,pred6);

		}
	}

	private static void generate(Block B, IntSet in,boolean change, int[] pred)
	{

		System.out.println( " gen:\t " + B.gen);

		System.out.println(" kill:\t " +B.kill);

		System.out.print(" succ:\t  " );
		for(int i=0; i<pred.length; i++)
			System.out.print("B"+pred[i] + " ");

		System.out.println("\n in:\t " + B.in);

		B.oldout = B.oldOut(B);
		System.out.println(" old:\t " + B.oldout);

		B.out = B.gen.Union(B.in.difference(B.kill));

		System.out.print(" out:\t " + B.out);

		if(!B.oldout.equals(B.out))	{
			change = true;		
		}
	}	
}