import java.util.Scanner;

public class GS04_02
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static String printReverse(String s)
    {
        String out = "";

        for (int i = s.length(); i > 0; i--)
        {
            out += s.charAt(i - 1);
        }

        return out;
    }

    public static void main(String[] args)
    {
        String s;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter string to reverse: ");
        s = reader.nextLine();

        s = printReverse(s);

        System.out.print(s);

    }
}
