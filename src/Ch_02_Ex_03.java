import java.util.Scanner;

public class Ch_02_Ex_03
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static int F(int k)
    {
        int out;

        if (k > 2)
        {
            out = F(k - 1) + F(k - 2);
        }
        else
        {
            out = 1;
        }

        return out;
    }

    public static void main(String[] args)
    {
        int out;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter how many numbers in Fibonacci Sequence you want: ");

        int x = reader.nextInt();

        for (int k = 1; k <= x; k++)
        {

         if (k > 2)
         {
             out = F(k -1) + F(k-2);
         }
         else
         {
             out = 1;
         }

         System.out.println(out);

        }
    }
}

