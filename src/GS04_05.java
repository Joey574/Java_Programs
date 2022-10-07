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

        int start;
        int end;


        for (int i = 0; i < x; i++)
        {
            if (2 * i * x > s.length())
            {
                end = s.length();
            }
            else
            {
                start = i * x;
                end = 2 * i * x;
            }

           temp = s.substring(start, end);

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
        s = reader.nextLine();

        System.out.print("Enter # of substrings: ");
        x = reader.nextInt();

        stairString(s, x);

    }
}
