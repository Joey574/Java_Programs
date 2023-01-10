import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GS12_03
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */

    static List<Integer> selectionSort (List<Integer> in) { // complexity class would be exponential, it would be faster than normal selection sort but still slower than merge sort

        if (in.size() == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        for (int i = 0; i < in.size() / 2; i++) {
            int smallest = in.get(i);
            int biggest = in.get(i);
            int biggestLoc = i;
            int smallestLoc = i;
            for (int x = i; x < in.size() - i; x++) {
                if (in.get(x) > biggest) {
                    biggest = in.get(x);
                    biggestLoc = x;
                    System.out.println("Biggest: " + biggest + " Loc: " + biggestLoc);
                } if (in.get(x) < smallest) {
                    smallest = in.get(x);
                    smallestLoc = x;
                    System.out.println("Smallest: " + smallest + " Loc: " + smallestLoc);
                }

            }
            in.remove(smallestLoc);
            in.add(i, smallest);
            if (smallestLoc > biggestLoc) {
                in.remove(biggestLoc + 1);
            } else {
                in.remove(biggestLoc);
            }
            in.add(in.size() - i, biggest);

            System.out.println(in);

        }
        return in;
    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        ArrayList<Integer> x = new ArrayList<Integer>(Arrays.asList(-5, 4, 1, -8, 10, 12, 5, 2, 7, 3));

        System.out.println("Input: " + x);

        selectionSort(x);

        System.out.println("Output: " + x);
    }
}
