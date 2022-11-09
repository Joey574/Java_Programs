import java.util.ArrayList;
import java.util.Scanner;

public class GS08_01
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static ArrayList<Integer> removeInRange(ArrayList<Integer> List, int element, int start, int stop)
    {
        int removed = 0;

        for (int i = start; i < stop - removed; i++)
        {
            if (List.get(i) == element)
            {
                List.remove(i);
                removed++;
            }
        }

        return List;
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

        System.out.print("Enter element to remove: ");
        int element = r.nextInt();

        System.out.print("Enter start index: ");
        int sIndex = r.nextInt();

        System.out.print("Enter end index: ");
        int eIndex = r.nextInt();

        System.out.print("Input: " + t + "\n");

        t = removeInRange(t, element, sIndex, eIndex);

        System.out.print("Output: " + t);
    }
}
