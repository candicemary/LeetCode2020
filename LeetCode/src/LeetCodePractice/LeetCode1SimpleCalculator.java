package LeetCodePractice;

import java.util.Stack;

/**
 * @author Candi Implement a basic calculator to evaluate a simple expression
 *         string. The expression string may contain open ( and closing
 *         parentheses ), the plus + or minus sign -, non-negative integers and
 *         empty spaces . Example 1: Input: "1 + 1 Output: 2 Example 2: Input: "
 *         2-1 + 2 " Output: 3 Example 3: Input: "(1+(4+5+2)-3)+(6+8)" Output:
 *         23 Note: You may assume that the given expression is always valid. Do
 *         not use the eval built-in library function.
 * 
 * 
 * 
 *         kkkklklkklklkklklk
 *
 */

class Solution {

	public int evaluateExpr(Stack<Object> stack) {

		int res = 0;

		if (!stack.empty()) {
			res = (int) stack.pop();
		}

		// Evaluate the expression till we get corresponding ')'
		while (!stack.empty() && !((char) stack.peek() == ')')) {

			char sign = (char) stack.pop();

			if (sign == '+') {
				res += (int) stack.pop();
			} else {
				res -= (int) stack.pop();
			}
		}
		return res;
	}

	public int calculate(String s) {

		int operand = 0;
		int n = 0;
		Stack<Object> stack = new Stack<Object>();

		for (int i = s.length() - 1; i >= 0; i--) {

			char ch = s.charAt(i);

			if (Character.isDigit(ch)) {

				// Forming the operand - in reverse order.
				operand = (int) Math.pow(10, n) * (int) (ch - '0') + operand;
				n += 1;

			} else if (ch != ' ') {
				if (n != 0) {

					// Save the operand on the stack
					// As we encounter some non-digit.
					stack.push(operand);
					n = 0;
					operand = 0;

				}
				if (ch == '(') {

					int res = evaluateExpr(stack);
					stack.pop();

					// Append the evaluated result to the stack.
					// This result could be of a sub-expression within the parenthesis.
					stack.push(res);

				} else {
					// For other non-digits just push onto the stack.
					stack.push(ch);
				}
			}
		}

		// Push the last operand to stack, if any.
		if (n != 0) {
			stack.push(operand);
		}

		// Evaluate any left overs in the stack.
		return evaluateExpr(stack);
	}
}