
public class Tester {
	static int a = 5;
	static int b;
	public static void main(String[] args) {

		b = ((fun1() > 5) ? a : b) + fun1();
		System.out.println((b == 10 ? a : b) + fun1());
		
		int a=5,  b=3, c=30;
		boolean tag = true;  
		if ((a < b) || !tag && ( b / (2*a*b-c)> 0))
			System.out.println("true1");
	
		a=5; b=3; c=30; tag = false;
		if((a > b) || !tag && ( b / (2*a*b-c)>0))
			System.out.println("true2");
		

	}
	public static int fun1()
	{
		a = a + 5; int b = 10;
		return 3;
	}
}
