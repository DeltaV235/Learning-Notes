package simple;

import java.util.Stack;

/**
 * 有效括号对
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid("[({}{})]"));
    }

    public boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : charArray) {
            if (c == '(' || c == '{' || c == '[')
                stack.push(c);
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty())
                    return false;
                Character pop = stack.pop();
                if (c == ')') {
                    if (pop != '(')
                        return false;
                } else if (c == '}') {
                    if (pop != '{')
                        return false;
                } else {
                    if (pop != '[')
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
