import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class GS07_01
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static ArrayList<Integer> scaleByK(ArrayList<Integer> t)
    {
        ArrayList<Integer> x = new ArrayList<Integer>();

        for (int i = 0; i < t.size(); i++)
        {
            for (int p = 0; p < t.get(i); p++)
            {
                x.add(t.get(i));
            }
        }

        return x;
    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        ArrayList<Integer> t = new ArrayList<Integer>();

        System.out.print("Enter number of elements in array: ");
        int temp = r.nextInt();

        for (int i = 0; i < temp; i++)
        {
            System.out.print("Enter " + (i + 1) + " element of array: ");
            t.add(r.nextInt());
        }

        System.out.print("Input: " + t + "\n");

        t = scaleByK(t);

        System.out.print("Output: " + t);

    }
}
