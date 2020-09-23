package stacks;

public class StackDemo {
    public static void main(String...args){
        System.out.println(Postfix.evaluateInfix("a+(b/c)", 4,12,2));
        //(a-b*c)/(d*e*f+g)
    }
}
