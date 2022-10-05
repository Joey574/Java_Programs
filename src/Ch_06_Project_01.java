import java.util.Objects;
import java.util.Scanner;

public class Ch_06_Project_01
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
        int words = 0;
        int lines = 0;
        int chars = 0;

        String in = "";

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter paper to count, enter one line at a time and when done enter \"EXIT\": ");

        for (int i = 0; !Objects.equals(in, "EXIT"); i++)
        {
            in = reader.nextLine();
            lines++;

            for (int p = 0; p < in.length(); p++)
            {

                if (in.charAt(p) == ' ')
                {
                    words++;
                }
                else
                {
                    chars++;
                }
            }

            words++;

        }

        //correcting for EXIT
        lines--;
        words--;
        chars -= 4;

        System.out.println("Lines: " + lines);
        System.out.println("Words: " + words);
        System.out.println("Chars: " + chars);




    }
}
