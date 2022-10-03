import java.util.Scanner;
import java.lang.Math;

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

        double x;

        Scanner reader = new Scanner(System.in);

        System.out.print("Enter side length a: ");
        sideA = reader.nextDouble();

        System.out.print("Enter side length b: ");
        sideB = reader.nextDouble();

        System.out.print("Enter side length c: ");
        sideC = reader.nextDouble();

        if (sideA > 0 && sideB > 0 && sideC > 0)
        {
            if (sideA + sideB > sideC && sideA + sideC > sideB && sideB + sideC > sideA)
            {
                angleAC = Math.acos(((sideC*sideC) + (sideA*sideA) - (sideB*sideB)) / (2*sideA*sideC));

                angleAB = Math.acos(((sideA*sideA) + (sideB*sideB) - (sideC*sideC)) / (2*sideA*sideB));

                angleBC = Math.acos(((sideB*sideB) + (sideC*sideC) - (sideA*sideA)) / (2*sideB*sideC));

                // converting to degrees

                angleAC = Math.toDegrees(angleAC);
                angleAB = Math.toDegrees(angleAB);
                angleBC = Math.toDegrees(angleBC);

                //output

                System.out.println("Angle AC: " + angleAC);
                System.out.println("Angle AB: " + angleAB);
                System.out.println("Angle BC: " + angleBC);
            }
            else
            {
                System.out.println("Not a triangle...");
            }
        }
        else
        {
            System.out.println("Not a triangle...");
        }


    }
}
