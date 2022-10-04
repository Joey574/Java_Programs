import java.util.Scanner;

public class Ch_04_Project_01
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
        int x;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter # to convert to roman numeral: ");
        x = reader.nextInt();

        for (int i = 0; x > 0; i++)
        {
            if (x >= 1000)
            {
                System.out.print("M");
                x -= 1000;
            }
            else if (x >= 900)
            {
                System.out.print("CM");
                x -= 900;
            }
            else if (x >= 500)
            {
                System.out.print("D");
                x -= 500;
            }
            else if (x >= 400)
            {
                System.out.print("CD");
                x -= 400;
            }
            else if (x >= 100)
            {
                System.out.print("C");
                x -= 100;
            }
            else if (x >= 90)
            {
                System.out.print("XC");
                x -= 90;
            }
            else if (x >= 50)
            {
                System.out.print("L");
                x -= 50;
            }
            else if (x >= 40)
            {
                System.out.print("XL");
                x -= 40;
            }
            else if (x >= 10)
            {
                System.out.print("X");
                x -= 10;
            }
            else if (x >= 9)
            {
                System.out.print("IX");
                x -= 9;
            }
            else if (x >= 5)
            {
                System.out.print("V");
                x -= 5;
            }
            else if (x >= 4)
            {
                System.out.print("IV");
                x -= 4;
            }
            else if (x >= 1)
            {
                System.out.print("I");
                x -= 1;
            }
        }
    }
}
