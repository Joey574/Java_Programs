import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GS10_03 {

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    public static void Swap (int [] list, int x, int y)
    {
        int temp = list[x];
        list[x] = list[y];
        list[y] = temp;
    }

    static void selectionSort(int [] in) // O(n^2) The speed in which this runs compared to normal selection sort is the same
    {
        for (int i = 0; i < in.length - 1; i++) {
            int max = 0;
            for (int p = 0; p < in.length - i; p++) {
                if (in[max] < in[p]) {
                    max = p;
                }
            }
            Swap(in, in.length - 1 - i, max);
            System.out.println("Step " + i + ":  " + Arrays.toString(in));
        }
    }

    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);


        System.out.print("Enter number of elements in array: ");
        int temp = r.nextInt();

        int [] t = new int [temp];

        for (int i = 0; i < temp; i++)
        {
            System.out.print("Enter " + (i + 1) + " element of array: ");
            t[i] = r.nextInt();
        }

        System.out.print("Input: " + Arrays.toString(t) + "\n");

        selectionSort(t);

        System.out.print("Output: " + Arrays.toString(t));
    }
}
