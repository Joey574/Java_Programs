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

        int start = 0;
        int end;

        for (int i = 0; i < x; i++)
        {
            start = i * (s.length() / x);
            end = (i + 1) * (s.length() / x);

            if (end > s.length())
            {
                temp = s.substring(start);
            }
            else if ((i+2) * (s.length() / x) > s.length())
            {
                temp = s.substring(start);
            }
            else
            {
                temp = s.substring(start, end);
            }

            for (int p = 0; p < i * (s.length() / x); p++)
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
