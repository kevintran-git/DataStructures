package linear.recursion;

public class Recursion {
    public static void main(String... args) {
        //System.out.println(cdSum(5));
        //countDown(5);
        //displayArrayIt(new Integer[]{1, 2, 3, 3, 7, 9, 9, 9}, 3, 6);
        //displayArray2(new Integer[]{1, 2, 3, 4, 5, 6, 7, 9}, 0, 3);

    }

    public static int cdSum(int n) {
        if (n > 1) return n + cdSum(n - 1);
        return 1;
    }

    public static void countDown(int n) {
        System.out.println(n);
        if (n > 1) countDown(n-1);
    }


    public static void displayArrayIt(Object[] array, int first, int last){
        for(int i = first; i <= last; i++) System.out.println(array[i]);
    }

    public static void displayArray(Object[] array, int first, int last){
        System.out.println(array[first]);
        if(first < last) displayArray(array, first + 1, last);
    }

    public static void displayArray2(Object[] array, int first, int last){
        if(first < last) displayArray(array, first, last - 1);
        System.out.println(array[last]);
    }
}
