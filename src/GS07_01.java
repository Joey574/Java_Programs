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
        ArrayList<Integer> x = t;

        for (int i = 0; i < x.size(); i++)
        {
            for (int p = 0; p < x.get(i); p++)
            {
                t.add(x.get(i));
                System.out.print(t + "\n");
            }
        }

        return t;
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

        t = scaleByK(t);

        System.out.print(t);

    }
}
