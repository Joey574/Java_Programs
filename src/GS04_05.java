import java.util.Scanner;

public class GS04_05
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void stairString(String s, int x)
    {
        String temp;

        for (int i = 0; i < x; i++)
        {
           temp = s.substring(i * x, 2 * i * x);

           for (int p = 0; p < i * x; p++)
           {
               System.out.print(" ");
           }

           System.out.println(temp);
        }
    }

    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);
        String s;
        int x;

        System.out.print("Enter String: ");

    }
}
