public class GS06_05
{
    /*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public interface Shape
    {
        double getVolume();
        double getSurfaceArea();
    }

    public class Cube implements Shape
    {
        private float width;
        private float height;
        private float length;

        public double getSurfaceArea()
        {
            return (double) (2 * (length * width)) + (2 * (height * length)) + (2 * (width * height));
        }

        public double getVolume()
        {
            return (double) (length * width * height);
        }
    }

    public class SquarePyramid implements Shape
    {
        private float base;
        private float slantHeight;
        private float height;

        public double getSurfaceArea()
        {
            return (double) 2 * (base * slantHeight) + (base * base);
        }

        public double getVolume()
        {
            return (double) (1/3) * (base * base) * height;
        }
    }

    public static void main(String[] args)
    {

    }
}
