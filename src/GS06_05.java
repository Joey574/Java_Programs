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

        public double getSurfaceArea() { return (double) (2 * (length * width)) + (2 * (height * length)) + (2 * (width * height)); }

        public double getVolume()
        {
            return (double) (length * width * height);
        }
    }

    public class RectangularPrism extends Cube
    {

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

    public class Cylinder implements Shape
    {
        private float height;
        private float radius;

        public double getSurfaceArea() {
            return (double) (2 * Math.PI * radius * height) + 2 * Math.PI * (radius * radius);
        }

        public double getVolume() {
            return (double) Math.PI * (radius * radius) * height;
        }
    }

    public class Sphere implements Shape
    {
        private float radius;

        public double getSurfaceArea() {
            return (double) 4 * Math.PI * (radius * radius);
        }

        public double getVolume() {
            return (double) (4/3) * Math.PI * (radius * radius * radius);
        }
    }


    public static void main(String[] args)
    {

    }
}
