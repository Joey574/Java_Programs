import java.util.Scanner;

public class Ch_04_Ex_02
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static String repl(String s, int x)
    {
        String out = "";

        for (int i = 0; i < x; i++)
        {
           out += s;
        }

        return out;
    }

    public static void main(String[] args)
    {
        int x;
        String s;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter text to repeat: ");
        s = reader.nextLine();

        System.out.print("Enter # of times to repeat: ");
        x = reader.nextInt();

        s = repl(s, x);

        System.out.print(s);
    }
}
