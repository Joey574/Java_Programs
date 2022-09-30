import java.util.Scanner;

public class Ch_03_Project_04
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
        double sideA;
        double sideB;
        double sideC;

        double angleAC;
        double angleAB;
        double angleBC;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter side length a: ");
        sideA = reader.nextDouble();

        System.out.print("Enter side length b: ");
        sideB = reader.nextDouble();

        System.out.print("Enter side length c: ");
        sideC = reader.nextDouble();

        angleAC = java.lang.Math.atan(sideA / sideC);

        angleAB = java.lang.Math.atan(sideA / sideB);

        angleBC = java.lang.Math.atan(sideB / sideC);

    }
}
