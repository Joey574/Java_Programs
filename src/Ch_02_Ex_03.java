import java.util.LinkedList;
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

    public static void main(String[] args)
    {
        int out;
        LinkedList <Integer> F = new LinkedList<Integer>();

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter how many numbers in Fibonacci Sequence you want: ");

        int x = reader.nextInt();

        for (int k = 1; k <= x; k++)
        {

         if (k > 2)
         {
             out = F.get(k - 2) + F.get(k - 3);
         }
         else
         {
             out = 1;
         }

         F.add(out);

         System.out.println(out);

        }
    }
}

