import java.util.Scanner;

public class Ch_04_Ex_05
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void printRange (int a, int b)
    {

        System.out.print("[");

        if (a > b)
        {
            for (int i = a; i >= b; i--)
            {
                if (a == i)
                {
                    System.out.print(i);
                }
                else
                {
                    System.out.print(", " + i);
                }
            }
        }
        else if (b > a)
        {
            for (int i = a; i <= b; i++)
            {
                if (a == i)
                {
                    System.out.print(i);
                }
                else
                {
                    System.out.print(", " + i);
                }
            }
        }
        else if (b == a)
        {
            System.out.print(a);
        }

        System.out.print("]");

    }

    public static void main(String[] args)
    {
        int a;
        int b;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter int a: ");
        a = reader.nextInt();

        System.out.print("Enter int b: ");
        b = reader.nextInt();

        printRange(a, b);

    }
}
