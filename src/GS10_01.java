import java.util.Scanner;

public class GS10_01
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static int fibonacciHelper(int n)
    {
        if (n > 1) {
            n = fibonacciHelper(n - 1) + fibonacciHelper(n - 2);
        }
        return n;
    }

    static int fibonacci (int n)
    {
        if (n > 1) {
            n = fibonacci(n - 1) + fibonacciHelper(n - 2);
        }
        System.out.print(n + ", ");
        return n;
    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter how many numbers in fibonacci sequence to display: ");
        int temp = r.nextInt();

        int t = fibonacci(temp);
    }
}
