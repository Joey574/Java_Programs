import java.util.Scanner;

public class GS04_01
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static String padString(String s, int x)
    {
        String out = s;

        if(out.length() < x)
        {
            for (int i = out.length(); i < x; i++)
            {
                out += " ";
            }
        }

        return out;
    }

    public static void main(String[] args)
    {
        String in;
        int x;

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter string to pad: ");
        in = sc.nextLine();

        System.out.print("Enter length to pad too: ");
        x = sc.nextInt();

        in = padString(in, x);

        System.out.print(in);

    }
}
