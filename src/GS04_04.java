import java.util.Scanner;

public class GS04_04
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void divide(String s, int x)
    {
        String temp;
        int t;
        boolean done = false;

        for (int i = 0; !done; i++)
        {
            if (x * (i + 1) > s.length())
            {
                done = true;
                t = s.length();
            }
            else
            {
                t = x * (i + 1);
            }

          temp = s.substring(x * i, t);
          System.out.println(temp);
        }

    }

    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        String s;
        int x;

        System.out.print("Enter string to split: ");
        s = reader.nextLine();

        System.out.print("Enter # to split by: ");
        x = reader.nextInt();

        divide(s, x);

    }
}
