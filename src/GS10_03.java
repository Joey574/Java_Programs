import java.util.ArrayList;
import java.util.Scanner;

public class GS10_03 {

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static ArrayList<Integer> selectionSort(ArrayList<Integer> in)
    {
        Integer longestLoc = null;
        int longest = 0;
        int t;

        for (int i = 0; i < in.size(); i++) {
            if (in.get(i) > longest) {
                longestLoc = i;
                longest = in.get(i);
            }
        }

        if (longestLoc != null) {
            t = longestLoc;
            in.remove(t);
            in.add(longest);
        }
        return in;
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

        t = selectionSort(t);

        System.out.print("Output: " + t);
    }
}
