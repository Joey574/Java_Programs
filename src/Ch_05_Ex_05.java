public class Ch_05_Ex_05
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void randomLines()
    {
        int x;

        String out;

        for (int i = Math.random() * 5 + 5; i > 0; i--) // random # of strings / lines
        {
            for (int p = Math.random() * 80; p > 0; p--) // random # of chars per string
            {
                x = Math.random() * 26 + 97;

                System.out.print((char)x);

            }
        }
    }

    public static void main(String[] args)
    {
        randomLines();
    }
}
