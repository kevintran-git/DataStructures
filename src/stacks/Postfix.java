package stacks;

public class Postfix {
    private static int preced(char operator) {
        if (operator == '+' || operator == '-') return 1;
        if (operator == '*' || operator == '/') return 2;
        if (operator == '^') return 3;
        return 0;
    }

    public static String toPost(String inf) {
        StackInterface<Character> stack = new LinkedStack<>();
        stack.push('#'); //Extra char to avoid underflow
        StringBuilder output = new StringBuilder();
        for (char c : inf.toCharArray()) {
            if (c == ' ') continue; //Ignore empty
            if ("+-*/^()".indexOf(c) == -1) output.append(c); //Outputs operand automatically.
            else if (c == '(' || c == '^') stack.push(c); //Special case to always push the highest precedence operators, exponentials and parentheses.
            else if (c == ')') { //If we find a close parentheses, pop the stack until the matching open parentheses is found.
                while (stack.peek() != '#' && stack.peek() != '(') output.append(stack.pop());
                stack.pop();//remove the open parentheses in the stack
            } else {
                if (preced(c) > preced(stack.peek())) stack.push(c);
                else {
                    while (stack.peek() != '#' && preced(c) <= (stack.peek()))output.append(stack.pop());
                    stack.push(c);
                }
            }
        }
        while (stack.peek() != '#') output.append(stack.pop()); //Once you don't have any more characters in the infix expression, pop all the stored operators to the output.
        return output.toString();
    }
}
