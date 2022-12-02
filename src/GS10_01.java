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

    public static void fibonacci (int n, int f, int s)
    {
        if (n > 0)
        {
            if (f + s == 0)
            {
                System.out.print("1");
                fibonacci(n -1, 1, f);
            }
            else
            {
                System.out.print(", " + f + s);
                fibonacci(n -1, f + s, f);
            }

        }
        else
        {
            System.out.print("");
        }

    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        System.out.print("Enter how many numbers in fibonacci sequence to display: ");
        int temp = r.nextInt();

        fibonacci(temp, 0, 0);
    }
}
