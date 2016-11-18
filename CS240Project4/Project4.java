import java.util.Scanner;
import java.util.Stack;
	
	public static void main(String[] args) throws Exception
	{
		Stack <Character> postOperators = new Stack<Character>();
		Stack <String> preOperands = new Stack<String>();
		Stack <Character> preOperators = new Stack<Character>();

		Scanner kb = new Scanner(System.in);	// Scanner for user interface to enter an equation.

		String equation = kb.nextLine();

		while (!equation.equals(""))	// Program should keep asking the user to enter the infix expression, until the user hits ENTER. 
		{
			String output = "";
			for(int i = 0; i < equation.length(); i++) // Infix to Postfix.
			{
				char c = equation.charAt(i);

				if(c == ' ')
				{
					continue;
				}
				else if(c != '(' && c != ')' && c != '+' && c != '-' && c != '*' && c != '/')	
					// Variables (such as a, b, and c) are directly copied to the output.
				{
					output += c + "";
				}
				else if (c == '(')
					// Left parentheses are always pushed onto a stack.
				{
					postOperators.push(c);
				}
				else if(c == ')')
					// When a right parenthesis is encountered, the symbol on the top of the stack is popped off the stack and copied to the output. 
				{
					try
					{
						char o = postOperators.pop();
						while(o != '(')	// This process repeats until the top of the stack is a left parenthesis. 
						{
							output += o + "";
							o = postOperators.pop();	// When that occurs, both parentheses are discarded.
						}
					}
					catch(Exception e)	// Checks for mismatched parens.
					{
						System.out.println("Mismatched parens for Postfix.");
						return;
					}
				}
				else if(postOperators.empty() || 
						(((c == '*') || (c == '/')) && (postOperators.peek() == '+' || postOperators.peek() == '-' || postOperators.peek() == '('))
						|| (((c == '+') || (c == '-')) && (postOperators.peek() == '(')))
					// If the symbol been scanned has a higher precedence than the symbol on the top of the stack, the symbol being scanned is pushed onto the stack.
				{
					postOperators.push(c);
				}
				else if(
						(((c == '+') || (c == '-')) && (postOperators.peek() == '*' || postOperators.peek() == '/' || postOperators.peek() == '+' || postOperators.peek() == '-')
								|| ((c == '*') || (c == '/')) && (postOperators.peek() == '*' || postOperators.peek() == '/'))
						)
					// If the symbol been scanned has a lower or the same precedence than the symbol at the top of the stack, the symbol at the top of the stack is popped off to the output. 
					// The symbol that has been scanned will be compared with the new top element on the stack, and the process continues. 
				{
					if(postOperators.peek() != '(')
						output += postOperators.pop() + "";		// Push the operator that it is scanning inside the stack.
					postOperators.push(c);
				}
			}
			while(postOperators.empty() == false)
			{
				if(postOperators.peek() == '(')
				{
					System.out.println("Mismatched prens for Postfix");
					return;
				}
				output += postOperators.pop() + "";
			}
			// When the input is empty, the stack is popped to the output until the stack is empty. Then the algorithm terminates.
			System.out.println("Infix to Postfix:	" + output);


			String op, RightOperand, LeftOperand;				
			for(int i = 0; i < equation.length(); i++) // Infix to Prefix.
			{
				char c = equation.charAt(i);
				if(c == ' ')	// Ignores spaces.
				{
					continue;
				}
				else if(c != '(' && c != ')' && c != '+' && c != '-' && c != '*' && c != '/')
					// Checks if it is an operand.
				{
					preOperands.push(c + "");
				}
				else if(c == '(') 
				{
					// Left parentheses are always pushed onto the operators stack.
					preOperators.push(c);
				}
				else if(c == ')')
					// When a right parenthesis is encountered, the operator in the operator stack is popped off and saved to op, 
					// the expression on the top of the operand stack is popped off  and saved to RightOperand, 
					// another expression on the top of the operand stack is popped off and saved to LeftOperand. 
					// A new expression is formed by "op LeftOperand RightOperand" and is pushed back to the operand stack. 
					// This process repeats until the top of the stack is a left parenthesis. When that occurs, both parentheses are discarded.
				{
					try
					{
						if(preOperators.peek() != '(')
						{
							op = preOperators.pop() + "";
							RightOperand = preOperands.pop() + "";
							LeftOperand = preOperands.pop() + "";
							preOperands.push(op + LeftOperand + RightOperand);
						}
						if(preOperators.peek() =='(')
						{
							preOperators.pop();
						}
					}
					catch(Exception e)
					{
						System.out.println("Mismatched parens for Prefix.");
						return;
					}

				}
				else if(preOperators.empty() || 
						(((c == '*') || (c == '/')) && (preOperators.peek() == '+' || preOperators.peek() == '-' || preOperators.peek() == '('))
						|| (((c == '+') || (c == '-')) && (preOperators.peek() == '(')))
					// If the symbol been scanned has a higher precedence than the symbol on the top of the stack, the symbol being scanned is pushed onto the stack.
				{
					preOperators.push(c);
				}

				else if((((c == '+') || (c == '-')) && (preOperators.peek() == '*' || preOperators.peek() == '/' 
						|| preOperators.peek() == '+' || preOperators.peek() == '-' ))
						|| (((c == '*') || (c == '/')) && (preOperators.peek() == '*' || preOperators.peek() == '/')))
					// If the symbol been scanned has a lower or the same precedence than the symbol on the top of the operator stack, the symbol on the top of the stack is popped off and saved to op. 
					// Popped two expressions from the operands stack and saved them to RightOperand and LeftOperator respectively. 
					// They form a new expression "op LeftOperand RightOperand" and is pushed to the operand stack. 
					// The symbol been scanned will be compared with the new top element on the operator stack, and the process continue.
				{
					try
					{
						if(preOperators.peek() != '(')
						{
							op = preOperators.pop() + "";
							RightOperand = preOperands.pop() + "";
							LeftOperand = preOperands.pop() + "";
							preOperands.push(op + LeftOperand + RightOperand);
							preOperators.push(c);
						}
						if(preOperators.empty())
						{
							System.out.println("error");
							preOperators.push(c);
						}
					}
					catch(Exception e)
					{
						System.out.println("Error, number of operators is incorrect.");
						return;
					}
				}

			}
			while(preOperators.empty() == false)
				// If the operator stack is not empty, continue to pop operator and operand stacks building prefix expression until the operator stack is empty.
			{
				try
				{
					op = preOperators.pop() + "";
					RightOperand = preOperands.pop() + "";
					LeftOperand = preOperands.pop() + "";
					preOperands.push(op + LeftOperand + RightOperand);
				}
				catch(Exception e)
				{
					System.out.println("Mismatched parens for Prefix.");
					return;
				}
			}
			System.out.println("Infix to Prefix:	" + preOperands.peek());
			equation = kb.nextLine();
		}
	}
}
