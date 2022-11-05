import java.util.Scanner;

public class GS06_04
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static class hexagon implements Shape
    {
        private float sideLength;

        public double getArea()
        {
            return ((3 * Math.sqrt(3)) / 2) * (sideLength * sideLength);
        }

        public double getPerimeter()
        {
            return sideLength * 6;
        }

        public void setSideLength(float s)
        {
            sideLength = s;
        }

    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        hexagon h = new hexagon();

        System.out.print("Enter regular Hexagon side length: ");
        h.setSideLength(r.nextFloat());

        System.out.print("\n\nArea: " + h.getArea() + "\nPerimeter: " + h.getPerimeter() + "\n");
    }

    public interface Shape
    {
        public double getArea();
        public double getPerimeter();
    }
}
