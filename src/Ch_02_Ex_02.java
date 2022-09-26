/*
Author: Joey Soroka
Problem: GS02-01
Purpose: Output squares of number from 1 to given #
Pseudocode: Get user input, for ending number, output 1, then using a for loop
            output the rest of the squares using the difference between them
Maintenance Log:
 */

import java.util.Scanner;

public class Ch_02_Ex_02
{
    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);

        System.out.print("Enter ending number: ");

        int x = reader.nextInt();

        reader.close();

        int lastNum = 1;

        System.out.println(1);

        for (int i = 0; i < x - 1; i++)
        {
            int dif = 3 + i + i;

            int out = lastNum + dif;

            System.out.println(out);

            lastNum = out;
        }
    }
}
