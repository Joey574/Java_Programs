import java.util.Scanner;

public class GS11_05
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */


    public static int fibonacciHelper(int n, int f, int s) {
        if (f + s == 0) {
            n = 1;
        } else {
            n = f + s;
        }
        return n;
    }

    public static int fibonacci2 (int n) {
        if (n > 1) {
            n = fibonacciHelper(n, fibonacci2(n - 1), fibonacci2(n - 2));
        }
        return n;
    }

    public static int fibonacci (int n) {
        if (n > 1) {
           n = fibonacciHelper(n, fibonacci(n - 1), fibonacci2(n - 2));
        }
        System.out.print(n + " ");
        return n;
    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();

        if (x > 0) {
            fibonacci(x);
        }
        else {
            throw new IllegalArgumentException("Value must be greater than 0");
        }
    }
}
