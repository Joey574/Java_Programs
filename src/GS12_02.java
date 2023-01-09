import java.awt.*;
import java.util.Comparator;

public class GS12_02
{


/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */


    public static class compareByDistance implements Comparator <Point>
    {
        public int compare(Point o1, Point o2) {
            return (int) ((Math.sqrt((o1.y * o1.y) + (o1.x * o1.x))) - (Math.sqrt((o2.y * o2.y) + (o2.x * o2.x))));
        }
    }

    public static void main(String[] args)
    {

    }

}
