import java.util.Scanner;

public class Ch_03_Ex_08
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
        double a;
        double b;
        double c;
        double x1;
        double x2;
        double root;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter a: ");
        a = reader.nextInt();

        System.out.print("Enter b: ");
        b = reader.nextInt();

        System.out.print("Enter c: ");
        c = reader.nextInt();

        // Solve root first

        root = (b*b) - (4*a*c);
        root = java.lang.Math.sqrt(root);

        // solve x1

        x1 = (-b + root) / (2*a);

        // solve x2

        x2 = (-b - root) / (2*a);

        System.out.println(x1 + "\n" + x2);



    }
}
