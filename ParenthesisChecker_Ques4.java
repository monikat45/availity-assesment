Question:
4.Coding exercise: You are tasked to write a checker that validates the parentheses of a LISP code.  
Write a program (in Java or JavaScript) which takes in a string as an input and returns true 
if all the parentheses in the string are properly closed and nested


import java.util.Stack;

public class Braces {

	public static void main(String[] args) {
		String s = "()))";
		String res = isBalanced(s);
		System.out.print(res);
	}

	private static String isBalanced(String s) {
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			switch (c) {
			case '(':
				stack.push(c);
				break;

			case ')':
				if(stack.isEmpty() || stack.pop() != '(') {
				return "false";
				}
				break;
				
			}
		}
		return String.valueOf(stack.isEmpty());
	}

}