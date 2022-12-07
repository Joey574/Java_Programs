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

    static void selectionSort(int [] in)
    {
        int max = 0;
        for (int l = 1; l < in.length; l++) {
            for (int i = 1 + l; i < in.length; i++) {
                if (in[max] < in[i]) {
                    max = i;
                }
            }
            Swap(in, l, max);
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
