package stacks;

public class StackDemo {
    public static void main(String...args){
        double a = 2, b = 3, c= 4, d = 5;
        System.out.println(MathDriver.evaluateInfix("a+b", a,b,c,d));
        System.out.println(MathDriver.evaluateInfix("(a+b)*c", a,b,c,d));
        System.out.println(MathDriver.evaluateInfix("a/b+c-d", a,b,c,d));
        System.out.println(MathDriver.evaluateInfix("a^b^c", a,b,c,d));

        //(a-b*c)/(d*e*f+g)
    }
}
