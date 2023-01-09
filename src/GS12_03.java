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

    static List<Integer> selectionSort (List<Integer> in) {
        int biggestLoc;
        int smallestLoc;
        int smallest;
        int biggest;

        for (int i = 0; i < (in.size() + 1) / 2; i++) {
            for (int x = i; x <in.size() - i; x++) {

            }
        }

    }


    public static void main(String[] args)
    {
        Scanner r = new Scanner(System.in);

        ArrayList<Integer> x = new ArrayList<Integer>();
        x.add(1);


        System.out.println("Input: " + x);

        x = (ArrayList<Integer>) selectionSort(x);

        System.out.println("Output: " + x);
    }
}
