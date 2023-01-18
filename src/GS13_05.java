import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GS13_05
{

/*
Author: Joey Soroka
Problem:
Purpose:
Pseudocode:
Maintenance Log:
 */


    public static List<Integer> alternate(List<Integer> in1, List<Integer> in2) {
        List<Integer> out = new ArrayList<Integer>();
        int i1 = 0;
        int i2 = 0;

        for (int i = 0; i1 < in1.size() && i2 < in2.size(); i++) {
            if ((i % 2) == 0) {
                out.add(in2.get(i2));
                i2++;
            } else {
                out.add(in1.get(i1));
                i1++;
            }
        }

        if (i1 < in1.size()) {
            while (i1 < in1.size()) {
                out.add(in1.get(i1));
                i1++;
            }
        } else if (i2 < in2.size()) {
            while (i2 < in2.size()) {
                out.add(in2.get(i2));
                i2++;
            }
        }

        return out;
    }

    public static void main(String[] args) // Chapter 11 exercise 2
    {
        List<Integer> x = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> y = new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10, 11, 12));

        System.out.println("Input: " + x + "\nInput: " + y);

        List<Integer> temp = alternate(y, x);

        System.out.println("Output: " + temp);
    }
}
