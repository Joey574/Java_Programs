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

    static int fibonacciHelper (int f, int s) {

    }

    static int fibonacci (int n) {
        int x;

        if (n > 0) {
            x = fibonacci(n - 1) + fibonacci(n - 2);

        }
    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter input: ");
        int x = r.nextInt();

        if (x > 0) {
            x = fibonacci(x);
        }
        else {
            throw new IllegalArgumentException("Value must be greater than 0");
        }

        System.out.print(x);
    }
}
