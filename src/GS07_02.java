import java.util.ArrayList;
import java.util.Scanner;

public class GS07_02
{
/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static ArrayList<Integer> minToFront(ArrayList<Integer> t)
    {
        int temp = 0;

        for (int i = 1; i < t.size(); i++)
        {
            if (t.get(i) < t.get(i - 1))
            {
                temp = i;
            }
        }

        t.add(0, t.get(temp));
        t.remove(temp + 1);

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

        System.out.print("Input: " + t + "\n");

        t = minToFront(t);

        System.out.print("Output: " + t);
    }
}
