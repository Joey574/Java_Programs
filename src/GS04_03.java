import java.util.Scanner;

public class GS04_03
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static String processName(String s)
    {
        String out = "";
        String first;
        String last;

        boolean done = false;

        for (int i = 0; !done; i++)
        {
            if (s.charAt(i) == ' ')
            {
                first = s.substring(0, i);
                last = s.substring(i + 1);

                out = last + ", " + first;
                done = true;
            }
        }

        return out;
    }

    public static void main(String[] args)
    {
        String s;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter your full name, (first last): ");
        s = reader.nextLine();

        s = processName(s);

        System.out.print(s);
    }
}
