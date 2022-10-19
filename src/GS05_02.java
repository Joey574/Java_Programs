import java.util.Scanner;

public class GS05_02
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void draw (int progress)
    {
        int p = progress;

        System.out.print("  -----\n");
        System.out.print("  |   |\n");
        System.out.print("  |   ");

        if (progress > 0)
        {
            System.out.print("o\n");
        }
        else
        {
            System.out.print("\n");
        }

    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        String word;

        int progress = 0;

        boolean win = false;
        boolean lose = false;


        System.out.print("Enter word to guess: ");
        word = r.nextLine();

        while(win == false && lose == false)
        {
            draw(progress);
        }

    }
}
