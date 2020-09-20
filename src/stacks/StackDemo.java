package stacks;

public class StackDemo {
    public static void main(String...args){
        System.out.println(Postfix.toPost("A + B * C - D"));


        /**
         * ABC*+D+
         * AB+CD+*
         * AB*CD*+
         * AB+C+D+
         * ab+
         * ab+c*
         * abc*+
         */
    }
}
