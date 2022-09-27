import java.io.StringWriter;
import java.util.Scanner;

public class Ch_02_Project_04
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static final String top = "*   o";
    public static final String upperBody = "*  /|\\";
    public static final String lowerBody = "*  / \\";
    public static final String bottom = "******";



    public static void main(String[] args)
    {

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter how many stairs you want: ");

        int x = reader.nextInt();

        if (x > 1)
        {
            for (int i = 1; i <= x; i++)
            {

                System.out.print(bottom);
                System.out.println(top);
                System.out.print("*");

                for(int q = 0; q < (5*i) + (i - 1); q++)
                {
                    System.out.print(" ");
                }

                System.out.println(upperBody);
                System.out.print("*");

                for(int q = 0; q < (5*i) + (i - 1); q++)
                {
                    System.out.print(" ");
                }

                System.out.println(lowerBody);
                System.out.print("*");

                for(int q = 0; q < (5*i) + (i - 1); q++)
                {
                    System.out.print(" ");
                }

                System.out.println(bottom);
                System.out.print("*");

            }
        }
        else
        {
            System.out.print("Done...");
        }




    }
}
