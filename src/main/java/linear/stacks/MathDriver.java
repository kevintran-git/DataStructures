package linear.stacks;


public class MathDriver {
    private static int preced(char operator) {
        if (operator == '+' || operator == '-') return 1;
        if (operator == '*' || operator == '/') return 2;
        if (operator == '^') return 3;
        return 0;
    }

    /**
     * Converts an infix expression to postfix expression.
     *
     * @param infix Infix expression. Valid operators are "+-*()^/", everything else will be treated as an operand.
     * @return A postfix expression logically equivalent to the infix expression.
     */
    public static String toPostfix(String infix) {
        StackInterface<Character> stack = new VectorStack<>();
        stack.push('#'); //Extra char to avoid underflow
        StringBuilder output = new StringBuilder();
        for (char c : infix.toCharArray()) {
            if (c == ' ') continue; //Ignore empty
            if ("+-*/^()".indexOf(c) == -1) output.append(c); //Outputs operand automatically.
            else if (c == '(' || c == '^')
                stack.push(c); //Special case to always push the highest precedence operators, exponentials and parentheses.
            else if (c == ')') { //If we find a close parentheses, pop the stack until the matching open parentheses is found.
                while (stack.peek() != '#' && stack.peek() != '(') output.append(stack.pop());
                stack.pop();//remove the open parentheses in the stack
            } else {
                if (preced(c) > preced(stack.peek())) stack.push(c);
                else {
                    while (stack.peek() != '#' && preced(c) <= preced((stack.peek()))) output.append(stack.pop());
                    stack.push(c);
                }
            }
        }
        while (stack.peek() != '#')
            output.append(stack.pop()); //Once you don't have any more characters in the infix expression, pop all the stored operators to the output.
        return output.toString();
    }

    /**
     * Converts an infix expression to postfix expression.
     *
     * @param postfix Post expression. Valid operators are "+-*^/", Letters A-Z and a-z will be treated as operands, as well as digits 0-9.
     * @param values A list of variable values that will replace the letter operands in alphabetical value. The first number will be a, second will be b, and so fourth.
     * @return A double equivalent to the evaluated expression.
     */
    public static double evaluatePostfix(String postfix, double... values) {
        StackInterface<Double> vals = new LinkedStack<>();
        for (char c : postfix.toCharArray())
            switch (c) {
                case '+':
                    vals.push(vals.pop() + vals.pop()); //Reads the next two operands and performs the requested operation in reverse order.
                    break;
                case '-':
                    vals.push(-vals.pop() + vals.pop()); //Second gets subtracted from the first.
                    break;
                case '*':
                    vals.push(vals.pop() * vals.pop());
                    break;
                case '/':
                    vals.push((1.0 / vals.pop()) * vals.pop());
                    break;
                case '^':
                    double b = vals.pop(), a = vals.pop();
                    vals.push(Math.pow(a, b));
                    break;
                default:
                    if (Character.isDigit(c)) {
                        vals.push((double) Character.digit(c, 10)); //If it's a digit then just use it.
                        break;
                    }
                    try {
                        vals.push(values["abcdefghijklmnopqrstuvwxyz".indexOf(Character.toLowerCase(c))]); //Otherwise, substitute with a corresponding variable.
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new IllegalArgumentException("Make sure all your variables are a-z and defined accordingly in alphabetical order.");
                    }
            }
        return vals.peek();
    }

    /**
     * Converts an infix expression to postfix expression.
     *
     * @param infix Infix expression. Valid operators are "+-*^()/", Letters A-Z and a-z will be treated as operands, as well as digits 0-9.
     * @param values A list of variable values that will replace the letter operands in alphabetical value. The first number will be a, second will be b, and so fourth.
     * @return A double equivalent to the evaluated expression.
     */
    public static double evaluateInfix(String infix, double... values) {
        return evaluatePostfix(toPostfix(infix), values);
    }
}
