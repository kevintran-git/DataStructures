package stacks;

public class StackDemo {
    public static void main(String...args){
        System.out.println(Postfix.toPost("(a-b*c)/(d*e*f+g)"));
    }
}
